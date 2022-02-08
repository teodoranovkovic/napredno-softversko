/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.Blockbuster.controllers;

import java.util.List;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.UserNotification;

import net.dualsoft.blockbuster.model.helper.pojos.Response;
import net.dualsoft.blockbuster.model.service.UserNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author teodora
 */
@RestController
@RequestMapping("/usersNotification")
public class UserNotificationController {

    @Autowired
    private UserNotificationService userNotificationService;

    @GetMapping("")
    public ResponseEntity<Response> getUsersNotifications() {
        Response<List<UserNotification>> userNotification = this.userNotificationService.getUsersNotification();
        return ResponseEntity.ok(new Response(userNotification, "Ok", 200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getUserNotificationById(@PathVariable int id) {
        Response<UserNotification> userNotification = this.userNotificationService.getUserNotification(id);
        return ResponseEntity.ok(new Response(userNotification, "Ok", 200));
    }

    @GetMapping("read")
    public ResponseEntity<Response> getReadUsersNotifications(int id) {
        Response<List<UserNotification>> userNotification = this.userNotificationService.getReadUsersNotification(id);
        return ResponseEntity.ok(new Response(userNotification, "Ok", 200));
    }

    @GetMapping("unread")
    public ResponseEntity<Response> getUnreadUsersNotifications(int id) {
        Response<List<UserNotification>> userNotification = this.userNotificationService.getUneadUsersNotification(id);
        return ResponseEntity.ok(new Response(userNotification, "Ok", 200));
    }

//    @GetMapping("/customer/{id}")
//    public ResponseEntity getNotificationForCustomer(@PathVariable int id) {
//        Response<List<UserNotification>> userNotification = this.userNotificationService.getNotificationForCustomer(id);
//        return ResponseEntity.ok(userNotification);
//    }
    @PutMapping("/setNotificationToRead/{id}")
    public ResponseEntity setNotificationToRead(@PathVariable int id) {
        Response<List<UserNotification>> userNotification = this.userNotificationService.setNotificationToRead(id);
        return ResponseEntity.ok(userNotification);
    }

    @GetMapping("/customer")
    public ResponseEntity getNotificationForCustomer(@RequestParam int id) {
        Response<List<UserNotification>> userNotification = this.userNotificationService.getNotificationForCustomer(id);
        return ResponseEntity.ok(userNotification);
    }

//    @PostMapping("/insert")
//    public ResponseEntity insertNotification(@RequestParam UserNotification userNotification) {
//        Response<UserNotification> userNotificat = this.userNotificationService.insertNotification(userNotification);
//        return ResponseEntity.ok(userNotificat);
//    }
    @GetMapping("/unread/count/{id}")
    public ResponseEntity unreadCount(@PathVariable int id) {
        Response count = this.userNotificationService.unreadNotificationsCount(id);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/setRead/{id}")
    public ResponseEntity setRead(@PathVariable int id) {
        Response res = this.userNotificationService.readNotification(id);
        if (res.getStatus() != 200) {
            return ResponseEntity.status(res.getStatus()).body(null);
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("/customerOffset")
    public ResponseEntity getNotificationForCustomerOffset(@RequestParam int customerId, @RequestParam int offset) {
        Response<List<UserNotification>> userNotification = this.userNotificationService.getNotificationsForCustomer(customerId, offset);
        return ResponseEntity.ok(userNotification);
    }

    @PostMapping("/insert")
    public ResponseEntity insertNotification(@RequestParam UserNotification userNotification) {
        Response<UserNotification> userNotificat = this.userNotificationService.insertNotification(userNotification);
        return ResponseEntity.ok(userNotificat);
    }
    
    @GetMapping("/readAll/{customerId}")
    public ResponseEntity readAll(@PathVariable Integer customerId){
        userNotificationService.readAllNotifications(customerId);
        return ResponseEntity.ok(null);
    }

}
