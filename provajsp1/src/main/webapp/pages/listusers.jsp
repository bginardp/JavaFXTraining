<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="ca">
<head>
<title>Llista usuaris</title>
<jsp:include page="/include/estilos.jsp"></jsp:include>
<jsp:include page="/include/header.jsp"></jsp:include>

</head>
<body>
	<header>
		<h1>Llista d'usuaris</h1>
		<p>Llista d'usuaris de mostra</p>
	</header>
	<div class="container">
		<section>
			<form role="form" action="llista" method="POST">
				<div class="form-group">
				    <label for="fname">First Name</label>
					<input type="text" name="fname">
					<label for="lname">Last name</label>
					<input type="text" name="lname">
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
		</section>
		<section>
		<div class="table-responsive">
			<table class="table table-condensed">
				<tr>
					<th>Usuari</th>
					<th>Nom</th>
					<th>Primer llinatge</th>
					<th>Segon llinatge</th>
					<th>Data naixement</th>
					<th>Telèfono</th>
				</tr>
				<c:forEach var="user" items="${users}">
					<tr>
						<td>${user.id}</td>
						<td>${user.nom}</td>
						<td>${user.ll1}</td>
						<td>${user.ll2}</td>
						<td>${user.datnai}</td>
						
					</tr>
				</c:forEach>
			</table>
		 </div>
		</section>
	</div>
	
</body>
<footer> Copyright © a-palma.ca </footer>	
</html>