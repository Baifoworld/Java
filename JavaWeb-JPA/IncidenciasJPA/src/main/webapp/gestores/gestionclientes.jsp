<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Clientes</title>
<style>
.form{
width: 100%;
max-width: 600px;
margin: 0 auto;
display: flex;
flex-direction: column;
justify-content: center;
align-intems: center;
}

.form input{
width: 90%;
height: 30px;
margin: 0.5rem;
}
</style>
</head>
<body>

<div style="text-align:center;">
<table border=1px >

<tr>
<td>
	<h3>Crear Cliente</h3>
	<form class="form" action="agregarcliente" method="POST">
	<p> *ID: <input type="text" name="idCliente"> </p>
	<p> *Nick: <input type="text" name="nick"> </p>
	<p> *Contraseña: <input type="text" name="password"> </p>
	<p> *Email: <input type="text" name="email"> </p>
	<p> *Nombre: <input type="text" name="nombre"> </p>
    <p> *Direccion: <input type="text" name="direccion"> </p>
    <p> Telefono: <input type="text" name="telefono"> </p>
    
    <p> <input type="submit" value="Crear"> </p>
	</form>
</td>
<td>
	<h3>Eliminar Cliente</h3>
	<form class="form" action="eliminarcliente" method="POST">
	<p> *ID: <input type="text" name="idCliente"> </p>
    <p> <input type="submit" value="Eliminar"> </p>
	</form>
</td>
<td>
	<h3>Modificar Cliente</h3>
	<form class="form" action="modificarcliente" method="POST">
	<p> *ID: <input type="text" name="idCliente"> </p>
	<p> Contraseña: <input type="text" name="contraseña"> </p>
	<p> Email: <input type="text" name="email"> </p>
	<p> Nombre: <input type="text" name="nombre"> </p>
    <p> Direccion: <input type="text" name="direccion"> </p>
    <p> Telefono: <input type="text" name="telefono"> </p>
    <p> <input type="submit" value="Modificar"> </p>
	</form>
</td>
<td>
	<h3>Mostrar Cliente</h3>
	<form class="form" action="mostrarcliente" method="POST">
	<p> ID: <input type="text" name="idCliente"> </p>
	<p> Nombre: <input type="text" name="nombre"> </p>
    <p> <input type="submit" value="Mostrar"> </p>
	</form>
</td>
</tr>
</table>
</div>
<a href= "dashboard.jsp">Volver</a>

<c:if test="${mensaje != null && !mensaje.isEmpty()}">

<p>${mensaje} </p>
</c:if>
<textarea rows="10" cols="50">
${stringJson}
</textarea>
</body>
</html>