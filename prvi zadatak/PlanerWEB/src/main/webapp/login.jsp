<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<style type="text/css">
</style>
<style type="text/css">
</style>
</head>
<body>
	<div>
		<h2>Login</h2>
		<br>
	</div>
	<br>
		<form action="/PlanerWEB/LoginServlet" method="post">
			<h4>Username:</h4>
			<input type="text" name="email" id="name" />
			<h4>Password:</h4>
			<input type="password" name="password" id="password" /><br>
			<br> <input type="submit" value="Login" /><br>
		</form>
	
	<br>
			<a href="/PlanerWEB/index.jsp">Home</a>
	
	<br>
</body>
</html>