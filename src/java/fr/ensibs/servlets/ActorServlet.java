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
@WebServlet(name = "ActorServlet", urlPatterns = {"/acteur"})
public class ActorServlet extends HttpServlet {

    @EJB
    private CinemaBeanLocal bean;

    
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
                Acteur f = bean.findActor(id);
                if (f == null) {
                    response.sendError(404);
                    return;
                }
                bean.removeActeur(f);
                
                ses.setAttribute("message", "acteur: \""+f.getNom()+"\" Supprimer !");
                break;
            case "add":
                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                int cote = Integer.parseInt(request.getParameter("cote"));
                
                
                Acteur a = bean.addActor(nom, prenom, cote);
                ses.setAttribute("message", "acteur Ajouter !");
                break;
            default:
                response.sendError(400);
                return;
        }
        response.sendRedirect("/Projet");
    }

}
