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
        <c:if test="${message != null}">
        <div class="alert alert-primary alert-dismissible fade show" role="alert">
            ${message}
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        </c:if>
        
        <h1>Programme du cinema !</h1>
        <form action="#" method="GET"><input type="text" name="search" placeholder="Rechercher"><input type="submit" value="rechercher"></form>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Date</th>
                    <th>Film</th>
                    <th>programmation</th>
                    <th>actions</th>
                </tr>
            </thead>
            <c:forEach var="f" items="${program}">
                <tr>
                    <td><c:out value="${f.getD()}"/></td>
                    <td><c:out value="${f.getFilm().getNom()}"/></td>
                    <td><form action="/Projet/program" method="POST"><input type="hidden" name="action" value="modify"/><input type="hidden" name="id" value="<c:out value="${f.getId()}"/>"><input type="date" name="date"/><input type="submit" class="btn btn-warning" value="Modifier"></form> </td>
                    <td><form action="/Projet/program" method="POST"><input type="hidden" name="action" value="delete"/><input type="hidden" name="id" value="<c:out value="${f.getId()}"/>"><input type="submit" class="btn btn-danger" value="Supprimer !"></form> </td>
                </tr>
            </c:forEach>
        </table>
        
        <h2>Liste des films </h2>

        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">nom</th>
                    <th scope="col">producteur</th>
                    <th scope="col">date de sortie</th>
                    <th scope="col">programmation</th>
                    <th scope="col">actions</th>
                </tr>
            </thead>
        <c:forEach var="f" items="${films}">
            <tr>
		    <td><c:out value="${f.getId()}"/></td>
		    <td><c:out value="${f.getNom()}" /></td>
		    <td><c:out value="${f.getProducteur()}" /></td>
		    <td><c:out value="${f.getSortie()}" /></td>
                    <td><form action="/Projet/program" method="POST"><input type="hidden" name="action" value="add"/><input type="hidden" name="id" value="<c:out value="${f.getId()}"/>"><input type="date" name="date"/><input type="submit" class="btn btn-success" value="programmer"></form> </td>
                    <td><a href="/Projet/film?id=${f.getId()}" class="btn btn-info">Infos</a><form action="/Projet/film" method="POST"><input type="hidden" name="action" value="delete"/><input type="hidden" name="id" value="<c:out value="${f.getId()}"/>"/><input type="submit" class="btn btn-danger" value="Supprimer !"/></form></td>
            </tr>
        </c:forEach>
        </table>
        <h2>Liste des acteurs </h2>

        <table class="table table-striped">
            <thead>
                <tr>
                    <th>id</th>
                    <th>nom</th>
                    <th>prenom</th>
                    <th>actions</th>
                </tr>
            </thead>
        <c:forEach var="f" items="${actors}">
            <tr>
		    <td><c:out value="${f.getId()}"/></td>
		    <td><c:out value="${f.getNom()}" /></td>
		    <td><c:out value="${f.getPrenom()}" /></td>
                    <td><form action="/Projet/acteur" method="POST"><input type="hidden" name="action" value="delete"/><input type="hidden" name="id" value="<c:out value="${f.getId()}"/>"><input type="submit" class="btn btn-danger" value="Supprimer !"></form> </td>
            </tr>
        </c:forEach>
        </table>
        <div class="container"><div class="row">
            <form class="col-sm rounded-left border" method="POST" action="/Projet/film">
                <fieldset>
                    <legend>Ajouter un film</legend>
                    <input type="hidden" name="action" value="add"/>
                    <div class="form-group">
                        <label for="f_nom">Nom:</label>
                        <input type="text" class="form-control" id="f_nom" name="nom"/>
                    </div>
                    <div class="form-group">
                        <label for="f_prod">Producteur:</label>
                        <input type="text" class="form-control" id="f_prod" name="producteur"/>
                    </div>
                    <div class="form-group">
                        <label for="f_sortie">Sortie:</label>
                        <input type="date" class="form-control" id="f_sortie" name="sortie"/>
                    </div>
                    <div class="form-group">
                        <label for="f_actors">Acteurs:</label>
                        <select name="acteurs" class="form-control" id="f_actors" multiple>
                            <c:forEach var="a" items="${actors}">
                                <option value="${a.getId()}">${a.getNom()} ${a.getPrenom()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <input type="submit" class="btn btn-primary" value="Ajouter"/>
                </fieldset>
            </form>
            <form class="col-sm rounded-right border" method="POST" action="/Projet/acteur">
                <fieldset>
                    <legend>Ajouter un acteur</legend>

                    <input type="hidden" name="action" value="add"/>
                    <div class="form-group">
                        <label for="f_nom2">Nom:</label>
                        <input type="text" class="form-control" id="f_nom2" name="nom"/>
                    </div>
                    <div class="form-group">
                        <label for="f_prod2">Prenom:</label>
                        <input type="text" class="form-control" id="f_prod2" name="prenom"/>
                    </div>
                    <div class="form-group">
                        <label for="f_sortie2">Cote:</label>
                        <input type="text" class="form-control" id="f_sortie2" name="cote"/>
                    </div>
                    <input type="submit" class="btn btn-primary" value="Ajouter"/>
                </fieldset>
            </form>
            </div></div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>    </body>
</html>
