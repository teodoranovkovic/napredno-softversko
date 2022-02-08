/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import net.dualsoft.blockbuster.db.jooq.Tables;
import static net.dualsoft.blockbuster.db.jooq.Tables.ADDRESS;
import static net.dualsoft.blockbuster.db.jooq.Tables.CITY;
import static net.dualsoft.blockbuster.db.jooq.Tables.COUNTRY;
import static net.dualsoft.blockbuster.db.jooq.Tables.CUSTOMER;
import static net.dualsoft.blockbuster.db.jooq.Tables.PAYMENT;
import static net.dualsoft.blockbuster.db.jooq.Tables.STORE;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Store;
import net.dualsoft.blockbuster.model.DTO.CustomerEditProfile;
import net.dualsoft.blockbuster.model.helper.pojos.Response;
import net.dualsoft.blockbuster.model.service.CustomerService;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author teodora
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private DSLContext context;

    @Override
    public Response<Customer> getCustomer(int id) {
        List<Customer> customer = context.selectFrom(CUSTOMER).where(CUSTOMER.CUSTOMER_ID.eq(id)).fetchInto(Customer.class);
        if (customer.isEmpty()) {
            return new Response(null, "Customer not found", 404);
        }
        return new Response(customer.get(0), "Ok", 200);
    }

    @Override
    public Response addMoney(int id, double amount) {
        List<Customer> customers = context.selectFrom(CUSTOMER).where(CUSTOMER.CUSTOMER_ID.eq(id)).fetchInto(Customer.class);
        if (customers.isEmpty()) {
            return new Response(null, "Customer not found", 404);
        }
        Customer customer = customers.get(0);
        int num = context.update(CUSTOMER)
                .set(CUSTOMER.BALANCE, customer.getBalance().add(new BigDecimal(amount)))
                .where(CUSTOMER.CUSTOMER_ID.eq(customer.getCustomerId())).execute();
        if (num <= 0) {
            return new Response(null, "Error updating funds", 500);
        }

        int execute = context.insertInto(PAYMENT, PAYMENT.AMOUNT, PAYMENT.TYPE, PAYMENT.CUSTOMER_ID, PAYMENT.PAYMENT_DATE)
                .values(new BigDecimal(amount), "in", (short) id, Timestamp.valueOf(LocalDateTime.now()))
                .execute();

        return new Response(null, "Ok", 200);
    }

    @Override
    public Response updateCustomer(CustomerEditProfile customer) {
        List<CustomerEditProfile> customers = context.selectFrom(CUSTOMER).where(CUSTOMER.CUSTOMER_ID.eq(customer.getCustomerId()))
.fetchInto(CustomerEditProfile.class);
        String bla = customer.getAvatarUrl();
        if (customers.isEmpty()) {
            return new Response(null, "Customer not found", 404);
        }

        BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();

        if (pwEncoder.matches(customer.getPassword(), customers.get(0).getPassword())) {

            int num = context.update(CUSTOMER)
                    .set(Tables.CUSTOMER.FIRST_NAME, customer.getFirstName())
                    .set(Tables.CUSTOMER.LAST_NAME, customer.getLastName())
                    .set(Tables.CUSTOMER.USERNAME, customer.getUsername())
                    .set(Tables.CUSTOMER.EMAIL, customer.getEmail())
                    .set(Tables.CUSTOMER.AVATAR_URL, customer.getAvatarUrl())
                    .where(CUSTOMER.CUSTOMER_ID.eq(customer.getCustomerId())).execute();
            if (num <= 0) {
                return new Response(null, "Error updating funds", 500);
            }

            return new Response(null, "Ok", 200);
        }

        return new Response(null, "Invalid pass", 403);
    }

    @Override
    public Response<Customer> getCustomers() {
        List<Customer> customer = context.selectFrom(CUSTOMER).fetchInto(Customer.class);
        if (customer.isEmpty()) {
            return new Response(null, "Customer not found", 404);
        }
        return new Response(customer, "Ok", 200);
    }

    @Override
    public Response updateStore(int customerId, int storeId) {
        List<Customer> customers = context
                .selectFrom(CUSTOMER)
                .where(CUSTOMER.CUSTOMER_ID.eq(customerId))
                .fetchInto(Customer.class);
        if (customers.isEmpty()) {
            return new Response(null, "Customer not found", 404);
        }
        List<Store> stores = context
                .selectFrom(STORE)
                .where(STORE.STORE_ID.eq(storeId))
                .fetchInto(Store.class);
        if (stores.isEmpty()) {
            return new Response(null, "Store not found", 404);
        }
        int res = context.update(CUSTOMER)
                .set(CUSTOMER.STORE_ID, (short) storeId)
                .where(CUSTOMER.CUSTOMER_ID.eq(customerId))
                .execute();
        if (res > 0) {
            return new Response(null, "Ok", 200);
        }
        return new Response(null, "Error updating store", 500);
    }

    @Override
    public Response<List<CustomerEditProfile>> getCustomeForPage(int numPage, int numOfCustomersForPage) {
        List<CustomerEditProfile> resultCust = new ArrayList<CustomerEditProfile>();
        List<CustomerEditProfile> customers
                = context.select(CUSTOMER.CUSTOMER_ID, CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME, CUSTOMER.BALANCE, CUSTOMER.USERNAME, CUSTOMER.EMAIL,
                        CUSTOMER.PASSWORD, CUSTOMER.AVATAR_URL)
                        .select(ADDRESS.ADDRESS_.as("address"))
                        .select(COUNTRY.COUNTRY_.as("country"))
                        .select(CITY.CITY_.as("city"))
                        .select(ADDRESS.DISTRICT.as("district"))
                        .select(ADDRESS.POSTAL_CODE.as("postalCode"))
                        .from(CUSTOMER)
                        .leftJoin(ADDRESS)
                        .on(ADDRESS.ADDRESS_ID.eq(CUSTOMER.ADDRESS_ID.cast(Integer.class)))
                        .leftJoin(CITY)
                        .on(CITY.CITY_ID.eq(ADDRESS.CITY_ID.cast(Integer.class)))
                        .leftJoin(COUNTRY)
                        .on(COUNTRY.COUNTRY_ID.eq(CITY.COUNTRY_ID.cast(Integer.class)))
                        .orderBy(CUSTOMER.CUSTOMER_ID)
                        .fetchInto(CustomerEditProfile.class);

        int totalNumOfCustomers = customers.size();
        int totalNumOfPages;

        System.out.println(numPage);
        if (totalNumOfCustomers % numOfCustomersForPage == 0) {
            totalNumOfPages = totalNumOfCustomers / numOfCustomersForPage;
            System.out.println("totalNumOfPages" + totalNumOfPages);

        } else {
            totalNumOfPages = totalNumOfCustomers / numOfCustomersForPage + 1;
            System.out.println("totalNumOfPages" + totalNumOfPages);
        }

        System.out.println("totalNumOfPages" + totalNumOfPages);

        if (customers.isEmpty()) {
            return new Response(null, "Customer not found", 404);
        } else {

            if (numPage < totalNumOfPages) {
                for (int i = (numPage - 1) * numOfCustomersForPage; i < (numOfCustomersForPage * (numPage)); i++) {
                    resultCust.add(customers.get(i));
                }
            } else {

                System.out.println("customers");

                for (int i = (numPage - 1) * numOfCustomersForPage; i < totalNumOfCustomers; i++) {
                    System.out.println("customers" + customers.get(i));
                    resultCust.add(customers.get(i));
                }
            }
        }

        return new Response(resultCust, "Ok", 200);
    }

    @Override
    public Response<List<CustomerEditProfile>> getCustomersBySearchTextForPage(int numPage, int numOfCustomersForPage, String searchText) {
        searchText = "%" + searchText + "%";
        List<CustomerEditProfile> resultCust = new ArrayList<CustomerEditProfile>();
        List<CustomerEditProfile> customers
                = context.select(CUSTOMER.CUSTOMER_ID, CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME, CUSTOMER.BALANCE, CUSTOMER.USERNAME, CUSTOMER.EMAIL,
                        CUSTOMER.PASSWORD, CUSTOMER.AVATAR_URL)
                        .select(ADDRESS.ADDRESS_.as("address"))
                        .select(COUNTRY.COUNTRY_.as("country"))
                        .select(CITY.CITY_.as("city"))
                        .select(ADDRESS.DISTRICT.as("district"))
                        .select(ADDRESS.POSTAL_CODE.as("postalCode"))
                        .from(CUSTOMER)
                        .leftJoin(ADDRESS)
                        .on(ADDRESS.ADDRESS_ID.eq(CUSTOMER.ADDRESS_ID.cast(Integer.class)))
                        .leftJoin(CITY)
                        .on(CITY.CITY_ID.eq(ADDRESS.CITY_ID.cast(Integer.class)))
                        .leftJoin(COUNTRY)
                        .on(COUNTRY.COUNTRY_ID.eq(CITY.COUNTRY_ID.cast(Integer.class)))
                        .where(CUSTOMER.FIRST_NAME.likeIgnoreCase(searchText).or(CUSTOMER.LAST_NAME.likeIgnoreCase(searchText)).or(CUSTOMER.USERNAME.likeIgnoreCase(searchText))
                                .or(CUSTOMER.EMAIL.likeIgnoreCase(searchText)).or(CUSTOMER.CUSTOMER_ID.cast(String.class).likeIgnoreCase((searchText)))
                                .or(ADDRESS.ADDRESS_.likeIgnoreCase(searchText)).or(CITY.CITY_.likeIgnoreCase(searchText)).or(COUNTRY.COUNTRY_.likeIgnoreCase(searchText)))
                        .orderBy(CUSTOMER.CUSTOMER_ID)
                        .fetchInto(CustomerEditProfile.class);

        int totalNumOfCustomers = customers.size();
        int totalNumOfPages;

        System.out.println(numPage);
        if (totalNumOfCustomers % numOfCustomersForPage == 0) {
            totalNumOfPages = totalNumOfCustomers / numOfCustomersForPage;
            System.out.println("totalNumOfPages" + totalNumOfPages);

        } else {
            totalNumOfPages = totalNumOfCustomers / numOfCustomersForPage + 1;
            System.out.println("totalNumOfPages" + totalNumOfPages);
        }

        System.out.println("totalNumOfPages" + totalNumOfPages);

        if (customers.isEmpty()) {
            return new Response(null, "Customer not found", 200);
        } else {

            if (numPage < totalNumOfPages) {
                for (int i = (numPage - 1) * numOfCustomersForPage; i < (numOfCustomersForPage * (numPage)); i++) {
                    resultCust.add(customers.get(i));
                }
                if (resultCust.isEmpty()) {
                    return new Response(resultCust, "Customer not found", 200);
                }
            } else {
                for (int i = (numPage - 1) * numOfCustomersForPage; i < totalNumOfCustomers; i++) {
                    System.out.println("customers" + customers.get(i));
                    resultCust.add(customers.get(i));
                }
                if (resultCust.isEmpty()) {
                    return new Response(resultCust, "Customer not found", 200);
                }
            }
        }

        return new Response(resultCust, "Ok", 200);
    }

    @Override
    public int getTotalNumOfCustomersBySearchText(String searchText) {
        searchText = "%" + searchText + "%";
        List<CustomerEditProfile> resultCust = new ArrayList<CustomerEditProfile>();
        List<CustomerEditProfile> customers
                = context.select(CUSTOMER.CUSTOMER_ID, CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME, CUSTOMER.BALANCE, CUSTOMER.USERNAME, CUSTOMER.EMAIL,
                        CUSTOMER.PASSWORD, CUSTOMER.AVATAR_URL)
                        .select(ADDRESS.ADDRESS_.as("address"))
                        .select(COUNTRY.COUNTRY_.as("country"))
                        .select(CITY.CITY_.as("city"))
                        .select(ADDRESS.DISTRICT.as("district"))
                        .select(ADDRESS.POSTAL_CODE.as("postalCode"))
                        .from(CUSTOMER)
                        .leftJoin(ADDRESS)
                        .on(ADDRESS.ADDRESS_ID.eq(CUSTOMER.ADDRESS_ID.cast(Integer.class)))
                        .leftJoin(CITY)
                        .on(CITY.CITY_ID.eq(ADDRESS.CITY_ID.cast(Integer.class)))
                        .leftJoin(COUNTRY)
                        .on(COUNTRY.COUNTRY_ID.eq(CITY.COUNTRY_ID.cast(Integer.class)))
                        .where(CUSTOMER.FIRST_NAME.likeIgnoreCase(searchText).or(CUSTOMER.LAST_NAME.likeIgnoreCase(searchText)).or(CUSTOMER.USERNAME.likeIgnoreCase(searchText))
                                .or(CUSTOMER.EMAIL.likeIgnoreCase(searchText)).or(CUSTOMER.CUSTOMER_ID.cast(String.class).likeIgnoreCase((searchText)))
                                .or(ADDRESS.ADDRESS_.likeIgnoreCase(searchText)).or(CITY.CITY_.likeIgnoreCase(searchText)).or(COUNTRY.COUNTRY_.likeIgnoreCase(searchText)))
                        .fetchInto(CustomerEditProfile.class);
        return customers.size();
    }

    @Override
    public int getTotalNumOfCustomers() {
        List<Customer> customers = context.selectFrom(CUSTOMER).fetchInto(Customer.class);
        return customers.size();
    }

    @Override

    public Response updateCustomerBackOffice(CustomerEditProfile customer) {
        List<CustomerEditProfile> customers = context.selectFrom(CUSTOMER).where(CUSTOMER.CUSTOMER_ID.eq(customer.getCustomerId())).fetchInto(CustomerEditProfile.class);

        int num = context.update(CUSTOMER)
                .set(Tables.CUSTOMER.FIRST_NAME, customer.getFirstName())
                .set(Tables.CUSTOMER.LAST_NAME, customer.getLastName())
                .set(Tables.CUSTOMER.USERNAME, customer.getUsername())
                .set(Tables.CUSTOMER.EMAIL, customer.getEmail())
                .set(Tables.CUSTOMER.AVATAR_URL, customer.getAvatarUrl())
                .set(Tables.CUSTOMER.BALANCE, customer.getBalance())
                .where(CUSTOMER.CUSTOMER_ID.eq(customer.getCustomerId())).execute();
        if (num <= 0) {
            return new Response(null, "Error updating funds", 500);
        }

        return new Response(null, "Ok", 200);
    }

    @Override
    public Response<Customer> getCustomerByEmail(String email) {
        Customer customer = context
                .selectFrom(CUSTOMER)
                .where(CUSTOMER.EMAIL.eq(email))
                .fetchOneInto(Customer.class);

        if (customer == null) {
            return new Response(null, "Customer not found", 404);
        }
        return new Response(customer, "Ok", 200);
    }

}
