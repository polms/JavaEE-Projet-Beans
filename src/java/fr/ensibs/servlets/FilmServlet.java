/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensibs.servlets;

import fr.ensibs.beans.CinemaBeanLocal;
import fr.ensibs.data.Acteur;
import fr.ensibs.data.Film;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rmoalic
 */
@WebServlet(name = "FilmServlet", urlPatterns = {"/film"})
public class FilmServlet extends HttpServlet {

    @EJB
    private CinemaBeanLocal bean;

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (java.lang.NumberFormatException e) {
            response.sendError(400);
            return;
        }
        
        Film f = bean.findFilm(id);
        if (f == null) {
            response.sendError(404);
            return;
        }
        
        getServletContext().setAttribute("film", f);
        Collection<Acteur> acteurs = f.getActeurCollection();
        acteurs.size();
        getServletContext().setAttribute("acteurs", acteurs);
        
        RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/film.jsp");  
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession ses = request.getSession(true);
        
        switch (action) {
            case "delete":
                int id = 0;
                try {
                    id = Integer.parseInt(request.getParameter("id"));
                } catch (java.lang.NumberFormatException e) {
                    response.sendError(400);
                    return;
                }
                Film f = bean.findFilm(id);
                if (f == null) {
                    response.sendError(404);
                    return;
                }
                bean.removeFilm(f);
                
                ses.setAttribute("message", "film: \""+f.getNom()+"\" Supprimer !");
                break;
            case "add":
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String nom = request.getParameter("nom");
                String prenom = request.getParameter("producteur");
                String[] actors = request.getParameterValues("acteurs");
                Date sortie;
                try {
                    sortie = simpleDateFormat.parse(request.getParameter("sortie"));
                } catch (ParseException ex) {
                    response.sendError(400);
                    return;
                }
                
                Film f2 = bean.addFilm(nom, prenom, sortie);
                
                for (String a_id: actors) {
                    Acteur acteur = bean.findActor(Integer.parseInt(a_id));
                    bean.addActorToFilm(acteur, f2);
                }
                
                ses.setAttribute("message", "film Ajouter !");
                break;
            default:
                response.sendError(400);
                return;
        }
        response.sendRedirect("/Projet");
    }

}
