/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.Blockbuster.models;

import java.util.concurrent.CountDownLatch;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.RentalRequest;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.UserNotification;
import net.dualsoft.blockbuster.model.DTO.RequestDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author nikola
 */
@Component
public class RentalRequestResponseReceiver {

    //private final CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void receiveMessage(UserNotification message) {
        messagingTemplate.convertAndSend("/mobile", message);
        //latch.countDown();
    }

//    public CountDownLatch getLatch() {
//        return latch;
//    }
}
