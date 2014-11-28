/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AAprojectchallenges;
import Model.AAprojectusers;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author michaef
 */

@Named(value = "dataHandler")
@SessionScoped
public class DataHandler implements Serializable{

        private EntityManager em;
    private EntityTransaction t;
    
    private List<AAprojectchallenges> listChallenges;
    private List<AAprojectusers> listUsers;
    
    // CHALLENGE LIST FIELDS
    private int id;
    private String name, description;
    private int points;
    
    
    // USER LIST FIELDS
    private int uId, totalPoints, challId;
    private String userName;
    private Boolean challengeActive, notified;
    
    
    
    /**
     * Creates a new instance of DataHandler
     */
    public DataHandler() {
        
        em = Persistence.createEntityManagerFactory("ChallengeAcceptedBackEndPU").createEntityManager();
        listChallenges = new ArrayList<AAprojectchallenges>();
        listUsers = new ArrayList<AAprojectusers>();
    }
    
    
    public void addChallenges(){
        AAprojectchallenges challenge = new AAprojectchallenges();
        challenge.setId(challenge.getId());
        challenge.setName(name);
        challenge.setDescription(description);
        challenge.setPoints(points);
        
        t = em.getTransaction();
        t.begin();
        em.persist(challenge);
        t.commit();
        listChallengesByName();
        
    }
    
    public void challengeUser(){
        AAprojectusers user = em.find(AAprojectusers.class, uId);
                

        user.setCurrentChallenge(challId);
        user.setChallengeActive(true);
        


        t = em.getTransaction();
        t.begin();
        em.merge(user);
        t.commit();
    }
    
    public void completeChallenge(){
        AAprojectusers user = em.find(AAprojectusers.class, uId);
                

        user.setCurrentChallenge(0);
        user.setChallengeActive(false);
        int currentTotalPoints = user.getTotalPoints();
        

        
        int addPoints = 10 + currentTotalPoints;
        
        user.setTotalPoints(addPoints);

        t = em.getTransaction();
        t.begin();
        em.merge(user);
        t.commit();
    }
    
    public void challengeChecker(){
        AAprojectusers checker = em.find(AAprojectusers.class, uId);
        setChallengeActive(checker.getChallengeActive());
        setUserName(checker.getName());
       
    }
    
    public void selectChallenge(){
        AAprojectchallenges selected = em.find(AAprojectchallenges.class, id);
        setName(selected.getName());
        setDescription(selected.getDescription());
        setPoints(selected.getPoints());
    }
    
        public void challengeAccepter(){
        AAprojectusers checker = em.find(AAprojectusers.class, uId);
        checker.setNotified(true);
        t = em.getTransaction();
        t.begin();
        em.merge(checker);
        t.commit();
        
       
    }
        public void challengeRejecter(){
        AAprojectusers checker = em.find(AAprojectusers.class, uId);
        checker.setChallengeActive(false);
        checker.setCurrentChallenge(null);
        t = em.getTransaction();
        t.begin();
        em.merge(checker);
        t.commit();
        }
    
    
    
    
    
            public void listChallengesByName(){
        
        listChallenges = em.createNamedQuery("AAprojectchallenges.orderByName").getResultList();
        
    }
            public void listChallengesById(){
                listChallenges = em.createNamedQuery("AAprojectchallenges.orderById").getResultList();
            }
            
    
            public void listUsersByName(){
                listUsers = em.createNamedQuery("AAprojectusers.orderByName").getResultList();
            }
            public void listUsersById(){
                listUsers = em.createNamedQuery("AAprojectusers.orderById").getResultList();
            }
    

    public List<AAprojectchallenges> getListChallenges() {
        listChallengesById();
        return listChallenges;
    }

    public void setListChallenges(List<AAprojectchallenges> listChallenges) {
        this.listChallenges = listChallenges;
    }

    public List<AAprojectusers> getListUsers() {
        listUsersById();
        return listUsers;
    }

    public void setListUsers(List<AAprojectusers> listUsers) {
        this.listUsers = listUsers;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getChallId() {
        return challId;
    }

    public void setChallId(int challId) {
        this.challId = challId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getChallengeActive() {
        return challengeActive;
    }

    public void setChallengeActive(Boolean challengeActive) {
        this.challengeActive = challengeActive;
    }

    public Boolean getNotified() {
        return notified;
    }

    public void setNotified(Boolean notified) {
        this.notified = notified;
    }
    
    
    
    
    
    
}
