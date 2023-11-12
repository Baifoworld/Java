<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestores</title>

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
	<h3>Crear Gestor</h3>
	<form class="form" action="agregargestor" method="POST">
	<p> *DNI: <input type="text" name="gestorId"> </p>
	<p> *Nombre: <input type="text" name="gestorNombre"> </p>
    <p> Apellidos: <input type="text" name="gestorApellido"> </p>
    <p> <input type="submit" value="Crear"> </p>
	</form>
</td>
<td>
	<h3>Eliminar Gestor</h3>
	<form class="form"  action="eliminargestor" method="POST">
	<p> *DNI: <input type="text" name="gestorId"> </p>
    <p> <input type="submit" value="Eliminar"> </p>
	</form>
</td>
<td>
	<h3>Modificar Gestor</h3>
	<form class="form" action="modificargestor" method="POST">
	<p> *DNI: <input type="text" name="gestorId"> </p>
	<p> Nombre: <input type="text" name="gestorNombre"> </p>
    <p> Apellidos: <input type="text" name="gestorApellidos"> </p>
    <p> <input type="submit" value="Modificar"> </p>
	</form>
</td>
<td>
	<h3>Mostrar Gestor</h3>
	<form class="form" action="mostrargestor" method="POST">
	<p> *DNI: <input type="text" name="gestorId"> </p>
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