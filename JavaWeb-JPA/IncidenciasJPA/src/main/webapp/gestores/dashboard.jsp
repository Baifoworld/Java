<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
					<ul>
						<li> <a href=<%=request.getServletContext().getContextPath()%>> Volver. </a> </li>
						<li><a href=<%=request.getServletContext().getContextPath()%>/gestores/gestionclientes.jsp> Gestionar
								clientes. </a></li>
						<li><a href=<%=request.getServletContext().getContextPath()%>/gestores/gestiongestores.jsp> Gestionar
								gestores. </a></li>
						<li><a href=<%=request.getServletContext().getContextPath()%>/gestores/gestionar_tickets.jsp> Gestionar
								tickets.</a>
					</ul>
				</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>