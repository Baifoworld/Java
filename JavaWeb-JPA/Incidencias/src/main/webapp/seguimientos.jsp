<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Seguimientos</title>
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
	<h3>Crear Seguimiento</h3>
	<form class="form" action="agregarseguimiento" method="POST">
	<p> *Fecha: <input type="text" name="seguimientoFecha"> </p>
    <p> *Comentario: <input type="text" name="seguimientoComentario"> </p>
    <p> *ID Ticket: <input type="text" name="idTicket"> </p>
    <p> *DNI Gestor: <input type="text" name="seguimientodni"> </p>
    <p> <input type="submit" value="Crear"> </p>
	</form>
</td>
<td>
	<h3>Eliminar Seguimiento</h3>
	<form class="form" action="eliminarseguimiento" method="POST">
	<p> *ID Seguimiento: <input type="text" name="seguimientoId"> </p>
    <p> <input type="submit" value="Eliminar"> </p>
	</form>
</td>
<td>
	<h3>Modificar Seguimiento</h3>
	<form class="form" action="modificarseguimiento" method="POST">
	<p> *ID Seguimiento: <input type="text" name="seguimientoID"> </p>
	<p> Fecha: <input type="text" name="fechaseguimiento"> </p>
	<p> Comentario: <input type="text" name="comentarioSeguimiento"> </p>
    <p> ID Ticket: <input type="text" name="idTicket"> </p>
    <p> DNI Gestor: <input type="text" name="dniGestor"> </p>
    <p> <input type="submit" value="Modificar"> </p>
	</form>
</td>
<td>
	<h3>Mostrar Seguimiento</h3>
	<form class="form" action="mostrarseguimiento" method="POST">
	<p> *ID Seguimiento: <input type="text" name="seguimientoId"> </p>
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