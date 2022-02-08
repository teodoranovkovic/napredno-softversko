/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.DTO;

/**
 *
 * @author teodora
 */
public class ActorForFilm {

    private int filmId;
    private int actorId;
    private String name;

    public ActorForFilm() {
    }

    public ActorForFilm(int filmId, int actorId, String name) {
        this.filmId = filmId;
        this.actorId = actorId;
        this.name = name;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }


    public int getFilmId() {
        return filmId;
    }

    public int getActorId() {
        return actorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    

}
