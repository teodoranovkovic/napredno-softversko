/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service;

import java.sql.Date;
import java.util.List;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Film;
import net.dualsoft.blockbuster.model.DTO.FilmDetails;
import net.dualsoft.blockbuster.model.DTO.FilmForEdit;
import net.dualsoft.blockbuster.model.DTO.FilmForList;
import net.dualsoft.blockbuster.model.DTO.FilmForSlider;
import net.dualsoft.blockbuster.model.DTO.FilmToAdd;
import net.dualsoft.blockbuster.model.DTO.FilmsForList;
import net.dualsoft.blockbuster.model.helper.pojos.Response;
import org.springframework.stereotype.Service;

/**
 *
 * @author teodora
 */
public interface FilmService {

    public List<Film> getFilms();

    public Film getFilm(int id);

    public Response addFilm(FilmToAdd film);

    //Zadaci
    public Film updateRating(int filmId, String rating);

    public List<Film> getOldestFilms();

    public List<Film> getFilmsUpdatedBetween(Date from, Date to);

    public List<FilmForList> getFilmsForList();

    public void addStarRating();

    public List<FilmForSlider> getTopRatedFilmsForSlider();

    public List<FilmForSlider> getTopRented(int count);

    public List<FilmForSlider> getNewFilmsForSlider();

    public List<FilmForSlider> getFilmSuggestion(int customerId);

    public List<FilmForList> getFilmsForActor(int id);

    public Response<FilmDetails> getFilmDetails(int filmId, int storeId);

    public List<FilmForSlider> getTopRatedByCategory(String category);

    public void addFilmFromMovieDB(String title);
    
    public void addActorsFromMovieDB();

    public List<FilmForList> getFilmsByCategory(int CategoryId);

    public Response<List<FilmsForList>> getFilmsForListPage(int page, int count, String sort, String filter);

    public Response<List<Film>> getAddSuggestion(String searchTerm);
    
    public Response<Film> getFilmForAdd(int id);
    
    
    
    
    public Response<FilmForEdit> getFilmForEdit(int filmId, int storeId);
    
    public Response<FilmForEdit> editFilm(FilmForEdit film);

    public List<FilmForList> getFilmsSuggestionForActor(int id);

}
