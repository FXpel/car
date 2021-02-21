<%@page import="car.tp4.entity.Book"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Map.Entry"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Achats</title>
        <%@include file="style.jsp" %>
    </head>
    <body>
        <%
          Map<Book, Integer> ordereds = (Map<Book, Integer>) request.getAttribute("ordereds");
        %>
        <div>
          <%@include file="bar.jsp" %>
        </div>
        <h2>Vos achats</h2>
        <div class="table">
        	<table>
        		<thead>
        			<tr>
        				<th>Livre</th>
        				<th>Quantit&eacute;</th>
                <th>Supprimer</th>
        			</tr>
        		</thead>
        		<tbody>
        		<% if(!ordereds.isEmpty()){
                for(Entry<Book, Integer> book : ordereds.entrySet()){ %>
    	        		<tr>
    	        			<td><%= book.getKey().getTitle() %></td>
    	        			<td><%= book.getValue() %></td>
                    <td>
                      <a href="/ordereds-remove?id=<%=book.getKey().getId()%>&quantity=<%=book.getKey().getQuantity()%>">Supprimer</a>
                    </td>
    	        		</tr>
	        	    <% } %>
            <% }else{ %>
              <tr>
                <td colspan="3">Aucun achats</td>
              </tr>
            <% } %>
        		</tbody>
        	</table>
        </div>
        <% if(!ordereds.isEmpty()){ %>
          <form action="/buyer" method="post">
            <input type="email" placeholder="Votre adresse mail" name="email" required/>
            <button type="submit">
              Acheter vos articles
            </button>
          </form>
        <% } %>
    </body>
</html>
