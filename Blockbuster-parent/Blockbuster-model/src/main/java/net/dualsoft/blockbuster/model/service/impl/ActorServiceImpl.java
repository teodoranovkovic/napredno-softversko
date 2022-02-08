/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import net.dualsoft.blockbuster.db.jooq.Tables;
import static net.dualsoft.blockbuster.db.jooq.Tables.ACTOR;
import static net.dualsoft.blockbuster.db.jooq.Tables.CATEGORY;
import static net.dualsoft.blockbuster.db.jooq.Tables.FILM;
import static net.dualsoft.blockbuster.db.jooq.Tables.FILM_ACTOR;
import static net.dualsoft.blockbuster.db.jooq.Tables.FILM_CATEGORY;
import static net.dualsoft.blockbuster.db.jooq.Tables.LANGUAGE;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Actor;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer;
import net.dualsoft.blockbuster.model.DTO.ActorForFilm;
import net.dualsoft.blockbuster.model.DTO.FilmForList;
import net.dualsoft.blockbuster.model.DTO.FilmsForActor;
import net.dualsoft.blockbuster.model.helper.pojos.Response;
import net.dualsoft.blockbuster.model.service.ActorService;
import org.jooq.DSLContext;
import org.jooq.Query;
import org.jooq.Record;
import org.jooq.tools.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author teodora
 */
@Component
public class ActorServiceImpl implements ActorService {

    @Autowired
    private DSLContext context;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Actor> getAllActors() {
        List<Actor> actors = context.selectFrom(ACTOR)
                .orderBy(ACTOR.NAME.asc())
                .fetchInto(Actor.class);
        return actors;
    }

    @Override
    public Actor getActor(int id) {
        Record actor = context.selectFrom(ACTOR)
                .where(ACTOR.ACTOR_ID.eq(id))
                .fetchOne();

        if (actor == null) {
            return null;
        } else {
            return actor.into(Actor.class);
        }
    }

//    @Override
//    public boolean updateActor(Actor a, FilmForList[] f) {
//
//        System.out.println("OVO" + a.getActorId());
//        System.out.println(f.length);
//        int brojIzmenjenihRezultata = context.update(ACTOR)
//                .set(ACTOR.FIRST_NAME, a.getFirstName())
//                .set(ACTOR.LAST_NAME, a.getLastName())
//                .where(ACTOR.ACTOR_ID.eq(a.getActorId()))
//                .execute();
//
//        int brRez = 0;
//        for (int i = 0; i < f.length; i++) {
//            brRez = context.insertInto(FILM_ACTOR, FILM_ACTOR.ACTOR_ID, FILM_ACTOR.FILM_ID)
//                    .values(a.getActorId().shortValue(), (short) f[i].getFilmId())
//                    .execute();
//
//        }
//
//        if (brojIzmenjenihRezultata > 0 && brRez > 0) {
//            return true;
//        } else {
//            return false;
//        }
//    }
    @Override
    public boolean updateActor(Actor a) {
        int brojIzmenjenihRezultata = context.update(ACTOR)
                .set(ACTOR.NAME, a.getName())
                .where(ACTOR.ACTOR_ID.eq(a.getActorId()))
                .execute();
        if (brojIzmenjenihRezultata > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateActorForFilms(FilmsForActor filmsForActor) {

        int brojIzmenjenihRezultata = context.update(ACTOR)
                .set(ACTOR.NAME, filmsForActor.getName())
                .where(ACTOR.ACTOR_ID.eq(filmsForActor.getActorId()))
                .execute();

        int brRez = 0;

        System.out.println(filmsForActor.getF().size());
        for (int i = 0; i < filmsForActor.getF().size(); i++) {

            System.out.println(filmsForActor.getF().get(i).getFilmId());
            brRez = context.insertInto(FILM_ACTOR, FILM_ACTOR.ACTOR_ID, FILM_ACTOR.FILM_ID)
                    .values((short) filmsForActor.getActorId(), (short) filmsForActor.getF().get(i).getFilmId())
                    .execute();

        }

        if (brojIzmenjenihRezultata > 0 || brRez > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addActor(String actorName
    ) {
        int brojDodatihGlumaca = context.insertInto(ACTOR, ACTOR.NAME)
                .values(actorName)
                .execute();
        if (brojDodatihGlumaca > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<ActorForFilm> getActorForFilm(int id
    ) {
        List<ActorForFilm> actors = context
                .select(FILM.FILM_ID, FILM.TITLE, FILM.RELEASE_YEAR)
                .select(ACTOR.ACTOR_ID.as("actorId"))
                .select(ACTOR.NAME)
                .from(FILM)
                .leftJoin(FILM_ACTOR)
                .on(FILM_ACTOR.FILM_ID.cast(Integer.class).eq(FILM.FILM_ID))
                .leftJoin(ACTOR)
                .on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID.cast(Integer.class)))
                .where(FILM.FILM_ID.eq(id))
                .fetchInto(ActorForFilm.class);

        return actors;
    }

    @Override
    public List<Actor> getSearchByName(String searchTerm, int count, int offset
    ) {
        String[] terms = searchTerm.split(" ");
        if (terms.length == 1) {
            List<Actor> actors = context.selectFrom(ACTOR)
                    .where(ACTOR.NAME.likeIgnoreCase("%" + terms[0] + "%"))
                    .orderBy(ACTOR.NAME.asc())
                    .limit(count)
                    .offset(offset)
                    .fetchInto(Actor.class);
            return actors;
        } else if (terms.length == 2) {
            List<Actor> actors = context.selectFrom(ACTOR)
                    .where(ACTOR.NAME.likeIgnoreCase("%" + terms[0] + "%")
                            .and(ACTOR.NAME.likeIgnoreCase("%" + terms[1] + "%")))
                    .orderBy(ACTOR.NAME.asc())
                    .limit(count)
                    .offset(offset)
                    .fetchInto(Actor.class);
            return actors;
        }
        return null;
    }

    @Override
    public Response<List<Actor>> getActorsForPage(int numPage, int numOfActorPage) {
        List<Actor> resultAct = new ArrayList<Actor>();
        List<Actor> actors = context.selectFrom(ACTOR)
                .orderBy(ACTOR.ACTOR_ID)
                .fetchInto(Actor.class);

        int totalNumOfActors = actors.size();
        int totalNumOfPages;

        System.out.println(numPage);
        if (totalNumOfActors % numOfActorPage == 0) {
            totalNumOfPages = totalNumOfActors / numOfActorPage;
            System.out.println("totalNumOfPages" + totalNumOfPages);

        } else {
            totalNumOfPages = totalNumOfActors / numOfActorPage + 1;
            System.out.println("totalNumOfPages" + totalNumOfPages);
        }

        System.out.println("totalNumOfPages" + totalNumOfPages);

        if (actors.isEmpty()) {
            return new Response(null, "Customer not found", 404);
        } else {

            if (numPage < totalNumOfPages) {
                for (int i = (numPage - 1) * numOfActorPage; i < (numOfActorPage * (numPage)); i++) {
                    resultAct.add(actors.get(i));
                }
            } else {

                System.out.println("customers");

                for (int i = (numPage - 1) * numOfActorPage; i < totalNumOfActors; i++) {
                    System.out.println("customers" + actors.get(i));
                    resultAct.add(actors.get(i));
                }
            }
        }

        return new Response(resultAct, "Ok", 200);
    }

    @Override
    public Response<List<Actor>> getActorsBySearchTextForPage(int numPage, int numOfActorPage, String searchText) {
        searchText = "%" + searchText + "%";

        List<Actor> resultAct = new ArrayList<Actor>();

        List<Actor> actors = context.selectFrom(ACTOR)
                .where(ACTOR.NAME.likeIgnoreCase(searchText).
                        or(ACTOR.ACTOR_ID.cast(String.class).likeIgnoreCase(searchText)))
                .fetchInto(Actor.class);

        int totalNumOfActors = actors.size();
        int totalNumOfPages;

        System.out.println(numPage);
        if (totalNumOfActors % numOfActorPage == 0) {
            totalNumOfPages = totalNumOfActors / numOfActorPage;
            System.out.println("totalNumOfPages" + totalNumOfPages);

        } else {
            totalNumOfPages = totalNumOfActors / numOfActorPage + 1;
            System.out.println("totalNumOfPages" + totalNumOfPages);
        }

        if (actors.isEmpty()) {
            return new Response(actors, "Customer not found", 200);
        } else {

            if (numPage < totalNumOfPages) {
                for (int i = (numPage - 1) * numOfActorPage; i < (numOfActorPage * (numPage)); i++) {
                    resultAct.add(actors.get(i));

                }
                if (resultAct.isEmpty()) {
                    return new Response(resultAct, "Customer not found", 200);
                }
            } else {

                for (int i = (numPage - 1) * numOfActorPage; i < totalNumOfActors; i++) {
                    System.out.println("customers" + actors.get(i));
                    resultAct.add(actors.get(i));
                }
                if (resultAct.isEmpty()) {
                    return new Response(resultAct, "Customer not found", 200);
                }
            }

        }

        return new Response(resultAct, "Ok", 200);
    }

    @Override
    public int getTotalNumOfActorsBySearchText(String searchText) {
        searchText = "%" + searchText + "%";
        List<Actor> actors = context.selectFrom(ACTOR)
                .where(ACTOR.NAME.likeIgnoreCase(searchText)
                        .or(ACTOR.ACTOR_ID.cast(String.class).likeIgnoreCase(searchText)))
                .fetchInto(Actor.class);
        return actors.size();
    }

    public int getTotalNumOfActors() {
        List<Customer> actors = context.selectFrom(ACTOR).fetchInto(Customer.class);
        return actors.size();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ResponseEntity<Long> deleteFilmForActor(int filmId, int actorId) {

        System.out.println("Film i actor id" + filmId + actorId);
        int isRemoved = context.deleteFrom(Tables.FILM_ACTOR)
                .where(FILM_ACTOR.ACTOR_ID.eq((short) actorId))
                .and(FILM_ACTOR.FILM_ID.eq((short) filmId))
                .execute();

        if (isRemoved < 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @Override
    public Response addActorsAndRoles(int moviedbId) {

        JSONObject res = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + moviedbId + "/credits?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb", JSONObject.class);
        ArrayList<LinkedHashMap> cast = (ArrayList<LinkedHashMap>) res.get("cast");
        for (LinkedHashMap actor : cast) {
            Integer moviedbActorId = (Integer) actor.get("id");
            Integer existing = context
                    .select(ACTOR.ACTOR_ID)
                    .from(ACTOR)
                    .where(ACTOR.MOVIEDB_ID.eq(moviedbActorId))
                    .fetchOneInto(Integer.class);
            if (existing == null) {
                JSONObject actorInfo = restTemplate.getForObject("https://api.themoviedb.org/3/person/" + moviedbActorId + "?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb", JSONObject.class);
                String bio = actorInfo.get("biography").toString();
                if (bio == null || bio.isEmpty()) {
                    continue;
                }
                String name = actorInfo.get("name").toString();
                Object profilePath = actorInfo.get("profile_path");
                String pictureUrl;
                if (profilePath == null) {
                    continue;
                } else {
                    pictureUrl = "https://image.tmdb.org/t/p/w500" + profilePath.toString();
                }
                Actor a = context
                        .insertInto(ACTOR, ACTOR.NAME,
                                ACTOR.MOVIEDB_ID, ACTOR.BIOGRAPHY, ACTOR.PICTURE_URL)
                        .values(name,
                                moviedbActorId, bio, pictureUrl)
                        .returning()
                        .fetchOne()
                        .into(Actor.class);
                existing = a.getActorId();
            }
            String character = actor.get("character").toString();

            Integer filmId = context
                    .select(FILM.FILM_ID)
                    .from(FILM)
                    .where(FILM.MOVIEDB_ID.eq(moviedbId))
                    .fetchOneInto(Integer.class);

            Integer filmActor = context
                    .select(FILM_ACTOR.FILM_ID)
                    .from(FILM_ACTOR)
                    .where(FILM_ACTOR.FILM_ID.cast(Integer.class).eq(filmId))
                    .and(FILM_ACTOR.ACTOR_ID.cast(Integer.class).eq(existing))
                    .fetchOneInto(Integer.class);

            if (filmActor == null) {
                context
                        .insertInto(FILM_ACTOR, FILM_ACTOR.ACTOR_ID, FILM_ACTOR.FILM_ID, FILM_ACTOR.CHARACTER)
                        .values(existing.shortValue(), filmId.shortValue(), character)
                        .execute();
            }

        }
        return new Response(null, "Ok", 200);
    }

}
