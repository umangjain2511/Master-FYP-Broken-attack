/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBs;

import Entities.Freelancers;
import Entities.Jobs;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author umangjain
 */
@Stateless
@LocalBean
public class JobEJB implements JobRemoteEJB {

    @PersistenceContext(unitName = "Broken_JM-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public List<Jobs> searchJobs(String search) {
        Query query = em.createNamedQuery("Jobs.findByKeywords").setParameter("keywords", search);
        return query.getResultList();
    }
    
    @Override
    public List<Jobs> searchJobsByID(int id) {
        Query query = em.createNamedQuery("Jobs.findById").setParameter("id", id);
        return query.getResultList();
    }
    
    @Override
    public List<Jobs> openJobs() {
        Query query = em.createNamedQuery("Jobs.findAllOpen");
        return query.getResultList();
    }
    
    @Override
    public List<Jobs> Jobs() {
        Query query = em.createNamedQuery("Jobs.findAll");
        return query.getResultList();
    }
    
    @Override
    public void delete(int id) {
        Query query = em.createQuery("DELETE FROM Jobs j WHERE j.id = :id");
        query.setParameter("id", id);
        int rowsUpdated = query.executeUpdate();
    }
    
    @Override
    public List<Jobs> searchProviderJob(String username) {
        List<Jobs> j = new ArrayList<>();
        try{
            Query query = em.createNamedQuery("Jobs.findPerProv").setParameter("username", username);
            j = query.getResultList();
        }
        catch(Exception e){
        }
        return j;
    }
    
    @Override
    public List<Jobs> showOpenJobs(String username) {
        Query query = em.createNamedQuery("Jobs.findOpenPerProv").setParameter("username", username);
        return query.getResultList();
    }
    
    @Override
    public List<Freelancers> seeApplicants(int id)                              {
        List<Freelancers> fl = new ArrayList<>();
        try{
            Query query1 = em.createNamedQuery("Jobs.findById").setParameter("id", id);
            Jobs j = (Jobs) query1.getResultList().get(0);
            String names = j.getFlnames();
            String[] fl_names = names.split(",");
            for(String fl1 : fl_names){
                Query query = em.createNamedQuery("Freelancers.findByUsername").setParameter("username", fl1);
                fl.add((Freelancers)query.getResultList().get(0));
            }
        }
        catch(Exception e){
        }
        return fl;
    }
    
    @Override
    public void jobAssign(int id,String username) {
        Query query = em.createQuery("UPDATE Jobs j SET j.status='Closed',j.flnames=:username WHERE j.id=:id");
        query.setParameter("username", username);
        query.setParameter("id", id);
        int rowsUpdated = query.executeUpdate();
        System.out.println(rowsUpdated);
    }
    
    @Override
    public List<Jobs> showAssJobs(String username) {
        Query query = em.createNamedQuery("Jobs.findAssJobs").setParameter("username", username);
        return query.getResultList();
    }
    
    @Override
    public List<Jobs> jobAssToMe(String username) {
        Query query = em.createNamedQuery("Jobs.jobsAssignToMe").setParameter("username", username);
        return query.getResultList();
    }
      
}
