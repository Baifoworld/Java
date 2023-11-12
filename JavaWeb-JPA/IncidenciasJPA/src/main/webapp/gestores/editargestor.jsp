<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar gestor</title>
</head>
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<body>
	<div class="container" align="center" style="padding-top: 100px;">
		<div class="abs-center">
			<table class="table table-bordered">
				<tr>
					<td>
						<form class="row g-3" action="gestiongestores" method="post">
							<div class="col-md-6">
								<label for="inputDniGestor" class="form-label">Dni
									gestor:</label> <input type="text" class="form-control" name="dni" value="${gestor.getDni()}">
							</div>
							<div class="col-md-6">
								<label for="inputNickUsuario" class="form-label">Nick
									usuario</label> <input type="text" class="form-control" name="nick" value="${gestor.getUsuario().getNick()}">
							</div>
							<div class="col-md-6">
								<label for="inputNombre" class="form-label">Nombre</label> <input
									type="text" class="form-control" name="nombre" value="${gestor.getNombre()}">
							</div>
							<div class="col-md-6">
								<label for="inputApellidos" class="form-label">Apellidos</label>
								<input type="text" class="form-control" name="apellidos" value="${gestor.getApellidos()}">
							</div>
							<div class="col-md-6">
								<label for="inputEmail" class="form-label"> Email:</label> <input
									type="text" class="form-control" name="email" value="${gestor.getUsuario().getEmail()}">
							</div>
							<div class="col-md-6">
								<label for="inputPassword" class="form-label">
									Contraseña:</label> <input type="password" class="form-control"
									name="password">
							</div>
							<div class="col-12" align="left">
								<input type="submit" name="opciones" value="Editar gestor"
									class="btn btn-primary">
							</div>
						</form>
					</td>
				</tr>
			</table>
			<a href=<%=request.getServletContext().getContextPath()%>
			/gestores/gestiongestores.jsp> Volver.</a>
		</div>
	</div>

</body>
</html>