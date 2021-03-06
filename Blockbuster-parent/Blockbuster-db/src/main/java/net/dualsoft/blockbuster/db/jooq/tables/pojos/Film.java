/*
 * This file is generated by jOOQ.
 */
package net.dualsoft.blockbuster.db.jooq.tables.pojos;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;

import javax.annotation.Generated;

import net.dualsoft.blockbuster.db.jooq.enums.MpaaRating;
import net.dualsoft.blockbuster.db.jooq.tables.interfaces.IFilm;


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
public class Film implements IFilm {

    private static final long serialVersionUID = -122095528;

    private Integer    filmId;
    private String     title;
    private String     description;
    private Integer    releaseYear;
    private Short      languageId;
    private Short      rentalDuration;
    private BigDecimal rentalRate;
    private Short      length;
    private BigDecimal replacementCost;
    private MpaaRating rating;
    private Timestamp  lastUpdate;
    private String[]   specialFeatures;
    private Object     fulltext;
    private String     posterUrl;
    private BigDecimal starRating;
    private Integer    moviedbId;

    public Film() {}

    public Film(IFilm value) {
        this.filmId = value.getFilmId();
        this.title = value.getTitle();
        this.description = value.getDescription();
        this.releaseYear = value.getReleaseYear();
        this.languageId = value.getLanguageId();
        this.rentalDuration = value.getRentalDuration();
        this.rentalRate = value.getRentalRate();
        this.length = value.getLength();
        this.replacementCost = value.getReplacementCost();
        this.rating = value.getRating();
        this.lastUpdate = value.getLastUpdate();
        this.specialFeatures = value.getSpecialFeatures();
        this.fulltext = value.getFulltext();
        this.posterUrl = value.getPosterUrl();
        this.starRating = value.getStarRating();
        this.moviedbId = value.getMoviedbId();
    }

    public Film(
        Integer    filmId,
        String     title,
        String     description,
        Integer    releaseYear,
        Short      languageId,
        Short      rentalDuration,
        BigDecimal rentalRate,
        Short      length,
        BigDecimal replacementCost,
        MpaaRating rating,
        Timestamp  lastUpdate,
        String[]   specialFeatures,
        Object     fulltext,
        String     posterUrl,
        BigDecimal starRating,
        Integer    moviedbId
    ) {
        this.filmId = filmId;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.languageId = languageId;
        this.rentalDuration = rentalDuration;
        this.rentalRate = rentalRate;
        this.length = length;
        this.replacementCost = replacementCost;
        this.rating = rating;
        this.lastUpdate = lastUpdate;
        this.specialFeatures = specialFeatures;
        this.fulltext = fulltext;
        this.posterUrl = posterUrl;
        this.starRating = starRating;
        this.moviedbId = moviedbId;
    }

    @Override
    public Integer getFilmId() {
        return this.filmId;
    }

    @Override
    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Integer getReleaseYear() {
        return this.releaseYear;
    }

    @Override
    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public Short getLanguageId() {
        return this.languageId;
    }

    @Override
    public void setLanguageId(Short languageId) {
        this.languageId = languageId;
    }

    @Override
    public Short getRentalDuration() {
        return this.rentalDuration;
    }

    @Override
    public void setRentalDuration(Short rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    @Override
    public BigDecimal getRentalRate() {
        return this.rentalRate;
    }

    @Override
    public void setRentalRate(BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }

    @Override
    public Short getLength() {
        return this.length;
    }

    @Override
    public void setLength(Short length) {
        this.length = length;
    }

    @Override
    public BigDecimal getReplacementCost() {
        return this.replacementCost;
    }

    @Override
    public void setReplacementCost(BigDecimal replacementCost) {
        this.replacementCost = replacementCost;
    }

    @Override
    public MpaaRating getRating() {
        return this.rating;
    }

    @Override
    public void setRating(MpaaRating rating) {
        this.rating = rating;
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
    public String[] getSpecialFeatures() {
        return this.specialFeatures;
    }

    @Override
    public void setSpecialFeatures(String... specialFeatures) {
        this.specialFeatures = specialFeatures;
    }


    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be turned off using {@literal <deprecationOnUnknownTypes/>} in your code generator configuration.
     */
    @java.lang.Deprecated
    @Override
    public Object getFulltext() {
        return this.fulltext;
    }


    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be turned off using {@literal <deprecationOnUnknownTypes/>} in your code generator configuration.
     */
    @java.lang.Deprecated
    @Override
    public void setFulltext(Object fulltext) {
        this.fulltext = fulltext;
    }

    @Override
    public String getPosterUrl() {
        return this.posterUrl;
    }

    @Override
    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    @Override
    public BigDecimal getStarRating() {
        return this.starRating;
    }

    @Override
    public void setStarRating(BigDecimal starRating) {
        this.starRating = starRating;
    }

    @Override
    public Integer getMoviedbId() {
        return this.moviedbId;
    }

    @Override
    public void setMoviedbId(Integer moviedbId) {
        this.moviedbId = moviedbId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Film (");

        sb.append(filmId);
        sb.append(", ").append(title);
        sb.append(", ").append(description);
        sb.append(", ").append(releaseYear);
        sb.append(", ").append(languageId);
        sb.append(", ").append(rentalDuration);
        sb.append(", ").append(rentalRate);
        sb.append(", ").append(length);
        sb.append(", ").append(replacementCost);
        sb.append(", ").append(rating);
        sb.append(", ").append(lastUpdate);
        sb.append(", ").append(Arrays.toString(specialFeatures));
        sb.append(", ").append(fulltext);
        sb.append(", ").append(posterUrl);
        sb.append(", ").append(starRating);
        sb.append(", ").append(moviedbId);

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
    public void from(IFilm from) {
        setFilmId(from.getFilmId());
        setTitle(from.getTitle());
        setDescription(from.getDescription());
        setReleaseYear(from.getReleaseYear());
        setLanguageId(from.getLanguageId());
        setRentalDuration(from.getRentalDuration());
        setRentalRate(from.getRentalRate());
        setLength(from.getLength());
        setReplacementCost(from.getReplacementCost());
        setRating(from.getRating());
        setLastUpdate(from.getLastUpdate());
        setSpecialFeatures(from.getSpecialFeatures());
        setFulltext(from.getFulltext());
        setPosterUrl(from.getPosterUrl());
        setStarRating(from.getStarRating());
        setMoviedbId(from.getMoviedbId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends IFilm> E into(E into) {
        into.from(this);
        return into;
    }
}
