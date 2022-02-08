/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service;

import java.util.List;
import net.dualsoft.blockbuster.db.jooq.enums.RequestStatus;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Rental;
import net.dualsoft.blockbuster.model.DTO.CustomerMinimal;
import net.dualsoft.blockbuster.model.DTO.RentalForList;
import net.dualsoft.blockbuster.model.DTO.RequestDetails;
import net.dualsoft.blockbuster.model.helper.pojos.RentalRequestModel;
import net.dualsoft.blockbuster.model.helper.pojos.Response;

/**
 *
 * @author teodora
 */
public interface RentalService {

    public Response<List<RentalForList>> getRentalsForCustomer(int customerId);
    
    public Response addRental(int customerId, int filmId, int storeId);

    public Response requestRental(RentalRequestModel request);
    
    public Response<List<RequestDetails>> getRequests(Integer storeId, String username, String status, int page, int count);
    
    public Response<List<CustomerMinimal>> getCustomersWithRequests(Integer storeId);
    
    public Response setStatus(Integer rentalRequestId, RequestStatus status);
    
    public Response<Boolean> hasUserRequested(int userId, int filmId);
    
    public Response<List<RentalForList>> getRentalsForCustomer(Integer storeId, String username);
    
    public Response returnRental(Integer rentalId);
    
    public Response getRentalRequests(Integer customerId, String status);
}
