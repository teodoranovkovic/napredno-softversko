/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import net.dualsoft.blockbuster.db.jooq.Tables;
import static net.dualsoft.blockbuster.db.jooq.Tables.FILM;
import static net.dualsoft.blockbuster.db.jooq.Tables.LANGUAGE;
import static net.dualsoft.blockbuster.db.jooq.Tables.FILM_CATEGORY;
import static net.dualsoft.blockbuster.db.jooq.Tables.CATEGORY;
import static net.dualsoft.blockbuster.db.jooq.Tables.FILM_ACTOR;
import net.dualsoft.blockbuster.db.jooq.enums.MpaaRating;
import static net.dualsoft.blockbuster.db.jooq.tables.Inventory.INVENTORY;
import static net.dualsoft.blockbuster.db.jooq.tables.Rental.RENTAL;
import static net.dualsoft.blockbuster.db.jooq.tables.Actor.ACTOR;
import static net.dualsoft.blockbuster.db.jooq.tables.Store.STORE;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Actor;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Category;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Film;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.FilmCategory;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Language;
import net.dualsoft.blockbuster.db.jooq.tables.records.FilmRecord;
import net.dualsoft.blockbuster.model.DTO.FilmDetails;
import net.dualsoft.blockbuster.model.DTO.FilmForEdit;
import net.dualsoft.blockbuster.model.DTO.FilmForList;
import net.dualsoft.blockbuster.model.DTO.FilmForSlider;
import net.dualsoft.blockbuster.model.DTO.FilmForSuggestion;
import net.dualsoft.blockbuster.model.DTO.FilmToAdd;
import net.dualsoft.blockbuster.model.DTO.FilmsForList;
import net.dualsoft.blockbuster.model.helper.pojos.CategoryCount;
import net.dualsoft.blockbuster.model.helper.RatingConvertor;
import net.dualsoft.blockbuster.model.helper.pojos.ActorRole;
import net.dualsoft.blockbuster.model.helper.pojos.Response;
import net.dualsoft.blockbuster.model.service.FilmService;
import org.jooq.DSLContext;
import org.jooq.JoinType;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Result;
import org.jooq.SelectConditionStep;
import org.jooq.SelectJoinStep;
import org.jooq.SelectOnConditionStep;
import org.jooq.SelectSeekStep1;
import org.jooq.TableField;
import static org.jooq.impl.DSL.count;
import static org.jooq.impl.DSL.nvl;
import org.jooq.tools.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author teodora
 */
//@Transactional(propagation = Propagation.MANDATORY)
@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private DSLContext context;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public Film getFilm(int id) {
        FilmRecord record = context.selectFrom(FILM).where(FILM.FILM_ID.eq(id)).fetchOne();
        if (record == null) {
            return null;
        }
        return record.into(Film.class);
    }

    @Override
    public List<Film> getFilms() {
        List<Film> films = context.selectFrom(FILM).fetchInto(Film.class);
        return films;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Response addFilm(FilmToAdd film) {
        
        List<Integer> exists = context
                .select(FILM.FILM_ID)
                .from(FILM)
                .where(FILM.MOVIEDB_ID.eq(film.getMoviedbId()))
                .fetchInto(Integer.class);
        
        if(!exists.isEmpty()){
            return new Response(null, "Film already exists", 403);
        }

        MpaaRating rating = MpaaRating.PG;

        Film record = context.insertInto(FILM, FILM.TITLE,
                FILM.DESCRIPTION, FILM.RELEASE_YEAR,
                FILM.LANGUAGE_ID, FILM.RENTAL_DURATION,
                FILM.RENTAL_RATE, FILM.LENGTH,
                FILM.REPLACEMENT_COST, FILM.RATING,
                FILM.POSTER_URL, FILM.STAR_RATING, FILM.MOVIEDB_ID)
                .values(film.getTitle(),
                        film.getDescription(), film.getReleaseYear(),
                        (short) 1, film.getRentalDuration(),
                        film.getRentalRate(), film.getLength(),
                        film.getReplacementCost(), rating,
                        film.getPosterUrl(), film.getStarRating(),
                        film.getMoviedbId())
                .returning().fetchOne().into(Film.class);

        context.insertInto(FILM_CATEGORY, FILM_CATEGORY.FILM_ID, FILM_CATEGORY.CATEGORY_ID)
                .values(record.getFilmId().shortValue(), film.getCategoryId().shortValue())
                .execute();

        return new Response(record, "Ok", 200);
    }

    @Override
    public List<Film> getOldestFilms() {
        List<Film> films = context.selectFrom(FILM).orderBy(FILM.RELEASE_YEAR.asc()).limit(10).fetch().into(Film.class);
        return films;
    }

    @Override
    public List<Film> getFilmsUpdatedBetween(Date from, Date to) {
        Timestamp fromTs = new Timestamp(from.getTime());
        Timestamp toTs = new Timestamp(to.getTime());
        List<Film> films = context.selectFrom(FILM).where(FILM.LAST_UPDATE.between(fromTs, toTs)).fetch().into(Film.class);
        return films;
    }

    @Override
    public Film updateRating(int filmId, String rating) {
        FilmRecord record = context.update(FILM)
                .set(FILM.RATING, RatingConvertor.StringToRating(rating))
                .where(FILM.FILM_ID.eq(filmId)).returning().fetchOne();
        if (record == null) {
            return null;
        }
        return record.into(Film.class);
    }

    @Override
    public List<FilmForList> getFilmsForList() {
        List<FilmForList> films = context
                .select(FILM.FILM_ID, FILM.TITLE, FILM.RELEASE_YEAR, FILM.LENGTH, FILM.RATING, FILM.STAR_RATING, FILM.POSTER_URL)
                .select(LANGUAGE.NAME.as("languageName"))
                .select(CATEGORY.NAME.as("categoryName"))
                .from(FILM)
                .leftJoin(LANGUAGE)
                .on(LANGUAGE.LANGUAGE_ID.eq(FILM.FILM_ID))
                .leftJoin(FILM_CATEGORY)
                .on(FILM.FILM_ID.eq(FILM_CATEGORY.FILM_ID.cast(Integer.class)))
                .leftJoin(CATEGORY)
                .on(FILM_CATEGORY.CATEGORY_ID.cast(Integer.class).eq(CATEGORY.CATEGORY_ID))
                .fetchInto(FilmForList.class);

        return films;

    }

    @Override
    public void addStarRating() {
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            double val = 1.0 + (10.0 - 1.0) * rand.nextDouble();
            BigDecimal bd = new BigDecimal(val).setScale(1, RoundingMode.HALF_UP);
            context.update(FILM)
                    .set(FILM.STAR_RATING, bd)
                    .where(FILM.FILM_ID.eq(i + 1))
                    .execute();
        }
    }

    @Override
    public List<FilmForSlider> getTopRatedFilmsForSlider() {
        List<FilmForSlider> films = context
                .select(FILM.FILM_ID, FILM.TITLE, FILM.RELEASE_YEAR, FILM.RATING, FILM.DESCRIPTION, FILM.STAR_RATING, FILM.POSTER_URL)
                .from(FILM)
                .orderBy(FILM.STAR_RATING.desc())
                .limit(12)
                .fetchInto(FilmForSlider.class);
        return films;
    }

    @Override
    public List<FilmForSlider> getTopRented(int count) {
        List<FilmForSlider> films = context
                .select(FILM.FILM_ID, FILM.TITLE, FILM.RELEASE_YEAR, FILM.RATING, FILM.DESCRIPTION, FILM.STAR_RATING, FILM.POSTER_URL)
                .select(count(RENTAL.RENTAL_ID))
                .from(FILM)
                .join(INVENTORY)
                .on(FILM.FILM_ID.eq(INVENTORY.FILM_ID.cast(Integer.class)))
                .join(RENTAL)
                .on(INVENTORY.INVENTORY_ID.eq(RENTAL.INVENTORY_ID))
                .groupBy(FILM.FILM_ID)
                .orderBy(count(RENTAL.RENTAL_ID).desc())
                .limit(count)
                .fetchInto(FilmForSlider.class);
        return films;
    }

    @Override
    public List<FilmForSlider> getNewFilmsForSlider() {
        List<FilmForSlider> films = context
                .select(FILM.FILM_ID, FILM.TITLE, FILM.RELEASE_YEAR, FILM.RATING, FILM.DESCRIPTION, FILM.STAR_RATING, FILM.POSTER_URL)
                .from(FILM)
                .orderBy(FILM.RELEASE_YEAR.desc())
                .limit(12)
                .fetchInto(FilmForSlider.class);
        return films;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<FilmForSlider> getFilmSuggestion(int customerId) {

//        List<FilmForSlider> films = new ArrayList<>();
        List<CategoryCount> categories = context
                .select(CATEGORY.NAME, count().as("rentCount"))
                .from(CATEGORY)
                .join(FILM_CATEGORY).on(FILM_CATEGORY.CATEGORY_ID.cast(Integer.class).eq(CATEGORY.CATEGORY_ID))
                .join(INVENTORY).on(FILM_CATEGORY.FILM_ID.eq(INVENTORY.FILM_ID))
                .join(RENTAL).on(INVENTORY.INVENTORY_ID.eq(RENTAL.INVENTORY_ID.cast(Integer.class)))
                .where(RENTAL.CUSTOMER_ID.cast(Integer.class).eq(customerId))
                .groupBy(CATEGORY.NAME)
                .orderBy(count().desc())
                .limit(3)
                .fetchInto(CategoryCount.class);

        SelectOnConditionStep<Record7<Integer, String, Integer, MpaaRating, String, BigDecimal, String>> step = context
                .select(FILM.FILM_ID, FILM.TITLE, FILM.RELEASE_YEAR,
                        FILM.RATING, FILM.DESCRIPTION, FILM.STAR_RATING, FILM.POSTER_URL)
                .from(FILM)
                .join(FILM_CATEGORY)
                .on(FILM_CATEGORY.FILM_ID.cast(Integer.class).eq(FILM.FILM_ID))
                .join(CATEGORY)
                .on(CATEGORY.CATEGORY_ID.eq(FILM_CATEGORY.CATEGORY_ID.cast(Integer.class)));

        SelectConditionStep<Record7<Integer, String, Integer, MpaaRating, String, BigDecimal, String>> where = null;
        if (categories.size() > 0) {
            where = step.where(CATEGORY.NAME.like(categories.get(0).getName()));
        }
        if (categories.size() > 1) {
            where = where.or(CATEGORY.NAME.like(categories.get(1).getName()));
        }
        if (categories.size() > 2) {
            where = where.or(CATEGORY.NAME.like(categories.get(2).getName()));
        }
        if (categories.size() < 1) {
            where = step.where(true);
        }
        List<FilmForSlider> allFilms = where
                .limit(200)
                .fetchInto(FilmForSlider.class);

        List<Integer> indexes = new ArrayList<>();
        int i = 0;
        while (i < 12) {
            int num = (int) (Math.random() * (double) allFilms.size());
            if (!indexes.contains(num)) {
                indexes.add(num);
                i++;
            }
        }

        List<FilmForSlider> films = new ArrayList<>();
        for (int index : indexes) {
            films.add(allFilms.get(index));
        }

//        int totalRent = 0;
//
//        totalRent = categories.stream().map((category) -> category.getRentCount()).reduce(totalRent, Integer::sum);
//
//        for (CategoryCount category : categories) {
//
//            BigDecimal countBD = new BigDecimal((double) category.getRentCount() / totalRent * 12).setScale(0, RoundingMode.HALF_UP);
//            int count = countBD.intValue();
//
//            films.addAll(
//                    context
//                            .select(FILM.FILM_ID, FILM.TITLE, FILM.RELEASE_YEAR, FILM.RATING, FILM.DESCRIPTION, FILM.STAR_RATING, FILM.POSTER_URL)
//                            .from(FILM)
//                            .join(FILM_CATEGORY)
//                            .on(FILM.FILM_ID.eq(FILM_CATEGORY.FILM_ID.cast(Integer.class)))
//                            .join(CATEGORY)
//                            .on(CATEGORY.CATEGORY_ID.eq(FILM_CATEGORY.CATEGORY_ID.cast(Integer.class)))
//                            .where(CATEGORY.NAME.eq(category.getCategory()))
//                            .orderBy(FILM.STAR_RATING.desc())
//                            .limit(count)
//                            .fetchInto(FilmForSlider.class)
//            );
//        }
        return films;
    }

    @Override
    public List<FilmForSlider> getTopRatedByCategory(String category) {

        List<FilmForSlider> films = context
                .select(FILM.FILM_ID, FILM.TITLE, FILM.RELEASE_YEAR, FILM.RATING, FILM.DESCRIPTION, FILM.STAR_RATING, FILM.POSTER_URL)
                .from(FILM)
                .join(FILM_CATEGORY)
                .on(FILM.FILM_ID.eq(FILM_CATEGORY.FILM_ID.cast(Integer.class)))
                .join(CATEGORY)
                .on(CATEGORY.CATEGORY_ID.eq(FILM_CATEGORY.CATEGORY_ID.cast(Integer.class)))
                .where(CATEGORY.NAME.eq(category))
                .orderBy(FILM.STAR_RATING.desc())
                .limit(12)
                .fetchInto(FilmForSlider.class);
        return films;
    }

    //U slucaju da nije setovan storeId vraca se broj svih dvd-eva
    //Mogao sam poseban servis ali sta da se
    @Override
    public Response<FilmDetails> getFilmDetails(int filmId, int storeId) {
        List<FilmDetails> films = context
                .select(FILM.FILM_ID, FILM.TITLE, FILM.DESCRIPTION,
                        FILM.RELEASE_YEAR, FILM.RENTAL_RATE, FILM.RENTAL_DURATION,
                        FILM.LENGTH, FILM.RATING, FILM.STAR_RATING, FILM.POSTER_URL, FILM.RELEASE_YEAR)
                .select(CATEGORY.NAME.as("category"))
                .select(LANGUAGE.NAME.as("language"))
                .from(FILM)
                .leftJoin(FILM_CATEGORY)
                .on(FILM.FILM_ID.eq(FILM_CATEGORY.FILM_ID.cast(Integer.class)))
                .leftJoin(CATEGORY)
                .on(FILM_CATEGORY.CATEGORY_ID.cast(Integer.class).eq(CATEGORY.CATEGORY_ID))
                .leftJoin(LANGUAGE)
                .on(FILM.LANGUAGE_ID.cast(Integer.class).eq(LANGUAGE.LANGUAGE_ID))
                .where(FILM.FILM_ID.eq(filmId))
                .fetchInto(FilmDetails.class);
        if (films.isEmpty()) {
            return new Response(null, "Film not found", 404);
        }
        FilmDetails film = films.get(0);
        List<ActorRole> actors = context
                .select(ACTOR.ACTOR_ID, ACTOR.NAME, ACTOR.LAST_UPDATE, FILM_ACTOR.CHARACTER)
                .from(FILM)
                .join(FILM_ACTOR)
                .on(FILM.FILM_ID.eq(FILM_ACTOR.FILM_ID.cast(Integer.class)))
                .join(ACTOR)
                .on(FILM_ACTOR.ACTOR_ID.cast(Integer.class).eq(ACTOR.ACTOR_ID))
                .where(FILM.FILM_ID.eq(filmId))
                .fetchInto(ActorRole.class);

        film.setActors(actors);

        SelectConditionStep<Record1<Integer>> query = context.select(count())
                .from(INVENTORY)
                .where(INVENTORY.FILM_ID.cast(Integer.class).eq(filmId));

        if (storeId != 0) {
            query = query.and(INVENTORY.STORE_ID.cast(Integer.class).eq(storeId));
        }

        Integer copies = query.groupBy(INVENTORY.FILM_ID)
                .fetchOneInto(Integer.class);

        query = context.select(nvl(count(), 0))
                .from(INVENTORY)
                .join(RENTAL)
                .on(INVENTORY.INVENTORY_ID.eq(RENTAL.INVENTORY_ID))
                .where(INVENTORY.FILM_ID.cast(Integer.class).eq(filmId));

        if (storeId != 0) {
            query = query.and(INVENTORY.STORE_ID.cast(Integer.class).eq(storeId));
        }

        Integer rented = query.and(RENTAL.RETURN_DATE.isNull())
                .groupBy(INVENTORY.FILM_ID)
                .fetchOneInto(Integer.class);

        if (rented == null) {
            rented = 0;
        }

        if (copies == null) {
            copies = 0;
        }

        film.setAvailable(copies - rented);

        return new Response<>(film, "Ok", 200);
    }

    @Override
    public List<FilmForList> getFilmsForActor(int id) {
        List<FilmForList> films = context
                .select(FILM.FILM_ID, FILM.DESCRIPTION, FILM.TITLE, FILM.RELEASE_YEAR, FILM.LENGTH, FILM.RATING, FILM.STAR_RATING, FILM.POSTER_URL)
                .select(LANGUAGE.NAME.as("languageName"))
                .select(CATEGORY.NAME.as("categoryName"))
                .from(FILM)
                .join(FILM_ACTOR).on(FILM_ACTOR.FILM_ID.cast(Integer.class).eq(FILM.FILM_ID))
                .leftJoin(LANGUAGE).on(LANGUAGE.LANGUAGE_ID.eq(FILM.FILM_ID))
                .leftJoin(FILM_CATEGORY).on(FILM.FILM_ID.eq(FILM_CATEGORY.FILM_ID.cast(Integer.class)))
                .leftJoin(CATEGORY).on(FILM_CATEGORY.CATEGORY_ID.cast(Integer.class).eq(CATEGORY.CATEGORY_ID))
                .where(FILM_ACTOR.ACTOR_ID.cast(Integer.class).eq(id))
                .fetchInto(FilmForList.class);

        return films;
    }

    @Override
    public void addActorsFromMovieDB() {
        List<Integer> moviedbIds = context
                .selectDistinct(FILM.MOVIEDB_ID)
                .from(FILM)
                .except(context
                        .selectDistinct(FILM.MOVIEDB_ID)
                        .from(FILM_ACTOR)
                        .join(FILM).on(FILM.FILM_ID.cast(Short.class).eq(FILM_ACTOR.FILM_ID)))
                .fetchInto(Integer.class);

        for (Integer moviedbId : moviedbIds) {
            JSONObject res = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + moviedbId + "/credits?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb", JSONObject.class);
            ArrayList<LinkedHashMap> cast = (ArrayList<LinkedHashMap>) res.get("cast");
            int i = 0;
            for (LinkedHashMap actor : cast) {
                if(i++ > 10){
                    break;
                }
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
        }
    }

    @Override
    public void addFilmFromMovieDB(String queryTitle) {
        int id = 1;
        int page = 1;
        while (id <= 1000) {
            JSONObject res = restTemplate.getForObject("https://api.themoviedb.org/3/trending/movie/week?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb&page=" + page++, JSONObject.class);
            ArrayList<LinkedHashMap> films = (ArrayList<LinkedHashMap>) res.get("results");
            for (LinkedHashMap film : films) {
                JSONObject details = null;
                try {
                    details = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + film.get("id") + "?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb", JSONObject.class);
                } catch (Exception e) {
                    System.out.println("Film " + film.get("original_title") + " ne radi");
                    continue;
                }
//                System.out.println(details.get("runtime"));

                BigDecimal starRating = new BigDecimal(Double.parseDouble(film.get("vote_average").toString()));
                if (starRating.doubleValue() < 1) {
                    continue;
                }
                ArrayList<Integer> categoryIds = (ArrayList<Integer>) film.get("genre_ids");
                if (categoryIds.isEmpty()) {
                    continue;
                }

                Integer voteCount = (Integer) film.get("vote_count");
                if (voteCount < 100) {
                    continue;
                }
//
                String title = (String) details.get("title");
                String description = (String) film.get("overview");
                String posterUrl = "https://image.tmdb.org/t/p/w500" + (String) film.get("poster_path");
                if (posterUrl == null) {
                    continue;
                }
                Integer releaseYear = Integer.parseInt(((String) film.get("release_date")).substring(0, 4));

                short languageId = 1;
                short rentalDuration = 6;
                BigDecimal rentalRate = new BigDecimal(2.99);
                BigDecimal replacementCost = new BigDecimal(20.99);
                short length = ((Integer) details.get("runtime")).shortValue();
                MpaaRating rating = MpaaRating.PG;

                Integer moviedbId = (Integer) film.get("id");

                int f = context
                        .update(FILM)
                        .set(FILM.TITLE, title)
                        .set(FILM.DESCRIPTION, description)
                        .set(FILM.POSTER_URL, posterUrl)
                        .set(FILM.RELEASE_YEAR, releaseYear)
                        .set(FILM.STAR_RATING, starRating)
                        .set(FILM.LENGTH, length)
                        .set(FILM.MOVIEDB_ID, moviedbId)
                        .where(FILM.FILM_ID.eq(id))
                        .execute();

                Integer categoryId = categoryIds.get(0);

                int c = context
                        .update(FILM_CATEGORY)
                        .set(FILM_CATEGORY.CATEGORY_ID, categoryId.shortValue())
                        .where(FILM_CATEGORY.FILM_ID.cast(Integer.class).eq(id))
                        .execute();

                id++;
            }
        }

    }

    public List<FilmForList> getFilmsByCategory(int categoryId) {
        List<FilmForList> films = context
                .select(FILM.FILM_ID, FILM.TITLE, FILM.RELEASE_YEAR, FILM.LENGTH, FILM.RATING, FILM.STAR_RATING, FILM.POSTER_URL)
                .select(LANGUAGE.NAME.as("languageName"))
                .select(CATEGORY.NAME.as("categoryName"))
                .from(FILM)
                .leftJoin(LANGUAGE)
                .on(LANGUAGE.LANGUAGE_ID.eq(FILM.FILM_ID))
                .leftJoin(FILM_CATEGORY)
                .on(FILM.FILM_ID.eq(FILM_CATEGORY.FILM_ID.cast(Integer.class)))
                .leftJoin(CATEGORY)
                .on(FILM_CATEGORY.CATEGORY_ID.cast(Integer.class).eq(CATEGORY.CATEGORY_ID))
                .where(CATEGORY.CATEGORY_ID.eq(categoryId))
                .fetchInto(FilmForList.class);
        return films;

    }

    @Override
    public Response<List<FilmsForList>> getFilmsForListPage(int page, int count, String sort, String categoryFilter) {
        int offset = (page - 1) * count;
        SelectOnConditionStep<Record> on = context
                .select(FILM.FILM_ID, FILM.TITLE, FILM.RELEASE_YEAR, FILM.LENGTH, FILM.RATING, FILM.STAR_RATING, FILM.POSTER_URL)
                .select(LANGUAGE.NAME.as("languageName"))
                .select(CATEGORY.NAME.as("categoryName"))
                .from(FILM)
                .leftJoin(LANGUAGE)
                .on(LANGUAGE.LANGUAGE_ID.eq(FILM.LANGUAGE_ID.cast(Integer.class)))
                .leftJoin(FILM_CATEGORY)
                .on(FILM.FILM_ID.eq(FILM_CATEGORY.FILM_ID.cast(Integer.class)))
                .leftJoin(CATEGORY)
                .on(FILM_CATEGORY.CATEGORY_ID.cast(Integer.class).eq(CATEGORY.CATEGORY_ID));

        SelectConditionStep<Record> where = null;
        if (!categoryFilter.equals("All")) {
            where = on.where(CATEGORY.NAME.like(categoryFilter));
        } else {
            where = on.where(true);
        }

        SelectSeekStep1 orderBy = null;
        switch (sort) {
            case "best":
                orderBy = where.orderBy(FILM.STAR_RATING.desc());
                break;
            case "worst":
                orderBy = where.orderBy(FILM.STAR_RATING.asc());
                break;
            case "a-z":
                orderBy = where.orderBy(FILM.TITLE.asc());
                break;
            case "z-a":
                orderBy = where.orderBy(FILM.TITLE.desc());
                break;
            case "newest":
                orderBy = where.orderBy(FILM.RELEASE_YEAR.desc());
                break;
            case "oldest":
                orderBy = where.orderBy(FILM.RELEASE_YEAR.asc());
                break;
            default:
                orderBy = where.orderBy(FILM.FILM_ID.asc());
                break;
        }

        List<FilmForList> films = orderBy
                .offset(offset)
                .limit(count)
                .fetchInto(FilmForList.class);

        SelectJoinStep<Record1<Integer>> from = context
                .select(count())
                .from(FILM)
                .leftJoin(FILM_CATEGORY)
                .on(FILM.FILM_ID.eq(FILM_CATEGORY.FILM_ID.cast(Integer.class)))
                .leftJoin(CATEGORY)
                .on(FILM_CATEGORY.CATEGORY_ID.cast(Integer.class).eq(CATEGORY.CATEGORY_ID));

        SelectConditionStep<Record1<Integer>> where1;
        if (!categoryFilter.equals("All")) {
            where1 = from.where(CATEGORY.NAME.like(categoryFilter));
        } else {
            where1 = from.where(true);
        }

        Integer numberOfRecords = where1.
                fetchOne()
                .into(Integer.class);

        FilmsForList filmsWithCount = new FilmsForList();
        filmsWithCount.setFilms(films);
        filmsWithCount.setNumberOfRecords(numberOfRecords);
        return new Response(filmsWithCount, "Ok", 200);
    }

//    @Override
//    public Response<Film> addFilm(Film film) {
//
//    }
    @Override
    @Async
    public Response<List<Film>> getAddSuggestion(String searchTerm) {

        List<FilmToAdd> filmList = new ArrayList<FilmToAdd>();

        JSONObject res = restTemplate.getForObject("http://api.themoviedb.org/3/search/movie?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb&query=" + searchTerm, JSONObject.class);
        ArrayList<LinkedHashMap> films = (ArrayList<LinkedHashMap>) res.get("results");

        for (LinkedHashMap film : films) {

            JSONObject details = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + film.get("id") + "?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb", JSONObject.class);

            FilmToAdd filmForList = new FilmToAdd();

            BigDecimal starRating = new BigDecimal(Double.parseDouble(film.get("vote_average").toString()));
            if (starRating.doubleValue() < 1) {
                continue;
            }
            ArrayList<Integer> categoryIds = (ArrayList<Integer>) film.get("genre_ids");
            if (categoryIds.isEmpty()) {
                continue;
            }

            Integer voteCount = (Integer) film.get("vote_count");
            if (voteCount < 100) {
                continue;
            }

            String title = (String) film.get("title");
            String description = (String) film.get("overview");
            String posterUrl = "https://image.tmdb.org/t/p/w500" + (String) film.get("poster_path");
            if (posterUrl == null) {
                continue;
            }
            Integer releaseYear = Integer.parseInt(((String) film.get("release_date")).substring(0, 4));

            String language = (String) film.get("original_language");

            Integer languageId = context.select(LANGUAGE.LANGUAGE_ID)
                    .from(LANGUAGE)
                    .where(LANGUAGE.NAME.eq(language))
                    .execute();

            if (languageId == 0) {
                context.insertInto(LANGUAGE, LANGUAGE.NAME)
                        .values(language)
                        .execute();
            }

            short rentalDuration = 6;
            BigDecimal rentalRate = new BigDecimal(2.99);
            BigDecimal replacementCost = new BigDecimal(20.99);
            short length = ((Integer) details.get("runtime")).shortValue();
            MpaaRating rating = MpaaRating.PG;
            Integer moviedbId = (Integer) film.get("id");

            Integer categoryId = categoryIds.get(0);

            filmForList.setDescription(description);
            filmForList.setLanguageId(languageId);
            filmForList.setPosterUrl(posterUrl);
            filmForList.setLength(length);
            filmForList.setReleaseYear(releaseYear);
            filmForList.setStarRating(starRating);
            filmForList.setTitle(title);
            filmForList.setCategoryId(categoryId);
            filmForList.setMoviedbId(moviedbId);

            filmList.add(filmForList);
        }

        return new Response(filmList, "OK", 200);
    }

    @Async
    private CompletableFuture<JSONObject> sugestionsToDetails(String id) {

        JSONObject details = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + id + "?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb", JSONObject.class);

        return CompletableFuture.completedFuture(details);
    }

    @Override

    public List<FilmForList> getFilmsSuggestionForActor(int id) {
        List<FilmForList> films = context
                .select(FILM.FILM_ID, FILM.TITLE, FILM.RELEASE_YEAR, FILM.LENGTH, FILM.RATING, FILM.STAR_RATING, FILM.POSTER_URL)
                .select(LANGUAGE.NAME.as("languageName"))
                .select(CATEGORY.NAME.as("categoryName"))
                .from(FILM)
                .leftJoin(LANGUAGE)
                .on(LANGUAGE.LANGUAGE_ID.eq(FILM.FILM_ID))
                .leftJoin(FILM_CATEGORY)
                .on(FILM.FILM_ID.eq(FILM_CATEGORY.FILM_ID.cast(Integer.class)))
                .leftJoin(CATEGORY)
                .on(FILM_CATEGORY.CATEGORY_ID.cast(Integer.class).eq(CATEGORY.CATEGORY_ID))
                .except(context.select(FILM.FILM_ID, FILM.TITLE, FILM.RELEASE_YEAR, FILM.LENGTH, FILM.RATING, FILM.STAR_RATING, FILM.POSTER_URL)
                        .select(LANGUAGE.NAME.as("languageName"))
                        .select(CATEGORY.NAME.as("categoryName"))
                        .from(FILM)
                        .join(FILM_ACTOR).on(FILM_ACTOR.FILM_ID.cast(Integer.class).eq(FILM.FILM_ID))
                        .leftJoin(LANGUAGE).on(LANGUAGE.LANGUAGE_ID.eq(FILM.FILM_ID))
                        .leftJoin(FILM_CATEGORY).on(FILM.FILM_ID.eq(FILM_CATEGORY.FILM_ID.cast(Integer.class)))
                        .leftJoin(CATEGORY).on(FILM_CATEGORY.CATEGORY_ID.cast(Integer.class).eq(CATEGORY.CATEGORY_ID))
                        .where(FILM_ACTOR.ACTOR_ID.cast(Integer.class).eq(id)))
                .fetchInto(FilmForList.class);

        return films;

    }

    public Response<FilmForEdit> getFilmForEdit(int filmId, int storeId) {
        FilmForEdit film = context
                .select(FILM.FILM_ID, FILM.TITLE, FILM.DESCRIPTION, FILM.LANGUAGE_ID,
                        FILM.LENGTH, FILM.POSTER_URL, FILM.RATING, FILM.RELEASE_YEAR,
                        FILM.RENTAL_DURATION, FILM.RENTAL_RATE, FILM.REPLACEMENT_COST,
                        FILM.STAR_RATING, FILM_CATEGORY.CATEGORY_ID)
                .from(FILM)
                .join(FILM_CATEGORY)
                .on(FILM_CATEGORY.FILM_ID.cast(Integer.class).eq(FILM.FILM_ID))
                .where(FILM.FILM_ID.eq(filmId))
                .fetchOne()
                .into(FilmForEdit.class);

        if (film == null) {
            return new Response(null, "Film not found", 404);
        }

        Integer copies = context.select(count())
                .from(INVENTORY)
                .where(INVENTORY.FILM_ID.cast(Integer.class).eq(filmId))
                .and(INVENTORY.STORE_ID.cast(Integer.class).eq(storeId))
                .fetchOneInto(Integer.class);

        Integer rented = context.select(nvl(count(), 0))
                .from(INVENTORY)
                .leftJoin(RENTAL)
                .on(INVENTORY.INVENTORY_ID.eq(RENTAL.INVENTORY_ID))
                .where(INVENTORY.FILM_ID.cast(Integer.class).eq(filmId))
                .and(INVENTORY.STORE_ID.cast(Integer.class).eq(storeId))
                .and(RENTAL.RETURN_DATE.isNull())
                .groupBy(INVENTORY.FILM_ID)
                .fetchOneInto(Integer.class);

        if (rented == null) {
            rented = 0;
        }

        if (copies == null) {
            copies = 0;
        }

        film.setTotalInventory(copies);
        film.setAvailableInventory(copies - rented);

        return new Response(film, "Ok", 200);
    }

    @Override
    public Response<FilmForEdit> editFilm(FilmForEdit film) {

        FilmForEdit editedFilm = context
                .update(FILM)
                .set(FILM.TITLE, film.getTitle())
                .set(FILM.DESCRIPTION, film.getDescription())
                .set(FILM.LANGUAGE_ID, (short) film.getLanguageId())
                .set(FILM.LENGTH, (short) film.getLength())
                .set(FILM.POSTER_URL, film.getPosterUrl())
                .set(FILM.RATING, film.getRating())
                .set(FILM.RELEASE_YEAR, film.getReleaseYear())
                .set(FILM.RENTAL_DURATION, (short) film.getRentalDuration())
                .set(FILM.RENTAL_RATE, film.getRentalRate())
                .set(FILM.REPLACEMENT_COST, film.getReplacementCost())
                .set(FILM.STAR_RATING, film.getStarRating())
                .where(FILM.FILM_ID.eq(film.getFilmId()))
                .returning()
                .fetchOne()
                .into(FilmForEdit.class);

        if (editedFilm == null) {
            return new Response(null, "Error updating movie", 500);
        }

        FilmCategory fCategory = context
                .selectFrom(FILM_CATEGORY)
                .where(FILM_CATEGORY.FILM_ID.eq((short) film.getFilmId()))
                .fetchOne()
                .into(FilmCategory.class);

        //If category changed
        if (fCategory.getCategoryId() != film.getCategoryId()) {
            context
                    .update(FILM_CATEGORY)
                    .set(FILM_CATEGORY.CATEGORY_ID, (short) film.getCategoryId())
                    .where(FILM_CATEGORY.FILM_ID.cast(Integer.class).eq(film.getFilmId()))
                    .execute();
        }

        //Dodati za inventar
        return new Response(editedFilm, "Ok", 200);

    }

    @Override
    public Response<Film> getFilmForAdd(int id) {

        return null;
    }

}
