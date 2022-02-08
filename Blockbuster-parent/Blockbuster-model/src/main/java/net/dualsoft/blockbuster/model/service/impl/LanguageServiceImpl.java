/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service.impl;

import java.util.List;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Language;
import net.dualsoft.blockbuster.model.helper.pojos.Response;
import net.dualsoft.blockbuster.model.service.LanguageService;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import static net.dualsoft.blockbuster.db.jooq.Tables.LANGUAGE;
import org.springframework.stereotype.Service;

/**
 *
 * @author teodora
 */
@Service
public class LanguageServiceImpl implements LanguageService {
    
    @Autowired
    private DSLContext context;

    @Override
    public Response<List<Language>> getAll() {
        List<Language> languages = context
                .selectFrom(LANGUAGE)
                .fetchInto(Language.class);
        return new Response(languages, "Ok", 200);
    }
    
}
