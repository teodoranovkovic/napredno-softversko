/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.helper.pojos;

/**
 *
 * @author nikola
 */
public class LoginForm {
    private String email;
    private String password;

    public LoginForm() {
        email = "";
        password = "";
    }
    
    

    public LoginForm(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
