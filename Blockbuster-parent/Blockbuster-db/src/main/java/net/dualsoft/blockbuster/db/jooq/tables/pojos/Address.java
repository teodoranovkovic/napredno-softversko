/*
 * This file is generated by jOOQ.
 */
package net.dualsoft.blockbuster.db.jooq.tables.pojos;


import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.annotation.Generated;

import net.dualsoft.blockbuster.db.jooq.tables.interfaces.IAddress;


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
public class Address implements IAddress {

    private static final long serialVersionUID = 1956567361;

    private Integer    addressId;
    private String     address;
    private String     district;
    private Short      cityId;
    private String     postalCode;
    private Timestamp  lastUpdate;
    private BigDecimal longitude;
    private BigDecimal latitude;

    public Address() {}

    public Address(IAddress value) {
        this.addressId = value.getAddressId();
        this.address = value.getAddress();
        this.district = value.getDistrict();
        this.cityId = value.getCityId();
        this.postalCode = value.getPostalCode();
        this.lastUpdate = value.getLastUpdate();
        this.longitude = value.getLongitude();
        this.latitude = value.getLatitude();
    }

    public Address(
        Integer    addressId,
        String     address,
        String     district,
        Short      cityId,
        String     postalCode,
        Timestamp  lastUpdate,
        BigDecimal longitude,
        BigDecimal latitude
    ) {
        this.addressId = addressId;
        this.address = address;
        this.district = district;
        this.cityId = cityId;
        this.postalCode = postalCode;
        this.lastUpdate = lastUpdate;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public Integer getAddressId() {
        return this.addressId;
    }

    @Override
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getDistrict() {
        return this.district;
    }

    @Override
    public void setDistrict(String district) {
        this.district = district;
    }

    @Override
    public Short getCityId() {
        return this.cityId;
    }

    @Override
    public void setCityId(Short cityId) {
        this.cityId = cityId;
    }

    @Override
    public String getPostalCode() {
        return this.postalCode;
    }

    @Override
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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
    public BigDecimal getLongitude() {
        return this.longitude;
    }

    @Override
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Override
    public BigDecimal getLatitude() {
        return this.latitude;
    }

    @Override
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Address (");

        sb.append(addressId);
        sb.append(", ").append(address);
        sb.append(", ").append(district);
        sb.append(", ").append(cityId);
        sb.append(", ").append(postalCode);
        sb.append(", ").append(lastUpdate);
        sb.append(", ").append(longitude);
        sb.append(", ").append(latitude);

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
    public void from(IAddress from) {
        setAddressId(from.getAddressId());
        setAddress(from.getAddress());
        setDistrict(from.getDistrict());
        setCityId(from.getCityId());
        setPostalCode(from.getPostalCode());
        setLastUpdate(from.getLastUpdate());
        setLongitude(from.getLongitude());
        setLatitude(from.getLatitude());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends IAddress> E into(E into) {
        into.from(this);
        return into;
    }
}