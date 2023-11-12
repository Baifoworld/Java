<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Clientes</title>
</head>
<body>
<h3>Lista de Clientes</h3>
<table>
	<tr>
		<th scope="col">ID</th>
		<th scope="col">Nombre</th>
		<th scope="col">Dirección</th>
		<th scope="col">Teléfono</th>
	</tr>
	<tbody>
	
	<c:forEach var="cliente" items="${listaclientes}">
		<tr>
			<td><c:out value="${cliente.idCliente}"/></td>
			<td><c:out value="${cliente.nombreContacto}"/></td>
			<td><c:out value="${cliente.direccion}"/></td>
			<td><c:out value="${cliente.telefono}"/></td>
		</tr>
	</c:forEach>
	</tbody>
	
</table>
<a href="dashboard.jsp">Volver</a>
</body>
</html>