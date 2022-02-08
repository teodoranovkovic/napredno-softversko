/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service;

import java.util.ArrayList;
import java.util.List;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.City;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Store;
import net.dualsoft.blockbuster.model.DTO.StaffView;
import net.dualsoft.blockbuster.model.DTO.StoresView;
import net.dualsoft.blockbuster.model.helper.pojos.Response;

/**
 *
 * @author teodora
 */
public interface StoreService {
    
    public List<StoresView> getStores();
    
    public Response<StoresView> getStore(int id);
     
    public List<StoresView> getLocalStores(int id);
     
    //public Response addAdreessToStores();
    
    public Response<List<String>> getCities();

    public Response<List<StoresView>> getStoresByCity(String city);
    
    public Response<StoresView> editStore(StoresView store);

    public ArrayList<StaffView> getFreeManagers();
    
    public Response addStore(StoresView store);
}
