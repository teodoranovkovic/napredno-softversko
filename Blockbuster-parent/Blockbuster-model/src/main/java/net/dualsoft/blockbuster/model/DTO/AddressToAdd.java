/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.DTO;

/**
 *
 * @author teodora
 */
public class AddressToAdd {

    private String address;
    private String address2;
    private String district;
    private String postalCode;
    private String phone;
    private Short cityId;

    public AddressToAdd(String address, String address2, String district, String postalCode, String phone, Short cityId) {
        this.address = address;
        this.address2 = address2;
        this.district = district;
        this.postalCode = postalCode;
        this.phone = phone;
        this.cityId = cityId;
    }

    public AddressToAdd() {
    }

    public String getAddress() {
        return address;
    }

    public String getAddress2() {
        return address2;
    }

    public String getDistrict() {
        return district;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public Short getCityId() {
        return cityId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCityId(Short cityId) {
        this.cityId = cityId;
    }

}
