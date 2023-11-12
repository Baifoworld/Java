<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu Ticktets</title>
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
						<form class="row g-3" action="gestiontickets" method="post">
							<div class="col-md-6">
								<label for="inputIdCliente" class="form-label">*ID Cliente:</label> <input
									type="text" class="form-control" name="idCliente">
							</div>
							<div class="col-md-6">
								<label for="inputFechaInicio" class="form-label">*Fecha Inicio:</label> <input type="text" class="form-control" name="fechaInicio">
							</div>
							<div class="col-md-6">
								<label for="inputFechaFin" class="form-label">Fecha Fin:</label> <input type="text" class="form-control" name="fechaFin">
							</div>
							<div class="col-md-6">
								<label for="inputEstado" class="form-label">*Estado</label>
								<input type="text" class="form-control" name="estado">
							</div>
							<div class="col-md-12">
								<label for="inputDescripcion" class="form-label">*Descripción:</label> <input
									type="text" class="form-control" name="descripcion">
							</div>
							<div class="col-12" align="left">
								<input type="submit" name="opciones" value="Crear Ticket"
									class="btn btn-primary">
							</div>
						</form>
					</td>
					<td align="center" style="padding-top: 100px;">

						<form action="gestiontickets" method="post">
							<label for="inputIdTicket" class="form-label">Buscar por ID de Ticket</label>
							<p>
								<input type="text" name="idTicket">
							</p>
							<p>
								<input type="submit" name="opciones" value="Buscar ticket"
									class="btn btn-primary">
							</p>
						</form>
					</td>
					<td align="center" style="padding-top: 100px;">
						<form action="gestiontickets" method="post">
							<label for="inputIdTicket" class="form-label">Eliminar Ticket:</label>
							<p>
								<input type="text" name="idTicket" placeholder="Id Ticket">
							</p>
							<p>
								<input type="submit" name="opciones" value="Eliminar Ticket"
									class="btn btn-primary">
							</p>
						</form>
					</td>
					<td align="center" style="padding-top: 100px;"><label
						for="inputTicket" class="form-label">Ver todos los
							tickets:</label>
						<form action="gestionticket" method="post">
							<input type="submit" value="Ver Tickets" name="opciones"
								class="btn btn-primary">
						</form></td>
					<td align="center" style="padding-top: 100px;"><label
						for="inputIdTicket" class="form-label"> Editar ticket: </label>
						<form action="editarticket" method="post">
							<p>
								<input type="text" name="idTicket">
							</p>
							<input type="submit" value="Editar Ticket" name="opciones"
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