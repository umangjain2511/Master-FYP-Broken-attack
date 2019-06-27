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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.EJB;
import javax.swing.JOptionPane;

/**
 *
 * @author umangjain
 */
@Named(value = "adminMBean")
@SessionScoped
public class AdminMBean implements Serializable {

    @EJB
    AdminRemoteEJB adm;
    
    private static final String URL = "jdbc:derby://localhost:1527/sample";
    private static final String USER = "app";
    private static final String PASSWD = "app";
    
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
    
   /* public String searchAdminName() {
        String[] data = adm.searchAdminName(username, password);
        if(data[1]=="correct")
            correct=true;
        else
            correct=false;
        String uname = (String) data[0];
        return uname;
    } */
    
    public String validate() {
        String valid=authenticate(username,password);
        return valid;
    }
    
    public String authenticate(String uname, String upass) {
        String returnPage = null;
        String sql = "SELECT username, password FROM ADMIN WHERE username=?";
        try(Connection connect = DriverManager.getConnection(URL,USER,PASSWD);
                PreparedStatement stmt = connect.prepareStatement(sql)) {
            stmt.setString(1, uname);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            String checkUsername = rs.getString("username");
            String checkPassword = rs.getString("password");
            if(checkUsername.equals(uname) && checkPassword.equals(upass))
                returnPage = "admin_home";
            else
                returnPage = "index";
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Message: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "State: " + ex.getSQLState());
        }
        return returnPage;
    }
    
    public String logout() {
      //  username="";
      //  password="";
      return "index";
    }
    
}
