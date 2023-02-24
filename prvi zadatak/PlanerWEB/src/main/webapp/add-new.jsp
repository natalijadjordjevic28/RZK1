<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<style type="text/css">
</style>
<style type="text/css">
</style>
</head>
<body>

	<h2>Add new event</h2><br>
<br>

	<form action="/PlanerWEB/AddEventServlet" method="post">
		<h4>Description: </h4>
		<input type="text" name="description" id="description"/><br>
		<h4>From date: </h4>
		<input type="datetime-local" name="inputFromDate" id="inputFromDate"/><br>
		<h4>To date: </h4>
		<input type="datetime-local" name="inputToDate" id="inputToDate"/><br>
		<h4>Event type: </h4>
		<select name="nameEventType" >
					<c:forEach items="${eventList}" var="e">
						<option value="${e.name}">${e.name}</option>
					</c:forEach>
		</select><br>
		<br><input type="submit" value="Add"/><br>
	</form>
<br>
<a href="/PlanerWEB/home.jsp">Home</a><br>
</body>
</html>