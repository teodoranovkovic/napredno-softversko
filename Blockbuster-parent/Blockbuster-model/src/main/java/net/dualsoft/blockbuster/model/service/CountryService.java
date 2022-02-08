/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service;

import java.util.List;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.City;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Country;
import net.dualsoft.blockbuster.model.DTO.CityCountByCountry;
import net.dualsoft.blockbuster.model.DTO.CountryToAdd;


/**
 *
 * @author teodora
 */

public interface CountryService {
    
    public List<Country> getCountries();
    
    public Country getCountryById(int id);
    
    public boolean addCountry(CountryToAdd country);
    
    public boolean deleteCountry(int id);
    
    public List<Country> getCountryByFirtLetter(char firstLetter);
    
    public List<CityCountByCountry>  getCityCount();
    
    public List<City> getCityInCountry(short countryId);
    
}
