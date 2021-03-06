/*
 * This file is generated by jOOQ.
 */
package net.dualsoft.blockbuster.db.jooq.tables;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import net.dualsoft.blockbuster.db.jooq.DefaultSchema;
import net.dualsoft.blockbuster.db.jooq.Indexes;
import net.dualsoft.blockbuster.db.jooq.Keys;
import net.dualsoft.blockbuster.db.jooq.enums.MpaaRating;
import net.dualsoft.blockbuster.db.jooq.tables.records.FilmRecord;

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
public class Film extends TableImpl<FilmRecord> {

    private static final long serialVersionUID = -1836162889;

    /**
     * The reference instance of <code>film</code>
     */
    public static final Film FILM = new Film();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<FilmRecord> getRecordType() {
        return FilmRecord.class;
    }

    /**
     * The column <code>film.film_id</code>.
     */
    public final TableField<FilmRecord, Integer> FILM_ID = createField("film_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('film_film_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>film.title</code>.
     */
    public final TableField<FilmRecord, String> TITLE = createField("title", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>film.description</code>.
     */
    public final TableField<FilmRecord, String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>film.release_year</code>.
     */
    public final TableField<FilmRecord, Integer> RELEASE_YEAR = createField("release_year", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>film.language_id</code>.
     */
    public final TableField<FilmRecord, Short> LANGUAGE_ID = createField("language_id", org.jooq.impl.SQLDataType.SMALLINT.nullable(false), this, "");

    /**
     * The column <code>film.rental_duration</code>.
     */
    public final TableField<FilmRecord, Short> RENTAL_DURATION = createField("rental_duration", org.jooq.impl.SQLDataType.SMALLINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("3", org.jooq.impl.SQLDataType.SMALLINT)), this, "");

    /**
     * The column <code>film.rental_rate</code>.
     */
    public final TableField<FilmRecord, BigDecimal> RENTAL_RATE = createField("rental_rate", org.jooq.impl.SQLDataType.NUMERIC(4, 2).nullable(false).defaultValue(org.jooq.impl.DSL.field("4.99", org.jooq.impl.SQLDataType.NUMERIC)), this, "");

    /**
     * The column <code>film.length</code>.
     */
    public final TableField<FilmRecord, Short> LENGTH = createField("length", org.jooq.impl.SQLDataType.SMALLINT, this, "");

    /**
     * The column <code>film.replacement_cost</code>.
     */
    public final TableField<FilmRecord, BigDecimal> REPLACEMENT_COST = createField("replacement_cost", org.jooq.impl.SQLDataType.NUMERIC(5, 2).nullable(false).defaultValue(org.jooq.impl.DSL.field("19.99", org.jooq.impl.SQLDataType.NUMERIC)), this, "");

    /**
     * The column <code>film.rating</code>.
     */
    public final TableField<FilmRecord, MpaaRating> RATING = createField("rating", org.jooq.impl.SQLDataType.VARCHAR.defaultValue(org.jooq.impl.DSL.field("'G'::mpaa_rating", org.jooq.impl.SQLDataType.VARCHAR)).asEnumDataType(net.dualsoft.blockbuster.db.jooq.enums.MpaaRating.class), this, "");

    /**
     * The column <code>film.last_update</code>.
     */
    public final TableField<FilmRecord, Timestamp> LAST_UPDATE = createField("last_update", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("now()", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>film.special_features</code>.
     */
    public final TableField<FilmRecord, String[]> SPECIAL_FEATURES = createField("special_features", org.jooq.impl.SQLDataType.CLOB.getArrayDataType(), this, "");

    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be turned off using {@literal <deprecationOnUnknownTypes/>} in your code generator configuration.
     */
    @java.lang.Deprecated
    public final TableField<FilmRecord, Object> FULLTEXT = createField("fulltext", org.jooq.impl.DefaultDataType.getDefaultDataType("\"pg_catalog\".\"tsvector\"").nullable(false), this, "");

    /**
     * The column <code>film.poster_url</code>.
     */
    public final TableField<FilmRecord, String> POSTER_URL = createField("poster_url", org.jooq.impl.SQLDataType.VARCHAR(512), this, "");

    /**
     * The column <code>film.star_rating</code>.
     */
    public final TableField<FilmRecord, BigDecimal> STAR_RATING = createField("star_rating", org.jooq.impl.SQLDataType.NUMERIC(3, 1), this, "");

    /**
     * The column <code>film.moviedb_id</code>.
     */
    public final TableField<FilmRecord, Integer> MOVIEDB_ID = createField("moviedb_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * Create a <code>film</code> table reference
     */
    public Film() {
        this(DSL.name("film"), null);
    }

    /**
     * Create an aliased <code>film</code> table reference
     */
    public Film(String alias) {
        this(DSL.name(alias), FILM);
    }

    /**
     * Create an aliased <code>film</code> table reference
     */
    public Film(Name alias) {
        this(alias, FILM);
    }

    private Film(Name alias, Table<FilmRecord> aliased) {
        this(alias, aliased, null);
    }

    private Film(Name alias, Table<FilmRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Film(Table<O> child, ForeignKey<O, FilmRecord> key) {
        super(child, key, FILM);
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
        return Arrays.<Index>asList(Indexes.FILM_FULLTEXT_IDX, Indexes.FILM_PKEY, Indexes.IDX_FK_LANGUAGE_ID, Indexes.IDX_TITLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<FilmRecord, Integer> getIdentity() {
        return Keys.IDENTITY_FILM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<FilmRecord> getPrimaryKey() {
        return Keys.FILM_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<FilmRecord>> getKeys() {
        return Arrays.<UniqueKey<FilmRecord>>asList(Keys.FILM_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<FilmRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<FilmRecord, ?>>asList(Keys.FILM__FILM_LANGUAGE_ID_FKEY);
    }

    public Language language() {
        return new Language(this, Keys.FILM__FILM_LANGUAGE_ID_FKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Film as(String alias) {
        return new Film(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Film as(Name alias) {
        return new Film(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Film rename(String name) {
        return new Film(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Film rename(Name name) {
        return new Film(name, null);
    }
}
