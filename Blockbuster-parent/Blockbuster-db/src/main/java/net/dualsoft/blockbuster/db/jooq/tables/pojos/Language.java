/*
 * This file is generated by jOOQ.
 */
package net.dualsoft.blockbuster.db.jooq.tables.pojos;


import java.sql.Timestamp;

import javax.annotation.Generated;

import net.dualsoft.blockbuster.db.jooq.tables.interfaces.ILanguage;


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
public class Language implements ILanguage {

    private static final long serialVersionUID = -66354908;

    private Integer   languageId;
    private String    name;
    private Timestamp lastUpdate;

    public Language() {}

    public Language(ILanguage value) {
        this.languageId = value.getLanguageId();
        this.name = value.getName();
        this.lastUpdate = value.getLastUpdate();
    }

    public Language(
        Integer   languageId,
        String    name,
        Timestamp lastUpdate
    ) {
        this.languageId = languageId;
        this.name = name;
        this.lastUpdate = lastUpdate;
    }

    @Override
    public Integer getLanguageId() {
        return this.languageId;
    }

    @Override
    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
    public String toString() {
        StringBuilder sb = new StringBuilder("Language (");

        sb.append(languageId);
        sb.append(", ").append(name);
        sb.append(", ").append(lastUpdate);

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
    public void from(ILanguage from) {
        setLanguageId(from.getLanguageId());
        setName(from.getName());
        setLastUpdate(from.getLastUpdate());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends ILanguage> E into(E into) {
        into.from(this);
        return into;
    }
}
