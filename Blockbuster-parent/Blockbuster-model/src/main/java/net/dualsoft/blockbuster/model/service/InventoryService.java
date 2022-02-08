/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service;

import java.util.List;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Inventory;
import net.dualsoft.blockbuster.model.DTO.FilmInventoryForStore;
import net.dualsoft.blockbuster.model.helper.pojos.Response;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author teodora
 */
public interface InventoryService {
    public Response<Inventory> addInventoryForFilm(int filmId, int storeId);
    
    public Response<List<FilmInventoryForStore>> filmInventoryPerStore(int filmId);
}
