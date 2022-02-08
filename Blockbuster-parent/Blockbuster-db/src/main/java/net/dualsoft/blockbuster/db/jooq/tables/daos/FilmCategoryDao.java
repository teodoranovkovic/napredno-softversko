/*
 * This file is generated by jOOQ.
 */
package net.dualsoft.blockbuster.db.jooq.tables.daos;


import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;

import net.dualsoft.blockbuster.db.jooq.tables.FilmCategory;
import net.dualsoft.blockbuster.db.jooq.tables.records.FilmCategoryRecord;

import org.jooq.Configuration;
import org.jooq.Record2;
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
public class FilmCategoryDao extends DAOImpl<FilmCategoryRecord, net.dualsoft.blockbuster.db.jooq.tables.pojos.FilmCategory, Record2<Short, Short>> {

    /**
     * Create a new FilmCategoryDao without any configuration
     */
    public FilmCategoryDao() {
        super(FilmCategory.FILM_CATEGORY, net.dualsoft.blockbuster.db.jooq.tables.pojos.FilmCategory.class);
    }

    /**
     * Create a new FilmCategoryDao with an attached configuration
     */
    public FilmCategoryDao(Configuration configuration) {
        super(FilmCategory.FILM_CATEGORY, net.dualsoft.blockbuster.db.jooq.tables.pojos.FilmCategory.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Record2<Short, Short> getId(net.dualsoft.blockbuster.db.jooq.tables.pojos.FilmCategory object) {
        return compositeKeyRecord(object.getFilmId(), object.getCategoryId());
    }

    /**
     * Fetch records that have <code>film_id IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.FilmCategory> fetchByFilmId(Short... values) {
        return fetch(FilmCategory.FILM_CATEGORY.FILM_ID, values);
    }

    /**
     * Fetch records that have <code>category_id IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.FilmCategory> fetchByCategoryId(Short... values) {
        return fetch(FilmCategory.FILM_CATEGORY.CATEGORY_ID, values);
    }

    /**
     * Fetch records that have <code>last_update IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.FilmCategory> fetchByLastUpdate(Timestamp... values) {
        return fetch(FilmCategory.FILM_CATEGORY.LAST_UPDATE, values);
    }
}
