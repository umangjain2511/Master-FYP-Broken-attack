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
@Table(name = "JOBS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jobs.findAll", query = "SELECT j FROM Jobs j")
    ,@NamedQuery(name = "Jobs.findAllOpen", query = "SELECT j FROM Jobs j WHERE j.status='Open'")
    ,@NamedQuery(name = "Jobs.findAssJobs", query = "SELECT j FROM Jobs j WHERE j.euname=:username AND j.status='Closed'")
    ,@NamedQuery(name = "Jobs.jobsAssignToMe", query = "SELECT j FROM Jobs j WHERE j.flnames=:username AND j.status='Closed'")
    ,@NamedQuery(name = "Jobs.findPerProv", query = "SELECT j FROM Jobs j WHERE j.euname=:username")
    ,@NamedQuery(name = "Jobs.findOpenPerProv", query = "SELECT j FROM Jobs j WHERE j.euname=:username AND j.status='Open'")    
    , @NamedQuery(name = "Jobs.findByFlnames", query = "SELECT j FROM Jobs j WHERE j.flnames = :flnames")
    , @NamedQuery(name = "Jobs.findByEuname", query = "SELECT j FROM Jobs j WHERE j.euname = :euname")
    , @NamedQuery(name = "Jobs.findByTitle", query = "SELECT j FROM Jobs j WHERE j.title = :title")
    , @NamedQuery(name = "Jobs.findById", query = "SELECT j FROM Jobs j WHERE j.id = :id")
    , @NamedQuery(name = "Jobs.findByPay", query = "SELECT j FROM Jobs j WHERE j.pay = :pay")
    , @NamedQuery(name = "Jobs.findByKeywords", query = "SELECT j FROM Jobs j WHERE j.keywords = :keywords")
    , @NamedQuery(name = "Jobs.findByDescription", query = "SELECT j FROM Jobs j WHERE j.description = :description")
    , @NamedQuery(name = "Jobs.findBySkills", query = "SELECT j FROM Jobs j WHERE j.skills = :skills")
    , @NamedQuery(name = "Jobs.findByStatus", query = "SELECT j FROM Jobs j WHERE j.status = :status")})
public class Jobs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 25)
    @Column(name = "FLNAMES")
    private String flnames;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "EUNAME")
    private String euname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TITLE")
    private String title;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PAY")
    private int pay;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "KEYWORDS")
    private String keywords;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "SKILLS")
    private String skills;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "STATUS")
    private String status;

    public Jobs() {
    }

    public Jobs(Integer id) {
        this.id = id;
    }

    public Jobs(Integer id, String euname, String title, int pay, String keywords, String description, String skills, String status) {
        this.id = id;
        this.euname = euname;
        this.title = title;
        this.pay = pay;
        this.keywords = keywords;
        this.description = description;
        this.skills = skills;
        this.status = status;
    }

    public String getFlnames() {
        return flnames;
    }

    public void setFlnames(String flnames) {
        this.flnames = flnames;
    }

    public String getEuname() {
        return euname;
    }

    public void setEuname(String euname) {
        this.euname = euname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jobs)) {
            return false;
        }
        Jobs other = (Jobs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Jobs[ id=" + id + " ]";
    }
    
}
