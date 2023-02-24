<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Filter events</title>
<style type="text/css">
</style>
<style type="text/css">
</style>
</head>
<body>

	<h2>Filter events</h2><br>
<br>

	<form action="/PlanerWEB/EventsServlet" method="get">
		<h4>From date: </h4>
		<input type="date" name="fromDate" id="startDate"/><br><br>
		<input type="submit" value="Show"/><br>
	</form><br>
	<br>
<c:if test="${!empty allEvents}">
<div>
	<h2>Your events</h2><br>
</div><br><br>
	<div class="page">
		<div style="overflow-x: auto; justify-content: center; background-color: #215894">
		<table style="height: 80vh">
			<tr>
				<th>Description</th>
				<th>From date</th>
				<th>To date</th>
				<th>Event type</th>
			</tr>
			<c:forEach items="${allEvents}" var="e">
				<tr>
					<td>${e.description}</td>
					<td>${e.fromDate}</td>
					<td>${e.toDate}</td>
					<td>${e.eventType.name}</td>
				</tr>
			</c:forEach>
		</table>
		</div>
	</div>
	</c:if><br>
<a href="/PlanerWEB/home.jsp">Home</a><br>
</body>
</html>