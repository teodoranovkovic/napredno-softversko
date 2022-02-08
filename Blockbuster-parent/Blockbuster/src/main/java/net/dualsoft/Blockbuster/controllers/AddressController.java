/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.Blockbuster.controllers;

import java.util.List;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Address;
import net.dualsoft.blockbuster.model.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import net.dualsoft.blockbuster.model.DTO.AddressToAdd;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author teodora
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private AddressService addressService;

    @GetMapping("")
    public List<Address> getAddresses() {
        List<Address> addresses = this.addressService.getAddresses();
        return addresses;
    }

    @GetMapping("/{id}")
    public Address getAddress(@PathVariable int id) {
        Address address = this.addressService.getAddress(id);
        if (address == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return address;
        }
        return address;
    }

    @DeleteMapping("/delete/{id}")
    public Address deleteAddress(@PathVariable int id) {
        return this.addressService.deleteAddress(id);
    }

    @PostMapping("/add")
    public AddressToAdd addAddress(@RequestBody AddressToAdd adr) {
        return this.addressService.addAddress(adr);
    }

    @GetMapping("/noviSad")
    public List<Address> getAllAddressesForNoviSad() {
        List<Address> adr = this.addressService.getAllAddressesForNoviSad();
        return adr;
    }

    @GetMapping("/postalCode/{postalCode}")
    public List<Address> getAllAddressesForPostalCode(@PathVariable String postalCode) {
        List<Address> adr = this.addressService.getAllAddressesForPostalCode(postalCode);
        return adr;
    }

    @PatchMapping("/update")
    public AddressToAdd updateAddress(@RequestParam Short id, @RequestBody AddressToAdd adr) {
        return this.addressService.updateAddress(id, adr);
    }

}
