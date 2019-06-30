/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBs;

import Entities.Admin;
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
public class AdminEJB implements AdminRemoteEJB {

    @PersistenceContext(unitName = "Broken_JM-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public List<Admin> searchAdmin(String username) {
        Query query = em.createNamedQuery("Admin.findByUsername").setParameter("username", username);
        return query.getResultList();
    }    
    
}
