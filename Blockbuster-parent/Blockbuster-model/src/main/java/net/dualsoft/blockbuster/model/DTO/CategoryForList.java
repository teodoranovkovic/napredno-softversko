/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.DTO;

/**
 *
 * @author teodora
 */
public class CategoryForList {

    private int categoryId;
    private String name;

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public CategoryForList() {
    }

    public CategoryForList(int categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

}
