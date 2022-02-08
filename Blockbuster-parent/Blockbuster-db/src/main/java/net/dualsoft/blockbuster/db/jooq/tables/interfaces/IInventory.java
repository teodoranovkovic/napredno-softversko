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
public interface IInventory extends Serializable {

    /**
     * Setter for <code>inventory.inventory_id</code>.
     */
    public void setInventoryId(Integer value);

    /**
     * Getter for <code>inventory.inventory_id</code>.
     */
    public Integer getInventoryId();

    /**
     * Setter for <code>inventory.film_id</code>.
     */
    public void setFilmId(Short value);

    /**
     * Getter for <code>inventory.film_id</code>.
     */
    public Short getFilmId();

    /**
     * Setter for <code>inventory.store_id</code>.
     */
    public void setStoreId(Short value);

    /**
     * Getter for <code>inventory.store_id</code>.
     */
    public Short getStoreId();

    /**
     * Setter for <code>inventory.last_update</code>.
     */
    public void setLastUpdate(Timestamp value);

    /**
     * Getter for <code>inventory.last_update</code>.
     */
    public Timestamp getLastUpdate();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IInventory
     */
    public void from(net.dualsoft.blockbuster.db.jooq.tables.interfaces.IInventory from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IInventory
     */
    public <E extends net.dualsoft.blockbuster.db.jooq.tables.interfaces.IInventory> E into(E into);
}
