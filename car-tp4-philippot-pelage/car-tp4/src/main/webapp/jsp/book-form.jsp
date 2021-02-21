<%@page import="car.tp4.entity.Book"%>
<%@page import="car.tp4.entity.Author"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <%@include file="style.jsp" %>
        <title>Edition d'un livre</title>
    </head>
    <body>
        <%
          Book book = (Book) request.getAttribute("book");
          List<Author> authors = (List<Author>) request.getAttribute("authors");
          String title = book.getTitle();
        %>
        <div>
          <%@include file="bar.jsp" %>
        </div>
        <% if(title.equals("")){%>
          <h2>Cr&eacute;ation d'un livre</h2>
        <%}else{%>
          <h2>Edition de <%= title %></h2>
        <%}%>
        <div class="form">
          <form role="form" method="post" action="/book-form">
            <input type="hidden" value="<%= book.getId() %>" name="id">
            <div class="row">
              <div class="label">
                <label class="lab">Titre</label>
              </div>
              <div class="input">
                <input type="text" value="<%= title %>" name="title" id="title" required class="form-control">
              </div>
            </div>
            <div class="row">
              <div class="label">
                <label>Auteur</label>
              </div>
              <div class="input">
                <select name="author" id="author" class="form-control">
                  <% for(Author author : authors){ %>
                    <% if(book.getAuthor() == null || author.getId() == book.getAuthor().getId()){%>
                      <option selected value="<%= author.getId() %>"><%= author.toString() %></option>
                    <% }else{ %>
                      <option value="<%= author.getId() %>"><%= author.toString() %></option>
                    <% } %>
                  <% } %>
                </select>
              </div>
            </div>
            <div class="row">
              <div class="label">
                <label>Quantit&eacute;</label>
              </div>
              <div class="input">
                <input type="number" value="<%= book.getQuantity() %>" name="quantity" id="quantity" required name="quantity" class="quantity">
              </div>
            </div>
            <div class="row">
              <div class="label">
                <label>Ann&eacute;e</label>
              </div>
              <div class="input">
                <input type="number" value="<%= book.getYear() %>" name="year" id="year" required name="year" class="year">
              </div>
            </div>
            <div class="row">
              <button type="submit">
                <% if(title.equals("")){%>
                  Créer
                <%}else{%>
                  Editer
                <%}%>
              </button>
            </div>
          </form>
        </div>
        <div class="left">
          <a href="/books">Retour</a>
        </div>
    </body>
</html>
