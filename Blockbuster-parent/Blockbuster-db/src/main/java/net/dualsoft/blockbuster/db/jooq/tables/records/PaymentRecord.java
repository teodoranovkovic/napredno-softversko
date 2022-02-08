/*
 * This file is generated by jOOQ.
 */
package net.dualsoft.blockbuster.db.jooq.tables.records;


import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.annotation.Generated;

import net.dualsoft.blockbuster.db.jooq.tables.Payment;
import net.dualsoft.blockbuster.db.jooq.tables.interfaces.IPayment;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
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
public class PaymentRecord extends UpdatableRecordImpl<PaymentRecord> implements Record7<Integer, Short, Short, Integer, BigDecimal, Timestamp, String>, IPayment {

    private static final long serialVersionUID = 198714676;

    /**
     * Setter for <code>payment.payment_id</code>.
     */
    @Override
    public void setPaymentId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>payment.payment_id</code>.
     */
    @Override
    public Integer getPaymentId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>payment.customer_id</code>.
     */
    @Override
    public void setCustomerId(Short value) {
        set(1, value);
    }

    /**
     * Getter for <code>payment.customer_id</code>.
     */
    @Override
    public Short getCustomerId() {
        return (Short) get(1);
    }

    /**
     * Setter for <code>payment.staff_id</code>.
     */
    @Override
    public void setStaffId(Short value) {
        set(2, value);
    }

    /**
     * Getter for <code>payment.staff_id</code>.
     */
    @Override
    public Short getStaffId() {
        return (Short) get(2);
    }

    /**
     * Setter for <code>payment.rental_id</code>.
     */
    @Override
    public void setRentalId(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>payment.rental_id</code>.
     */
    @Override
    public Integer getRentalId() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>payment.amount</code>.
     */
    @Override
    public void setAmount(BigDecimal value) {
        set(4, value);
    }

    /**
     * Getter for <code>payment.amount</code>.
     */
    @Override
    public BigDecimal getAmount() {
        return (BigDecimal) get(4);
    }

    /**
     * Setter for <code>payment.payment_date</code>.
     */
    @Override
    public void setPaymentDate(Timestamp value) {
        set(5, value);
    }

    /**
     * Getter for <code>payment.payment_date</code>.
     */
    @Override
    public Timestamp getPaymentDate() {
        return (Timestamp) get(5);
    }

    /**
     * Setter for <code>payment.type</code>.
     */
    @Override
    public void setType(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>payment.type</code>.
     */
    @Override
    public String getType() {
        return (String) get(6);
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
    // Record7 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Integer, Short, Short, Integer, BigDecimal, Timestamp, String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Integer, Short, Short, Integer, BigDecimal, Timestamp, String> valuesRow() {
        return (Row7) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Payment.PAYMENT.PAYMENT_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Short> field2() {
        return Payment.PAYMENT.CUSTOMER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Short> field3() {
        return Payment.PAYMENT.STAFF_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field4() {
        return Payment.PAYMENT.RENTAL_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<BigDecimal> field5() {
        return Payment.PAYMENT.AMOUNT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field6() {
        return Payment.PAYMENT.PAYMENT_DATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return Payment.PAYMENT.TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getPaymentId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Short component2() {
        return getCustomerId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Short component3() {
        return getStaffId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component4() {
        return getRentalId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal component5() {
        return getAmount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component6() {
        return getPaymentDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component7() {
        return getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getPaymentId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Short value2() {
        return getCustomerId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Short value3() {
        return getStaffId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value4() {
        return getRentalId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal value5() {
        return getAmount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value6() {
        return getPaymentDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PaymentRecord value1(Integer value) {
        setPaymentId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PaymentRecord value2(Short value) {
        setCustomerId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PaymentRecord value3(Short value) {
        setStaffId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PaymentRecord value4(Integer value) {
        setRentalId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PaymentRecord value5(BigDecimal value) {
        setAmount(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PaymentRecord value6(Timestamp value) {
        setPaymentDate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PaymentRecord value7(String value) {
        setType(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PaymentRecord values(Integer value1, Short value2, Short value3, Integer value4, BigDecimal value5, Timestamp value6, String value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void from(IPayment from) {
        setPaymentId(from.getPaymentId());
        setCustomerId(from.getCustomerId());
        setStaffId(from.getStaffId());
        setRentalId(from.getRentalId());
        setAmount(from.getAmount());
        setPaymentDate(from.getPaymentDate());
        setType(from.getType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends IPayment> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PaymentRecord
     */
    public PaymentRecord() {
        super(Payment.PAYMENT);
    }

    /**
     * Create a detached, initialised PaymentRecord
     */
    public PaymentRecord(Integer paymentId, Short customerId, Short staffId, Integer rentalId, BigDecimal amount, Timestamp paymentDate, String type) {
        super(Payment.PAYMENT);

        set(0, paymentId);
        set(1, customerId);
        set(2, staffId);
        set(3, rentalId);
        set(4, amount);
        set(5, paymentDate);
        set(6, type);
    }
}
