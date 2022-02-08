/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service.impl;

import java.util.List;
import net.dualsoft.blockbuster.db.jooq.Tables;
import static net.dualsoft.blockbuster.db.jooq.Tables.ADDRESS;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Inventory;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Store;
import net.dualsoft.blockbuster.model.DTO.FilmInventoryForStore;
import net.dualsoft.blockbuster.model.helper.pojos.Response;
import net.dualsoft.blockbuster.model.service.InventoryService;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static net.dualsoft.blockbuster.db.jooq.Tables.STORE;
import static net.dualsoft.blockbuster.db.jooq.Tables.FILM;
import static net.dualsoft.blockbuster.db.jooq.Tables.INVENTORY;
import net.dualsoft.blockbuster.model.DTO.StoresView;
import static org.jooq.impl.DSL.count;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author teodora
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private DSLContext context;

    @Override
    public Response<Inventory> addInventoryForFilm(int filmId, int storeId) {
        Inventory inventory = context
                .insertInto(Tables.INVENTORY, Tables.INVENTORY.FILM_ID, Tables.INVENTORY.STORE_ID)
                .values((short) filmId, (short) storeId)
                .returning()
                .fetchOne()
                .into(Inventory.class);

        return new Response<Inventory>(inventory, "Ok", 200);

    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Response<List<FilmInventoryForStore>> filmInventoryPerStore(int filmId) {
        List<FilmInventoryForStore> stores = context
                .select(STORE.STORE_ID, ADDRESS.ADDRESS_, count().as("inventoryCount"))
                .from(INVENTORY)
                .join(STORE).on(STORE.STORE_ID.eq(INVENTORY.STORE_ID.cast(Integer.class)))
                .join(FILM).on(FILM.FILM_ID.eq(INVENTORY.FILM_ID.cast(Integer.class)))
                .join(ADDRESS).on(ADDRESS.ADDRESS_ID.eq(STORE.ADDRESS_ID.cast(Integer.class)))
                .where(FILM.FILM_ID.eq(filmId))
                .groupBy(STORE.STORE_ID, ADDRESS.ADDRESS_)
                .fetchInto(FilmInventoryForStore.class);

        List<FilmInventoryForStore> emptyStores = context
                .select(STORE.STORE_ID, ADDRESS.ADDRESS_)
                .from(STORE)
                .join(ADDRESS).on(ADDRESS.ADDRESS_ID.eq(STORE.ADDRESS_ID.cast(Integer.class)))
                .fetchInto(FilmInventoryForStore.class);

        for (FilmInventoryForStore emptyStore : emptyStores) {
            boolean contains = false;
            for (FilmInventoryForStore store : stores) {
                if (emptyStore.getStoreId() == store.getStoreId()) {
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                FilmInventoryForStore temp = new FilmInventoryForStore();
                temp.setAddress(emptyStore.getAddress());
                temp.setStoreId(emptyStore.getStoreId());
                stores.add(temp);
            }
        }

        return new Response(stores, "Ok", 200);
    }
}
