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
@Table(name = "AA_project_challenges")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AAprojectchallenges.findAll", query = "SELECT a FROM AAprojectchallenges a"),
    @NamedQuery(name = "AAprojectchallenges.findById", query = "SELECT a FROM AAprojectchallenges a WHERE a.id = :id"),
    @NamedQuery(name = "AAprojectchallenges.findByName", query = "SELECT a FROM AAprojectchallenges a WHERE a.name = :name"),
    @NamedQuery(name = "AAprojectchallenges.findByDescription", query = "SELECT a FROM AAprojectchallenges a WHERE a.description = :description"),
    @NamedQuery(name = "AAprojectchallenges.findByPoints", query = "SELECT a FROM AAprojectchallenges a WHERE a.points = :points"),
    @NamedQuery(name = "AAprojectchallenges.findByCategory", query = "SELECT a FROM AAprojectchallenges a WHERE a.category = :category"),
    @NamedQuery(name = "AAprojectchallenges.orderById", query = "SELECT c FROM AAprojectchallenges c ORDER BY c.id"),
    @NamedQuery(name = "AAprojectchallenges.orderByName", query = "SELECT c FROM AAprojectchallenges c ORDER BY c.name")
})
public class AAprojectchallenges implements Serializable {
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
    @Size(min = 1, max = 100)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "points")
    private int points;
    @Size(max = 15)
    @Column(name = "category")
    private String category;

    public AAprojectchallenges() {
    }

    public AAprojectchallenges(Integer id) {
        this.id = id;
    }

    public AAprojectchallenges(Integer id, String name, String description, int points) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.points = points;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
        if (!(object instanceof AAprojectchallenges)) {
            return false;
        }
        AAprojectchallenges other = (AAprojectchallenges) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.AAprojectchallenges[ id=" + id + " ]";
    }
    
}
