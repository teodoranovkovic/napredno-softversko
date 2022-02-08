/*
 * This file is generated by jOOQ.
 */
package net.dualsoft.blockbuster.db.jooq.tables;


import javax.annotation.Generated;

import net.dualsoft.blockbuster.db.jooq.DefaultSchema;
import net.dualsoft.blockbuster.db.jooq.tables.records.FilmInStockRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
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
public class FilmInStock extends TableImpl<FilmInStockRecord> {

    private static final long serialVersionUID = 1090850345;

    /**
     * The reference instance of <code>film_in_stock</code>
     */
    public static final FilmInStock FILM_IN_STOCK = new FilmInStock();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<FilmInStockRecord> getRecordType() {
        return FilmInStockRecord.class;
    }

    /**
     * The column <code>film_in_stock.p_film_count</code>.
     */
    public final TableField<FilmInStockRecord, Integer> P_FILM_COUNT = createField("p_film_count", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * Create a <code>film_in_stock</code> table reference
     */
    public FilmInStock() {
        this(DSL.name("film_in_stock"), null);
    }

    /**
     * Create an aliased <code>film_in_stock</code> table reference
     */
    public FilmInStock(String alias) {
        this(DSL.name(alias), FILM_IN_STOCK);
    }

    /**
     * Create an aliased <code>film_in_stock</code> table reference
     */
    public FilmInStock(Name alias) {
        this(alias, FILM_IN_STOCK);
    }

    private FilmInStock(Name alias, Table<FilmInStockRecord> aliased) {
        this(alias, aliased, new Field[2]);
    }

    private FilmInStock(Name alias, Table<FilmInStockRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> FilmInStock(Table<O> child, ForeignKey<O, FilmInStockRecord> key) {
        super(child, key, FILM_IN_STOCK);
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
    public FilmInStock as(String alias) {
        return new FilmInStock(DSL.name(alias), this, parameters);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FilmInStock as(Name alias) {
        return new FilmInStock(alias, this, parameters);
    }

    /**
     * Rename this table
     */
    @Override
    public FilmInStock rename(String name) {
        return new FilmInStock(DSL.name(name), null, parameters);
    }

    /**
     * Rename this table
     */
    @Override
    public FilmInStock rename(Name name) {
        return new FilmInStock(name, null, parameters);
    }

    /**
     * Call this table-valued function
     */
    public FilmInStock call(Integer pFilmId, Integer pStoreId) {
        return new FilmInStock(DSL.name(getName()), null, new Field[] { 
              DSL.val(pFilmId, org.jooq.impl.SQLDataType.INTEGER)
            , DSL.val(pStoreId, org.jooq.impl.SQLDataType.INTEGER)
        });
    }

    /**
     * Call this table-valued function
     */
    public FilmInStock call(Field<Integer> pFilmId, Field<Integer> pStoreId) {
        return new FilmInStock(DSL.name(getName()), null, new Field[] { 
              pFilmId
            , pStoreId
        });
    }
}