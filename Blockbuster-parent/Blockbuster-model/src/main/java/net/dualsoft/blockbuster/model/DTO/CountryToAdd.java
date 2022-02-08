/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.DTO;



public class CountryToAdd {
    
    private String countryName;
    
    public CountryToAdd(){
        
    }
    
    public CountryToAdd(String countryName){
        
        this.countryName = countryName;
    }
    
    public String getCountryName(){
        
        return this.countryName;
    }
    
    public void setCountryName(String name){
        
        this.countryName = name;
    }
}

