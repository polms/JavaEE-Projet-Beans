/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensibs.servlets;

import fr.ensibs.beans.CinemaBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "IndexServlet", urlPatterns = {"/"})
public class IndexServlet extends HttpServlet {
    
    @EJB
    private CinemaBeanLocal bean;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession(true);
        getServletContext().setAttribute("message", ses.getAttribute("message"));
        ses.setAttribute("message", null);
        
        String search = request.getParameter("search");
        if (search == null || search.isEmpty()) {
            getServletContext().setAttribute("program", bean.showProgramme());
        } else {
            getServletContext().setAttribute("program", bean.showProgramme(search));
        }
        getServletContext().setAttribute("actors", bean.listActeurs());
        getServletContext().setAttribute("films", bean.listFilms());
        RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/index.jsp");  
        rd.forward(request, response);
    }
}
