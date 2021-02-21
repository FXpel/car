<%@page import="car.tp4.entity.Book"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <%@include file="style.jsp" %>
    </head>
    <body>
        <%
            Collection<Book> books = (Collection<Book>) request.getAttribute("books");
        %>
        <div>
          <%@include file="bar.jsp" %>
        </div>
        <h2>Librairie</h2>
        <div class="table">
        	<table>
        		<thead>
        			<tr>
                <th>ID</th>
        				<th>Auteur</th>
        				<th>Livre</th>
        				<th>Quantit&eacute;</th>
                <th>
                  Ann&eacute;e
                  <a href="/books?asc=<%=true%>">ASC</a>
                  <a href="/books?desc=<%=true%>">DESC</a>
                </th>
                <th>Edition</th>
                <th>Ajouter au panier</th>
                <th>Supprimer</th>
        			</tr>
        		</thead>
        		<tbody>
        		<% for(Book book : books){ %>
	        		<tr>
                <td><%= book.getId() %></td>
	        			<td><%= book.getAuthor().toString() %></td>
	        			<td><%= book.getTitle() %></td>
	        			<td><%= book.getQuantity() %></td>
                <td><%= book.getYear() %></td>
                <td>
                  <a href="/book-form?id=<%=book.getId()%>">Edition</a>
                </td>
                <td>
                  <a href="/book-recap?id=<%=book.getId()%>">Ajouter au panier</a>
                </td>
                <td>
                  <a href="/book-remove?id=<%=book.getId()%>">Supprimer</a>
                </td>
	        		</tr>
	        	<% } %>
        		</tbody>
        	</table>
        </div>
        <% if(books.isEmpty()){ %>
          <div class="left">
            <a href="/init">Initialiser les livres de la librairie</a>
          </div>
        <% } %>
        <br>
        <div class="right">
          <a href="/book-form">Ajouter un livre à la librairie</a>
        </div>
    </body>
</html>
