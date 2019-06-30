/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import EJBs.JobRemoteEJB;
import EJBs.ProviderRemoteEJB;
import Entities.Freelancers;
import Entities.Jobs;
import Entities.Provider;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.EJB;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author umangjain
 */
@Named(value = "providerMBean")
@SessionScoped
public class ProviderMBean implements Serializable {
    
    private static final String URL = "jdbc:derby://localhost:1527/sample";
    private static final String USER = "app";
    private static final String PASSWD = "app";

    @EJB
    ProviderRemoteEJB provBean;
    
    @EJB
    JobRemoteEJB job;
    
    private String username,password,name,description,title,keywords;
    int pay,jobid;
    private boolean correct=false;
    private String old_password,new_password;
    private boolean passChange=false;
    
    public ProviderMBean() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public int getJobid() {
        return jobid;
    }

    public void setJobid(int jobid) {
        this.jobid = jobid;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public boolean isPassChange() {
        return passChange;
    }

    public void setPassChange(boolean passChange) {
        this.passChange = passChange;
    }
    
    
    
    public List<Provider> searchProviders() {
        return provBean.searchProviders();
    }
    
    public String validate() {
        String valid = authenticate(username,password);
        return valid;
    }
    
    public String authenticate(String uname, String upass) {
        String returnPage = null;
        String sql = "SELECT username, password FROM PROVIDER WHERE username=?";
        try(Connection connect = DriverManager.getConnection(URL,USER,PASSWD);
                PreparedStatement stmt = connect.prepareStatement(sql)) {
            stmt.setString(1, uname);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            String checkUsername = rs.getString("username");
            String checkPassword = rs.getString("password");
            if(checkUsername.equals(uname) && checkPassword.equals(upass))
                returnPage = "employer_home";
            else
                returnPage = "index";
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Message: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "State: " + ex.getSQLState());
        }
        return returnPage;
    }
    
    public void delete(Provider pr) {
        provBean.delete(pr.getUsername());
    }
    
    public List<Jobs> providerJob() {
        return job.searchProviderJob(username);
    }
    
    public void createJob() {
       keywords=keywords.toLowerCase();
       provBean.createJob(title, description, pay, keywords, username);
    }
    
    public List<Jobs> showJobApplicants() {
        return job.showOpenJobs(username);
    }
    
    public List<Freelancers> seeApplicants() {
        return job.seeApplicants(jobid);
    }
    
    public void storeJobId(Jobs j) {
        jobid=j.getId();
    }
    
    public void jobAssign(Freelancers fl) {
        job.jobAssign(jobid, fl.getUsername());
    }
    
    public String logout() {
       // username="";
       // password="";
       return "index";
    }
    
    public List<Jobs> providerAssJob() {
        return job.showAssJobs(username);
    }
    
    public void complete(Jobs j) {
        provBean.complete(j.getId());
    }
    
    public void changePass() {
        passChange=provBean.changePass(old_password, new_password, username);
        if(passChange)
            password=new_password;
    }
               
}
