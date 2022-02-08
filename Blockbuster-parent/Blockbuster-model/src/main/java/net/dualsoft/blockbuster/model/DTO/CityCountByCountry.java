/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.DTO;



public class CityCountByCountry {
    
    private String countryName;
    private int cityCount;
    
    public CityCountByCountry(String country, int count){
        
        this.countryName = country;
        this.cityCount = count;
    }
    
    public String getCountryName(){
        
        return this.countryName;
    }
    
    public void setCountryName(String country){
        
        this.countryName = country;
    }
    
    public int getCityCount(){
        
        return this.cityCount;
    }
    
    public void setCityCount(int count){
        
        this.cityCount = count;
    }
}

