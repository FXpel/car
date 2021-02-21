<%@page import="car.tp4.entity.Author"%>
<%@page import="car.tp4.entity.Book"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <%@include file="style.jsp" %>
        <title>Auteur</title>
    </head>
    <body>
        <%
            Author author = (Author) request.getAttribute("author");
            List<Book> books = (List<Book>) request.getAttribute("books");
        %>
        <div>
          <%@include file="bar.jsp" %>
        </div>
        <h2><%= author.toString() %></h2>
        <div class="table">
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Livre</th>
                <th>Quantit&eacute;</th>
                <th>
                  Ann&eacute;e
                  <a href="/authors?id=<%= author.getId() %>&asc=<%=true%>">ASC</a>
                  <a href="/authors?id=<%= author.getId() %>&desc=<%=true%>">DESC</a>
                </th>
                <th>Informations</th>
              </tr>
            </thead>
            <tbody>
            <% for(Book book : books){ %>
              <tr>
                <td><%= book.getId() %></td>
                <td><%= book.getTitle() %></td>
                <td><%= book.getQuantity() %></td>
                <td><%= book.getYear() %></td>
                <td>
                  <a href="/book-recap?id=<%=book.getId()%>">Information</a>
                </td>
              </tr>
            <% } %>
            </tbody>
          </table>
        </div>
    </body>
</html>
