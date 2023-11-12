<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu login usuario</title>
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
</head>

<body>
	<div class="container" align="center" style="padding-top: 100px;">
		<div class="abs-center">
			<aside class="col-sm-4">
				<div class="card">
					<article class="card-body">
						<form action="login" method="post">
							<div class="form-group">
								<label>Nick usuario:</label> <input name="nick"
									class="form-control" placeholder="nick" type="text" alig="left">
							</div>
							<div class="form-group">
								<label>Contraseña:</label> <input name="password"
									class="form-control" placeholder="password" type="password" alig="left">
							</div>
							<!-- form-group// -->
							<div class="form-group" style="padding: 20px;">
								<button type="submit" class="btn btn-primary btn-block"
									value="Login">Login</button>
							</div>
						</form>
					</article>
					<p style="color: red">
						<%
						if (null != request.getAttribute("errorMessage")) {
							out.println(request.getAttribute("errorMessage"));
						}
						%>
					</p>
				</div>
				</aside>
		</div>
		</div>
</body>
</html>