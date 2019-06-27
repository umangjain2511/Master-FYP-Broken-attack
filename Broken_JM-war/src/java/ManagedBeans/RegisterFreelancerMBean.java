/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import EJBs.FreelancerRemoteEJB;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author umangjain
 */
@Named(value = "registerFreelancerMBean")
@RequestScoped
public class RegisterFreelancerMBean {

    @EJB
    FreelancerRemoteEJB freelance;
    
    String name,username,password,description,skills;
    
    public RegisterFreelancerMBean() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
    
    public void register() {
        freelance.registerFreelancer(name, skills, description, username, password);
    }
    
}
