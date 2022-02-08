/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service;

import java.util.List;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Address;
import net.dualsoft.blockbuster.model.DTO.AddressToAdd;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author teodora
 */
@Service
public interface AddressService {

    public List<Address> getAddresses();

    public Address getAddress(int id);

    public Address deleteAddress(int id);

    public AddressToAdd addAddress(AddressToAdd address);

    public AddressToAdd updateAddress(Short id, AddressToAdd address);

    //Zadaci
    public List<Address> getAllAddressesForNoviSad();

    public List<Address> getAllAddressesForPostalCode(String postalCode);

}
