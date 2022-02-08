/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.Blockbuster.controllers;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Actor;
import net.dualsoft.blockbuster.model.DTO.ActorForFilm;
import net.dualsoft.blockbuster.model.DTO.FilmsForActor;
import net.dualsoft.blockbuster.model.helper.pojos.Response;
import net.dualsoft.blockbuster.model.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author teodora
 */
@RestController
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @Autowired
    private HttpServletResponse response;

    @GetMapping("")
    public List<Actor> getAllActors() {
        List<Actor> actors = actorService.getAllActors();
        return actors;
    }

    @GetMapping("{id}")
    public Actor getActor(@PathVariable int id) {
        Actor actor = actorService.getActor(id);
        if (actor == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return actor;
    }

    @PostMapping("updateActor")
    public Actor updateActor(@RequestBody Actor a) {
        if (actorService.updateActor(a)) {
            return actorService.getActor(a.getActorId());
        }
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return null;
    }

    @PostMapping("")
    public void addActor(@RequestBody String actorName) {
        actorService.addActor(actorName);
    }

    @GetMapping("/getActorForFilm/{id}")
    public List<ActorForFilm> getActorsForFilm(@PathVariable int id) {
        List<ActorForFilm> actors = this.actorService.getActorForFilm(id);
        return actors;
    }

    @GetMapping("/search")
    public List<Actor> getActorsForFilm(@RequestParam String name, @RequestParam int count, @RequestParam int offset) {
        List<Actor> actors = this.actorService.getSearchByName(name, count, offset);
        return actors;
    }

    @GetMapping("/page/{pageNum}/{numOfCust}")
    public ResponseEntity<Response> getCustomerForPage(@PathVariable int pageNum, @PathVariable int numOfCust) {
        Response res = this.actorService.getActorsForPage(pageNum, numOfCust);
        if (res.getStatus() != 200) {
            return ResponseEntity.status(res.getStatus()).body(res);
        }
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/totalNum")
    public int getTotalNumOfCustomers() {
        int res = this.actorService.getTotalNumOfActors();
        return res;
    }

    @PutMapping("/upadateFilmsForActor")
    public Actor upadateFilmsForActor(@RequestBody FilmsForActor a) {

        if (actorService.updateActorForFilms(a)) {
            return actorService.getActor(a.getActorId());
        }
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return null;

    }

    @DeleteMapping("/deleteFilmsForActor/{intFilmId}/{intActirId}")
    public ResponseEntity<Long> deleteFilmsForActor(@PathVariable int intFilmId, @PathVariable int intActirId) {
        System.out.println("FilmID Actorid" + intFilmId + intActirId);
        return this.actorService.deleteFilmForActor(intFilmId, intActirId);
    }

    @GetMapping("/page/{pageNum}/{numOfCust}/{searchText}")
    public ResponseEntity<Response> getActorBySearchTextForPage(@PathVariable int pageNum, @PathVariable int numOfCust, @PathVariable String searchText) {
        Response res = actorService.getActorsBySearchTextForPage(pageNum, numOfCust, searchText);
        if (res.getStatus() != 200) {
            return ResponseEntity.status(res.getStatus()).body(res);
        }
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/totalNum/{searchText}")
    public int getTotalNumOfActorsBySearchText(@PathVariable String searchText) {
        int res = actorService.getTotalNumOfActorsBySearchText(searchText);
        return res;
    }
    
    @GetMapping("/addActorsAndRoles/{moviedbId}")
    public ResponseEntity addActorsAndRoles(@PathVariable int moviedbId){
        actorService.addActorsAndRoles(moviedbId);
        return ResponseEntity.ok(null);
    }
}
