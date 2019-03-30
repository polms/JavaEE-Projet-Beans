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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body class="container">
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
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>    </body>
</html>
