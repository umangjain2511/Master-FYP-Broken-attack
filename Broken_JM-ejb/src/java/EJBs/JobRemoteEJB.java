/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBs;

import Entities.Freelancers;
import Entities.Jobs;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author umangjain
 */
@Remote
public interface JobRemoteEJB {
    
    public List<Jobs> searchJobs(String search);

    public List<Jobs> searchJobsByID(int id);
    
    public List<Jobs> openJobs();
    
    public List<Jobs> Jobs();

    public void delete(int id);

    public List<Jobs> searchProviderJob(String username);

    public List<Jobs> showOpenJobs(String username);
     
    public List<Freelancers> seeApplicants(int id);
    
    public void jobAssign(int id,String username);
    
    public List<Jobs> showAssJobs(String username);
     
    public List<Jobs> jobAssToMe(String username);
    
}
