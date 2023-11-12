<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tickets</title>
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
<table border=1px >

<tr>
<td>
	<h3>Crear Ticket</h3>
	<form class="form" action="agregarticket" method="POST">
	<p> *Fecha Inicio: <input type="text" name="fechaInicio"> </p>
    <p> Fecha Fin: <input type="text" name="fechaFin"> </p>
    <p> Descripcion: <input type="text" name="descripcion"> </p>
    <p> Estado: <input type="text" name="estado"> </p>
    <p> ID Cliente: <input type="text" name="idCliente"> </p>
    <p> *DNI Gestores: <input type="text" name="dniGestor"> </p>
    <p> <input type="submit" value="Crear"> </p>
	</form>
</td>
<td>
	<h3>Eliminar Ticket</h3>
	<form class="form" action="eliminarticket" method="POST">
	<p> *ID Ticket: <input type="number" name="IdTicket"> </p>
    <p> <input type="submit" value="Eliminar"> </p>
	</form>
</td>
<td>
	<h3>Modificar Ticket</h3>
	<form class="form" action="modificarticket" method="POST">
	<p> *ID Ticket: <input type="text" name="idTicket"> </p>
	<p> Fecha Inicio: <input type="text" name="fechaInicio"> </p>
    <p> Fecha Fin: <input type="text" name="fechaFin"> </p>
    <p> Descripcion: <input type="text" name="descripcion"> </p>
    <p> Estado: <input type="text" name="estado"> </p>
    <p> ID Cliente: <input type="text" name="idCliente"> </p>
    <p> DNI Gestores: <input type="text" name="dniGestor"> </p>
    <p> <input type="submit" value="Modificar"> </p>
	</form>
</td>
<td>
	<h3>Mostrar Ticket</h3>
	<form class="form" action="mostrarticket" method="POST">
	<p> *ID: <input type="text" name="idTicket"> </p>
    <p> <input type="submit" value="Mostrar"> </p>
	</form>
</td>
</tr>
</table>
<a href= "dashboard.jsp">Volver</a>

<c:if test="${mensaje != null && !mensaje.isEmpty()}">

<p>${mensaje} </p>
</c:if>
<textarea rows="10" cols="50">
${stringJson}
</textarea>
</body>
</html>