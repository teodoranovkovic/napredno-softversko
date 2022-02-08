/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.DTO;

import java.math.BigDecimal;

/**
 *
 * @author teodora
 */
public class RequestDetailsLess {
    private int rentalRequestId;
    private int filmId;
    private String title;
    private int customerId;
    private String status;
    private String createDate;
    private String posterUrl;
    private BigDecimal rentalRate;
    private BigDecimal rentalDuration;

    public int getRentalRequestId() {
        return rentalRequestId;
    }

    public void setRentalRequestId(int rentalRequestId) {
        this.rentalRequestId = rentalRequestId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }

    public BigDecimal getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(BigDecimal rentalDuration) {
        this.rentalDuration = rentalDuration;
    }
    
    
}
