/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service;

import java.util.List;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Actor;
import net.dualsoft.blockbuster.model.DTO.ActorForFilm;
import net.dualsoft.blockbuster.model.DTO.FilmForList;
import net.dualsoft.blockbuster.model.DTO.FilmsForActor;
import net.dualsoft.blockbuster.model.helper.pojos.Response;

import org.jooq.Record;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author teodora
 */
@Service
public interface ActorService {

    public List<Actor> getAllActors();

    public Actor getActor(int id);

    public boolean updateActor(Actor a);

    public boolean addActor(String actorName);

    public List<ActorForFilm> getActorForFilm(int id);

    List<Actor> getSearchByName(String searchTerm, int count, int offset);

    public Response<List<Actor>> getActorsForPage(int numPage, int numOfCustomersForPage);

    public int getTotalNumOfActors();

    public boolean updateActorForFilms(FilmsForActor filmsForActor);

    public ResponseEntity<Long> deleteFilmForActor(int filmId, int actorId);

    public Response<List<Actor>> getActorsBySearchTextForPage(int numPage, int numOfActorPage, String searchText);

    public int getTotalNumOfActorsBySearchText(String searchText);

    public Response addActorsAndRoles(int moviedbId);
}
