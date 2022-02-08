/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service.impl;

import java.util.HashMap;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer;
import net.dualsoft.blockbuster.model.helper.pojos.EmailWithCode;
import net.dualsoft.blockbuster.model.helper.pojos.Response;
import net.dualsoft.blockbuster.model.service.CustomerService;
import net.dualsoft.blockbuster.model.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author teodora
 */
@Component
public class MailServiceImpl implements MailService {
    
    @Autowired
    private JavaMailSender emailSender;
    
    @Autowired
    private CustomerService customerService;
    
    private HashMap<String, String> codeMap = new HashMap<>();

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("blockbuster.office@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        
        emailSender.send(message);
    }

    @Override
    public Response sendEmailForPasswordChange(String email) {
        
        Response<Customer> res = customerService.getCustomerByEmail(email);
        if(res.getStatus() != 200){
            return res;
        }
        
        SimpleMailMessage message = new SimpleMailMessage();
        BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        message.setFrom("blockbuster.office@gmail.com");
        message.setTo(email);
        message.setSubject("Password change");
        String code = pwEncoder.encode(email);
        code = code.replace("/", "");
        
        message.setText("To change your password click on this link " + "http://praktikanti.dualsoft.net/change-password/" + code);
        
        codeMap.put(code, email);
        
        emailSender.send(message);
        
        return new Response(null, "Ok", 200);
    }

    @Override
    public String getEmailByCode(String code) {
        return codeMap.get(code);
    }
    
}
