/*
 * This file is generated by jOOQ.
 */
package net.dualsoft.blockbuster.db.jooq.tables.records;


import javax.annotation.Generated;

import net.dualsoft.blockbuster.db.jooq.tables.FilmInStock;
import net.dualsoft.blockbuster.db.jooq.tables.interfaces.IFilmInStock;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Row1;
import org.jooq.impl.TableRecordImpl;


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
public class FilmInStockRecord extends TableRecordImpl<FilmInStockRecord> implements Record1<Integer>, IFilmInStock {

    private static final long serialVersionUID = -1640978402;

    /**
     * Setter for <code>film_in_stock.p_film_count</code>.
     */
    @Override
    public void setPFilmCount(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>film_in_stock.p_film_count</code>.
     */
    @Override
    public Integer getPFilmCount() {
        return (Integer) get(0);
    }

    // -------------------------------------------------------------------------
    // Record1 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row1<Integer> fieldsRow() {
        return (Row1) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row1<Integer> valuesRow() {
        return (Row1) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return FilmInStock.FILM_IN_STOCK.P_FILM_COUNT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getPFilmCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getPFilmCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FilmInStockRecord value1(Integer value) {
        setPFilmCount(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FilmInStockRecord values(Integer value1) {
        value1(value1);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void from(IFilmInStock from) {
        setPFilmCount(from.getPFilmCount());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends IFilmInStock> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached FilmInStockRecord
     */
    public FilmInStockRecord() {
        super(FilmInStock.FILM_IN_STOCK);
    }

    /**
     * Create a detached, initialised FilmInStockRecord
     */
    public FilmInStockRecord(Integer pFilmCount) {
        super(FilmInStock.FILM_IN_STOCK);

        set(0, pFilmCount);
    }
}