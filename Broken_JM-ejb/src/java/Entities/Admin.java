/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author umangjain
 */
@Entity
@Table(name = "ADMIN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Admin.findAll", query = "SELECT a FROM Admin a")
    , @NamedQuery(name = "Admin.findByAdminid", query = "SELECT a FROM Admin a WHERE a.adminid = :adminid")
    , @NamedQuery(name = "Admin.findByName", query = "SELECT a FROM Admin a WHERE a.name = :name")
    , @NamedQuery(name = "Admin.findByUsername", query = "SELECT a FROM Admin a WHERE a.username = :username")
    , @NamedQuery(name = "Admin.findByPassword", query = "SELECT a FROM Admin a WHERE a.password = :password")})
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ADMINID")
    private Integer adminid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PASSWORD")
    private String password;

    public Admin() {
    }

    public Admin(Integer adminid) {
        this.adminid = adminid;
    }

    public Admin(Integer adminid, String name, String username, String password) {
        this.adminid = adminid;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adminid != null ? adminid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Admin)) {
            return false;
        }
        Admin other = (Admin) object;
        if ((this.adminid == null && other.adminid != null) || (this.adminid != null && !this.adminid.equals(other.adminid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Admin[ adminid=" + adminid + " ]";
    }
    
}
