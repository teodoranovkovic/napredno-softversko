/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service;

import java.util.List;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Customer;
import net.dualsoft.blockbuster.model.DTO.CustomerEditProfile;
import net.dualsoft.blockbuster.model.helper.pojos.Response;

/**
 *
 * @author teodora
 */
public interface CustomerService {

    public Response<Customer> getCustomers();

    public Response<Customer> getCustomer(int id);

    public Response<Customer> getCustomerByEmail(String email);

    public Response addMoney(int id, double amount);

    public Response updateCustomer(CustomerEditProfile customer);

    public Response updateStore(int customerId, int storeId);

    public Response<List<CustomerEditProfile>> getCustomeForPage(int numPage, int numOfCustomersForPage);

    public int getTotalNumOfCustomers();

    public Response updateCustomerBackOffice(CustomerEditProfile customer);

    public Response<List<CustomerEditProfile>> getCustomersBySearchTextForPage(int numPage, int numOfCustomersForPage, String searchText);

    public int getTotalNumOfCustomersBySearchText(String searchText);

}
