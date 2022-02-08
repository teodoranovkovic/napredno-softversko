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
public interface IFilmCategory extends Serializable {

    /**
     * Setter for <code>film_category.film_id</code>.
     */
    public void setFilmId(Short value);

    /**
     * Getter for <code>film_category.film_id</code>.
     */
    public Short getFilmId();

    /**
     * Setter for <code>film_category.category_id</code>.
     */
    public void setCategoryId(Short value);

    /**
     * Getter for <code>film_category.category_id</code>.
     */
    public Short getCategoryId();

    /**
     * Setter for <code>film_category.last_update</code>.
     */
    public void setLastUpdate(Timestamp value);

    /**
     * Getter for <code>film_category.last_update</code>.
     */
    public Timestamp getLastUpdate();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IFilmCategory
     */
    public void from(net.dualsoft.blockbuster.db.jooq.tables.interfaces.IFilmCategory from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IFilmCategory
     */
    public <E extends net.dualsoft.blockbuster.db.jooq.tables.interfaces.IFilmCategory> E into(E into);
}