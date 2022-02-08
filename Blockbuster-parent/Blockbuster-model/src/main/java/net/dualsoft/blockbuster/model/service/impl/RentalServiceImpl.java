/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import net.dualsoft.blockbuster.db.jooq.Tables;
import static net.dualsoft.blockbuster.db.jooq.Tables.CUSTOMER;
import static net.dualsoft.blockbuster.db.jooq.Tables.FILM;
import static net.dualsoft.blockbuster.db.jooq.Tables.INVENTORY;
import static net.dualsoft.blockbuster.db.jooq.Tables.RENTAL;
import static net.dualsoft.blockbuster.db.jooq.Tables.PAYMENT;
import static net.dualsoft.blockbuster.db.jooq.Tables.RENTAL_REQUEST;
import static net.dualsoft.blockbuster.db.jooq.Tables.USER_NOTIFICATION;
import static net.dualsoft.blockbuster.db.jooq.Tables.STORE;
import static net.dualsoft.blockbuster.db.jooq.Tables.ADDRESS;
import net.dualsoft.blockbuster.db.jooq.enums.RequestStatus;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Film;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Inventory;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Rental;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.RentalRequest;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.UserNotification;
import net.dualsoft.blockbuster.model.DTO.CustomerMinimal;
import net.dualsoft.blockbuster.model.DTO.RentalForList;
import net.dualsoft.blockbuster.model.DTO.RequestDetails;
import net.dualsoft.blockbuster.model.DTO.RequestDetailsLess;
import net.dualsoft.blockbuster.model.helper.RabbitUtil;
import net.dualsoft.blockbuster.model.helper.pojos.InventoryCount;
import net.dualsoft.blockbuster.model.helper.pojos.RentalRequestModel;
import net.dualsoft.blockbuster.model.helper.pojos.Response;
import net.dualsoft.blockbuster.model.service.RentalService;
import org.jooq.DSLContext;
import static org.jooq.impl.DSL.count;
import static org.jooq.impl.DSL.sum;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author teodora
 */
@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DSLContext context;

    @Override
    public Response<List<RentalForList>> getRentalsForCustomer(int customerId) {
        List<Customer> customers = context.selectFrom(CUSTOMER).where(CUSTOMER.CUSTOMER_ID.eq(customerId)).fetchInto(Customer.class);
        if (customers.isEmpty()) {
            return new Response(null, "Customer not found", 404);
        }
        List<RentalForList> rentals = context
                .select(RENTAL.RENTAL_ID, RENTAL.RENTAL_DATE, RENTAL.CUSTOMER_ID,
                        FILM.FILM_ID, FILM.TITLE, FILM.POSTER_URL, RENTAL.RETURN_DATE,
                        ADDRESS.ADDRESS_)
                .from(RENTAL)
                .join(INVENTORY)
                .on(INVENTORY.INVENTORY_ID.eq(RENTAL.INVENTORY_ID))
                .join(FILM)
                .on(FILM.FILM_ID.eq(INVENTORY.FILM_ID.cast(Integer.class)))
                .join(STORE)
                .on(STORE.STORE_ID.cast(Short.class).eq(INVENTORY.STORE_ID))
                .join(ADDRESS)
                .on(ADDRESS.ADDRESS_ID.cast(Short.class).eq(STORE.ADDRESS_ID))
                .where(RENTAL.CUSTOMER_ID.cast(Integer.class).eq(customerId))
                .orderBy(RENTAL.RENTAL_DATE.desc())
                .fetchInto(RentalForList.class);
        return new Response<List<RentalForList>>(rentals, "Ok", 200);
    }

    @Override
    public Response addRental(int customerId, int filmId, int storeId) {
        List<Customer> customers = context.selectFrom(CUSTOMER).where(CUSTOMER.CUSTOMER_ID.eq(customerId)).fetchInto(Customer.class);
        if (customers.isEmpty()) {
            return new Response(null, "Customer not found", 404);
        }
        Customer customer = customers.get(0);
        List<Film> films = context.selectFrom(FILM).where(FILM.FILM_ID.eq(filmId)).fetchInto(Film.class);
        if (films.isEmpty()) {
            return new Response(null, "Film not found", 404);
        }
        Film film = films.get(0);
        float amount = film.getRentalDuration().floatValue() * film.getRentalRate().floatValue();
        float funds = customer.getBalance().floatValue();
        if (amount > funds) {
            return new Response(null, "Insufficient funds", 406);
        }
        List<Integer> inventoryIds = context
                .select(INVENTORY.INVENTORY_ID)
                .from(INVENTORY)
                .where(INVENTORY.FILM_ID.cast(Integer.class).eq(filmId))
                .and(INVENTORY.STORE_ID.cast(Integer.class).eq(storeId))
                .exceptAll(context
                        .select(INVENTORY.INVENTORY_ID)
                        .from(INVENTORY)
                        .join(RENTAL)
                        .on(INVENTORY.INVENTORY_ID.eq(RENTAL.INVENTORY_ID))
                        .where(INVENTORY.FILM_ID.cast(Integer.class).eq(filmId))
                        .and(RENTAL.RETURN_DATE.isNull())
                        .and(INVENTORY.STORE_ID.cast(Integer.class).eq(storeId))
                )
                .fetchInto(Integer.class);
        if (inventoryIds.isEmpty()) {
            return new Response(null, "No films left", 404);
        }
        Integer inventoryId = inventoryIds.get(0);

        Rental rental = context
                .insertInto(RENTAL, RENTAL.INVENTORY_ID, RENTAL.CUSTOMER_ID, RENTAL.RENTAL_DATE, RENTAL.STAFF_ID)
                .values(inventoryId, customer.getCustomerId().shortValue(), Timestamp.from(Instant.now()), (short) 1)
                .returning()
                .fetchOne()
                .into(Rental.class);

        if (rental == null) {
            return new Response(null, "Error renting film", 500);
        }

        int res = context
                .insertInto(PAYMENT, PAYMENT.CUSTOMER_ID, PAYMENT.RENTAL_ID, PAYMENT.AMOUNT, PAYMENT.PAYMENT_DATE, PAYMENT.TYPE)
                .values(customer.getCustomerId().shortValue(), rental.getRentalId(), new BigDecimal(amount), Timestamp.from(Instant.now()), "out")
                .execute();

        if (res < 1) {
            return new Response(null, "Error renting film", 500);
        }

        res = context
                .update(CUSTOMER)
                .set(CUSTOMER.BALANCE, new BigDecimal(funds - amount))
                .where(CUSTOMER.CUSTOMER_ID.eq(customerId))
                .execute();

        if (res < 1) {
            return new Response(null, "Error renting film", 500);
        }

        return new Response(null, "You rented the film successfully", 200);
    }

    @Override
    public Response requestRental(RentalRequestModel request) {
        Customer customer = context
                .selectFrom(CUSTOMER)
                .where(CUSTOMER.CUSTOMER_ID.eq(request.getCustomerId()))
                .fetchOneInto(Customer.class);
        if (customer == null) {
            return new Response(null, "Customer not found", 404);
        }
        Film film = context
                .selectFrom(FILM)
                .where(FILM.FILM_ID.eq(request.getFilmId()))
                .fetchOneInto(Film.class);
        if (film == null) {
            return new Response(null, "Film not found", 404);
        }
        if (customer.getBalance().floatValue() < film.getRentalRate().floatValue() * film.getRentalDuration().floatValue()) {
            return new Response(null, "Insufficient funds", 400);
        }
        Float lockedMoney = context
                .select(sum(FILM.RENTAL_DURATION.multiply(FILM.RENTAL_RATE)))
                .from(RENTAL_REQUEST)
                .join(FILM).on(RENTAL_REQUEST.FILM_ID.eq(FILM.FILM_ID))
                .where(RENTAL_REQUEST.CUSTOMER_ID.eq(request.getCustomerId()))
                .and(RENTAL_REQUEST.STATUS.eq(RequestStatus.pending))
                .groupBy(RENTAL_REQUEST.CUSTOMER_ID)
                .fetchOneInto(Float.class);
        if (lockedMoney != null && customer.getBalance().floatValue() - lockedMoney < film.getRentalRate().floatValue() * film.getRentalDuration().floatValue()) {
            return new Response(null, "Locked funds, try canceling pending requests", 406);
        }
        RentalRequest renReq = context
                .insertInto(RENTAL_REQUEST, RENTAL_REQUEST.FILM_ID, RENTAL_REQUEST.STORE_ID, RENTAL_REQUEST.CUSTOMER_ID)
                .values(request.getFilmId(), request.getStoreId(), request.getCustomerId())
                .returning()
                .fetchOne()
                .into(RentalRequest.class);
        if (renReq == null) {
            return new Response(null, "Rental request failed", 500);
        }

        RequestDetails reqDetails = context
                .select(RENTAL_REQUEST.RENTAL_REQUEST_ID,
                        RENTAL_REQUEST.FILM_ID, FILM.TITLE,
                        RENTAL_REQUEST.CUSTOMER_ID, CUSTOMER.USERNAME,
                        RENTAL_REQUEST.STATUS,
                        RENTAL_REQUEST.CREATE_DATE, count().as("inventoryCount"))
                .from(RENTAL_REQUEST)
                .join(FILM).on(RENTAL_REQUEST.FILM_ID.eq(FILM.FILM_ID))
                .join(CUSTOMER).on(RENTAL_REQUEST.CUSTOMER_ID.eq(CUSTOMER.CUSTOMER_ID))
                .join(INVENTORY).on(FILM.FILM_ID.eq(INVENTORY.FILM_ID.cast(Integer.class)))
                .where(RENTAL_REQUEST.RENTAL_REQUEST_ID.eq(renReq.getRentalRequestId()))
                .and(INVENTORY.STORE_ID.cast(Integer.class).eq(request.getStoreId()))
                .groupBy(RENTAL_REQUEST.RENTAL_REQUEST_ID,
                        RENTAL_REQUEST.FILM_ID, FILM.TITLE,
                        RENTAL_REQUEST.CUSTOMER_ID, CUSTOMER.USERNAME,
                        RENTAL_REQUEST.STATUS,
                        RENTAL_REQUEST.CREATE_DATE)
                .orderBy(RENTAL_REQUEST.RENTAL_REQUEST_ID)
                .fetchOne()
                .into(RequestDetails.class);

        Integer rentedCount = context
                .select(count())
                .from(INVENTORY)
                .join(RENTAL).on(INVENTORY.INVENTORY_ID.cast(Integer.class).eq(RENTAL.INVENTORY_ID))
                .where(RENTAL.RETURN_DATE.isNull())
                .and(INVENTORY.FILM_ID.cast(Integer.class).eq(reqDetails.getFilmId()))
                .and(INVENTORY.STORE_ID.cast(Integer.class).eq(request.getStoreId()))
                .fetchOneInto(Integer.class);

        reqDetails.setInventoryCount(reqDetails.getInventoryCount() - rentedCount);

        rabbitTemplate.convertAndSend(RabbitUtil.rentalRequestTopicName, "auth.rr.request.#", reqDetails);
        return new Response(renReq, "Rental request sent successfully", 200);
    }

    @Override
    public Response<List<RequestDetails>> getRequests(Integer storeId, String username, String status, int page, int pageCount) {

        List<Integer> totalInventory = context
                .select(INVENTORY.FILM_ID)
                .from(INVENTORY)
                .where(INVENTORY.FILM_ID.cast(Integer.class).in(context.selectDistinct(RENTAL_REQUEST.FILM_ID).from(RENTAL_REQUEST).where(RENTAL_REQUEST.STORE_ID.eq(storeId))))
                .and(INVENTORY.STORE_ID.eq(storeId.shortValue()))
                .exceptAll(context
                        .select(INVENTORY.FILM_ID)
                        .from(INVENTORY)
                        .join(RENTAL).on(RENTAL.INVENTORY_ID.eq(INVENTORY.INVENTORY_ID))
                        .where(INVENTORY.STORE_ID.eq(storeId.shortValue()))
                        .and(RENTAL.RETURN_DATE.isNull()))
                .orderBy(INVENTORY.FILM_ID)
                .fetchInto(Integer.class);

        HashMap<Integer, Integer> inventoryCountHash = new HashMap<>();

        totalInventory.forEach((id) -> {

            Integer pom = inventoryCountHash.get(id);

            if (pom != null) {
                inventoryCountHash.put(id, ++pom);
            } else {
                inventoryCountHash.put(id, 1);
            }
        });

        List<RequestDetails> req;

        if (username.equals("all") && status.equals("all")) {
            req = context
                    .select(RENTAL_REQUEST.RENTAL_REQUEST_ID,
                            RENTAL_REQUEST.FILM_ID, FILM.TITLE,
                            RENTAL_REQUEST.CUSTOMER_ID, CUSTOMER.USERNAME,
                            RENTAL_REQUEST.STATUS,
                            RENTAL_REQUEST.CREATE_DATE, FILM.POSTER_URL,
                            FILM.RENTAL_DURATION, FILM.RENTAL_RATE)
                    .from(RENTAL_REQUEST)
                    .join(FILM).on(RENTAL_REQUEST.FILM_ID.eq(FILM.FILM_ID))
                    .join(CUSTOMER).on(RENTAL_REQUEST.CUSTOMER_ID.eq(CUSTOMER.CUSTOMER_ID))
                    .where(RENTAL_REQUEST.STORE_ID.eq(storeId))
                    .orderBy(RENTAL_REQUEST.RENTAL_REQUEST_ID.desc())
                    .offset((page - 1) * pageCount)
                    .limit(pageCount)
                    .fetchInto(RequestDetails.class);
        } else if (!username.equals("all") && status.equals("all")) {
            req = context
                    .select(RENTAL_REQUEST.RENTAL_REQUEST_ID,
                            RENTAL_REQUEST.FILM_ID, FILM.TITLE,
                            RENTAL_REQUEST.CUSTOMER_ID, CUSTOMER.USERNAME,
                            RENTAL_REQUEST.STATUS,
                            RENTAL_REQUEST.CREATE_DATE, FILM.POSTER_URL,
                            FILM.RENTAL_DURATION, FILM.RENTAL_RATE)
                    .from(RENTAL_REQUEST)
                    .join(FILM).on(RENTAL_REQUEST.FILM_ID.eq(FILM.FILM_ID))
                    .join(CUSTOMER).on(RENTAL_REQUEST.CUSTOMER_ID.eq(CUSTOMER.CUSTOMER_ID))
                    .where(RENTAL_REQUEST.STORE_ID.eq(storeId))
                    .and(CUSTOMER.USERNAME.eq(username))
                    .orderBy(RENTAL_REQUEST.RENTAL_REQUEST_ID.desc())
                    .offset((page - 1) * pageCount)
                    .limit(pageCount)
                    .fetchInto(RequestDetails.class);
        } else if (username.equals("all") && !status.equals("all")) {
            req = context
                    .select(RENTAL_REQUEST.RENTAL_REQUEST_ID,
                            RENTAL_REQUEST.FILM_ID, FILM.TITLE,
                            RENTAL_REQUEST.CUSTOMER_ID, CUSTOMER.USERNAME,
                            RENTAL_REQUEST.STATUS,
                            RENTAL_REQUEST.CREATE_DATE, FILM.POSTER_URL,
                            FILM.RENTAL_DURATION, FILM.RENTAL_RATE)
                    .from(RENTAL_REQUEST)
                    .join(FILM).on(RENTAL_REQUEST.FILM_ID.eq(FILM.FILM_ID))
                    .join(CUSTOMER).on(RENTAL_REQUEST.CUSTOMER_ID.eq(CUSTOMER.CUSTOMER_ID))
                    .where(RENTAL_REQUEST.STORE_ID.eq(storeId))
                    .and(RENTAL_REQUEST.STATUS.eq(RequestStatus.valueOf(status)))
                    .orderBy(RENTAL_REQUEST.RENTAL_REQUEST_ID.desc())
                    .offset((page - 1) * pageCount)
                    .limit(pageCount)
                    .fetchInto(RequestDetails.class);
        } else {
            req = context
                    .select(RENTAL_REQUEST.RENTAL_REQUEST_ID,
                            RENTAL_REQUEST.FILM_ID, FILM.TITLE,
                            RENTAL_REQUEST.CUSTOMER_ID, CUSTOMER.USERNAME,
                            RENTAL_REQUEST.STATUS,
                            RENTAL_REQUEST.CREATE_DATE, FILM.POSTER_URL,
                            FILM.RENTAL_DURATION, FILM.RENTAL_RATE)
                    .from(RENTAL_REQUEST)
                    .join(FILM).on(RENTAL_REQUEST.FILM_ID.eq(FILM.FILM_ID))
                    .join(CUSTOMER).on(RENTAL_REQUEST.CUSTOMER_ID.eq(CUSTOMER.CUSTOMER_ID))
                    .where(RENTAL_REQUEST.STORE_ID.eq(storeId))
                    .and(CUSTOMER.USERNAME.eq(username))
                    .and(RENTAL_REQUEST.STATUS.eq(RequestStatus.valueOf(status)))
                    .orderBy(RENTAL_REQUEST.RENTAL_REQUEST_ID.desc())
                    .offset((page - 1) * pageCount)
                    .limit(pageCount)
                    .fetchInto(RequestDetails.class);
        }

        for (RequestDetails request : req) {
            Integer count = inventoryCountHash.get(request.getFilmId());
            if (count == null) {
                count = new Integer(0);
            }
            request.setInventoryCount(count);
        }

        return new Response(req, "Request get successfully", 200);
    }

    @Override
    public Response<List<CustomerMinimal>> getCustomersWithRequests(Integer storeId) {

        List<CustomerMinimal> cust = context
                .selectDistinct(RENTAL_REQUEST.CUSTOMER_ID, CUSTOMER.USERNAME)
                .from(RENTAL_REQUEST)
                .join(CUSTOMER).on(RENTAL_REQUEST.CUSTOMER_ID.eq(CUSTOMER.CUSTOMER_ID))
                .where(RENTAL_REQUEST.STORE_ID.eq(storeId))
                .fetchInto(CustomerMinimal.class);

        return new Response(cust, "Request get successfully", 200);
    }

    @Override
    public Response<RentalRequest> setStatus(Integer rentalRequestId, RequestStatus status) {
        RentalRequest rentalRequest = context
                .update(RENTAL_REQUEST)
                .set(RENTAL_REQUEST.STATUS, status)
                .where(RENTAL_REQUEST.RENTAL_REQUEST_ID.eq(rentalRequestId))
                .returning()
                .fetchOne()
                .into(RentalRequest.class);

        if (rentalRequest == null) {
            return new Response(null, "Customer not found", 404);
        }

        if (status == RequestStatus.accepted) {
            Response<Rental> res = addRental(rentalRequest.getCustomerId(), rentalRequest.getFilmId(), rentalRequest.getStoreId());

            if (res.getStatus() != 200) {
                return new Response(null, res.getMessage(), res.getStatus());
            }
        }

        String filmTitle = context
                .select(FILM.TITLE)
                .from(FILM)
                .where(FILM.FILM_ID.eq(rentalRequest.getFilmId()))
                .fetchOneInto(String.class);

        UserNotification notif = context
                .insertInto(USER_NOTIFICATION, USER_NOTIFICATION.CUSTOMER_ID, USER_NOTIFICATION.NOTIFICATION_TEXT,
                        USER_NOTIFICATION.READ, USER_NOTIFICATION.TITLE)
                .values(rentalRequest.getCustomerId(),
                        "Rent for film " + filmTitle + " " + rentalRequest.getStatus(),
                        false, "Request " + status)
                .returning()
                .fetchOne()
                .into(UserNotification.class);

        rabbitTemplate.convertAndSend(RabbitUtil.rentalRequestResponseTopicName, "core.rr.response.#", notif);

        RequestDetails reqDetails = context
                .select(RENTAL_REQUEST.RENTAL_REQUEST_ID,
                        RENTAL_REQUEST.FILM_ID, FILM.TITLE,
                        RENTAL_REQUEST.CUSTOMER_ID, CUSTOMER.USERNAME,
                        RENTAL_REQUEST.STATUS,
                        RENTAL_REQUEST.CREATE_DATE, count().as("inventoryCount"))
                .from(RENTAL_REQUEST)
                .join(FILM).on(RENTAL_REQUEST.FILM_ID.eq(FILM.FILM_ID))
                .join(CUSTOMER).on(RENTAL_REQUEST.CUSTOMER_ID.eq(CUSTOMER.CUSTOMER_ID))
                .join(INVENTORY).on(FILM.FILM_ID.eq(INVENTORY.FILM_ID.cast(Integer.class)))
                .where(RENTAL_REQUEST.RENTAL_REQUEST_ID.eq(rentalRequest.getRentalRequestId()))
                .and(INVENTORY.STORE_ID.cast(Integer.class).eq(rentalRequest.getStoreId()))
                .groupBy(RENTAL_REQUEST.RENTAL_REQUEST_ID,
                        RENTAL_REQUEST.FILM_ID, FILM.TITLE,
                        RENTAL_REQUEST.CUSTOMER_ID, CUSTOMER.USERNAME,
                        RENTAL_REQUEST.STATUS,
                        RENTAL_REQUEST.CREATE_DATE)
                .orderBy(RENTAL_REQUEST.RENTAL_REQUEST_ID)
                .fetchOne()
                .into(RequestDetails.class);
        rabbitTemplate.convertAndSend(RabbitUtil.rentalRequestTopicName, "auth.rr.#", reqDetails);

        return new Response(rentalRequest, "Request status changed successfully", 200);
    }

    @Override
    public Response<Boolean> hasUserRequested(int customerId, int filmId) {
        List<RentalRequest> request = context
                .selectFrom(RENTAL_REQUEST)
                .where(RENTAL_REQUEST.CUSTOMER_ID.eq(customerId))
                .and(RENTAL_REQUEST.FILM_ID.eq(filmId))
                .and(RENTAL_REQUEST.STATUS.eq(RequestStatus.pending))
                .fetchInto(RentalRequest.class);

        if (request.isEmpty()) {
            return new Response(false, "Ok", 200);
        }

        return new Response(true, "Ok", 200);
    }

    @Override
    public Response<List<RentalForList>> getRentalsForCustomer(Integer storeId, String username) {
        List<Customer> customers = context.selectFrom(CUSTOMER).where(CUSTOMER.USERNAME.eq(username)).fetchInto(Customer.class);
        if (customers.isEmpty()) {
            return new Response(null, "Customer not found", 404);
        }
        List<RentalForList> rentals = context
                .select(RENTAL.RENTAL_ID, RENTAL.RENTAL_DATE, RENTAL.CUSTOMER_ID, FILM.FILM_ID, FILM.TITLE, FILM.POSTER_URL, RENTAL.RETURN_DATE)
                .from(RENTAL)
                .join(INVENTORY)
                .on(INVENTORY.INVENTORY_ID.eq(RENTAL.INVENTORY_ID))
                .join(FILM)
                .on(FILM.FILM_ID.eq(INVENTORY.FILM_ID.cast(Integer.class)))
                .join(CUSTOMER)
                .on(CUSTOMER.CUSTOMER_ID.cast(Short.class).eq(RENTAL.CUSTOMER_ID))
                .where(CUSTOMER.USERNAME.eq(username))
                .and(INVENTORY.STORE_ID.eq(storeId.shortValue()))
                .orderBy(RENTAL.RENTAL_DATE.desc())
                .fetchInto(RentalForList.class);
        return new Response<List<RentalForList>>(rentals, "Ok", 200);
    }

    @Override
    public Response returnRental(Integer rentalId) {
        int res = context
                .update(RENTAL)
                .set(RENTAL.RETURN_DATE, Timestamp.from(Instant.now()))
                .where(RENTAL.RENTAL_ID.eq(rentalId))
                .execute();

        if (res < 1) {
            return new Response(null, "Error returning rental", 500);
        }

        return new Response(null, "Ok", 200);
    }

    @Override
    public Response getRentalRequests(Integer customerId, String status) {
        List<RequestDetailsLess> requests;
        if (status.equals("all")) {
            requests = context
                    .select(RENTAL_REQUEST.RENTAL_REQUEST_ID, RENTAL_REQUEST.CREATE_DATE,
                            RENTAL_REQUEST.FILM_ID, RENTAL_REQUEST.STATUS,
                            RENTAL_REQUEST.CUSTOMER_ID, FILM.TITLE,
                            FILM.RENTAL_DURATION, FILM.RENTAL_RATE,
                            FILM.POSTER_URL)
                    .from(RENTAL_REQUEST)
                    .join(FILM).on(RENTAL_REQUEST.FILM_ID.eq(FILM.FILM_ID))
                    .where(RENTAL_REQUEST.CUSTOMER_ID.eq(customerId))
                    .fetchInto(RequestDetailsLess.class);
        } else {
            requests = context
                    .select(RENTAL_REQUEST.RENTAL_REQUEST_ID, RENTAL_REQUEST.CREATE_DATE,
                            RENTAL_REQUEST.FILM_ID, RENTAL_REQUEST.STATUS,
                            RENTAL_REQUEST.CUSTOMER_ID, FILM.TITLE,
                            FILM.RENTAL_DURATION, FILM.RENTAL_RATE,
                            FILM.POSTER_URL)
                    .from(RENTAL_REQUEST)
                    .join(FILM).on(RENTAL_REQUEST.FILM_ID.eq(FILM.FILM_ID))
                    .where(RENTAL_REQUEST.CUSTOMER_ID.eq(customerId))
                    .and(RENTAL_REQUEST.STATUS.eq(RequestStatus.valueOf(status)))
                    .fetchInto(RequestDetailsLess.class);
        }
        return new Response(requests, "Ok", 200);
    }

}
