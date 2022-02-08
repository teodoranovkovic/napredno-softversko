/*
 * This file is generated by jOOQ.
 */
package net.dualsoft.blockbuster.db.jooq;


import java.math.BigDecimal;

import javax.annotation.Generated;

import net.dualsoft.blockbuster.db.jooq.tables.Actor;
import net.dualsoft.blockbuster.db.jooq.tables.Address;
import net.dualsoft.blockbuster.db.jooq.tables.Category;
import net.dualsoft.blockbuster.db.jooq.tables.City;
import net.dualsoft.blockbuster.db.jooq.tables.Country;
import net.dualsoft.blockbuster.db.jooq.tables.Customer;
import net.dualsoft.blockbuster.db.jooq.tables.Film;
import net.dualsoft.blockbuster.db.jooq.tables.FilmActor;
import net.dualsoft.blockbuster.db.jooq.tables.FilmCategory;
import net.dualsoft.blockbuster.db.jooq.tables.FilmInStock;
import net.dualsoft.blockbuster.db.jooq.tables.FilmNotInStock;
import net.dualsoft.blockbuster.db.jooq.tables.Inventory;
import net.dualsoft.blockbuster.db.jooq.tables.Language;
import net.dualsoft.blockbuster.db.jooq.tables.Payment;
import net.dualsoft.blockbuster.db.jooq.tables.Rental;
import net.dualsoft.blockbuster.db.jooq.tables.RentalRequest;
import net.dualsoft.blockbuster.db.jooq.tables.RewardsReport;
import net.dualsoft.blockbuster.db.jooq.tables.Staff;
import net.dualsoft.blockbuster.db.jooq.tables.Store;
import net.dualsoft.blockbuster.db.jooq.tables.UserNotification;
import net.dualsoft.blockbuster.db.jooq.tables.records.FilmInStockRecord;
import net.dualsoft.blockbuster.db.jooq.tables.records.FilmNotInStockRecord;
import net.dualsoft.blockbuster.db.jooq.tables.records.RewardsReportRecord;

import org.jooq.Configuration;
import org.jooq.Field;
import org.jooq.Result;


/**
 * Convenience access to all tables in 
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>actor</code>.
     */
    public static final Actor ACTOR = net.dualsoft.blockbuster.db.jooq.tables.Actor.ACTOR;

    /**
     * The table <code>address</code>.
     */
    public static final Address ADDRESS = net.dualsoft.blockbuster.db.jooq.tables.Address.ADDRESS;

    /**
     * The table <code>category</code>.
     */
    public static final Category CATEGORY = net.dualsoft.blockbuster.db.jooq.tables.Category.CATEGORY;

    /**
     * The table <code>city</code>.
     */
    public static final City CITY = net.dualsoft.blockbuster.db.jooq.tables.City.CITY;

    /**
     * The table <code>country</code>.
     */
    public static final Country COUNTRY = net.dualsoft.blockbuster.db.jooq.tables.Country.COUNTRY;

    /**
     * The table <code>customer</code>.
     */
    public static final Customer CUSTOMER = net.dualsoft.blockbuster.db.jooq.tables.Customer.CUSTOMER;

    /**
     * The table <code>film</code>.
     */
    public static final Film FILM = net.dualsoft.blockbuster.db.jooq.tables.Film.FILM;

    /**
     * The table <code>film_actor</code>.
     */
    public static final FilmActor FILM_ACTOR = net.dualsoft.blockbuster.db.jooq.tables.FilmActor.FILM_ACTOR;

    /**
     * The table <code>film_category</code>.
     */
    public static final FilmCategory FILM_CATEGORY = net.dualsoft.blockbuster.db.jooq.tables.FilmCategory.FILM_CATEGORY;

    /**
     * The table <code>film_in_stock</code>.
     */
    public static final FilmInStock FILM_IN_STOCK = net.dualsoft.blockbuster.db.jooq.tables.FilmInStock.FILM_IN_STOCK;

    /**
     * Call <code>film_in_stock</code>.
     */
    public static Result<FilmInStockRecord> FILM_IN_STOCK(Configuration configuration, Integer pFilmId, Integer pStoreId) {
        return configuration.dsl().selectFrom(net.dualsoft.blockbuster.db.jooq.tables.FilmInStock.FILM_IN_STOCK.call(pFilmId, pStoreId)).fetch();
    }

    /**
     * Get <code>film_in_stock</code> as a table.
     */
    public static FilmInStock FILM_IN_STOCK(Integer pFilmId, Integer pStoreId) {
        return net.dualsoft.blockbuster.db.jooq.tables.FilmInStock.FILM_IN_STOCK.call(pFilmId, pStoreId);
    }

    /**
     * Get <code>film_in_stock</code> as a table.
     */
    public static FilmInStock FILM_IN_STOCK(Field<Integer> pFilmId, Field<Integer> pStoreId) {
        return net.dualsoft.blockbuster.db.jooq.tables.FilmInStock.FILM_IN_STOCK.call(pFilmId, pStoreId);
    }

    /**
     * The table <code>film_not_in_stock</code>.
     */
    public static final FilmNotInStock FILM_NOT_IN_STOCK = net.dualsoft.blockbuster.db.jooq.tables.FilmNotInStock.FILM_NOT_IN_STOCK;

    /**
     * Call <code>film_not_in_stock</code>.
     */
    public static Result<FilmNotInStockRecord> FILM_NOT_IN_STOCK(Configuration configuration, Integer pFilmId, Integer pStoreId) {
        return configuration.dsl().selectFrom(net.dualsoft.blockbuster.db.jooq.tables.FilmNotInStock.FILM_NOT_IN_STOCK.call(pFilmId, pStoreId)).fetch();
    }

    /**
     * Get <code>film_not_in_stock</code> as a table.
     */
    public static FilmNotInStock FILM_NOT_IN_STOCK(Integer pFilmId, Integer pStoreId) {
        return net.dualsoft.blockbuster.db.jooq.tables.FilmNotInStock.FILM_NOT_IN_STOCK.call(pFilmId, pStoreId);
    }

    /**
     * Get <code>film_not_in_stock</code> as a table.
     */
    public static FilmNotInStock FILM_NOT_IN_STOCK(Field<Integer> pFilmId, Field<Integer> pStoreId) {
        return net.dualsoft.blockbuster.db.jooq.tables.FilmNotInStock.FILM_NOT_IN_STOCK.call(pFilmId, pStoreId);
    }

    /**
     * The table <code>inventory</code>.
     */
    public static final Inventory INVENTORY = net.dualsoft.blockbuster.db.jooq.tables.Inventory.INVENTORY;

    /**
     * The table <code>language</code>.
     */
    public static final Language LANGUAGE = net.dualsoft.blockbuster.db.jooq.tables.Language.LANGUAGE;

    /**
     * The table <code>payment</code>.
     */
    public static final Payment PAYMENT = net.dualsoft.blockbuster.db.jooq.tables.Payment.PAYMENT;

    /**
     * The table <code>rental</code>.
     */
    public static final Rental RENTAL = net.dualsoft.blockbuster.db.jooq.tables.Rental.RENTAL;

    /**
     * The table <code>rental_request</code>.
     */
    public static final RentalRequest RENTAL_REQUEST = net.dualsoft.blockbuster.db.jooq.tables.RentalRequest.RENTAL_REQUEST;

    /**
     * The table <code>rewards_report</code>.
     */
    public static final RewardsReport REWARDS_REPORT = net.dualsoft.blockbuster.db.jooq.tables.RewardsReport.REWARDS_REPORT;

    /**
     * Call <code>rewards_report</code>.
     */
    public static Result<RewardsReportRecord> REWARDS_REPORT(Configuration configuration, Integer minMonthlyPurchases, BigDecimal minDollarAmountPurchased) {
        return configuration.dsl().selectFrom(net.dualsoft.blockbuster.db.jooq.tables.RewardsReport.REWARDS_REPORT.call(minMonthlyPurchases, minDollarAmountPurchased)).fetch();
    }

    /**
     * Get <code>rewards_report</code> as a table.
     */
    public static RewardsReport REWARDS_REPORT(Integer minMonthlyPurchases, BigDecimal minDollarAmountPurchased) {
        return net.dualsoft.blockbuster.db.jooq.tables.RewardsReport.REWARDS_REPORT.call(minMonthlyPurchases, minDollarAmountPurchased);
    }

    /**
     * Get <code>rewards_report</code> as a table.
     */
    public static RewardsReport REWARDS_REPORT(Field<Integer> minMonthlyPurchases, Field<BigDecimal> minDollarAmountPurchased) {
        return net.dualsoft.blockbuster.db.jooq.tables.RewardsReport.REWARDS_REPORT.call(minMonthlyPurchases, minDollarAmountPurchased);
    }

    /**
     * The table <code>staff</code>.
     */
    public static final Staff STAFF = net.dualsoft.blockbuster.db.jooq.tables.Staff.STAFF;

    /**
     * The table <code>store</code>.
     */
    public static final Store STORE = net.dualsoft.blockbuster.db.jooq.tables.Store.STORE;

    /**
     * The table <code>user_notification</code>.
     */
    public static final UserNotification USER_NOTIFICATION = net.dualsoft.blockbuster.db.jooq.tables.UserNotification.USER_NOTIFICATION;
}
