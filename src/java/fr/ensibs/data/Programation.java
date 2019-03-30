/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensibs.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rmoalic
 */
@Entity
@Table(name = "PROGRAMATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Programation.findAll", query = "SELECT p FROM Programation p")
    , @NamedQuery(name= "Programation.findRecent", query = "SELECT p  FROM Programation p WHERE p.d >= current_date")
    , @NamedQuery(name= "Programation.findContain", query = "SELECT p  FROM Programation p INNER JOIN Film f where LOCATE(f.nom, :c) <> 0")
    , @NamedQuery(name = "Programation.findById", query = "SELECT p FROM Programation p WHERE p.id = :id")
    , @NamedQuery(name = "Programation.findByD", query = "SELECT p FROM Programation p WHERE p.d = :d")})
public class Programation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "D")
    @Temporal(TemporalType.DATE)
    private Date d;
    @JoinColumn(name = "FILM", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Film film;

    public Programation() {
    }

    public Programation(Integer id) {
        this.id = id;
    }

    public Programation(Integer id, Date d) {
        this.id = id;
        this.d = d;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getD() {
        return d;
    }

    public void setD(Date d) {
        this.d = d;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
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
        if (!(object instanceof Programation)) {
            return false;
        }
        Programation other = (Programation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.ensibs.data.Programation[ id=" + id + " ]";
    }
    
}
