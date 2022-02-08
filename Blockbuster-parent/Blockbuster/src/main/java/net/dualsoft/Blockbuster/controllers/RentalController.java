/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.Blockbuster.controllers;

import java.util.List;
import net.dualsoft.blockbuster.db.jooq.enums.RequestStatus;
import net.dualsoft.blockbuster.model.DTO.RentalForList;
import net.dualsoft.blockbuster.model.DTO.RequestDetails;
import net.dualsoft.blockbuster.model.helper.pojos.RentalRequestModel;
import net.dualsoft.blockbuster.model.helper.pojos.Response;
import net.dualsoft.blockbuster.model.service.RentalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rentals")
public class RentalController {

    private static final Logger logger = LogManager.getLogger(RentalController.class);

    @Autowired
    private RentalService rentalService;

    @GetMapping("/{customerId}")
    public ResponseEntity<Response<List<RentalForList>>> getRentalsForCustomer(@PathVariable int customerId) {

        Response<List<RentalForList>> rentals = rentalService.getRentalsForCustomer(customerId);
        return ResponseEntity.ok().body(rentals);
    }

    @GetMapping("/add")
    public ResponseEntity<Response> addRental(@RequestParam int customerId, @RequestParam int filmId, @RequestParam int storeId) {
        logger.info("Attempted to add rental for customer " + customerId + " for film " + filmId);
        Response res = rentalService.addRental(customerId, filmId, storeId);
        if (res.getStatus() != 200) {
            logger.info("Failed to add rental for customer " + customerId + " for film " + filmId + ": " + res.getMessage());
            return ResponseEntity.status(res.getStatus()).body(res);
        }
        logger.info("Successfully added rental for customer " + customerId + " for film " + filmId);
        return ResponseEntity.ok().body(res);
    }

    @PostMapping("/request")
    public ResponseEntity requestRental(@RequestBody RentalRequestModel request) {
        logger.info("User " + request.getCustomerId() + " attempted to request rental for "
                + request.getFilmId() + " in store " + request.getStoreId());
        Response res = rentalService.requestRental(request);
        if (res.getStatus() != 200) {
            logger.info("User " + request.getCustomerId() + " failed to request rental for "
                    + request.getFilmId() + " in store " + request.getStoreId() + ": " + res.getMessage());
            return ResponseEntity.status(res.getStatus()).body(res);
        }
        logger.info("User " + request.getCustomerId() + " successfully rented "
                + request.getFilmId() + " in store " + request.getStoreId());
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/hasUserRequested")
    public ResponseEntity<Response<Boolean>> hasUserRequested(@RequestParam int customerId, @RequestParam int filmId) {

        Response<Boolean> hasRequested = rentalService.hasUserRequested(customerId, filmId);
        return ResponseEntity.ok().body(hasRequested);
    }

    @GetMapping("/requests")
    public ResponseEntity<Response<List<RequestDetails>>> getRentalsForCustomer(@RequestParam Integer storeId, 
            @RequestParam String username, @RequestParam String status) {

        Response<List<RequestDetails>> rentals = rentalService.getRequests(storeId, username, status, 1, 99999);
        return ResponseEntity.ok().body(rentals);
    }

    @GetMapping("requestsNoStore")
    public ResponseEntity getRentalRequests(@RequestParam Integer customerId, @RequestParam String status) {
        Response<List<RequestDetails>> rentals = rentalService.getRentalRequests(customerId, status);
        return ResponseEntity.ok().body(rentals);
    }

    @GetMapping("/setStatus")
    public ResponseEntity<Response> setStatus(@RequestParam Integer rentalRequestId, @RequestParam RequestStatus status) {
        logger.info("User attempted to set status for rental request " + rentalRequestId + " to " + status.getName());
        Response res = rentalService.setStatus(rentalRequestId, status);
        if (res.getStatus() != 200) {
            logger.info("User failed to set status for rental request "
                    + rentalRequestId + " to " + status.getName() + ": " + res.getMessage());
            return ResponseEntity.status(res.getStatus()).body(res);
        }
        logger.info("User successfully set status for rental request " + rentalRequestId + " to " + status.getName());
        return ResponseEntity.ok(res);
    }
}
