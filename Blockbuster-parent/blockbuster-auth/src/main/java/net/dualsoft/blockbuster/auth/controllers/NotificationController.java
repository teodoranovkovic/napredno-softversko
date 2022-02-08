/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 *
 * @author teodora
 */

@Controller
public class NotificationController {
    
    @Autowired private SimpMessagingTemplate messagingTemplate;
    
    @MessageMapping("/chat")
    public void processMessage(@Payload String message){
        
        //messagingTemplate.convertAndSendToUser("1", "/topic/messages", message);
        messagingTemplate.convertAndSend("/user/topic/messages", message);
    } 
}
