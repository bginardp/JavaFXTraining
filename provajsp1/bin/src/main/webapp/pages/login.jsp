<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>

<head>
<title>Bootstrap 101 Template</title>
<jsp:include page="/include/estilos.jsp"></jsp:include>
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<h1 class="text-center login-title">Sign in to continue to
					Bootsnipp</h1>
				<div class="account-wall">
					<img class="profile-img"
						src="/resources/images/logo.png"
						alt="">
					<form class="form-signin" action="Login" method="post">
						<input name="txtUserName" type="text" class="form-control" placeholder="Email"	required autofocus>
						 <input name="txtPass" type="password"	class="form-control" placeholder="Password" required>
						<button class="btn btn-lg btn-primary btn-block" type="submit">
							Sign in</button>
						<label class="checkbox pull-left"> <input type="checkbox"
							value="remember-me"> Remember me
						</label> <a href="#" class="pull-right need-help">Need help? </a><span
							class="clearfix"></span>
					</form>
				</div>
				<a href="pages/register.jsp" class="text-center new-account">Create an account </a>
			</div>
		</div>
	</div>
</body>
</html>