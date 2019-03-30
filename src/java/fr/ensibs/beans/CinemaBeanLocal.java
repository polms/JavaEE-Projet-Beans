/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensibs.beans;

import fr.ensibs.data.Acteur;
import fr.ensibs.data.Film;
import fr.ensibs.data.Programation;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author rmoalic
 */
@Local
public interface CinemaBeanLocal {
    List<Acteur> listActeurs();
    List<Film> listFilms();
    List<Programation> showProgramme();
    Acteur addActor(String nom, String prenom, int cote);
    Film addFilm(String nom, String producteur, Date sortie);
    void addActorToFilm(Acteur a, Film f);
    List<Acteur> listActeursFilms(Film f);
    void programFilm(Film f, Date d);
    Film findFilm(int id);
    Acteur findActor(int id);
    void removeFilm(Film f);
    void removeActeur(Acteur f);
    Programation findProgramation(int id);
    void removeProgramation(Programation f);
    void changeProgramation(Programation f);
    
}
