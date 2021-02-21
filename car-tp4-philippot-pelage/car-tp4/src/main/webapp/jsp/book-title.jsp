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
            String title = "";
            if(request.getAttribute("title") != null){
              title = (String) request.getAttribute("title");
            }
        %>
        <div>
          <%@include file="bar.jsp" %>
        </div>
        <h2>Titre recherch&eacute; : <%= title %></h2>
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
                  <a href="/book-title?title=<%=title%>&asc=<%=true%>">ASC</a>
                  <a href="/book-title?title=<%=title%>&desc=<%=true%>">DESC</a>
                </th>
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
	        		</tr>
	        	<% } %>
            </tbody>
        	</table>
        </div>
    </body>
</html>
