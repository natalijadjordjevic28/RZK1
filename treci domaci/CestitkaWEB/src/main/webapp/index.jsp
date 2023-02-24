<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>
		<h1>Posaljite cestitku</h1>
	<form action="/CestitkaWEB/CestitkaServlet" method="post">
		Tip cestitke: <input type="text" name="tip"><br> 
		Salje: <input type="text" name="cardFrom"><br> 
		Prima: <input type="text" name="cardTo"><br> 
		Email primaoca: <input type="text" name="email"><br>
		<input type="submit" value="Posalji">
	</form>

</body>
</html>