<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <%@include file="style.jsp" %>
        <title>Error</title>
    </head>
    <body>
        <%
        	int status = (Integer) (request.getAttribute("status"));
        	String error = (String) request.getAttribute("error");
        	response.setStatus(status);
        %>
        <h2>
        	Status de l'erreur : <%= status %>
        </h2>
        <p>
        	<%= error %>
        </p>
        <p>
        	<a href="/books">Back</a>
        </p>
    </body>
</html>
