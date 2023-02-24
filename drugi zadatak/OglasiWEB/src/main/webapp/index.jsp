<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pocetna stranica</title>
</head>
<body>
	<h1>Dobrodosli, molimo ulogujte se!</h1><br>
	<form action="/OglasiWEB/LoginServlet" method="post">
	Username: <input type="text" name="username"><br>
	Password: <input type="password" name="pass"><br>
	<input type="submit" value="Log in">
	</form>
</body>
</html>