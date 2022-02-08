/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.auth.controllers;

import java.util.List;
import net.dualsoft.blockbuster.db.jooq.enums.RequestStatus;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.RentalRequest;
import net.dualsoft.blockbuster.model.DTO.CustomerMinimal;
import net.dualsoft.blockbuster.model.DTO.RentalForList;
import net.dualsoft.blockbuster.model.DTO.RequestDetails;
import net.dualsoft.blockbuster.model.helper.RabbitUtil;
import net.dualsoft.blockbuster.model.helper.pojos.Response;
import net.dualsoft.blockbuster.model.service.RentalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author teodora
 */
@RestController
@RequestMapping("/rentals")
public class RentalController {

    private static final Logger logger = LogManager.getLogger(RentalController.class);

    @Autowired
    private RentalService rentalService;

    @GetMapping("/requests")
    public ResponseEntity<Response<List<RequestDetails>>> getRentalsForCustomer(@RequestParam Integer storeId, 
            @RequestParam String username, @RequestParam String status,
            @RequestParam int page, @RequestParam int count) {

        Response<List<RequestDetails>> rentals = rentalService.getRequests(storeId, username, status, page, count);
        return ResponseEntity.ok().body(rentals);
    }

    @GetMapping("/customers")
    public ResponseEntity<Response<List<CustomerMinimal>>> getCustomersWithRequests(@RequestParam Integer storeId) {
        Response<List<CustomerMinimal>> customers = rentalService.getCustomersWithRequests(storeId);
        return ResponseEntity.ok().body(customers);
    }

    @GetMapping("/setStatus")
    public ResponseEntity<Response<RentalRequest>> setStatus(@RequestParam Integer rentalRequestId, @RequestParam RequestStatus status) {
        logger.info("Staff member attempted to set status for " + rentalRequestId + " to " + status.getName());
        Response<RentalRequest> res = rentalService.setStatus(rentalRequestId, status);
        if (res.getStatus() != 200) {
            logger.info("Staff member failed to set status for " + rentalRequestId + " to " + status.getName() + ": " + res.getMessage());
            return ResponseEntity.status(res.getStatus()).body(res);
        }
        logger.info("Staff member successfully set status for " + rentalRequestId + " to " + status.getName());
        return ResponseEntity.ok(res);
    }

    @GetMapping("")
    public ResponseEntity getRentals(@RequestParam Integer storeId, @RequestParam String username) {
        Response res = rentalService.getRentalsForCustomer(storeId, username);
        if (res.getStatus() != 200) {
            return ResponseEntity.status(res.getStatus()).body(null);
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("/return/{id}")
    public ResponseEntity returnRental(@PathVariable int id) {
        logger.info("Staff member attempted to return rental for " + id);
        Response res = rentalService.returnRental(id);
        if (res.getStatus() != 200) {
            logger.info("Staff member failed to return rental for " + id + ": " + res.getMessage());
            return ResponseEntity.status(res.getStatus()).body(null);
        }
        logger.info("Staff member successfully returned rental for " + id);
        return ResponseEntity.ok(null);
    }

}
