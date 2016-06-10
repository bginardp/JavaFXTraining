<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ca">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <title>Llista usuaris</title>
</head>
<body>

<div class="container">


<h1>Llista d'usuaris</h1>
<p> Llista d'usuaris de mostra </p>

<div class="table-responsive">
<table class="table table-condensed">
	<tr>
		<th>User</th>
		<th>Name</th>
		<th>LastName</th>
	</tr>
	
	<c:forEach var="user" items="${users}">
		<tr>
			<td>${user.username}</td>
			<td>${user.firstName}</td>
			<td>${user.lastName}</td>
		</tr>
	</c:forEach>
	
</table>
</div>
</div>
</body>
</html>