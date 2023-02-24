<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Oglasi - Odgovor na oglas</h1>
	<c:if test="${!empty greska}">
		<h3>${greska}</h3>
	</c:if>
	<form action="/OglasiWEB/JavljanjeNaOglasServlet" method="post">
		<table>
			<tr>
				<td>Oglas id</td>
				<td><input type="text" readonly="readonly"
					value="${param.adId}" name="adId"></td>
			</tr>
			<tr>
				<td>Text</td>
				<td><textarea name="text"></textarea></td>
			</tr>
		</table>
		<input type="submit" value="Respond">
	</form>
</body>
</html>