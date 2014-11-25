/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Model.AAprojectusers;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author michaef
 */
@Named(value = "controlBean")
@SessionScoped
public class ControlBean implements Serializable {
    
   
    private List<AAprojectusers> listUsers;

 


    
    
    private EntityManager em;
    private EntityTransaction t;
    
    private int totalPoints;
    private boolean dataChecker;

    
@Size(min = 3, max = 32)
    @Pattern(regexp = "[A-Za-z]*")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Max(120)
    @Min(1)
    private int age;
    private int id;
    @Email
    private String Challenge;
    
    
    

    /**
     * Creates a new instance of ControlBean
     */
    public ControlBean() {
        em = Persistence.createEntityManagerFactory("ChallengeAcceptedBackEndPU").createEntityManager();
        listUsers = new ArrayList<AAprojectusers>();
        
    }


    public List<AAprojectusers> getListUsers() {
        listUsersByName();
        return listUsers;
    }

    public void setListUsers(List<AAprojectusers> listUsers) {
        this.listUsers = listUsers;
    }
    
    public void listUsersByName(){
        
        listUsers = em.createNamedQuery("AAprojectusers.orderByName").getResultList();
        
    }


        
        
        
    
        public void addUserInfo(AAprojectusers p) {
        AAprojectusers person = new AAprojectusers();

        person.setId(person.getId());
        person.setName(name);


        t = em.getTransaction();
        t.begin();
        em.persist(p);
        t.commit();
        listUsersByName();
        
        
    }
        

    public int getTotalPoints() {
       
        
        
        return totalPoints;
    }
        
    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
        
    public boolean isDataChecker() {
        return dataChecker;
    }

    public void setDataChecker(boolean dataChecker) {
        this.dataChecker = dataChecker;
    } 
    
    
    
  
        
}


