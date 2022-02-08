/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service.impl;

import java.util.List;
import net.dualsoft.blockbuster.db.jooq.Tables;
import static net.dualsoft.blockbuster.db.jooq.Tables.CITY;
import static net.dualsoft.blockbuster.db.jooq.Tables.COUNTRY;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.City;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Country;
import net.dualsoft.blockbuster.model.DTO.CityCountByCountry;
import net.dualsoft.blockbuster.model.DTO.CountryToAdd;
import net.dualsoft.blockbuster.model.service.CountryService;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.exception.DataAccessException;
import static org.jooq.impl.DSL.count;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author teodora
 */

@Service
public class CountryServiceImpl implements CountryService{

    @Autowired
    private DSLContext context;
    
    @Override
    public List<Country> getCountries() {
        
        return context.selectFrom(COUNTRY).fetchInto(Country.class);        
    }

    @Override
    public Country getCountryById(int id) {
        
        Record country = context.selectFrom(COUNTRY).where(COUNTRY.COUNTRY_ID.eq(id)).fetchOne();
        
        if(country!=null){
            return country.into(Country.class);
        }
        
        return null;
    }

    @Override
    public boolean addCountry(CountryToAdd country) {
        
        int addedCount = context.insertInto(COUNTRY,COUNTRY.COUNTRY_)
                .values(country.getCountryName())
                .execute();
        
        return addedCount > 0;
    }
    
    @Override
    public boolean deleteCountry(int id) {

        int deletedCount;
        
        deletedCount = context.delete(COUNTRY)
                .where(COUNTRY.COUNTRY_ID.equal(id))
                .execute();

        return deletedCount > 0;
    }

    @Override
    public List<Country> getCountryByFirtLetter(char firstLetter) {
        
        String letter = Character.toString(firstLetter);

        List<Country> countries= context.selectFrom(COUNTRY).where(COUNTRY.COUNTRY_.startsWith(letter)).fetchInto(Country.class);
        
        return countries;
    }

    @Override
    public List<CityCountByCountry> getCityCount() {

        List<CityCountByCountry> list =
                context.select(
                        COUNTRY.COUNTRY_,
                        count())
                        .from(COUNTRY)
                        .leftJoin(CITY)
                        .on(CITY.COUNTRY_ID.cast(Integer.class).eq(COUNTRY.COUNTRY_ID))
                        .groupBy(COUNTRY.COUNTRY_)
                        .orderBy(count().desc())
                        .fetchInto(CityCountByCountry.class);
        
        return list;
    }

    @Override
    public List<City> getCityInCountry(short countryId) {
        
        List<City> cities = context
                .selectFrom(CITY)
                .where(CITY.COUNTRY_ID.eq(countryId))
                .fetchInto(City.class);
                
        return cities;
    }
    
    
}
