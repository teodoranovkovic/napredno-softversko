/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.Blockbuster.controllers;

import java.util.List;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Staff;
import net.dualsoft.blockbuster.model.helper.StaffAndStoreId;
import net.dualsoft.blockbuster.model.helper.pojos.Response;
import net.dualsoft.blockbuster.model.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author teodora
 */
@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping("/list")
    public ResponseEntity getStaff(){

        Response<List<Staff>> staff = staffService.getStaff();

        return new ResponseEntity(staff,HttpStatus.OK);
    }
    
    @PutMapping("/changeStoreId")
    public void changeStoreId(@RequestBody StaffAndStoreId body) {
        staffService.changeStoreId(body.getStaffId(),body.getStoreId());
    }
}
