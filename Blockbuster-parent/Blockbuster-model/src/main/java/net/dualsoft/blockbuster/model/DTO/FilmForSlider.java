/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.DTO;

import java.util.logging.Logger;

/**
 *
 * @author teodora
 */
public class FilmForSlider {

    private int filmId;
    private String title;
    private int releaseYear;
    private String description;
    private String posterUrl;
    private double starRating;

    public FilmForSlider() {
    }

    public FilmForSlider(int filmId, String title, int releaseYear, String description, String poster, int starRating) {
        this.filmId = filmId;
        this.title = title;
        this.releaseYear = releaseYear;
        this.description = description;
        this.posterUrl = posterUrl;
        this.starRating = starRating;
    }

    public int getFilmId() {
        return filmId;
    }

    public String getTitle() {
        return title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public double getStarRating() {
        return starRating;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public void setStarRating(double starRating) {
        this.starRating = starRating;
    }

}
