package fr.ensibs.beans;

import fr.ensibs.data.Acteur;
import fr.ensibs.data.Film;
import fr.ensibs.data.Programation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class CinemaBean implements CinemaBeanLocal {

    @PersistenceContext(unitName = "ProjetPU")
    private EntityManager em;
    
    @Override
    public List<Acteur> listActeurs() {
        TypedQuery<Acteur> q = em.createNamedQuery("Acteur.findAll", Acteur.class);
        return q.getResultList();
    }

    @Override
    public List<Film> listFilms() {
        TypedQuery<Film> q = em.createNamedQuery("Film.findAll", Film.class);
        return q.getResultList();
    }
    
    @Override
    public List<Programation> showProgramme() {
        TypedQuery<Programation> q = em.createNamedQuery("Programation.findRecent", Programation.class);
        return q.getResultList();
    }
    
    @Override
    public Acteur addActor(String nom, String prenom, int cote) {
        Acteur a = new Acteur(null, nom, prenom, cote);
        persist(a);
        return a;
    }
    
    @Override
    public Film addFilm(String nom, String producteur, Date sortie) {
        Film f = new Film(null, nom, producteur, sortie);
        persist(f);
        return f;
    }
    
    @Override
    public void addActorToFilm(Acteur a, Film f) {
        f.getActeurCollection().add(a);
        a.getFilmCollection().add(f);
        
        em.merge(f);
        em.merge(a);
        em.flush();
    }
    
    @Override
    public List<Acteur> listActeursFilms(Film f) {
        return new ArrayList<>(f.getActeurCollection());
    }
    
    @Override
    public void programFilm(Film f, Date d) {
        Programation p = new Programation(null, d);
        p.setFilm(f);
        persist(p);
    }
    
    @Override
    public Film findFilm(int id) {
        TypedQuery<Film> q = em.createNamedQuery("Film.findById", Film.class);
        q.setParameter("id", id);
        Film f = null;
        try {
            f = q.getSingleResult();
        } catch (javax.persistence.NoResultException e) {
        }
        return f;
    }
    
    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public void removeFilm(Film f) {
        Film f2 = em.merge(f);
        
        for (Acteur a : f2.getActeurCollection()) {
            a.getFilmCollection().remove(f2);
            em.merge(a);
        }
        
        em.remove(f2);
    }
    
    @Override
    public void removeActeur(Acteur f) {
        Acteur f2 = em.merge(f);
        em.remove(f2);
    }
    
    @Override
    public void removeProgramation(Programation f) {
        Programation f2 = em.merge(f);
        em.remove(f2);
    }

    @Override
    public Acteur findActor(int id) {
        TypedQuery<Acteur> q = em.createNamedQuery("Acteur.findById", Acteur.class);
        q.setParameter("id", id);
        Acteur f = null;
        try {
            f = q.getSingleResult();
        } catch (javax.persistence.NoResultException e) {
        }
        return f;    
    }
    
    @Override
    public Programation findProgramation(int id) {
        TypedQuery<Programation> q = em.createNamedQuery("Programation.findById", Programation.class);
        q.setParameter("id", id);
        Programation f = null;
        try {
            f = q.getSingleResult();
        } catch (javax.persistence.NoResultException e) {
        }
        return f;    
    }

    @Override
    public void changeProgramation(Programation f) {
        em.merge(f);
    }
}
