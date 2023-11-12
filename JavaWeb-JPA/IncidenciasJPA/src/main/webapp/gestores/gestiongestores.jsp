<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu gestores</title>
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
									gestor:</label> <input type="text" class="form-control" name="dni">
							</div>
							<div class="col-md-6">
								<label for="inputNickUsuario" class="form-label">Nick
									usuario</label> <input type="text" class="form-control" name="nick">
							</div>
							<div class="col-md-6">
								<label for="inputNombre" class="form-label">Nombre</label> <input
									type="text" class="form-control" name="nombre">
							</div>
							<div class="col-md-6">
								<label for="inputApellidos" class="form-label">Apellidos</label>
								<input type="text" class="form-control" name="apellidos">
							</div>
							<div class="col-md-6">
								<label for="inputEmail" class="form-label"> Email:</label> <input
									type="text" class="form-control" name="email">
							</div>
							<div class="col-md-6">
								<label for="inputPassword" class="form-label">
									Contraseña:</label> <input type="password" class="form-control"
									name="password">
							</div>
							<div class="col-12" align="left">
								<input type="submit" name="opciones" value="Crear usuario"
									class="btn btn-primary">
							</div>
						</form>
					</td>
					<td align="center" style="padding-top: 100px;">

						<form action="gestiongestores" method="post">
							<label for="inputDniGestor" class="form-label">Buscar por
								dni gestor:</label>
							<p>
								<input type="text" name="dni">
							</p>
							<p>
								<input type="submit" name="opciones" value="Buscar gestor"
									class="btn btn-primary">
							</p>
						</form>
					</td>
					<td align="center" style="padding-top: 100px;">
						<form action="gestiongestores" method="post">
							<label for="inputDniGestor" class="form-label">Eliminar
								gestor:</label>
							<p>
								<input type="text" name="dni" placeholder="dni gestor">
							</p>
							<p>
								<input type="text" name="nick" placeholder="nick usuario">
							</p>
							<p>
								<input type="submit" name="opciones" value="Eliminar gestor"
									class="btn btn-primary">
							</p>
						</form>
					</td>
					<td align="center" style="padding-top: 100px;"><label
						for="inputDniGestor" class="form-label">Ver todos los
							gestores:</label>
						<form action="gestiongestores" method="post">
							<input type="submit" value="Ver gestores" name="opciones"
								class="btn btn-primary">
						</form></td>
					<td align="center" style="padding-top: 100px;"><label
						for="inputDniGestor" class="form-label"> Editar gestor: </label>
						<form action="editargestor" method="post">
							<p>
								<input type="text" name="dni">
							</p>
							<input type="submit" value="Editar" name="opciones"
								class="btn btn-primary">
						</form></td>
				</tr>
			</table>
			<p style="color: green">
				<%
				if (null != request.getAttribute("deletedTrueMessage")) {
					out.println(request.getAttribute("deletedTrueMessage"));
				}
				%>
			</p>
			<p style="color: red">
				<%
				if (null != request.getAttribute("deletedFalseMessage")) {
					out.println(request.getAttribute("deletedFalseMessage"));
				}
				%>
			</p>
			<a href=<%=request.getServletContext().getContextPath()%>
				/gestores/dashboard.jsp> Volver. </a>

		</div>
	</div>
	<div class="container" align="center" style="padding-top: 100px;">
</body>
</html>