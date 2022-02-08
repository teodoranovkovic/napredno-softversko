/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.Blockbuster.controllers;

import java.util.List;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Language;
import net.dualsoft.blockbuster.model.helper.pojos.Response;
import net.dualsoft.blockbuster.model.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/languages")
public class LanguageController {
    
    @Autowired
    private LanguageService languageService;

    @GetMapping("/all")
    public ResponseEntity<Response<List<Language>>> getAddresses() {
        Response<List<Language>> languages = this.languageService.getAll();
        return ResponseEntity.ok(languages);
    }
    
}
