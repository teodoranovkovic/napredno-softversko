/*
 * This file is generated by jOOQ.
 */
package net.dualsoft.blockbuster.db.jooq.tables.records;


import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.annotation.Generated;

import net.dualsoft.blockbuster.db.jooq.tables.Address;
import net.dualsoft.blockbuster.db.jooq.tables.interfaces.IAddress;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
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
public class AddressRecord extends UpdatableRecordImpl<AddressRecord> implements Record8<Integer, String, String, Short, String, Timestamp, BigDecimal, BigDecimal>, IAddress {

    private static final long serialVersionUID = -1781608778;

    /**
     * Setter for <code>address.address_id</code>.
     */
    @Override
    public void setAddressId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>address.address_id</code>.
     */
    @Override
    public Integer getAddressId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>address.address</code>.
     */
    @Override
    public void setAddress(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>address.address</code>.
     */
    @Override
    public String getAddress() {
        return (String) get(1);
    }

    /**
     * Setter for <code>address.district</code>.
     */
    @Override
    public void setDistrict(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>address.district</code>.
     */
    @Override
    public String getDistrict() {
        return (String) get(2);
    }

    /**
     * Setter for <code>address.city_id</code>.
     */
    @Override
    public void setCityId(Short value) {
        set(3, value);
    }

    /**
     * Getter for <code>address.city_id</code>.
     */
    @Override
    public Short getCityId() {
        return (Short) get(3);
    }

    /**
     * Setter for <code>address.postal_code</code>.
     */
    @Override
    public void setPostalCode(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>address.postal_code</code>.
     */
    @Override
    public String getPostalCode() {
        return (String) get(4);
    }

    /**
     * Setter for <code>address.last_update</code>.
     */
    @Override
    public void setLastUpdate(Timestamp value) {
        set(5, value);
    }

    /**
     * Getter for <code>address.last_update</code>.
     */
    @Override
    public Timestamp getLastUpdate() {
        return (Timestamp) get(5);
    }

    /**
     * Setter for <code>address.longitude</code>.
     */
    @Override
    public void setLongitude(BigDecimal value) {
        set(6, value);
    }

    /**
     * Getter for <code>address.longitude</code>.
     */
    @Override
    public BigDecimal getLongitude() {
        return (BigDecimal) get(6);
    }

    /**
     * Setter for <code>address.latitude</code>.
     */
    @Override
    public void setLatitude(BigDecimal value) {
        set(7, value);
    }

    /**
     * Getter for <code>address.latitude</code>.
     */
    @Override
    public BigDecimal getLatitude() {
        return (BigDecimal) get(7);
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
    // Record8 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<Integer, String, String, Short, String, Timestamp, BigDecimal, BigDecimal> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<Integer, String, String, Short, String, Timestamp, BigDecimal, BigDecimal> valuesRow() {
        return (Row8) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Address.ADDRESS.ADDRESS_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Address.ADDRESS.ADDRESS_;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Address.ADDRESS.DISTRICT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Short> field4() {
        return Address.ADDRESS.CITY_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Address.ADDRESS.POSTAL_CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field6() {
        return Address.ADDRESS.LAST_UPDATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<BigDecimal> field7() {
        return Address.ADDRESS.LONGITUDE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<BigDecimal> field8() {
        return Address.ADDRESS.LATITUDE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getAddressId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getAddress();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getDistrict();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Short component4() {
        return getCityId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getPostalCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component6() {
        return getLastUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal component7() {
        return getLongitude();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal component8() {
        return getLatitude();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getAddressId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getAddress();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getDistrict();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Short value4() {
        return getCityId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getPostalCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value6() {
        return getLastUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal value7() {
        return getLongitude();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal value8() {
        return getLatitude();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AddressRecord value1(Integer value) {
        setAddressId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AddressRecord value2(String value) {
        setAddress(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AddressRecord value3(String value) {
        setDistrict(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AddressRecord value4(Short value) {
        setCityId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AddressRecord value5(String value) {
        setPostalCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AddressRecord value6(Timestamp value) {
        setLastUpdate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AddressRecord value7(BigDecimal value) {
        setLongitude(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AddressRecord value8(BigDecimal value) {
        setLatitude(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AddressRecord values(Integer value1, String value2, String value3, Short value4, String value5, Timestamp value6, BigDecimal value7, BigDecimal value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
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

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AddressRecord
     */
    public AddressRecord() {
        super(Address.ADDRESS);
    }

    /**
     * Create a detached, initialised AddressRecord
     */
    public AddressRecord(Integer addressId, String address, String district, Short cityId, String postalCode, Timestamp lastUpdate, BigDecimal longitude, BigDecimal latitude) {
        super(Address.ADDRESS);

        set(0, addressId);
        set(1, address);
        set(2, district);
        set(3, cityId);
        set(4, postalCode);
        set(5, lastUpdate);
        set(6, longitude);
        set(7, latitude);
    }
}