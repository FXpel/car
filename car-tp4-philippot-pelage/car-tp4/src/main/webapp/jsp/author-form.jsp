<%@page import="car.tp4.entity.Author"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <%@include file="style.jsp" %>
        <title>Edition d'un auteur</title>
    </head>
    <body>
        <%
            Author author = (Author) request.getAttribute("author");
        %>
        <div>
          <%@include file="bar.jsp" %>
        </div>
        <% if(author.getFirstname().equals("") && author.getLastname().equals("")){%>
          <h2>Cr&eacute;ation d'un auteur</h2>
        <%}else{%>
          <h2>Edition de <%= author.toString() %></h2>
        <%}%>
        <div class="form">
          <form role="form" method="post" action="/author-form">
            <input type="hidden" value="<%= author.getId() %>" name="id">
            <div class="row">
              <div class="label">
                <label>Pr&eacute;nom</label>
              </div>
              <div class="input">
                <input type="text" value="<%= author.getFirstname() %>" name="firstname" id="firstname" class="firstname">
              </div>
            </div>
            <div class="row">
              <div class="label">
                <label>Nom</label>
              </div>
              <div class="input">
                <input type="text" value="<%= author.getLastname() %>" name="lastname" id="lastname" class="lastname">
              </div>
            </div>
            <div class="row">
              <button type="submit">
                <% if(author.getFirstname().equals("") && author.getLastname().equals("")){%>
                  Créer
                <%}else{%>
                  Editer
                <%}%>
              </button>
            </div>
          </form>
        </div>
        <div class="left">
          <a href="/authors">Retour</a>
        </div>
    </body>
</html>
