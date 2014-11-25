/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author michaef
 */
@Entity
@Table(name = "AA_project_users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AAprojectusers.findAll", query = "SELECT a FROM AAprojectusers a"),
    @NamedQuery(name = "AAprojectusers.findById", query = "SELECT a FROM AAprojectusers a WHERE a.id = :id"),
    @NamedQuery(name = "AAprojectusers.findByName", query = "SELECT a FROM AAprojectusers a WHERE a.name = :name"),
    @NamedQuery(name = "AAprojectusers.findByChallengeActive", query = "SELECT a FROM AAprojectusers a WHERE a.challengeActive = :challengeActive"),
    @NamedQuery(name = "AAprojectusers.findByNotified", query = "SELECT a FROM AAprojectusers a WHERE a.notified = :notified"),
    @NamedQuery(name = "AAprojectusers.findByTotalPoints", query = "SELECT a FROM AAprojectusers a WHERE a.totalPoints = :totalPoints"),
    @NamedQuery(name = "AAprojectusers.findByCurrentChallenge", query = "SELECT a FROM AAprojectusers a WHERE a.currentChallenge = :currentChallenge"),
    @NamedQuery(name = "AAprojectusers.orderByName", query = "SELECT a FROM AAprojectusers a ORDER BY a.name")})
    
public class AAprojectusers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "challengeActive")
    private boolean challengeActive;
    @Basic(optional = false)
    @NotNull
    @Column(name = "notified")
    private boolean notified;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalPoints")
    private int totalPoints;
    @Column(name = "currentChallenge")
    private Integer currentChallenge;

    public AAprojectusers() {
    }

    public AAprojectusers(Integer id) {
        this.id = id;
    }

    public AAprojectusers(Integer id, String name, boolean challengeActive, boolean notified, int totalPoints) {
        this.id = id;
        this.name = name;
        this.challengeActive = challengeActive;
        this.notified = notified;
        this.totalPoints = totalPoints;
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

    public boolean getChallengeActive() {
        return challengeActive;
    }

    public void setChallengeActive(boolean challengeActive) {
        this.challengeActive = challengeActive;
    }

    public boolean getNotified() {
        return notified;
    }

    public void setNotified(boolean notified) {
        this.notified = notified;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Integer getCurrentChallenge() {
        return currentChallenge;
    }

    public void setCurrentChallenge(Integer currentChallenge) {
        this.currentChallenge = currentChallenge;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AAprojectusers)) {
            return false;
        }
        AAprojectusers other = (AAprojectusers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.AAprojectusers[ id=" + id + " ]";
    }
    
}
