/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import EJBs.AdminRemoteEJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author umangjain
 */
@Named(value = "adminMBean")
@SessionScoped
public class AdminMBean implements Serializable {

    @EJB
    AdminRemoteEJB adm;
    
    String username,name,password;
    boolean correct=false;
    
    public AdminMBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
    
    public String searchAdminName() {
        String[] data = adm.searchAdminName(username, password);
        if(data[1]=="correct")
            correct=true;
        else
            correct=false;
        String uname = (String) data[0];
        return uname;
    }
    
    public void logout() {
        username="";
        password="";
    }
    
}
