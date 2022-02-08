/*
 * This file is generated by jOOQ.
 */
package net.dualsoft.blockbuster.db.jooq.tables.daos;


import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;

import net.dualsoft.blockbuster.db.jooq.tables.Language;
import net.dualsoft.blockbuster.db.jooq.tables.records.LanguageRecord;

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
public class LanguageDao extends DAOImpl<LanguageRecord, net.dualsoft.blockbuster.db.jooq.tables.pojos.Language, Integer> {

    /**
     * Create a new LanguageDao without any configuration
     */
    public LanguageDao() {
        super(Language.LANGUAGE, net.dualsoft.blockbuster.db.jooq.tables.pojos.Language.class);
    }

    /**
     * Create a new LanguageDao with an attached configuration
     */
    public LanguageDao(Configuration configuration) {
        super(Language.LANGUAGE, net.dualsoft.blockbuster.db.jooq.tables.pojos.Language.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(net.dualsoft.blockbuster.db.jooq.tables.pojos.Language object) {
        return object.getLanguageId();
    }

    /**
     * Fetch records that have <code>language_id IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.Language> fetchByLanguageId(Integer... values) {
        return fetch(Language.LANGUAGE.LANGUAGE_ID, values);
    }

    /**
     * Fetch a unique record that has <code>language_id = value</code>
     */
    public net.dualsoft.blockbuster.db.jooq.tables.pojos.Language fetchOneByLanguageId(Integer value) {
        return fetchOne(Language.LANGUAGE.LANGUAGE_ID, value);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.Language> fetchByName(String... values) {
        return fetch(Language.LANGUAGE.NAME, values);
    }

    /**
     * Fetch records that have <code>last_update IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.Language> fetchByLastUpdate(Timestamp... values) {
        return fetch(Language.LANGUAGE.LAST_UPDATE, values);
    }
}