<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu clientes</title>
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
						<form class="row g-3" action="gestionclientes" method="post">
							<div class="col-md-6">
								<label for="inputIdCliente" class="form-label">Id Cliente:</label> <input type="text" class="form-control" name="id">
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
								<label for="inputDireccion" class="form-label">Direccion</label>
								<input type="text" class="form-control" name="direccion">
							</div>
							<div class="col-md-6">
								<label for="inputTelefono" class="form-label">Telefono</label>
								<input type="text" class="form-control" name="telefono">
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
								<input type="submit" name="opciones" value="Crear cliente"
									class="btn btn-primary">
							</div>
						</form>
					</td>
					<td>
						<form class="row g-3" action="gestionclientes" method="post">
							<div class="col-md-6">
								<label for="inputIdCliente" class="form-label">*Id Cliente:</label> <input type="text" class="form-control" name="id">
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
								<label for="inputDireccion" class="form-label">Direccion</label>
								<input type="text" class="form-control" name="direccion">
							</div>
							<div class="col-md-6">
								<label for="inputTelefono" class="form-label">Telefono</label>
								<input type="text" class="form-control" name="telefono">
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
								<input type="submit" name="opciones" value="Modificar cliente"
									class="btn btn-primary">
							</div>
						</form>
					</td>
					<td>
						<form class="row g-3" action="gestionclientes" method="post">
							<div class="col-md-6">
								<label for="inputIdCliente" class="form-label">Id Cliente:</label> <input type="text" class="form-control" name="id">
							</div>
							<div class="col-md-6">
								<label for="inputNickUsuario" class="form-label">Nick
									usuario</label> <input type="text" class="form-control" name="nick">
							</div>
							<div class="col-md-6">
								<input type="submit" name="opciones" value="Eliminar cliente"
									class="btn btn-primary">
							</div>
						</form>
					</td>
					<td align="center" style="padding-top: 100px;">
						<form action="gestionclientes" method="post">
							<label for="inputIdCliente" class="form-label">Buscar por
								id cliente:</label>
							<p>
								<input type="text" name="dni">
							</p>
							<p>
								<input type="submit" name="opciones" value="Buscar cliente"
									class="btn btn-primary">
							</p>
						</form>
					</td>

				</tr>
			</table>
		</div>
	</div>
	<div class="container" align="center" style="padding-top: 100px;">
</body>
</html>