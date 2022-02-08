/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.DTO;

import java.util.List;

/**
 *
 * @author teodora
 */
public class FilmsForList {
    private List<FilmForList> films;
    private int numberOfRecords;

    public List<FilmForList> getFilms() {
        return films;
    }

    public void setFilms(List<FilmForList> films) {
        this.films = films;
    }

    public int getNumberOfRecords() {
        return numberOfRecords;
    }

    public void setNumberOfRecords(int numberOfRecords) {
        this.numberOfRecords = numberOfRecords;
    }
    
    
}
