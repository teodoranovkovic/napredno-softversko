/*
 * This file is generated by jOOQ.
 */
package net.dualsoft.blockbuster.db.jooq.tables.daos;


import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;

import net.dualsoft.blockbuster.db.jooq.tables.UserNotification;
import net.dualsoft.blockbuster.db.jooq.tables.records.UserNotificationRecord;

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
public class UserNotificationDao extends DAOImpl<UserNotificationRecord, net.dualsoft.blockbuster.db.jooq.tables.pojos.UserNotification, Integer> {

    /**
     * Create a new UserNotificationDao without any configuration
     */
    public UserNotificationDao() {
        super(UserNotification.USER_NOTIFICATION, net.dualsoft.blockbuster.db.jooq.tables.pojos.UserNotification.class);
    }

    /**
     * Create a new UserNotificationDao with an attached configuration
     */
    public UserNotificationDao(Configuration configuration) {
        super(UserNotification.USER_NOTIFICATION, net.dualsoft.blockbuster.db.jooq.tables.pojos.UserNotification.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(net.dualsoft.blockbuster.db.jooq.tables.pojos.UserNotification object) {
        return object.getUserNotificationId();
    }

    /**
     * Fetch records that have <code>user_notification_id IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.UserNotification> fetchByUserNotificationId(Integer... values) {
        return fetch(UserNotification.USER_NOTIFICATION.USER_NOTIFICATION_ID, values);
    }

    /**
     * Fetch a unique record that has <code>user_notification_id = value</code>
     */
    public net.dualsoft.blockbuster.db.jooq.tables.pojos.UserNotification fetchOneByUserNotificationId(Integer value) {
        return fetchOne(UserNotification.USER_NOTIFICATION.USER_NOTIFICATION_ID, value);
    }

    /**
     * Fetch records that have <code>customer_id IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.UserNotification> fetchByCustomerId(Integer... values) {
        return fetch(UserNotification.USER_NOTIFICATION.CUSTOMER_ID, values);
    }

    /**
     * Fetch records that have <code>title IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.UserNotification> fetchByTitle(String... values) {
        return fetch(UserNotification.USER_NOTIFICATION.TITLE, values);
    }

    /**
     * Fetch records that have <code>notification_text IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.UserNotification> fetchByNotificationText(String... values) {
        return fetch(UserNotification.USER_NOTIFICATION.NOTIFICATION_TEXT, values);
    }

    /**
     * Fetch records that have <code>read IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.UserNotification> fetchByRead(Boolean... values) {
        return fetch(UserNotification.USER_NOTIFICATION.READ, values);
    }

    /**
     * Fetch records that have <code>last_update IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.UserNotification> fetchByLastUpdate(Timestamp... values) {
        return fetch(UserNotification.USER_NOTIFICATION.LAST_UPDATE, values);
    }
}
