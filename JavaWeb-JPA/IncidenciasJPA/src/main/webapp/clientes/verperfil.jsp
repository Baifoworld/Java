<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mi perfil</title>
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
		<div table></div>
			<form class="row g-3" action="vercliente" method="POST">
			  <div class="col-md-6">
			    <label for="inputIdCliente" class="form-label">Id Cliente</label>
			    <input type="text" class="form-control" name="idCliente" value="${cliente.getIdCliente()}">
			  </div>
			  <div class="col-md-6">
			    <label for="inputContraseña" class="form-label">Contraseña</label>
			    <input type="password" class="form-control" name="contraseña">
			  </div>
			  <div class="col-md-6">
			    <label for="inputContraseña" class="form-label">Email:</label>
			    <input type="text" class="form-control" name="email" value="${cliente.getUsuario().getEmail()}">
			  </div>
			  <div class="col-md-6">
			    <label for="inputNombre" class="form-label">Nombre</label>
			    <input type="text" class="form-control" name="nombre" value="${cliente.getNombre()}">
			  </div>
			  <div class="col-md-6">
			    <label for="inputNick" class="form-label">Nick</label>
			    <input type="text" class="form-control" name="nick" value="${cliente.getUsuario().getNick()}">
			  </div>
			  <div class="col-md-4">
			    <label for="inputTelefono" class="form-label">Telefono</label>
			    <input type="text" class="form-control" name="telefono" value="${cliente.getTelefono()}">
			  </div>
			  <div class="col-12">
			    <label for="inputDireccion" class="form-label">Direccion</label>
			    <input type="text" class="form-control" name="direccion" value="${cliente.getDireccion()}">
			  </div>
			  <div class="col-12">
			    <button type="submit" name="opciones" value="Modificar cliente" class="btn btn-primary">Modificar informacion</button>
			  </div>
			  <div class="col-12">
			    <button type="submit" name="opciones" value="Eliminar cliente" class="btn btn-primary">Borrar cuenta</button>
			  </div>
			</form>
	</div>
</div>

<a href=<%=request.getServletContext().getContextPath()%>/clientes/menu.jsp>Volver</a>
<c:if test="${mensaje != null && !mensaje.isEmpty()}">

<p>${mensaje} </p>
</c:if>
</body>
</html>