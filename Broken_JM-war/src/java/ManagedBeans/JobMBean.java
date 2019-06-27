/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import EJBs.JobRemoteEJB;
import Entities.Jobs;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author umangjain
 */
@Named(value = "jobMBean")
@SessionScoped
public class JobMBean implements Serializable {

    @EJB
    JobRemoteEJB job;
    
    private String description,title,search,keywords;
    private int id,pay;
    private boolean isOpen;
    
    public JobMBean() {
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

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public boolean isIsOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
    
    public List<Jobs> searchJobs() {
        search=search.toLowerCase();
        return job.searchJobs(search);
    }
    
    public List<Jobs> searchJobsByID() {
        return job.searchJobsByID(id);
    }
    
    public List<Jobs> openJobs() {
        return job.openJobs();
    }
    
    public List<Jobs> Jobs() {
        return job.Jobs();
    }
    
    public void delete(Jobs j) {
        job.delete(j.getId());
    }
    
}
