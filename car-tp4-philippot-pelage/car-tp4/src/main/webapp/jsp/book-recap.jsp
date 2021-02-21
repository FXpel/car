<%@page import="car.tp4.entity.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajouter au panier</title>
    </head>
    <body>
        <%
            Book book = (Book) request.getAttribute("book");
        %>
        <h2>Ajout d'un livre au panier</h2>
        <div>
          <table class="table">
            <tr>
              <th>Auteur</th>
              <td><%= book.getAuthor().toString() %></td>
            </tr>
            <tr>
              <th>Titre</th>
              <td><%= book.getTitle() %></td>
            </tr>
            <tr>
              <th>Quantit&eacute;</th>
              <td><%= book.getQuantity() %></td>
            </tr>
            <tr>
              <th>Ann&eacute;e</th>
              <td><%= book.getYear() %></td>
            </tr>
          </table>
        </div>
        <% if(book.getQuantity() > 0){ %>
          <div>
            <form method="post" action="/ordereds-add">
              <input type="hidden" value="<%= book.getId() %>" name="id">
              <div>
                <select name="quantity" id="quantity">
                  <% for(int i = book.getQuantity() ; i > 0; i--){%>
                    <option selected value="<%= i %>"><%= i %></option>
                  <% } %>
                </select>
              </div>
              <div>
                <button type="submit">
                  Ajout au panier
                </button>
              </div>
            </form>
          </div>
        <% } %>
    </body>
</html>
