/*
 * This file is generated by jOOQ.
 */
package net.dualsoft.blockbuster.db.jooq.tables.daos;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;

import net.dualsoft.blockbuster.db.jooq.tables.Customer;
import net.dualsoft.blockbuster.db.jooq.tables.records.CustomerRecord;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CustomerDao extends DAOImpl<CustomerRecord, net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer, Integer> {

    /**
     * Create a new CustomerDao without any configuration
     */
    public CustomerDao() {
        super(Customer.CUSTOMER, net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer.class);
    }

    /**
     * Create a new CustomerDao with an attached configuration
     */
    public CustomerDao(Configuration configuration) {
        super(Customer.CUSTOMER, net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer object) {
        return object.getCustomerId();
    }

    /**
     * Fetch records that have <code>customer_id IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer> fetchByCustomerId(Integer... values) {
        return fetch(Customer.CUSTOMER.CUSTOMER_ID, values);
    }

    /**
     * Fetch a unique record that has <code>customer_id = value</code>
     */
    public net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer fetchOneByCustomerId(Integer value) {
        return fetchOne(Customer.CUSTOMER.CUSTOMER_ID, value);
    }

    /**
     * Fetch records that have <code>store_id IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer> fetchByStoreId(Short... values) {
        return fetch(Customer.CUSTOMER.STORE_ID, values);
    }

    /**
     * Fetch records that have <code>first_name IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer> fetchByFirstName(String... values) {
        return fetch(Customer.CUSTOMER.FIRST_NAME, values);
    }

    /**
     * Fetch records that have <code>last_name IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer> fetchByLastName(String... values) {
        return fetch(Customer.CUSTOMER.LAST_NAME, values);
    }

    /**
     * Fetch records that have <code>email IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer> fetchByEmail(String... values) {
        return fetch(Customer.CUSTOMER.EMAIL, values);
    }

    /**
     * Fetch records that have <code>address_id IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer> fetchByAddressId(Short... values) {
        return fetch(Customer.CUSTOMER.ADDRESS_ID, values);
    }

    /**
     * Fetch records that have <code>activebool IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer> fetchByActivebool(Boolean... values) {
        return fetch(Customer.CUSTOMER.ACTIVEBOOL, values);
    }

    /**
     * Fetch records that have <code>create_date IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer> fetchByCreateDate(Timestamp... values) {
        return fetch(Customer.CUSTOMER.CREATE_DATE, values);
    }

    /**
     * Fetch records that have <code>last_update IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer> fetchByLastUpdate(Timestamp... values) {
        return fetch(Customer.CUSTOMER.LAST_UPDATE, values);
    }

    /**
     * Fetch records that have <code>active IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer> fetchByActive(Integer... values) {
        return fetch(Customer.CUSTOMER.ACTIVE, values);
    }

    /**
     * Fetch records that have <code>password IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer> fetchByPassword(String... values) {
        return fetch(Customer.CUSTOMER.PASSWORD, values);
    }

    /**
     * Fetch records that have <code>balance IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer> fetchByBalance(BigDecimal... values) {
        return fetch(Customer.CUSTOMER.BALANCE, values);
    }

    /**
     * Fetch records that have <code>avatar_url IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer> fetchByAvatarUrl(String... values) {
        return fetch(Customer.CUSTOMER.AVATAR_URL, values);
    }

    /**
     * Fetch records that have <code>username IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer> fetchByUsername(String... values) {
        return fetch(Customer.CUSTOMER.USERNAME, values);
    }

    /**
     * Fetch a unique record that has <code>username = value</code>
     */
    public net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer fetchOneByUsername(String value) {
        return fetchOne(Customer.CUSTOMER.USERNAME, value);
    }
}