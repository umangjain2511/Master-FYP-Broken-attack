/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBs;

import Entities.Freelancers;
import Entities.Jobs;
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
public class FreelancerEJB implements FreelancerRemoteEJB {

    @PersistenceContext(unitName = "Broken_JM-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public List<Freelancers> searchFreelancers() {
        Query query = em.createNamedQuery("Freelancers.findAll");
        return query.getResultList();
    }
    
    @Override
    public String[] getFreelancerName(String username,String password) {
        String[] data=new String[2];
        try{
            Query query = em.createNamedQuery("Freelancers.findByUsername").setParameter("username", username);
            Freelancers free = (Freelancers)query.getResultList().get(0);
            data[0] = free.getName();
            if(free.getPassword().equals(password)){
                data[1] = "correct";
            }
            else{
                data[1] = "incorrect";
            }
        }
        catch(Exception e){
            data[0] = "Error";
            data[1] = "incorrect";
        }
        return data;
    }
    
    @Override
    public int getFreelancerBalance(String username) {
        Query query = em.createNamedQuery("Freelancers.findByUsername").setParameter("username", username);
        Freelancers free = (Freelancers)query.getResultList().get(0);
        return free.getBalance();
    }
    
    @Override
    public List<Freelancers> searchFreelancersName(String username) {
        Query query = em.createNamedQuery("Freelancers.findByUsername").setParameter("username", username);
        return query.getResultList();
    }
    
    @Override
    public void updateFreelancer(String name,String skills,String description,String username,String password) {
        Query query = em.createQuery("UPDATE Freelancers f SET f.name=:name,f.skills=:skills,f.description=:description,f.username=:username,f.password=:password WHERE f.username = :username");
        query.setParameter("name", name);
        query.setParameter("skills", skills);
        query.setParameter("description", description);
        query.setParameter("username", username);
        query.setParameter("password", password);
        int rowsUpdated = query.executeUpdate();
    }
    
    @Override
    public void registerFreelancer(String name,String skills,String description,String username,String password) {
        Query query1 = em.createNamedQuery("Freelancers.findAll");
        List<Freelancers> freelan = query1.getResultList();
        int max = 0;
        for (Freelancers freelan1 : freelan) {
            if(max<=freelan1.getId()){
                max = freelan1.getId();
            }
        } 
        Query query = em.createNativeQuery("INSERT INTO Freelancers (id,name,skills,description,username,password,balance) values(?,?,?,?,?,?,?)");      
        query.setParameter(2, name);
        query.setParameter(3, skills);
        query.setParameter(4, description);
        query.setParameter(5, username);
        query.setParameter(6, password);
        query.setParameter(7, 0);
        query.setParameter(1, max);
        int rowsUpdated = query.executeUpdate();
    }
    
    @Override
    public void delete(String username) {
        Query query = em.createQuery("DELETE FROM Freelancers f WHERE f.username = :username");
        query.setParameter("username", username);
        int rowsUpdated = query.executeUpdate();
    }
    
    @Override
    public void applyJob(int id,String username) {
        Query query1 = em.createNamedQuery("Jobs.findById");
        query1.setParameter("id", id);
        Jobs j = (Jobs) query1.getResultList().get(0);
        String fl_uname = j.getFlnames();
        if(fl_uname==null){
            fl_uname=username;
        }
        else{
            if(fl_uname.contains(username)){
                return;
            }
            else{
                fl_uname = fl_uname+","+username;
            }
        }
        Query query = em.createQuery("UPDATE Jobs j SET j.flnames=:fl_uname WHERE j.id=:id");
        query.setParameter("fl_uname", fl_uname);
        query.setParameter("id", id);
        int rowsUpdated = query.executeUpdate();
    }
    
    @Override
    public void revokeJob(int id,String username) {
        Query query1 = em.createNamedQuery("Jobs.findById");
        query1.setParameter("id", id);
        Jobs j = (Jobs) query1.getResultList().get(0);
        String fl_uname = j.getFlnames();
        String[] flnames = fl_uname.split(",");
        String finalNames="";
        for(String fn : flnames ){
            if(fn.equals(username)){
            }
            else{
                if("".equals(finalNames)){
                    finalNames=fn;
                }
                else{
                    fn=","+fn;
                    finalNames=finalNames+fn;
                }
            }
        }
        if("".equals(finalNames)){
            finalNames=null;
        }
        Query query = em.createQuery("UPDATE Jobs j SET j.flnames=:fl_uname WHERE j.id=:id");
        query.setParameter("fl_uname", finalNames);
        query.setParameter("id", id);
        int rowsUpdated = query.executeUpdate();
    }
    
}
