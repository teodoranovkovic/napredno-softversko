package net.dualsoft.Blockbuster.controllers;

import net.dualsoft.Blockbuster.models.MoneyForm;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer;
import net.dualsoft.blockbuster.model.DTO.CustomerEditProfile;
import net.dualsoft.blockbuster.model.DTO.CustomerStore;
import net.dualsoft.blockbuster.model.helper.pojos.Response;
import net.dualsoft.blockbuster.model.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

@RestController
@RequestMapping("/customers")
public class CustomerController {
    
    private static final Logger logger = LogManager.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<Response<Customer>> getCustomer(@PathVariable int id) {
        Response<Customer> customerRes = customerService.getCustomer(id);
        if (customerRes.getData() == null) {
            return ResponseEntity.status(customerRes.getStatus()).body(customerRes);
        }
        return ResponseEntity.ok().body(customerRes);
    }

    @PostMapping("/addMoney")
    public ResponseEntity<Response> addMoney(@RequestBody MoneyForm moneyForm) {
        logger.info("User " + moneyForm.getId() + " attempted to add " + moneyForm.getAmount());
        Response res = customerService.addMoney(moneyForm.getId(), moneyForm.getAmount());
        if (res.getStatus() != 200) {
            logger.info("User " + moneyForm.getId() + " failed to add money: " + res.getMessage());
            return ResponseEntity.status(res.getStatus()).body(res);
        }
        logger.info("User " + moneyForm.getId() + " added " + moneyForm.getAmount());
        return ResponseEntity.ok().body(res);
    }

    @PostMapping("/updateCustomer")
    public ResponseEntity<Response> updateCustomer(@RequestBody CustomerEditProfile cust) {
        logger.info("User " + cust.getCustomerId() + " attempted to edit profile");
        Response res = customerService.updateCustomer(cust);
        if (res.getStatus() != 200) {
            logger.info("User " + cust.getCustomerId() + " failed to edit profile: " + res.getMessage());
            return ResponseEntity.status(res.getStatus()).body(res);
        }
        logger.info("User " + cust.getCustomerId() + " successfully edited profile");
        return ResponseEntity.ok().body(res);
    }

    @PostMapping("/updateStore")
    public ResponseEntity<Response> updateStore(@RequestBody CustomerStore cs) {
        logger.info("User " + cs.getCustomerId() + " attempted to update their store to " + cs.getStoreId());
        Response res = customerService.updateStore(cs.getCustomerId(), cs.getStoreId());
        if (res.getStatus() != 200) {
            logger.info("User " + cs.getCustomerId() + " failed to update their store: " + res.getMessage());
            return ResponseEntity.status(res.getStatus()).body(res);
        }
        logger.info("User " + cs.getCustomerId() + " successfully updated their store to " + cs.getStoreId());
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllCustomer() {
        Response res = customerService.getCustomers();
        if (res.getStatus() != 200) {
            return ResponseEntity.status(res.getStatus()).body(res);
        }
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/page/{pageNum}/{numOfCust}")
    public ResponseEntity<Response> getCustomerForPage(@PathVariable int pageNum, @PathVariable int numOfCust) {
        Response res = customerService.getCustomeForPage(pageNum, numOfCust);
        if (res.getStatus() != 200) {
            return ResponseEntity.status(res.getStatus()).body(res);
        }
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/totalNum")
    public int getTotalNumOfCustomers() {
        int res = customerService.getTotalNumOfCustomers();
        return res;
    }

    @PostMapping("/updateCustomerBackOffice")
    public ResponseEntity<Response> updateCustomerBackOffice(@RequestBody CustomerEditProfile cust) {
        Response res = customerService.updateCustomerBackOffice(cust);
        if (res.getStatus() != 200) {
            return ResponseEntity.status(res.getStatus()).body(res);
        }
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/page/{pageNum}/{numOfCust}/{searchText}")
    public ResponseEntity<Response> getCustomerBySearchTextForPage(@PathVariable int pageNum, @PathVariable int numOfCust, @PathVariable String searchText) {
        Response res = customerService.getCustomersBySearchTextForPage(pageNum, numOfCust, searchText);
        if (res.getStatus() != 200) {
            return ResponseEntity.status(res.getStatus()).body(res);
        }
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/totalNum/{searchText}")
    public int getTotalNumOfCustomersBySearchText(@PathVariable String searchText) {
        int res = customerService.getTotalNumOfCustomersBySearchText(searchText);
        return res;
    }

}
