/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.Blockbuster.controllers;

import java.util.List;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.City;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Country;
import net.dualsoft.blockbuster.model.DTO.CityCountByCountry;
import net.dualsoft.blockbuster.model.DTO.CountryToAdd;
import net.dualsoft.blockbuster.model.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @Autowired

    @GetMapping("")
    public List<Country> getCountries() {

        return countryService.getCountries();
    }

    @GetMapping("/{id}")
    public Country getCountryById(@PathVariable int id) {

        return countryService.getCountryById(id);
    }

    @PostMapping("/addCountry")
    public void addCountry(@RequestBody CountryToAdd counrty) {

        countryService.addCountry(counrty);
    }

    @DeleteMapping("/deleteCountry/{id}")
    public void deleteCountry(@PathVariable int id) {

        countryService.deleteCountry(id);
    }

    @GetMapping("/getByFirstLetter/{firstLetter}")
    public List<Country> getCountryByFirtLetter(@PathVariable char firstLetter) {

        return countryService.getCountryByFirtLetter(firstLetter);
    }

    @GetMapping("/getCityCount")
    public List<CityCountByCountry> getCityCount() {

        return countryService.getCityCount();
    }
    
    @GetMapping("/getCities/{countryId}")
    public List<City> getCities(@PathVariable short countryId){
        
        return countryService.getCityInCountry(countryId);
    }
}
