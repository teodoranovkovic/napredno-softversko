/*
 * This file is generated by jOOQ.
 */
package net.dualsoft.blockbuster.db.jooq.tables.pojos;


import java.sql.Timestamp;

import javax.annotation.Generated;

import net.dualsoft.blockbuster.db.jooq.tables.interfaces.ICity;


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
public class City implements ICity {

    private static final long serialVersionUID = -1735196992;

    private Integer   cityId;
    private String    city;
    private Short     countryId;
    private Timestamp lastUpdate;

    public City() {}

    public City(ICity value) {
        this.cityId = value.getCityId();
        this.city = value.getCity();
        this.countryId = value.getCountryId();
        this.lastUpdate = value.getLastUpdate();
    }

    public City(
        Integer   cityId,
        String    city,
        Short     countryId,
        Timestamp lastUpdate
    ) {
        this.cityId = cityId;
        this.city = city;
        this.countryId = countryId;
        this.lastUpdate = lastUpdate;
    }

    @Override
    public Integer getCityId() {
        return this.cityId;
    }

    @Override
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @Override
    public String getCity() {
        return this.city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public Short getCountryId() {
        return this.countryId;
    }

    @Override
    public void setCountryId(Short countryId) {
        this.countryId = countryId;
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
        StringBuilder sb = new StringBuilder("City (");

        sb.append(cityId);
        sb.append(", ").append(city);
        sb.append(", ").append(countryId);
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
    public void from(ICity from) {
        setCityId(from.getCityId());
        setCity(from.getCity());
        setCountryId(from.getCountryId());
        setLastUpdate(from.getLastUpdate());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends ICity> E into(E into) {
        into.from(this);
        return into;
    }
}