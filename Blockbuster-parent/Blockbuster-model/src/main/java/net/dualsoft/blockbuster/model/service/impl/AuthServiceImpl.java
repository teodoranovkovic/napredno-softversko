/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service.impl;

import java.util.List;
import static net.dualsoft.blockbuster.db.jooq.Tables.ADDRESS;
import static net.dualsoft.blockbuster.db.jooq.Tables.CITY;
import static net.dualsoft.blockbuster.db.jooq.Tables.COUNTRY;
import static net.dualsoft.blockbuster.db.jooq.Tables.CUSTOMER;
import static net.dualsoft.blockbuster.db.jooq.Tables.FILM;
import static net.dualsoft.blockbuster.db.jooq.Tables.RENTAL_REQUEST;
import static net.dualsoft.blockbuster.db.jooq.Tables.STAFF;
import net.dualsoft.blockbuster.db.jooq.enums.RequestStatus;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Address;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.City;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Country;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Staff;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer;
import net.dualsoft.blockbuster.db.jooq.tables.records.AddressRecord;
import net.dualsoft.blockbuster.db.jooq.tables.records.CustomerRecord;
import net.dualsoft.blockbuster.model.helper.pojos.CustomerReg;
import net.dualsoft.blockbuster.model.helper.pojos.Response;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.dualsoft.blockbuster.model.service.AuthService;
import net.dualsoft.blockbuster.model.service.MailService;
import static org.jooq.impl.DSL.sum;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author teodora
 */
@Service
public class AuthServiceImpl implements AuthService {
    
    @Autowired
    private DSLContext context;
    
    @Autowired
    private MailService mailService;
    

    @Override
    public Response<Customer> login(String email, String password) {
        List<Customer> customer = context.selectFrom(CUSTOMER).where(CUSTOMER.EMAIL.eq(email)).fetchInto(Customer.class);
        if(customer.isEmpty()) {
            return new Response(null, "Email is invalid", 422);
        }
        
        BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        if(!pwEncoder.matches(password, customer.get(0).getPassword())){
            return new Response(null, "Invalid password", 403);
        }
        
        return new Response(customer.get(0), "Ok", 200);
    }

    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Response<Customer> register(CustomerReg customer) {
        
        Customer existing = context
                .select(CUSTOMER.USERNAME)
                .from(CUSTOMER)
                .where(CUSTOMER.USERNAME.like(customer.getUsername()))
                .fetchOneInto(Customer.class);
        if(existing != null){
            return new Response(null, "Username is taken", 409);
        }
        
        existing = context
                .select(CUSTOMER.EMAIL)
                .from(CUSTOMER)
                .where(CUSTOMER.EMAIL.like(customer.getEmail()))
                .fetchOneInto(Customer.class);
        if(existing != null){
            return new Response(null, "Email is taken", 409);
        }
        
        String password = customer.getPassword();     
        
        BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        String hashsedPassword = pwEncoder.encode(password);
        
        AddressRecord addressRecord = context
                .insertInto(ADDRESS, ADDRESS.ADDRESS_,ADDRESS.CITY_ID,ADDRESS.DISTRICT,ADDRESS.POSTAL_CODE)
                .values(customer.getStreet(),customer.getCity(),customer.getDistrict(),customer.getPostalCode())
                .returning().fetchOne();
        
        if(addressRecord == null) {
            return new Response(null, "Bad request", 403);
        }
                
        CustomerRecord customerRecord = context
                .insertInto(CUSTOMER, CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME, 
                        CUSTOMER.USERNAME ,CUSTOMER.EMAIL,CUSTOMER.ADDRESS_ID,
                        CUSTOMER.PASSWORD, CUSTOMER.STORE_ID)
                .values(customer.getFirstName(),customer.getLastName(),
                        customer.getUsername(),customer.getEmail(),addressRecord.getAddressId().shortValue(),
                        hashsedPassword, new Integer(1).shortValue())
                .returning().fetchOne();
       
        if(customerRecord == null){
            return new Response(null, "Bad request", 403);
        }

        return new Response(customerRecord.into(Customer.class), "Ok", 200);
    }
    
    @Override
    public Response<Staff> loginStaff(String email, String password) {
        List<Staff> staff = context.selectFrom(STAFF).where(STAFF.EMAIL.eq(email)).fetchInto(Staff.class);
        if(staff.isEmpty()) {
            return new Response(null, "The user with this email does not exist", 422);
        }
        
        BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        String pass = staff.get(0).getPassword();
        if(!pwEncoder.matches(password, pass)){
            return new Response(null, "This password does not match this email", 403);
        }
        
        return new Response(staff.get(0), "Ok", 200);
    }

    @Override
    public Response changePassword(String code, String newPassword) {
        String email = mailService.getEmailByCode(code);
        if(email == null){
            return new Response(null, "Invalid code", 404);
        }
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordHash = encoder.encode(newPassword);
        
        context
                .update(CUSTOMER)
                .set(CUSTOMER.PASSWORD, passwordHash)
                .where(CUSTOMER.EMAIL.eq(email))
                .execute();
        
        return new Response(null, "Ok", 200);
    }

    @Override
    public Response editPassword(int customerId, String oldPassword, String newPassword) {
        Customer customer = context
                .selectFrom(CUSTOMER)
                .where(CUSTOMER.CUSTOMER_ID.eq(customerId))
                .fetchOneInto(Customer.class);
        
        if(customer == null){
            return new Response(null, "Customer not found", 404);
        }
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String oldHash = customer.getPassword();
        if(!encoder.matches(oldPassword, oldHash)){
            return new Response(null, "Wrong password", 403);
        }
        
        context
                .update(CUSTOMER)
                .set(CUSTOMER.PASSWORD, encoder.encode(newPassword))
                .where(CUSTOMER.CUSTOMER_ID.eq(customerId))
                .execute();
        
        return new Response(null, "Ok", 200);
    }

    @Override
    public Response getLockedFunds(int customerId) {
        Float lockedMoney = context
                .select(sum(FILM.RENTAL_DURATION.multiply(FILM.RENTAL_RATE)))
                .from(RENTAL_REQUEST)
                .join(FILM).on(RENTAL_REQUEST.FILM_ID.eq(FILM.FILM_ID))
                .where(RENTAL_REQUEST.CUSTOMER_ID.eq(customerId))
                .and(RENTAL_REQUEST.STATUS.eq(RequestStatus.pending))
                .groupBy(RENTAL_REQUEST.CUSTOMER_ID)
                .fetchOneInto(Float.class);
        if(lockedMoney == null){
            return new Response(null, "Error getting locked funds", 501);
        }
        return new Response(lockedMoney, "Ok", 200);
    }

}
