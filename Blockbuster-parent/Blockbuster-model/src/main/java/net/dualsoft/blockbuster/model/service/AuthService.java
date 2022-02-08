/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service;

import net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer;
import net.dualsoft.blockbuster.model.helper.pojos.Response;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Staff;
import net.dualsoft.blockbuster.model.helper.pojos.CustomerReg;

/**
 *
 * @author teodora
 */
public interface AuthService {
    
    public Response<Customer> login(String email, String password);
    
    public Response<Customer> register(CustomerReg customer);
    
    public Response<Staff> loginStaff(String email, String password);
    
    public Response changePassword(String code, String newPassword);
    
    public Response editPassword(int customerId, String oldPassword, String newPassword);
    
    public Response getLockedFunds(int customerId);
}
