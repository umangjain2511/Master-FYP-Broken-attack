/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import EJBs.ProviderRemoteEJB;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author umangjain
 */
@Named(value = "registerProviderMBean")
@RequestScoped
public class RegisterProviderMBean {

    @EJB
    ProviderRemoteEJB provider; 
    
    String name,username,password;
    
    public RegisterProviderMBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void register() {
        provider.registerProvider(name, username, password);
    }
    
}
