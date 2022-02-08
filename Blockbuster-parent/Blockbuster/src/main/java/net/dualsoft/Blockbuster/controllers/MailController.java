/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.Blockbuster.controllers;

import net.dualsoft.blockbuster.model.helper.pojos.Response;
import net.dualsoft.blockbuster.model.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/mail")
public class MailController {
    
    @Autowired
    private MailService mailService;
    
    @GetMapping("/sendSimpleMail")
    public void SendMail() 
    {
        mailService.sendSimpleMessage("peca1998@gmail.com", "First test", "It works");
    }
    
    @PostMapping("/changePassword")
    public ResponseEntity ChangePassword(@RequestBody String email)
    {
        Response res = mailService.sendEmailForPasswordChange(email);
        if(res.getStatus() != 200){
            return ResponseEntity.status(res.getStatus()).body(res);
        }
        return ResponseEntity.ok(res);
    }
    
}
