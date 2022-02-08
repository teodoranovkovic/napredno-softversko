/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service.impl;

import java.util.List;
import static net.dualsoft.blockbuster.db.jooq.Tables.CATEGORY;
import net.dualsoft.blockbuster.model.DTO.CategoryForList;
import net.dualsoft.blockbuster.model.service.CategoryService;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author teodora
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private DSLContext context;

    @Override
    public List<CategoryForList> getCategories() {
        List<CategoryForList> category = context.selectFrom(CATEGORY).fetchInto(CategoryForList.class);
        return category;
    }

}
