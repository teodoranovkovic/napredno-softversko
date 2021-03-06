/*
 * This file is generated by jOOQ.
 */
package net.dualsoft.blockbuster.db.jooq.tables.interfaces;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;


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
public interface IStaff extends Serializable {

    /**
     * Setter for <code>staff.staff_id</code>.
     */
    public void setStaffId(Integer value);

    /**
     * Getter for <code>staff.staff_id</code>.
     */
    public Integer getStaffId();

    /**
     * Setter for <code>staff.first_name</code>.
     */
    public void setFirstName(String value);

    /**
     * Getter for <code>staff.first_name</code>.
     */
    public String getFirstName();

    /**
     * Setter for <code>staff.last_name</code>.
     */
    public void setLastName(String value);

    /**
     * Getter for <code>staff.last_name</code>.
     */
    public String getLastName();

    /**
     * Setter for <code>staff.address_id</code>.
     */
    public void setAddressId(Short value);

    /**
     * Getter for <code>staff.address_id</code>.
     */
    public Short getAddressId();

    /**
     * Setter for <code>staff.email</code>.
     */
    public void setEmail(String value);

    /**
     * Getter for <code>staff.email</code>.
     */
    public String getEmail();

    /**
     * Setter for <code>staff.store_id</code>.
     */
    public void setStoreId(Short value);

    /**
     * Getter for <code>staff.store_id</code>.
     */
    public Short getStoreId();

    /**
     * Setter for <code>staff.active</code>.
     */
    public void setActive(Boolean value);

    /**
     * Getter for <code>staff.active</code>.
     */
    public Boolean getActive();

    /**
     * Setter for <code>staff.username</code>.
     */
    public void setUsername(String value);

    /**
     * Getter for <code>staff.username</code>.
     */
    public String getUsername();

    /**
     * Setter for <code>staff.password</code>.
     */
    public void setPassword(String value);

    /**
     * Getter for <code>staff.password</code>.
     */
    public String getPassword();

    /**
     * Setter for <code>staff.last_update</code>.
     */
    public void setLastUpdate(Timestamp value);

    /**
     * Getter for <code>staff.last_update</code>.
     */
    public Timestamp getLastUpdate();

    /**
     * Setter for <code>staff.picture</code>.
     */
    public void setPicture(String value);

    /**
     * Getter for <code>staff.picture</code>.
     */
    public String getPicture();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IStaff
     */
    public void from(net.dualsoft.blockbuster.db.jooq.tables.interfaces.IStaff from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IStaff
     */
    public <E extends net.dualsoft.blockbuster.db.jooq.tables.interfaces.IStaff> E into(E into);
}
