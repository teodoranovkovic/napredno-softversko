/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service.impl;

import java.util.List;
import net.dualsoft.blockbuster.db.jooq.Tables;
import static net.dualsoft.blockbuster.db.jooq.Tables.ADDRESS;
import static net.dualsoft.blockbuster.db.jooq.Tables.CITY;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Address;
import net.dualsoft.blockbuster.model.DTO.AddressToAdd;
import net.dualsoft.blockbuster.model.service.AddressService;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author teodora
 */
@Component
public class AddressServiceImpl implements AddressService {

    @Autowired
    private DSLContext context;

    @Override
    public List<Address> getAddresses() {
        List<Address> addresses = context.selectFrom(ADDRESS).fetchInto(Address.class);
        return addresses;

    }

    @Override
    public Address getAddress(int id) {
        Address address = new Address();
        address = context.selectFrom(Tables.ADDRESS)
                .where(Tables.ADDRESS.ADDRESS_ID.equal(id))
                .fetchOne()
                .into(Address.class);
        return address;
    }

    @Override
    public Address deleteAddress(int id) {
        Address deletedAddress = this.getAddress(id);
        int delete = context.delete(Tables.ADDRESS)
                .where(Tables.ADDRESS.ADDRESS_ID.equal(id))
                .execute();
        return deletedAddress;
    }

    @Override
    public AddressToAdd addAddress(AddressToAdd address) {
        context.insertInto(Tables.ADDRESS)
                .set(Tables.ADDRESS.ADDRESS_, address.getAddress())
                .set(Tables.ADDRESS.DISTRICT, address.getDistrict())
                .set(Tables.ADDRESS.POSTAL_CODE, address.getPostalCode())
                .set(Tables.ADDRESS.CITY_ID, address.getCityId())
                .returning()
                .fetchOne();
        return address;

    }

    @Override
    public List<Address> getAllAddressesForNoviSad() {

        List<Address> adr = context.select()
                .from(ADDRESS)
                .join(CITY).on(ADDRESS.CITY_ID.eq(CITY.CITY_ID.cast(Short.class)))
                .where(CITY.CITY_.eq("Novi Sad"))
                .fetchInto(Address.class);

        return adr;
    }

    @Override
    public List<Address> getAllAddressesForPostalCode(String postalCode) {

        List<Address> adr = context.select()
                .from(ADDRESS)
                .where(ADDRESS.POSTAL_CODE.eq(postalCode))
                .fetchInto(Address.class);
        return adr;
    }

    @Override
    public AddressToAdd updateAddress(Short id, AddressToAdd address) {
        context.update(Tables.ADDRESS)
                .set(Tables.ADDRESS.ADDRESS_, address.getAddress())
                .set(Tables.ADDRESS.DISTRICT, address.getDistrict())
                .set(Tables.ADDRESS.POSTAL_CODE, address.getPostalCode())
                .set(Tables.ADDRESS.CITY_ID, address.getCityId())
                .where(ADDRESS.ADDRESS_ID.cast(Short.class).eq(id))
                .returning()
                .fetchOne();
        return address;
    }

}
