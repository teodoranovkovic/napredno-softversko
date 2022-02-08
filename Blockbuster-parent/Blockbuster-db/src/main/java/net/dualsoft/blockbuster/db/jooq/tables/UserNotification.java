/*
 * This file is generated by jOOQ.
 */
package net.dualsoft.blockbuster.db.jooq.tables;


import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import net.dualsoft.blockbuster.db.jooq.DefaultSchema;
import net.dualsoft.blockbuster.db.jooq.Indexes;
import net.dualsoft.blockbuster.db.jooq.Keys;
import net.dualsoft.blockbuster.db.jooq.tables.records.UserNotificationRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class UserNotification extends TableImpl<UserNotificationRecord> {

    private static final long serialVersionUID = 2140985500;

    /**
     * The reference instance of <code>user_notification</code>
     */
    public static final UserNotification USER_NOTIFICATION = new UserNotification();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UserNotificationRecord> getRecordType() {
        return UserNotificationRecord.class;
    }

    /**
     * The column <code>user_notification.user_notification_id</code>.
     */
    public final TableField<UserNotificationRecord, Integer> USER_NOTIFICATION_ID = createField("user_notification_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('user_notification_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>user_notification.customer_id</code>.
     */
    public final TableField<UserNotificationRecord, Integer> CUSTOMER_ID = createField("customer_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>user_notification.title</code>.
     */
    public final TableField<UserNotificationRecord, String> TITLE = createField("title", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>user_notification.notification_text</code>.
     */
    public final TableField<UserNotificationRecord, String> NOTIFICATION_TEXT = createField("notification_text", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>user_notification.read</code>.
     */
    public final TableField<UserNotificationRecord, Boolean> READ = createField("read", org.jooq.impl.SQLDataType.BOOLEAN.nullable(false).defaultValue(org.jooq.impl.DSL.field("false", org.jooq.impl.SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>user_notification.last_update</code>.
     */
    public final TableField<UserNotificationRecord, Timestamp> LAST_UPDATE = createField("last_update", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("now()", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * Create a <code>user_notification</code> table reference
     */
    public UserNotification() {
        this(DSL.name("user_notification"), null);
    }

    /**
     * Create an aliased <code>user_notification</code> table reference
     */
    public UserNotification(String alias) {
        this(DSL.name(alias), USER_NOTIFICATION);
    }

    /**
     * Create an aliased <code>user_notification</code> table reference
     */
    public UserNotification(Name alias) {
        this(alias, USER_NOTIFICATION);
    }

    private UserNotification(Name alias, Table<UserNotificationRecord> aliased) {
        this(alias, aliased, null);
    }

    private UserNotification(Name alias, Table<UserNotificationRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> UserNotification(Table<O> child, ForeignKey<O, UserNotificationRecord> key) {
        super(child, key, USER_NOTIFICATION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.USER_NOTIFICATION_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<UserNotificationRecord, Integer> getIdentity() {
        return Keys.IDENTITY_USER_NOTIFICATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<UserNotificationRecord> getPrimaryKey() {
        return Keys.USER_NOTIFICATION_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<UserNotificationRecord>> getKeys() {
        return Arrays.<UniqueKey<UserNotificationRecord>>asList(Keys.USER_NOTIFICATION_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<UserNotificationRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<UserNotificationRecord, ?>>asList(Keys.USER_NOTIFICATION__USER_NOTIFICATION_CUSTOMER_ID_FKEY);
    }

    public Customer customer() {
        return new Customer(this, Keys.USER_NOTIFICATION__USER_NOTIFICATION_CUSTOMER_ID_FKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserNotification as(String alias) {
        return new UserNotification(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserNotification as(Name alias) {
        return new UserNotification(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public UserNotification rename(String name) {
        return new UserNotification(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public UserNotification rename(Name name) {
        return new UserNotification(name, null);
    }
}
