/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensibs.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rmoalic
 */
@Entity
@Table(name = "FILM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Film.findAll", query = "SELECT f FROM Film f")
    , @NamedQuery(name = "Film.findById", query = "SELECT f FROM Film f WHERE f.id = :id")
    , @NamedQuery(name = "Film.findByNom", query = "SELECT f FROM Film f WHERE f.nom = :nom")
    , @NamedQuery(name = "Film.findByProducteur", query = "SELECT f FROM Film f WHERE f.producteur = :producteur")
    , @NamedQuery(name = "Film.findBySortie", query = "SELECT f FROM Film f WHERE f.sortie = :sortie")})
public class Film implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "NOM")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "PRODUCTEUR")
    private String producteur;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SORTIE")
    @Temporal(TemporalType.DATE)
    private Date sortie;
    @JoinTable(name = "JOUE", joinColumns = {
        @JoinColumn(name = "ID_FILM", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_ACTEUR", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<Acteur> acteurCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "film")
    private Collection<Programation> programationCollection;

    public Film() {
    }

    public Film(Integer id) {
        this.id = id;
    }

    public Film(Integer id, String nom, String producteur, Date sortie) {
        this.id = id;
        this.nom = nom;
        this.producteur = producteur;
        this.sortie = sortie;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getProducteur() {
        return producteur;
    }

    public void setProducteur(String producteur) {
        this.producteur = producteur;
    }

    public Date getSortie() {
        return sortie;
    }

    public void setSortie(Date sortie) {
        this.sortie = sortie;
    }

    @XmlTransient
    public Collection<Acteur> getActeurCollection() {
        return acteurCollection;
    }

    public void setActeurCollection(Collection<Acteur> acteurCollection) {
        this.acteurCollection = acteurCollection;
    }

    @XmlTransient
    public Collection<Programation> getProgramationCollection() {
        return programationCollection;
    }

    public void setProgramationCollection(Collection<Programation> programationCollection) {
        this.programationCollection = programationCollection;
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
        if (!(object instanceof Film)) {
            return false;
        }
        Film other = (Film) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.ensibs.data.Film[ id=" + id + " ]";
    }
    
}
