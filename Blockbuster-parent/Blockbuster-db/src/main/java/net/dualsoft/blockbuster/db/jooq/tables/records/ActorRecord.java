/*
 * This file is generated by jOOQ.
 */
package net.dualsoft.blockbuster.db.jooq.tables.records;


import java.sql.Timestamp;

import javax.annotation.Generated;

import net.dualsoft.blockbuster.db.jooq.tables.Actor;
import net.dualsoft.blockbuster.db.jooq.tables.interfaces.IActor;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


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
public class ActorRecord extends UpdatableRecordImpl<ActorRecord> implements Record6<Integer, String, Timestamp, String, String, Integer>, IActor {

    private static final long serialVersionUID = 1946396498;

    /**
     * Setter for <code>actor.actor_id</code>.
     */
    @Override
    public void setActorId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>actor.actor_id</code>.
     */
    @Override
    public Integer getActorId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>actor.name</code>.
     */
    @Override
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>actor.name</code>.
     */
    @Override
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>actor.last_update</code>.
     */
    @Override
    public void setLastUpdate(Timestamp value) {
        set(2, value);
    }

    /**
     * Getter for <code>actor.last_update</code>.
     */
    @Override
    public Timestamp getLastUpdate() {
        return (Timestamp) get(2);
    }

    /**
     * Setter for <code>actor.picture_url</code>.
     */
    @Override
    public void setPictureUrl(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>actor.picture_url</code>.
     */
    @Override
    public String getPictureUrl() {
        return (String) get(3);
    }

    /**
     * Setter for <code>actor.biography</code>.
     */
    @Override
    public void setBiography(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>actor.biography</code>.
     */
    @Override
    public String getBiography() {
        return (String) get(4);
    }

    /**
     * Setter for <code>actor.moviedb_id</code>.
     */
    @Override
    public void setMoviedbId(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>actor.moviedb_id</code>.
     */
    @Override
    public Integer getMoviedbId() {
        return (Integer) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Integer, String, Timestamp, String, String, Integer> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Integer, String, Timestamp, String, String, Integer> valuesRow() {
        return (Row6) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Actor.ACTOR.ACTOR_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Actor.ACTOR.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field3() {
        return Actor.ACTOR.LAST_UPDATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Actor.ACTOR.PICTURE_URL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Actor.ACTOR.BIOGRAPHY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field6() {
        return Actor.ACTOR.MOVIEDB_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getActorId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component3() {
        return getLastUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getPictureUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getBiography();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component6() {
        return getMoviedbId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getActorId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value3() {
        return getLastUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getPictureUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getBiography();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value6() {
        return getMoviedbId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActorRecord value1(Integer value) {
        setActorId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActorRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActorRecord value3(Timestamp value) {
        setLastUpdate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActorRecord value4(String value) {
        setPictureUrl(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActorRecord value5(String value) {
        setBiography(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActorRecord value6(Integer value) {
        setMoviedbId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActorRecord values(Integer value1, String value2, Timestamp value3, String value4, String value5, Integer value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void from(IActor from) {
        setActorId(from.getActorId());
        setName(from.getName());
        setLastUpdate(from.getLastUpdate());
        setPictureUrl(from.getPictureUrl());
        setBiography(from.getBiography());
        setMoviedbId(from.getMoviedbId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends IActor> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ActorRecord
     */
    public ActorRecord() {
        super(Actor.ACTOR);
    }

    /**
     * Create a detached, initialised ActorRecord
     */
    public ActorRecord(Integer actorId, String name, Timestamp lastUpdate, String pictureUrl, String biography, Integer moviedbId) {
        super(Actor.ACTOR);

        set(0, actorId);
        set(1, name);
        set(2, lastUpdate);
        set(3, pictureUrl);
        set(4, biography);
        set(5, moviedbId);
    }
}
