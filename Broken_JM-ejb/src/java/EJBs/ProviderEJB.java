/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBs;

import Entities.Freelancers;
import Entities.Jobs;
import Entities.Provider;
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
public class ProviderEJB implements ProviderRemoteEJB {

    @PersistenceContext(unitName = "Broken_JM-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public List<Provider> searchProviders() {
        Query query = em.createNamedQuery("Provider.findAll");
        return query.getResultList();
    }
    
   /* @Override
    public String[] getProviderName(String username, String password) {
        String[] data=new String[2];
        try{
            Query query = em.createNamedQuery("Provider.findByUsername").setParameter("username", username);
            Provider prov = (Provider)query.getResultList().get(0);
            data[0] = prov.getName();
            if(prov.getPassword().equals(password)){
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
    } */
    
    @Override
    public void registerProvider(String name,String username,String password) {
        Query query1 = em.createNamedQuery("Provider.findAll");
        List<Provider> prov = query1.getResultList();
        int max = 0;
        for (Provider prov1 : prov) {
            if(max<=prov1.getId()){
                max = prov1.getId();
            }
        }
        max=max+1;
        Query query = em.createNativeQuery("INSERT INTO Provider (id,name,username,password) values(?,?,?,?)");
        query.setParameter(2, name);
        query.setParameter(3, username);
        query.setParameter(4, password);
        query.setParameter(1, max);
        //execute the query
        int rowsUpdated = query.executeUpdate();
    }
    
    @Override
    public void delete(String username) {
        Query query = em.createQuery("DELETE FROM Provider p WHERE p.username = :username");
        query.setParameter("username", username);
        int rowsUpdated = query.executeUpdate();
    }
    
    @Override
    public void createJob(String title, String description,int pay,String keywords,String username) {
        Query query1 = em.createNamedQuery("Jobs.findAll");
        List<Jobs> j = query1.getResultList();
        int max = 0;
        for (Jobs j1 : j) {
            if(max<=j1.getId()){
                max = j1.getId();
            }
        }
        max=max+1;
        Query query = em.createNativeQuery("INSERT INTO Jobs (id,title,description,pay,keywords,e_uname,status) values(?,?,?,?,?,?,?)");
        query.setParameter(2, title);
        query.setParameter(3, description);
        query.setParameter(4, pay);
        query.setParameter(5, keywords);
        query.setParameter(6, username);
        query.setParameter(7, "Open");
        query.setParameter(1, max);
        int rowsUpdated = query.executeUpdate();
    }
    
    @Override
    public void complete(int id) {
        Query query1 = em.createNamedQuery("Jobs.findById").setParameter("id", id);
        Jobs j1 = (Jobs)query1.getResultList().get(0);
        String uname = j1.getFlnames();
        int payment = j1.getPay();
        Query query2 = em.createQuery("UPDATE Jobs j SET j.status='Completed' WHERE j.id=:id");
        query2.setParameter("id", id);
        int rowsUpdated = query2.executeUpdate();
        Query query3 = em.createNamedQuery("Freelancers.findByUsername").setParameter("username", uname);
        Freelancers f1 = (Freelancers)query3.getResultList().get(0);
        payment = payment+f1.getBalance();
        Query query4 = em.createQuery("UPDATE Freelancers f SET f.balance=:payment WHERE f.username=:uname");
        query4.setParameter("payment", payment);
        query4.setParameter("uname", uname);
        int rowsUpdated3 = query4.executeUpdate();
    }
    
}
