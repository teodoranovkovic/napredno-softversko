/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import static net.dualsoft.blockbuster.db.jooq.Tables.CUSTOMER;
import static net.dualsoft.blockbuster.db.jooq.Tables.FILM;
import static net.dualsoft.blockbuster.db.jooq.Tables.INVENTORY;
import static net.dualsoft.blockbuster.db.jooq.Tables.PAYMENT;
import static net.dualsoft.blockbuster.db.jooq.Tables.RENTAL;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer;
import net.dualsoft.blockbuster.model.DTO.PaymentForList;
import net.dualsoft.blockbuster.model.helper.pojos.Response;
import net.dualsoft.blockbuster.model.service.PaymentService;
import org.jooq.DSLContext;
import org.jooq.Record7;
import org.jooq.SelectConditionStep;
import org.jooq.SelectOnConditionStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author teodora
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private DSLContext context;

    @Override
    public Response<List<PaymentForList>> getPaymentsForCustomer(int customerId) {
        List<Customer> customers = context.selectFrom(CUSTOMER).where(CUSTOMER.CUSTOMER_ID.eq(customerId)).fetchInto(Customer.class);
        if (customers.isEmpty()) {
            return new Response(null, "Customer not found", 404);
        }
        List<PaymentForList> payments = context
                .select(PAYMENT.PAYMENT_ID, PAYMENT.CUSTOMER_ID, PAYMENT.AMOUNT, PAYMENT.PAYMENT_DATE, PAYMENT.TYPE, FILM.TITLE, FILM.POSTER_URL)
                .from(PAYMENT)
                .leftJoin(RENTAL)
                .on(RENTAL.RENTAL_ID.eq(PAYMENT.RENTAL_ID))
                .leftJoin(INVENTORY)
                .on(INVENTORY.INVENTORY_ID.eq(RENTAL.INVENTORY_ID.cast(Integer.class)))
                .leftJoin(FILM)
                .on(FILM.FILM_ID.cast(Short.class).eq(INVENTORY.FILM_ID))
                .where(PAYMENT.CUSTOMER_ID.cast(Integer.class).eq(customerId))
                .orderBy(PAYMENT.PAYMENT_DATE.desc())
                .fetchInto(PaymentForList.class);
        return new Response<List<PaymentForList>>(payments, "Ok", 200);
    }

    @Override
    public Response<List<PaymentForList>> getFilteredPaymentsForCustomer(int customerId, String age, String type) {
        SelectConditionStep<Record7<Integer, Short, BigDecimal, Timestamp, String, String, String>> query = context
                .select(PAYMENT.PAYMENT_ID, PAYMENT.CUSTOMER_ID, PAYMENT.AMOUNT, PAYMENT.PAYMENT_DATE, PAYMENT.TYPE, FILM.TITLE, FILM.POSTER_URL)
                .from(PAYMENT)
                .leftJoin(RENTAL)
                .on(RENTAL.RENTAL_ID.eq(PAYMENT.RENTAL_ID))
                .leftJoin(INVENTORY)
                .on(INVENTORY.INVENTORY_ID.eq(RENTAL.INVENTORY_ID.cast(Integer.class)))
                .leftJoin(FILM)
                .on(FILM.FILM_ID.cast(Short.class).eq(INVENTORY.FILM_ID))
                .where(PAYMENT.CUSTOMER_ID.cast(Integer.class).eq(customerId));
        if (age.compareTo("today") == 0) {
            query = query.and(PAYMENT.PAYMENT_DATE.ge(Timestamp.valueOf(LocalDateTime.now().minusDays(1))));
        } else if (age.compareTo("week") == 0) {
            query = query.and(PAYMENT.PAYMENT_DATE.ge(Timestamp.valueOf(LocalDateTime.now().minusWeeks(1))));
        } else if (age.compareTo("month") == 0) {
            query = query.and(PAYMENT.PAYMENT_DATE.ge(Timestamp.valueOf(LocalDateTime.now().minusMonths(1))));
        }
        
        if(type.compareTo("in") == 0){
            query = query.and(PAYMENT.TYPE.eq("in"));
        } else if(type.compareTo("out") == 0){
            query = query.and(PAYMENT.TYPE.eq("out"));
        }
        List<PaymentForList> payments = query
                .orderBy(PAYMENT.PAYMENT_DATE.desc())
                .fetchInto(PaymentForList.class);
        return new Response<List<PaymentForList>>(payments, "Ok", 200);
    }

}
