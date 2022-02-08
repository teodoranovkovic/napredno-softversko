/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.Blockbuster.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer;
import net.dualsoft.blockbuster.model.helper.pojos.LoginForm;
import net.dualsoft.blockbuster.model.helper.pojos.CustomerReg;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Staff;
import net.dualsoft.blockbuster.model.helper.pojos.EditPasswordForm;
import net.dualsoft.blockbuster.model.helper.pojos.PasswordWithCode;
import net.dualsoft.blockbuster.model.helper.pojos.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.dualsoft.blockbuster.model.service.AuthService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    private static final Logger logger = LogManager.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<Response<Customer>> login(@RequestBody LoginForm form) {
        logger.info("User with email " + form.getEmail() + " tried to login");
        Response<Customer> loginRes = authService.login(form.getEmail(), form.getPassword());
        if (loginRes.getData() != null) {
            Customer customer = loginRes.getData();
            ResponseCookie cookie = ResponseCookie.from("email", customer.getEmail())
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .maxAge(3600)
                    .build();
            logger.info("User with email " + form.getEmail() + " successfully logged in");
            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(loginRes);
        }
        logger.info("User with email " + form.getEmail() + " failed to login: " + loginRes.getMessage());
        return ResponseEntity.status(loginRes.getStatus()).body(loginRes);
    }

    @PostMapping("/register")
    public ResponseEntity<Response<Customer>> register(@RequestBody CustomerReg customer) {
        logger.info("User attempted registration with " + customer.getEmail());
        Response<Customer> regResponse = authService.register(customer);
        if (regResponse.getStatus() != 200) {
            logger.info("User failed registration with " + customer.getEmail() + ": " + regResponse.getMessage());
            return ResponseEntity.status(regResponse.getStatus()).body(regResponse);
        }
        if (regResponse.getData() != null) {
            Customer logCustomer = regResponse.getData();
            ResponseCookie cookie = ResponseCookie.from("email", logCustomer.getEmail())
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .maxAge(3600)
                    .build();
            logger.info("User registered successfully with " + customer.getEmail());
            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(regResponse);
        }
        logger.info("User failed registration with " + customer.getEmail() + ": " + regResponse.getMessage());
        return ResponseEntity.status(regResponse.getStatus()).body(regResponse);
    }

    @PostMapping("/loginStaff")
    public ResponseEntity<Response<Staff>> loginStaff(@RequestBody LoginForm form) {
        logger.info("Staff member attempted login with " + form.getEmail());
        Response<Staff> loginRes = authService.loginStaff(form.getEmail(), form.getPassword());
        if (loginRes.getData() != null) {
            Staff staff = loginRes.getData();
            ResponseCookie cookie = ResponseCookie.from("email", staff.getEmail())
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .maxAge(3600)
                    .build();
            logger.info("Staff member successful login with " + form.getEmail());
            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(loginRes);
        }
        logger.info("Staff member failed login with " + form.getEmail() + ": " + loginRes.getMessage());
        return ResponseEntity.status(loginRes.getStatus()).body(loginRes);
    }

    @PostMapping("/changePassword")
    public ResponseEntity changePassword(@RequestBody PasswordWithCode pwc) {
        logger.info("User with code " + pwc.getCode() + " attempted to change password");
        Response res = authService.changePassword(pwc.getCode(), pwc.getPassword());
        if (res.getStatus() != 200) {
            logger.info("User with code " + pwc.getCode() + " failed to change password: " + res.getMessage());
            return ResponseEntity.status(res.getStatus()).body(res);
        }
        logger.info("User with code " + pwc.getCode() + " changed the password successfully");
        return ResponseEntity.ok(res);
    }

    @PostMapping("/editPassword")
    public ResponseEntity editPassword(@RequestBody EditPasswordForm form) {
        logger.info("User  " + form.getCustomerId() + " attempted to edit password");
        Response res = authService
                .editPassword(form.getCustomerId(), form.getOldPassword(), form.getNewPassword());
        if (res.getStatus() != 200) {
            logger.info("User  " + form.getCustomerId() + " failed to edit password: " + res.getMessage());
            return ResponseEntity.status(res.getStatus()).body(res);
        }
        logger.info("User  " + form.getCustomerId() + " edited password successfully");
        return ResponseEntity.ok(res);
    }
    
    @GetMapping("/lockedFunds/{id}")
    public ResponseEntity getLockedFunds(@PathVariable int id){
        Response res = authService.getLockedFunds(id);
        if(res.getStatus() != 200){
            return ResponseEntity.status(res.getStatus()).body(null);
        }
        return ResponseEntity.ok(res);
    }

}
