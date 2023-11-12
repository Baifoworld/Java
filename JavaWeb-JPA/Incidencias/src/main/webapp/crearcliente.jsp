<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Clientes</title>
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
			<form class="row g-3">
			  <div class="col-md-6">
			    <label for="inputIdCliente" class="form-label">Id Cliente</label>
			    <input type="text" class="form-control" id="IdCliente">
			  </div>
			  <div class="col-md-6">
			    <label for="inputContraseña" class="form-label">Contraseña</label>
			    <input type="text" class="form-control" id="contraseñaCliente">
			  </div>
			  <div class="col-md-6">
			    <label for="inputNombre" class="form-label">Nombre</label>
			    <input type="text" class="form-control" id="nombreCliente">
			  </div>
			  <div class="col-md-6">
			    <label for="inputNick" class="form-label">Nick</label>
			    <input type="text" class="form-control" id="nickCliente">
			  </div>
			  <div class="col-md-4">
			    <label for="inputTelefono" class="form-label">Telefono</label>
			    <input type="text" class="form-control" id="inputTelefono">
			  </div>
			  <div class="col-12">
			    <label for="inputDireccion" class="form-label">Direccion</label>
			    <input type="text" class="form-control" id="inputDireccion">
			  </div>
			  <div class="col-12">
			    <button type="submit" class="btn btn-primary">Crear Nuevo</button>
			  </div>
			</form>
	</div>
</div>

<a href= "user_login.jsp">Volver</a>
<c:if test="${mensaje != null && !mensaje.isEmpty()}">

<p>${mensaje} </p>
</c:if>
</body>
</html>