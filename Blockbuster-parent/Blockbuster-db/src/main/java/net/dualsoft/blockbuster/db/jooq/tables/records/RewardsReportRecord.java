/*
 * This file is generated by jOOQ.
 */
package net.dualsoft.blockbuster.db.jooq.tables.records;


import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.annotation.Generated;

import net.dualsoft.blockbuster.db.jooq.tables.RewardsReport;
import net.dualsoft.blockbuster.db.jooq.tables.interfaces.IRewardsReport;

import org.jooq.Field;
import org.jooq.Record14;
import org.jooq.Row14;
import org.jooq.impl.TableRecordImpl;


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
public class RewardsReportRecord extends TableRecordImpl<RewardsReportRecord> implements Record14<Integer, Short, String, String, String, Short, Boolean, Timestamp, Timestamp, Integer, String, BigDecimal, String, String>, IRewardsReport {

    private static final long serialVersionUID = 746291781;

    /**
     * Setter for <code>rewards_report.customer_id</code>.
     */
    @Override
    public void setCustomerId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>rewards_report.customer_id</code>.
     */
    @Override
    public Integer getCustomerId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>rewards_report.store_id</code>.
     */
    @Override
    public void setStoreId(Short value) {
        set(1, value);
    }

    /**
     * Getter for <code>rewards_report.store_id</code>.
     */
    @Override
    public Short getStoreId() {
        return (Short) get(1);
    }

    /**
     * Setter for <code>rewards_report.first_name</code>.
     */
    @Override
    public void setFirstName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>rewards_report.first_name</code>.
     */
    @Override
    public String getFirstName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>rewards_report.last_name</code>.
     */
    @Override
    public void setLastName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>rewards_report.last_name</code>.
     */
    @Override
    public String getLastName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>rewards_report.email</code>.
     */
    @Override
    public void setEmail(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>rewards_report.email</code>.
     */
    @Override
    public String getEmail() {
        return (String) get(4);
    }

    /**
     * Setter for <code>rewards_report.address_id</code>.
     */
    @Override
    public void setAddressId(Short value) {
        set(5, value);
    }

    /**
     * Getter for <code>rewards_report.address_id</code>.
     */
    @Override
    public Short getAddressId() {
        return (Short) get(5);
    }

    /**
     * Setter for <code>rewards_report.activebool</code>.
     */
    @Override
    public void setActivebool(Boolean value) {
        set(6, value);
    }

    /**
     * Getter for <code>rewards_report.activebool</code>.
     */
    @Override
    public Boolean getActivebool() {
        return (Boolean) get(6);
    }

    /**
     * Setter for <code>rewards_report.create_date</code>.
     */
    @Override
    public void setCreateDate(Timestamp value) {
        set(7, value);
    }

    /**
     * Getter for <code>rewards_report.create_date</code>.
     */
    @Override
    public Timestamp getCreateDate() {
        return (Timestamp) get(7);
    }

    /**
     * Setter for <code>rewards_report.last_update</code>.
     */
    @Override
    public void setLastUpdate(Timestamp value) {
        set(8, value);
    }

    /**
     * Getter for <code>rewards_report.last_update</code>.
     */
    @Override
    public Timestamp getLastUpdate() {
        return (Timestamp) get(8);
    }

    /**
     * Setter for <code>rewards_report.active</code>.
     */
    @Override
    public void setActive(Integer value) {
        set(9, value);
    }

    /**
     * Getter for <code>rewards_report.active</code>.
     */
    @Override
    public Integer getActive() {
        return (Integer) get(9);
    }

    /**
     * Setter for <code>rewards_report.password</code>.
     */
    @Override
    public void setPassword(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>rewards_report.password</code>.
     */
    @Override
    public String getPassword() {
        return (String) get(10);
    }

    /**
     * Setter for <code>rewards_report.balance</code>.
     */
    @Override
    public void setBalance(BigDecimal value) {
        set(11, value);
    }

    /**
     * Getter for <code>rewards_report.balance</code>.
     */
    @Override
    public BigDecimal getBalance() {
        return (BigDecimal) get(11);
    }

    /**
     * Setter for <code>rewards_report.avatar_url</code>.
     */
    @Override
    public void setAvatarUrl(String value) {
        set(12, value);
    }

    /**
     * Getter for <code>rewards_report.avatar_url</code>.
     */
    @Override
    public String getAvatarUrl() {
        return (String) get(12);
    }

    /**
     * Setter for <code>rewards_report.username</code>.
     */
    @Override
    public void setUsername(String value) {
        set(13, value);
    }

    /**
     * Getter for <code>rewards_report.username</code>.
     */
    @Override
    public String getUsername() {
        return (String) get(13);
    }

    // -------------------------------------------------------------------------
    // Record14 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row14<Integer, Short, String, String, String, Short, Boolean, Timestamp, Timestamp, Integer, String, BigDecimal, String, String> fieldsRow() {
        return (Row14) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row14<Integer, Short, String, String, String, Short, Boolean, Timestamp, Timestamp, Integer, String, BigDecimal, String, String> valuesRow() {
        return (Row14) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return RewardsReport.REWARDS_REPORT.CUSTOMER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Short> field2() {
        return RewardsReport.REWARDS_REPORT.STORE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return RewardsReport.REWARDS_REPORT.FIRST_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return RewardsReport.REWARDS_REPORT.LAST_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return RewardsReport.REWARDS_REPORT.EMAIL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Short> field6() {
        return RewardsReport.REWARDS_REPORT.ADDRESS_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field7() {
        return RewardsReport.REWARDS_REPORT.ACTIVEBOOL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field8() {
        return RewardsReport.REWARDS_REPORT.CREATE_DATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field9() {
        return RewardsReport.REWARDS_REPORT.LAST_UPDATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field10() {
        return RewardsReport.REWARDS_REPORT.ACTIVE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field11() {
        return RewardsReport.REWARDS_REPORT.PASSWORD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<BigDecimal> field12() {
        return RewardsReport.REWARDS_REPORT.BALANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field13() {
        return RewardsReport.REWARDS_REPORT.AVATAR_URL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field14() {
        return RewardsReport.REWARDS_REPORT.USERNAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getCustomerId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Short component2() {
        return getStoreId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getFirstName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getLastName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getEmail();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Short component6() {
        return getAddressId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component7() {
        return getActivebool();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component8() {
        return getCreateDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component9() {
        return getLastUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component10() {
        return getActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component11() {
        return getPassword();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal component12() {
        return getBalance();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component13() {
        return getAvatarUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component14() {
        return getUsername();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getCustomerId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Short value2() {
        return getStoreId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getFirstName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getLastName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getEmail();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Short value6() {
        return getAddressId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value7() {
        return getActivebool();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value8() {
        return getCreateDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value9() {
        return getLastUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value10() {
        return getActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value11() {
        return getPassword();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal value12() {
        return getBalance();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value13() {
        return getAvatarUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value14() {
        return getUsername();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RewardsReportRecord value1(Integer value) {
        setCustomerId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RewardsReportRecord value2(Short value) {
        setStoreId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RewardsReportRecord value3(String value) {
        setFirstName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RewardsReportRecord value4(String value) {
        setLastName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RewardsReportRecord value5(String value) {
        setEmail(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RewardsReportRecord value6(Short value) {
        setAddressId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RewardsReportRecord value7(Boolean value) {
        setActivebool(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RewardsReportRecord value8(Timestamp value) {
        setCreateDate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RewardsReportRecord value9(Timestamp value) {
        setLastUpdate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RewardsReportRecord value10(Integer value) {
        setActive(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RewardsReportRecord value11(String value) {
        setPassword(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RewardsReportRecord value12(BigDecimal value) {
        setBalance(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RewardsReportRecord value13(String value) {
        setAvatarUrl(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RewardsReportRecord value14(String value) {
        setUsername(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RewardsReportRecord values(Integer value1, Short value2, String value3, String value4, String value5, Short value6, Boolean value7, Timestamp value8, Timestamp value9, Integer value10, String value11, BigDecimal value12, String value13, String value14) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void from(IRewardsReport from) {
        setCustomerId(from.getCustomerId());
        setStoreId(from.getStoreId());
        setFirstName(from.getFirstName());
        setLastName(from.getLastName());
        setEmail(from.getEmail());
        setAddressId(from.getAddressId());
        setActivebool(from.getActivebool());
        setCreateDate(from.getCreateDate());
        setLastUpdate(from.getLastUpdate());
        setActive(from.getActive());
        setPassword(from.getPassword());
        setBalance(from.getBalance());
        setAvatarUrl(from.getAvatarUrl());
        setUsername(from.getUsername());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends IRewardsReport> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RewardsReportRecord
     */
    public RewardsReportRecord() {
        super(RewardsReport.REWARDS_REPORT);
    }

    /**
     * Create a detached, initialised RewardsReportRecord
     */
    public RewardsReportRecord(Integer customerId, Short storeId, String firstName, String lastName, String email, Short addressId, Boolean activebool, Timestamp createDate, Timestamp lastUpdate, Integer active, String password, BigDecimal balance, String avatarUrl, String username) {
        super(RewardsReport.REWARDS_REPORT);

        set(0, customerId);
        set(1, storeId);
        set(2, firstName);
        set(3, lastName);
        set(4, email);
        set(5, addressId);
        set(6, activebool);
        set(7, createDate);
        set(8, lastUpdate);
        set(9, active);
        set(10, password);
        set(11, balance);
        set(12, avatarUrl);
        set(13, username);
    }
}
