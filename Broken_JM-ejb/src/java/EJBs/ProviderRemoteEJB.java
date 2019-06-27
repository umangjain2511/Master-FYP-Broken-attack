/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBs;

import Entities.Provider;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author umangjain
 */
@Remote
public interface ProviderRemoteEJB {
    
    public List<Provider> searchProviders();
    
   // public String[] getProviderName(String username, String password);
    
    public void registerProvider(String name,String username,String password);
    
    public void delete(String username);
    
    public void createJob(String title, String description,int pay,String keywords,String username);
    
    public void complete(int id);  
    
}
