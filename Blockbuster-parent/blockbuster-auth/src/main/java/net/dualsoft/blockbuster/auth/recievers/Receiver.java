/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.auth.recievers;

import java.util.concurrent.CountDownLatch;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.RentalRequest;
import net.dualsoft.blockbuster.model.DTO.RequestDetails;
import net.dualsoft.blockbuster.model.helper.pojos.RentalRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author teodora
 */
@Component
public class Receiver {
    
    @Autowired private SimpMessagingTemplate messagingTemplate;

    public void receiveMessage(RequestDetails message) {
        messagingTemplate.convertAndSend("/user", message);
    }

}
