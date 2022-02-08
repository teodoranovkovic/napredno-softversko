/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.Blockbuster.controllers;

import java.util.List;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Inventory;
import net.dualsoft.blockbuster.model.DTO.CategoryForList;
import net.dualsoft.blockbuster.model.DTO.FilmInventoryForStore;
import net.dualsoft.blockbuster.model.helper.pojos.Response;
import net.dualsoft.blockbuster.model.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/add")
    public ResponseEntity<Response> getAddresses(@RequestParam int filmId, @RequestParam int storeId) {
        Response<Inventory> inventory = inventoryService.addInventoryForFilm(filmId, storeId);
        return ResponseEntity.ok(new Response(inventory, "Ok", 200));
    }

    @GetMapping("/filmInventoryPerStore")
    public ResponseEntity<Response<List<FilmInventoryForStore>>> filmInventoryPerStore(@RequestParam int filmId) {
        Response<List<FilmInventoryForStore>> filmInventoryPerStore = inventoryService.filmInventoryPerStore(filmId);
        return ResponseEntity.ok(filmInventoryPerStore);
    }

}
