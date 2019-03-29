<%-- 
    Document   : index
    Created on : 29 mars 2019, 10:30:31
    Author     : rmoalic
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>${message}</h1>
        <h2>Liste des films </h2>

        <table>
            <tr>
                <th>id</th>
                <th>nom</th>
                <th>producteur</th>
                <th>date de sortie</th>
                <th>action</th>
            </tr>
        <c:forEach var="f" items="${films}">
            <tr>
		    <td><c:out value="${f.getId()}"/></td>
		    <td><c:out value="${f.getNom()}" /></td>
		    <td><c:out value="${f.getProducteur()}" /></td>
		    <td><c:out value="${f.getSortie()}" /></td>
                    <td><a href="/Projet/film?id=${f.getId()}">Infos</a><form action="/Projet/film" method="POST"><input type="hidden" name="action" value="delete"/><input type="hidden" name="id" value="<c:out value="${f.getId()}"/>"/><input type="submit" value="Supprimer !"/></form></td>
            </tr>
        </c:forEach>
        </table>
        <h3>Ajouter un film</h3>
        <form method="POST" action="/Projet/film">
            <input type="hidden" name="action" value="add"/>
            <label for="f_nom">Nom:</label>
            <input type="text" id="f_nom" name="nom"/>
            <label for="f_prod">Producteur:</label>
            <input type="text" id="f_prod" name="producteur"/>
            <label for="f_sortie">Sortie:</label>
            <input type="date" id="f_sortie" name="sortie"/>
            <label for="f_actors">Acteurs:</label>
            <select name="acteurs" id="f_actors" multiple>
                <c:forEach var="a" items="${actors}">
                    <option value="${a.getId()}">${a.getNom()} ${a.getPrenom()}</option>
                </c:forEach>
            </select>
            
            <input type="submit" value="Ajouter"/>
        </form>
        <h3>Ajouter un acteur</h3>
        <form method="POST" action="/Projet/acteur">
            <input type="hidden" name="action" value="add"/>
            <label for="f_nom">Nom:</label>
            <input type="text" id="f_nom" name="nom"/>
            <label for="f_prod">prenom</label>
            <input type="text" id="f_prod" name="prenom"/>
            <label for="f_sortie">cote</label>
            <input type="text" id="f_sortie" name="cote"/>
         
            <input type="submit" value="Ajouter"/>
        </form>
    </body>
</html>
