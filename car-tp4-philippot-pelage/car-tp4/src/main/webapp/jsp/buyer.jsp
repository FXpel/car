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
            List<Orders> orders = (List<Orders>) request.getAttribute("orders");
        %>
        <div>
          <%@include file="bar.jsp" %>
        </div>
        <h2>Acheteurs existants</h2>
        <div class="table">
        	<table>
        		<thead>
        			<tr>
                <th>ID</th>
        				<th>Nom</th>
                <th>Informations</th>
        			</tr>
        		</thead>
        		<tbody>
        		<% for(Orders o : orders){ %>
	        		<tr>
                <td><%= o.getId() %></td>
	        			<td><%= o.getBuyer() %></td>
                <td>
                  <a href="/orders?id=<%=o.getId()%>">Information</a>
                </td>
	        		</tr>
	        	<% } %>
        		</tbody>
        	</table>
        </div>
    </body>
</html>
