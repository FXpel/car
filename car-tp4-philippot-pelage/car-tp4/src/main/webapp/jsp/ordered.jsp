<%@page import="car.tp4.entity.Book"%>
<%@page import="car.tp4.entity.Buyer"%>
<%@page import="car.tp4.entity.Orders"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.List"%>
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
            Buyer buyer = (Buyer) request.getAttribute("buyer");
            List<Orders> orders = (List<Orders>) request.getAttribute("orders");
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
                <th>Ann&eacute;e</th>
        			</tr>
        		</thead>
        		<tbody>
        		<% for(Orders o : orders){ %>
	        		<tr>
                <td><%= o.getId() %></td>
	        			<td><%= o.getBook().getAuthor().toString() %></td>
	        			<td><%= o.getBook().getTitle() %></td>
	        			<td><%= o.getBook().getQuantity() %></td>
                <td><%= o.getBook().getYear() %></td>
	        		</tr>
	        	<% } %>
        		</tbody>
        	</table>
        </div>
    </body>
</html>
