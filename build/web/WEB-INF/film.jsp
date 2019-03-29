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
        <h2>Liste des acteurs de "${film.getNom()}"</h2>

        <table>
            <tr>
                <th>id</th>
                <th>nom</th>
                <th>prenom</th>
                <th>cote</th>
                <th>action</th>
            </tr>
        <c:forEach var="f" items="${acteurs}">
            <tr>
		    <td><c:out value="${f.getId()}"/></td>
		    <td><c:out value="${f.getNom()}" /></td>
		    <td><c:out value="${f.getPrenom()}" /></td>
		    <td><c:out value="${f.getCote()}" /></td>
<!--                    <td><form action="/WebApplication1/entreprise/delete" method="POST"><input type="hidden" name="id" value="<c:out value="${entr.getId()}"/>"/><input type="submit" value="Supprimer !"/></form></td>-->
            </tr>
        </c:forEach>
        </table>

    </body>
</html>
