/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensibs.servlets;

import fr.ensibs.beans.CinemaBeanLocal;
import fr.ensibs.data.Acteur;
import fr.ensibs.data.Film;
import fr.ensibs.data.Programation;
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
@WebServlet(name = "ProgramServlet", urlPatterns = {"/program"})
public class ProgramServlet extends HttpServlet {

    @EJB
    private CinemaBeanLocal bean;

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession ses = request.getSession(true);
        
        switch (action) {
            case "delete":
            {
                int id = 0;
                try {
                    id = Integer.parseInt(request.getParameter("id"));
                } catch (java.lang.NumberFormatException e) {
                    response.sendError(400);
                    return;
                }
                Programation f = bean.findProgramation(id);
                if (f == null) {
                    response.sendError(404);
                    return;
                }
                bean.removeProgramation(f);
                
                ses.setAttribute("message", "programe: La difusion de \""+f.getFilm().getNom()+"\" le "+f.getD()+" est annulée !");
            }
                break;
            case "add":
            {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String id_film = request.getParameter("id");
                Date date;
                try {
                    date = simpleDateFormat.parse(request.getParameter("date"));
                } catch (ParseException ex) {
                    response.sendError(400);
                    return;
                }
                Film fi = bean.findFilm(Integer.parseInt(id_film));
                
                if (fi.getSortie().after(date)) {
                    ses.setAttribute("message", "On ne peux pas diffuser un film avant sa date de sortie");
                    break;
                }
                
                bean.programFilm(fi, date);
                ses.setAttribute("message", "Film programmer");
            }
                break;
            case "modify":
            {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String id_prog = request.getParameter("id");
                Date date;
                try {
                    date = simpleDateFormat.parse(request.getParameter("date"));
                } catch (ParseException ex) {
                    response.sendError(400);
                    return;
                }
                Programation p = bean.findProgramation(Integer.parseInt(id_prog));
                
                if (p.getFilm().getSortie().after(date)) {
                    ses.setAttribute("message", "On ne peux pas diffuser un film avant sa date de sortie");
                    break;
                }
                
                p.setD(date);
                bean.changeProgramation(p);
                ses.setAttribute("message", "Film reprogrammer");
            }
                break;
            default:
                response.sendError(400);
                return;
        }
        response.sendRedirect("/Projet");
    }

}
