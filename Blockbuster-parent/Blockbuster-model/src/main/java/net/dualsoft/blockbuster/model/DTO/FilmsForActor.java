/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author teodora
 */
public class FilmsForActor {

    private int filmId;
    private int actorId;

    private String name;
    private List<FilmForList> filmsForList = new ArrayList<FilmForList>();

    public void setFilmsForList(List<FilmForList> filmsForList) {
        this.filmsForList = filmsForList;
    }

    public void setFilms(List<FilmForList> films) {
        this.filmsForList = films;
    }

    public FilmsForActor() {
    }

    public FilmsForActor(int filmId, int actorId, String name) {
        this.filmId = filmId;
        this.actorId = actorId;
        this.name = name;
    }

    public int getFilmId() {
        return filmId;
    }

    public int getActorId() {
        return actorId;
    }

    public List<FilmForList> getF() {
        return filmsForList;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    

}
