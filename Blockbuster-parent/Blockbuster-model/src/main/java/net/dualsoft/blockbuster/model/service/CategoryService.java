/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service;

import java.util.List;
import net.dualsoft.blockbuster.model.DTO.CategoryForList;

/**
 *
 * @author teodora
 */
public interface CategoryService {

    public List<CategoryForList> getCategories();

}
