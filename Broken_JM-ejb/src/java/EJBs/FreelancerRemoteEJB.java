/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBs;

import Entities.Freelancers;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author umangjain
 */
@Remote
public interface FreelancerRemoteEJB {
    
    public List<Freelancers> searchFreelancers();
    
    public String[] getFreelancerName(String username,String password);
    
    public int getFreelancerBalance(String username);
    
    public List<Freelancers> searchFreelancersName(String username);
    
    public void updateFreelancer(String name,String skills,String description,String username,String password);
    
    public void registerFreelancer(String name,String skills,String description,String username,String password);
    
    public void delete(String username);
    
    public void applyJob(int id,String username);
    
    public void revokeJob(int id,String username);
    
}
