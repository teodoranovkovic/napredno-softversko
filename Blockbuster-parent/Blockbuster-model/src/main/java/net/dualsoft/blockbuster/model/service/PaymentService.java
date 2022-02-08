/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service;

import java.util.List;
import net.dualsoft.blockbuster.model.DTO.PaymentForList;
import net.dualsoft.blockbuster.model.helper.pojos.Response;

/**
 *
 * @author teodora
 */
public interface PaymentService {

    public Response<List<PaymentForList>> getPaymentsForCustomer(int customerId);

    public Response<List<PaymentForList>> getFilteredPaymentsForCustomer(int customerId, String age, String type);
}
