/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import javax.persistence.*;
import JPAOperations.*;

/**
 *
 * @author x00131787
 */

/*necessary changes to function:
1. Change username and password in Persistence XML.
*/

public class Test {
    
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("CAPU");
    static EntityManager em = emf.createEntityManager();
    
    public static void main(String[] args){
        
        em.getTransaction().begin();
        
        Branch b1 = new Branch("AIB_ITT1", "AIB IT Tallaght", "Blessington Rd, Tallaght, Dublin 24");
        em.persist(b1); //added a branch
        
        Customer d1 = new Customer("James", "Corden", "22 Fakestreet");
        b1.addCustomer(d1);
        em.persist(d1);
        
        SavingsAccount sa1 = new SavingsAccount("11448866", 2000, 9999);
        sa1.addCustomer(d1);
        em.persist(sa1);
        
        em.persist(sa1.makeLodgement(30000, "CarPoolKaraoke Royalties"));
        
        em.getTransaction().commit();
        
        em.close();
        emf.close();
                
    }
}
