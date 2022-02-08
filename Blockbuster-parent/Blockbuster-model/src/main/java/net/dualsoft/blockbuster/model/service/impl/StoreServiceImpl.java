/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static net.dualsoft.blockbuster.db.jooq.Tables.ADDRESS;
import static net.dualsoft.blockbuster.db.jooq.Tables.CITY;
import static net.dualsoft.blockbuster.db.jooq.Tables.COUNTRY;
import static net.dualsoft.blockbuster.db.jooq.Tables.CUSTOMER;
import static net.dualsoft.blockbuster.db.jooq.Tables.STORE;
import static net.dualsoft.blockbuster.db.jooq.Tables.STAFF;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Address;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.City;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Store;
import net.dualsoft.blockbuster.model.DTO.StaffView;
import net.dualsoft.blockbuster.model.DTO.StoresView;
import net.dualsoft.blockbuster.model.helper.pojos.Response;
import net.dualsoft.blockbuster.model.service.StaffService;
import net.dualsoft.blockbuster.model.service.StoreService;
import org.jooq.DSLContext;
import org.jooq.tools.json.JSONArray;
import org.jooq.tools.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author teodora
 */
@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private DSLContext context;

    @Autowired
    private StaffService staffService;

    private String apiKey = "7e39e83d1c8d8807224312b1c238d832";
//    @Autowired
//    private RestTemplate restTemplate;

    @Override
    public List<StoresView> getStores() {

        List<StoresView> stores = context
                .select(STORE.STORE_ID, ADDRESS.ADDRESS_ID, ADDRESS.DISTRICT, ADDRESS.ADDRESS_, ADDRESS.POSTAL_CODE, CITY.CITY_, COUNTRY.COUNTRY_, ADDRESS.LATITUDE, ADDRESS.LONGITUDE, ADDRESS.CITY_ID, CITY.COUNTRY_ID)
                .from(STORE)
                .join(ADDRESS).on(STORE.ADDRESS_ID.eq(ADDRESS.ADDRESS_ID.cast(Short.class)))
                .join(CITY).on(ADDRESS.CITY_ID.eq(CITY.CITY_ID.cast(Short.class)))
                .join(COUNTRY).on(CITY.COUNTRY_ID.eq(COUNTRY.COUNTRY_ID.cast(Short.class)))
                .fetchInto(StoresView.class);

        for (StoresView store : stores) {
            List<StaffView> list = store.getStaff();
            list = staffService.getStaffForStore(store.getStoreId());
            store.setStaff(list);
        }

        return stores;
    }

    @Override
    public List<StoresView> getLocalStores(int id) {

        List<StoresView> stores = context
                .select(STORE.STORE_ID, ADDRESS.ADDRESS_, ADDRESS.POSTAL_CODE, CITY.CITY_, COUNTRY.COUNTRY_, ADDRESS.LATITUDE, ADDRESS.LONGITUDE)
                .from(STORE)
                .join(ADDRESS)
                .on(STORE.ADDRESS_ID.eq(ADDRESS.ADDRESS_ID.cast(Short.class)))
                .join(CITY)
                .on(ADDRESS.CITY_ID.eq(CITY.CITY_ID.cast(Short.class)))
                .join(COUNTRY)
                .on(CITY.COUNTRY_ID.eq(COUNTRY.COUNTRY_ID.cast(Short.class)))
                .where(CITY.CITY_ID
                        .eq(context
                                .select(ADDRESS.CITY_ID.cast(Integer.class))
                                .from(ADDRESS)
                                .join(CUSTOMER)
                                .on(CUSTOMER.ADDRESS_ID.eq(ADDRESS.ADDRESS_ID.cast(Short.class)))
                                .where(CUSTOMER.CUSTOMER_ID.cast(Integer.class).eq(id))
                                .fetchOneInto(Integer.class)))
                .fetchInto(StoresView.class);

        return stores;
    }

//    @Override
//    public Response addAdreessToStores() {
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        List<Store> stores = context
//                .selectFrom(STORE)
//                .fetchInto(Store.class);
//
//        for (Store store : stores) {
//
//            JSONObject addresses = restTemplate.getForObject("http://api.positionstack.com/v1/reverse?access_key=" + apiKey + "&query=" + store.getLatitude() + "," + store.getLongitude(), JSONObject.class);
//            ArrayList<LinkedHashMap> addr = (ArrayList<LinkedHashMap>) addresses.get("data");
//
//            String adresa = addr.get(0).get("label").toString();
//
//            int res = context
//                    .update(STORE)
//                    .set(STORE.ADDRESS, adresa)
//                    .where(STORE.STORE_ID.eq(store.getStoreId()))
//                    .execute();
//            if (res == 0) {
//                return new Response(null, "Error updating store", 500);
//            }
//
//        }
//        return new Response(null, "Ok", 200);
//    }
    @Override
    public Response<List<String>> getCities() {

        List<String> cities = context
                .selectDistinct(CITY.CITY_)
                .from(STORE)
                .join(ADDRESS)
                .on(STORE.ADDRESS_ID.eq(ADDRESS.ADDRESS_ID.cast(Short.class)))
                .join(CITY)
                .on(ADDRESS.CITY_ID.eq(CITY.CITY_ID.cast(Short.class)))
                .fetchInto(String.class);

        return new Response(cities, "OK", 200);
    }

    @Override
    public Response<List<StoresView>> getStoresByCity(String city) {

        List<StoresView> stores = context
                .select(STORE.STORE_ID, ADDRESS.ADDRESS_, ADDRESS.POSTAL_CODE, CITY.CITY_, COUNTRY.COUNTRY_, ADDRESS.LATITUDE, ADDRESS.LONGITUDE)
                .from(STORE)
                .join(ADDRESS)
                .on(STORE.ADDRESS_ID.eq(ADDRESS.ADDRESS_ID.cast(Short.class)))
                .join(CITY)
                .on(ADDRESS.CITY_ID.eq(CITY.CITY_ID.cast(Short.class)))
                .join(COUNTRY)
                .on(CITY.COUNTRY_ID.eq(COUNTRY.COUNTRY_ID.cast(Short.class)))
                .where(CITY.CITY_.eq(city))
                .fetchInto(StoresView.class);

        for (StoresView store : stores) {
            List<StaffView> list = store.getStaff();
            list = staffService.getStaffForStore(store.getStoreId());
            store.setStaff(list);
        }

        return new Response(stores, "OK", 200);
    }

    @Override
    public Response<StoresView> editStore(StoresView store) {

        StoresView editedStore = new StoresView();

        Address adr = context
                .insertInto(ADDRESS, ADDRESS.ADDRESS_, ADDRESS.POSTAL_CODE, ADDRESS.CITY_ID, ADDRESS.LATITUDE, ADDRESS.LONGITUDE, ADDRESS.DISTRICT)
                .values(store.getAddress(), store.getPostalCode(), store.getCityId(), store.getLatitude(), store.getLongitude(), store.getDistrict())
                .returning().fetchOne().into(Address.class);

        if (adr == null) {
            return new Response(null, "Address not inserted", 500);
        }

        int res = context
                .update(STORE)
                .set(STORE.ADDRESS_ID, adr.getAddressId().shortValue())
                .where(STORE.STORE_ID.eq(store.getStoreId()))
                .execute();

        if (res == 0) {
            return new Response(null, "Address change in store error", 500);
        }

//        editedStore.setAddress(adr.getAddress());
//        editedStore.setAddressId(adr.getAddressId().shortValue());
        return new Response(editedStore, "OK", 200);

    }

    @Override
    public ArrayList<StaffView> getFreeManagers() {
        List<Integer> freeManagers = context
                .select(STAFF.STAFF_ID.cast(Short.class))
                .from(STAFF)
                .except(
                        context
                                .select(STORE.MANAGER_STAFF_ID)
                                .from(STORE))
                .fetchInto(Integer.class);

        ArrayList<StaffView> staff = new ArrayList<StaffView>();

        for (Integer freeManager : freeManagers) {
            staff.add(staffService.getStaffWithId(freeManager));
        }

        return staff;
    }

    @Override
    public Response addStore(StoresView store) {

        Address adr = context
                .insertInto(ADDRESS, ADDRESS.ADDRESS_, ADDRESS.POSTAL_CODE, ADDRESS.CITY_ID, ADDRESS.LATITUDE, ADDRESS.LONGITUDE, ADDRESS.DISTRICT)
                .values(store.getAddress(), store.getPostalCode(), store.getCityId(), store.getLatitude(), store.getLongitude(), store.getDistrict())
                .returning().fetchOne().into(Address.class);

        if (adr == null) {
            return new Response(null, "Address not inserted", 500);
        }

        StoresView res = context
                .insertInto(STORE, STORE.MANAGER_STAFF_ID, STORE.ADDRESS_ID)
                .values(store.getManagerStaffId(), adr.getAddressId().shortValue())
                .returning().fetchOne().into(StoresView.class);

        if (res == null) {
            return new Response(null, "Address change in store error", 500);
        }

        return new Response(null, "OK", 200);
    }

    @Override
    public Response<StoresView> getStore(int id) {
        StoresView store = context
                .select(STORE.STORE_ID, ADDRESS.ADDRESS_ID, ADDRESS.DISTRICT, ADDRESS.ADDRESS_, ADDRESS.POSTAL_CODE, CITY.CITY_, COUNTRY.COUNTRY_, ADDRESS.LATITUDE, ADDRESS.LONGITUDE, ADDRESS.CITY_ID, CITY.COUNTRY_ID)
                .from(STORE)
                .join(ADDRESS).on(STORE.ADDRESS_ID.eq(ADDRESS.ADDRESS_ID.cast(Short.class)))
                .join(CITY).on(ADDRESS.CITY_ID.eq(CITY.CITY_ID.cast(Short.class)))
                .join(COUNTRY).on(CITY.COUNTRY_ID.eq(COUNTRY.COUNTRY_ID.cast(Short.class)))
                .where(STORE.STORE_ID.eq(id))
                .fetchOne()
                .into(StoresView.class);

        if (store == null) {
            return new Response(null, "Store not found", 404);
        }

        return new Response(store, "OK", 200);
    }

}
