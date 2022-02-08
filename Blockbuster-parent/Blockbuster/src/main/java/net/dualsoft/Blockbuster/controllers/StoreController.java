/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.Blockbuster.controllers;

import java.util.ArrayList;
import java.util.List;
import net.dualsoft.blockbuster.model.DTO.StoresView;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Store;
import net.dualsoft.blockbuster.model.DTO.StaffView;
import net.dualsoft.blockbuster.model.helper.pojos.RentalRequestModel;
import net.dualsoft.blockbuster.model.helper.pojos.Response;
import net.dualsoft.blockbuster.model.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/stores")
public class StoreController {
    
    @Autowired
    private StoreService storeService;
    
    @GetMapping("")
    public ResponseEntity<List<StoresView>> getStores() {
        List<StoresView> stores = storeService.getStores();
        return new ResponseEntity(stores, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Response<StoresView>> getStore(@PathVariable int id) {
        Response<StoresView> res = storeService.getStore(id);
        if(res.getStatus() != 200){
            return ResponseEntity.status(res.getStatus()).body(null);
        }
        return ResponseEntity.ok(res);
    }
    
    @GetMapping("/local/{id}")
    public ResponseEntity<List<StoresView>> getLocalStores(@PathVariable int id) {
        List<StoresView> stores = storeService.getLocalStores(id);
        return new ResponseEntity(stores, HttpStatus.OK);
    }
    
//    @PostMapping("/setAddress")
//    public ResponseEntity<Object> setAddress(){
//        storeService.addAdreessToStores();
//        return new ResponseEntity(null, HttpStatus.OK);
//    }
    
    @GetMapping("/cities")
    public ResponseEntity<List<String>> getCities(){
        List<String> cities = storeService.getCities().getData();
        return new ResponseEntity(cities,HttpStatus.OK);
    }
    
    @GetMapping("/byCity")
    public ResponseEntity<List<StoresView>> getStoresByCity(@RequestParam String city){
        List<StoresView> stores = storeService.getStoresByCity(city).getData();
        return new ResponseEntity(stores,HttpStatus.OK);
    } 
 
    
    @PutMapping("/editStore")
    public ResponseEntity<StoresView> editStore(@RequestBody StoresView store) {
        StoresView editedStore = storeService.editStore(store).getData();
        return new ResponseEntity(store,HttpStatus.OK);
    }
    
    @GetMapping("/managers")
    public ArrayList<StaffView> getManagers(){
        ArrayList<StaffView> staff = storeService.getFreeManagers();
        return staff;
    }
    
    @PostMapping("/addNewStore")
    public ResponseEntity requestRental(@RequestBody StoresView store) {
        Response res = storeService.addStore(store);
        if (res.getStatus() != 200) {
            return ResponseEntity.status(res.getStatus()).body(res);
        }
        return ResponseEntity.ok().body(res);
    }
}
