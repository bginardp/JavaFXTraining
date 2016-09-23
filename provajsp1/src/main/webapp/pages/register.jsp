<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="usuBean" class="es.palma.provajsp1.entities.UserBean"></jsp:useBean>
<jsp:useBean id="companyBean" class="es.palma.provajsp1.entities.Company"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Registrar Usuari</title>
<jsp:include page="/include/estilos.jsp"></jsp:include>
</head>
<jsp:include page="/include/header.jsp"></jsp:include>
<body>
	<div class="container">
		<div class="jumbotron">
			<h2>Dades registre d'usuari</h2>
			<form role="form">
				<div class="form-group">
					<label for="usr">Nom</label> 
					<input type="text" class="form-control" id="usr" name="id">
				</div>
				<div class="form-group">
					<label for="pwd">Password</label> 
					<input type="password"	class="form-control" id="pwd" name = "pwd">
				</div>
				<div class="form-group">
					<label for="datn">Data naixament:</label> <input type="date"
						class="form-control" id="datnaix">
				</div>
				<button type="button" class="btn btn-primary">Acceptar</button>
				<button type="button" class="btn btn-primary">Cancel·lar</button>
			</form>
		</div>
	</div>

</body>
</html>