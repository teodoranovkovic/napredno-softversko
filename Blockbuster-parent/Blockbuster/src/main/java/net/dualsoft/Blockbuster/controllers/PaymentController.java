/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.Blockbuster.controllers;

import java.util.List;
import net.dualsoft.blockbuster.model.DTO.RentalForList;
import net.dualsoft.blockbuster.model.DTO.PaymentForList;
import net.dualsoft.blockbuster.model.helper.pojos.Response;
import net.dualsoft.blockbuster.model.service.PaymentService;
import net.dualsoft.blockbuster.model.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author teodora
 */
@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("")
    public ResponseEntity<Response<List<PaymentForList>>> getTransactionsForCustomer(@RequestParam int customerId) {
        Response<List<PaymentForList>> transactions = paymentService.getPaymentsForCustomer(customerId);
        return ResponseEntity.ok().body(transactions);

    }

    @GetMapping("/filtered")
    public ResponseEntity<Response<List<PaymentForList>>> getFilteredTransactionsForCustomer(@RequestParam int customerId, @RequestParam String age, @RequestParam String type) {
        Response<List<PaymentForList>> transactions = paymentService.getFilteredPaymentsForCustomer(customerId, age, type);
        return ResponseEntity.ok().body(transactions);

    }
}
