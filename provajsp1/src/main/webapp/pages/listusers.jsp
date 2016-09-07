<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<html lang="ca">
<head>
 <jsp:include page="/include/estilos.jsp"></jsp:include>
 <jsp:include page="/include/header.jsp"></jsp:include>
 <title>Llista usuaris</title>
</head>

<body>
<div class="container">
	<header>
		<h1>Llista d'usuaris</h1>
	</header>
	
		<!--  <section>
			<form role="form" action="llista" method="POST">
				<div class="form-group">
				    <label for="fname">First Name</label>
					<input type="text" name="fname">
					<label for="lname">Last name</label>
					<input type="text" name="lname">
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
		</section> -->
		<section>
		
			<table class="table table-striped table-bordered table-hover" id="emptaula">
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
	
		</section>
	
</div> <!-- container -->	
<jsp:include page="/include/peujs.jsp"></jsp:include>
</body>
<script>
   $(document).ready(function() {
	  $('#example').DataTable();
   } );
</script>
<footer> Copyright © a-palma.ca </footer>	
</html>