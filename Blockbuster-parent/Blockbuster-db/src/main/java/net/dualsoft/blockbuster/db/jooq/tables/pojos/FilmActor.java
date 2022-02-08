/*
 * This file is generated by jOOQ.
 */
package net.dualsoft.blockbuster.db.jooq.tables.pojos;


import java.sql.Timestamp;

import javax.annotation.Generated;

import net.dualsoft.blockbuster.db.jooq.tables.interfaces.IFilmActor;


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
public class FilmActor implements IFilmActor {

    private static final long serialVersionUID = -422287468;

    private Short     actorId;
    private Short     filmId;
    private Timestamp lastUpdate;
    private String    character;

    public FilmActor() {}

    public FilmActor(IFilmActor value) {
        this.actorId = value.getActorId();
        this.filmId = value.getFilmId();
        this.lastUpdate = value.getLastUpdate();
        this.character = value.getCharacter();
    }

    public FilmActor(
        Short     actorId,
        Short     filmId,
        Timestamp lastUpdate,
        String    character
    ) {
        this.actorId = actorId;
        this.filmId = filmId;
        this.lastUpdate = lastUpdate;
        this.character = character;
    }

    @Override
    public Short getActorId() {
        return this.actorId;
    }

    @Override
    public void setActorId(Short actorId) {
        this.actorId = actorId;
    }

    @Override
    public Short getFilmId() {
        return this.filmId;
    }

    @Override
    public void setFilmId(Short filmId) {
        this.filmId = filmId;
    }

    @Override
    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }

    @Override
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String getCharacter() {
        return this.character;
    }

    @Override
    public void setCharacter(String character) {
        this.character = character;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("FilmActor (");

        sb.append(actorId);
        sb.append(", ").append(filmId);
        sb.append(", ").append(lastUpdate);
        sb.append(", ").append(character);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void from(IFilmActor from) {
        setActorId(from.getActorId());
        setFilmId(from.getFilmId());
        setLastUpdate(from.getLastUpdate());
        setCharacter(from.getCharacter());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends IFilmActor> E into(E into) {
        into.from(this);
        return into;
    }
}