<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<style type="text/css">
</style>
<style type="text/css">
  
</style>
</head>
<body>
<div>
	<h2>Register</h2><br>
</div><br>

	<form action="/PlanerWEB/RegisterServlet" method="post">
		<h4>Firstname: </h4>
		<input type="text" name="firstname" id="firstname"/><br>
		<h4>Lastname: </h4>
		<input type="text" name="lastname" id="lastname"/><br>
		<h4>Username: </h4>
		<input type="text" name="email" id="email"/><br>
		<h4>Password: </h4>
		<input type="password" name="password" id="password"/><br><br>
		<input type="submit" value="Register"/><br>
	</form>
<br>
<a href="/PlanerWEB/index.jsp">Home</a><br>
</body>
</html>