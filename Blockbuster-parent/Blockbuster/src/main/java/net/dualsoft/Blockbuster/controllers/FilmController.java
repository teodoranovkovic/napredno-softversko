/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.Blockbuster.controllers;

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
import net.dualsoft.blockbuster.model.service.FilmService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/films")
public class FilmController {

    private static final Logger logger = LogManager.getLogger(FilmController.class);

    @Autowired
    private FilmService filmService;

    @GetMapping("")
    public ResponseEntity<List<Film>> getFilms() {
        List<Film> films = filmService.getFilms();
        return new ResponseEntity(films, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> getFilm(@PathVariable int id) {
        Film film = filmService.getFilm(id);
        if (film != null) {
            return new ResponseEntity(film, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity addFilm(@RequestBody FilmToAdd film) {
        logger.info("Staff member attempted to add " + film.getTitle());
        Response res = filmService.addFilm(film);

        if(res.getStatus() != 200){
            return ResponseEntity.status(res.getStatus()).body(res);
        }
//                logger.info("Staff member added " + res.getData().getTitle() + " with id " + res.getFilmId());
        return ResponseEntity.ok(res);
    }

    //Zadaci
    @PatchMapping("/updateRating/{id}")
    public ResponseEntity<Film> updateRating(@PathVariable int id, @RequestParam String rating) {
        Film film = filmService.updateRating(id, rating);
        if (film == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(film, HttpStatus.OK);
    }

    @GetMapping("/oldest")
    public ResponseEntity<List<Film>> getOldestFilms() {
        List<Film> films = filmService.getOldestFilms();
        return ResponseEntity.ok(films);
    }

    @GetMapping("/updatedBetween")
    public ResponseEntity<List<Film>> getFilmUpdatedBetween(@RequestParam Date from, @RequestParam Date to) {
        List<Film> films = filmService.getFilmsUpdatedBetween(from, to);
        return ResponseEntity.ok(films);
    }

    @GetMapping("/filmsForList")
    public List<FilmForList> getFilmsForList() {
        List<FilmForList> films = filmService.getFilmsForList();
        return films;
    }

    @GetMapping("addStarRating")
    public void getRandom() {
        filmService.addStarRating();
    }

    @GetMapping("/filmsForSlider")
    public List<FilmForSlider> getFilmsForSlider() {
        List<FilmForSlider> films = filmService.getTopRatedFilmsForSlider();
        return films;
    }

    @GetMapping("/newFilmsForSlider")
    public List<FilmForSlider> getNewFilmsForSlider() {
        List<FilmForSlider> films = filmService.getNewFilmsForSlider();
        return films;
    }

    @GetMapping("/topRentedForSlider")
    public List<FilmForSlider> getTopRented() {
        List<FilmForSlider> films = filmService.getTopRented(12);
        return films;
    }

    @GetMapping("/suggestion")
    public List<FilmForSlider> getSuggestion(@RequestParam int customerId) {
        List<FilmForSlider> films = filmService.getFilmSuggestion(customerId);
        return films;
    }

    @GetMapping("/actor")
    public List<FilmForList> getFilmsForActor(@RequestParam int id) {
        List<FilmForList> films = filmService.getFilmsForActor(id);
        return films;
    }

    @GetMapping("/category/{id}")
    public List<FilmForList> getFilmsByCategory(@PathVariable int id) {
        List<FilmForList> films = filmService.getFilmsByCategory(id);
        return films;
    }

    @GetMapping("/details")
    public ResponseEntity<Response<FilmDetails>> getFilmDetails(@RequestParam int filmId, @RequestParam int storeId) {
        Response<FilmDetails> film = filmService.getFilmDetails(filmId, storeId);
        if (film.getStatus() != 200) {
            return ResponseEntity.status(film.getStatus()).body(film);
        }
        return ResponseEntity.ok().body(film);
    }

    @GetMapping("category")
    public List<FilmForSlider> getTopRatedByCategory(@RequestParam String category) {
        List<FilmForSlider> films = filmService.getTopRatedByCategory(category);
        return films;
    }

    @GetMapping("util/addFilmsFromMovieDB")
    public ResponseEntity addFilmsFromMovieDB(@RequestParam String title) {
        filmService.addFilmFromMovieDB(title);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("util/addActorsFromMovieDB")
    public ResponseEntity addFilmsFromMovieDB() {
        filmService.addActorsFromMovieDB();
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("filmsForListPage")
    public ResponseEntity filmsForListPage(@RequestParam int page, @RequestParam int count,
            @RequestParam String sort, @RequestParam String filter) {
        if (count == 0) {
            count = 20;
        }
        if (filter.isEmpty()) {
            filter = "All";
        }
        Response<List<FilmsForList>> films = filmService.getFilmsForListPage(page, count, sort, filter);
        return ResponseEntity.ok().body(films);
    }

    @GetMapping("getAddSuggestion")
    public ResponseEntity getAddSuggestion(@RequestParam String searchTerm) {

        Response<List<Film>> films = filmService.getAddSuggestion(searchTerm);

        return ResponseEntity.ok(films);
    }

    @GetMapping("/actor/{id}")
    public List<FilmForList> getFilmsSuggestionForActor(@PathVariable int id) {
        List<FilmForList> films = filmService.getFilmsSuggestionForActor(id);
        return films;
    }

    @GetMapping("getFilmForEdit")
    public ResponseEntity getFilmForEdit(@RequestParam int filmId, @RequestParam int storeId) {
        Response<FilmForEdit> film = filmService.getFilmForEdit(filmId, storeId);
        if (film.getStatus() != 200) {
            return ResponseEntity.status(film.getStatus()).body(null);
        }
        return ResponseEntity.ok(film);
    }

    @PutMapping("edit")
    public ResponseEntity editFilm(@RequestBody FilmForEdit film) {
        logger.info("Staff member attempted to edit film " + film.getFilmId());
        Response<FilmForEdit> res = filmService.editFilm(film);
        if (res.getStatus() != 200) {
            logger.info("Staff member failed to edit film " + film.getFilmId() + ": " + res.getMessage());
            return ResponseEntity.status(res.getStatus()).body(null);
        }
        logger.info("Staff member successfully edited film " + film.getFilmId());
        return ResponseEntity.ok(res);
    }

}
