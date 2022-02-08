/*
 * This file is generated by jOOQ.
 */
package net.dualsoft.blockbuster.db.jooq.tables.daos;


import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;

import net.dualsoft.blockbuster.db.jooq.tables.FilmActor;
import net.dualsoft.blockbuster.db.jooq.tables.records.FilmActorRecord;

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
public class FilmActorDao extends DAOImpl<FilmActorRecord, net.dualsoft.blockbuster.db.jooq.tables.pojos.FilmActor, Record2<Short, Short>> {

    /**
     * Create a new FilmActorDao without any configuration
     */
    public FilmActorDao() {
        super(FilmActor.FILM_ACTOR, net.dualsoft.blockbuster.db.jooq.tables.pojos.FilmActor.class);
    }

    /**
     * Create a new FilmActorDao with an attached configuration
     */
    public FilmActorDao(Configuration configuration) {
        super(FilmActor.FILM_ACTOR, net.dualsoft.blockbuster.db.jooq.tables.pojos.FilmActor.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Record2<Short, Short> getId(net.dualsoft.blockbuster.db.jooq.tables.pojos.FilmActor object) {
        return compositeKeyRecord(object.getActorId(), object.getFilmId());
    }

    /**
     * Fetch records that have <code>actor_id IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.FilmActor> fetchByActorId(Short... values) {
        return fetch(FilmActor.FILM_ACTOR.ACTOR_ID, values);
    }

    /**
     * Fetch records that have <code>film_id IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.FilmActor> fetchByFilmId(Short... values) {
        return fetch(FilmActor.FILM_ACTOR.FILM_ID, values);
    }

    /**
     * Fetch records that have <code>last_update IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.FilmActor> fetchByLastUpdate(Timestamp... values) {
        return fetch(FilmActor.FILM_ACTOR.LAST_UPDATE, values);
    }

    /**
     * Fetch records that have <code>character IN (values)</code>
     */
    public List<net.dualsoft.blockbuster.db.jooq.tables.pojos.FilmActor> fetchByCharacter(String... values) {
        return fetch(FilmActor.FILM_ACTOR.CHARACTER, values);
    }
}