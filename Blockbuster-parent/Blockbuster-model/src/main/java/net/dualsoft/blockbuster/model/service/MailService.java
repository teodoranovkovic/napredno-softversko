/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service;

import net.dualsoft.blockbuster.model.helper.pojos.Response;
import org.springframework.stereotype.Service;

/**
 *
 * @author teodora
 */
@Service
public interface MailService {
    
    void sendSimpleMessage(String to, String subject, String text);
    
    Response sendEmailForPasswordChange(String email);
    
    public String getEmailByCode(String code);
}
