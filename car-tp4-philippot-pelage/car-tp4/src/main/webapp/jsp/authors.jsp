<%@page import="car.tp4.entity.Author"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <%@include file="style.jsp" %>
        <title>Auteurs</title>
    </head>
    <body>
        <%
            Collection<Author> authors = (Collection<Author>) request.getAttribute("authors");
        %>
        <div>
          <%@include file="bar.jsp" %>
        </div>
        <h2>Auteurs existants</h2>
        <div class="table">
        	<table>
        		<thead>
        			<tr>
                <th>ID</th>
        				<th>Pr&eacute;nom</th>
        				<th>Nom</th>
                <th>Edition</th>
                <th>Informations de l'auteur</th>
                <th>Supprimer</th>
        			</tr>
        		</thead>
        		<tbody>
        		<% for(Author author : authors){ %>
	        		<tr>
                <td><%= author.getId() %></td>
	        			<td><%= author.getFirstname() %></td>
	        			<td><%= author.getLastname() %></td>
                <td>
                  <a href="/author-form?id=<%= author.getId() %>">Editer</a>
                </td>
                <td>
                  <a href="/authors?id=<%= author.getId() %>">Informations</a>
                </td>
                <td>
                  <a href="/author-remove?id=<%= author.getId() %>">Supprimer</a>
                </td>
	        		</tr>
	        	<% } %>
        		</tbody>
        	</table>
        </div>
    </body>
</html>
