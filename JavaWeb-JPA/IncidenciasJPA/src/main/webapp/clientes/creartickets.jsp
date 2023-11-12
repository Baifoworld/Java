<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Crear Nuevo Ticket</title>
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
			<form class="row g-3">
			  <div class="col-md-6">
			    <label for="inputFechaInicio" class="form-label">Fecha Inicio</label>
			    <input type="text" class="form-control" name="fechaInicio">
			  </div>
			  <div class="col-md-6">
			    <label for="inputFechaFin" class="form-label">Fecha Fin</label>
			    <input type="text" class="form-control" name="fechaFin">
			  </div> 
			  <div class="col-md-12">
			    <label for="inputDescripcion" class="form-label">Descripción</label>
			    <input type="text" class="form-control" name="descripcion">
			  </div>
			 
			  <div class="col-12">
			    <button type="submit" class="btn btn-primary">Crear Ticket</button>
			  </div>
			</form>
	</div>
</div>

<a href=<%=request.getServletContext().getContextPath()%>
				/clientes/menu.jsp> Volver. </a>
<c:if test="${mensaje != null && !mensaje.isEmpty()}">

<p>${mensaje} </p>
</c:if>
</body>
</html>