/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.DTO;

import java.math.BigDecimal;


public class CustomerEditProfile {

    private int customerId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String country;
    private String district;
    private String city;
    private String postalCode;
    private String street;
    private String password;
    private String repassword;
    private String avatarUrl;
    private BigDecimal balance;
    private String address;

    public CustomerEditProfile() {
    }

    public CustomerEditProfile(String firstName, String lastName, String username, String email, String country, String district, String city, String postalCode, String street, String password, String repassword, String avatarUrl, BigDecimal balance, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.country = country;
        this.district = district;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.password = password;
        this.repassword = repassword;
        this.avatarUrl = avatarUrl;
        this.balance = balance;
        this.address = address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setAvatarUrl(String imageUrl) {
        this.avatarUrl = imageUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public String getDistrict() {
        return district;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getStreet() {
        return street;
    }

    public String getPassword() {
        return password;
    }

    public String getRepassword() {
        return repassword;
    }

}
