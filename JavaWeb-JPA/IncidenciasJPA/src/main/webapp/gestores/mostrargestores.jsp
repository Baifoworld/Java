<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ver gestores</title>
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
</head>
<body>

	<%
	if (null != request.getAttribute("gestores"))
		out.println("<!--");
	%>
	<div class="container" align="center" style="padding-top: 100px;">
		<table border="1px" class="table table-bordered">
			<tr>
				<td>
					<p align="center">
						<textarea rows="10" id="gestor"> ${gestor} </textarea>
					</p>
				</td>
			</tr>
		</table>
		<a href=<%=request.getServletContext().getContextPath()%>
			/gestores/gestiongestores.jsp> Volver.</a>
	</div>
	<%
	if (null != request.getAttribute("gestores"))
		out.println("-->");
	%>

	<%
	if (null != request.getAttribute("gestor"))
		out.println("<!--");
	%>
	<div class="container" align="center" style="padding-top: 100px;">
		<table border="1px" class="table table-bordered">
			<tr>
				<td>
					<p align="center">
						<textarea rows="10" id="gestores"> ${gestores} </textarea>
					</p>
				</td>
			</tr>
		</table>
		<a href=<%=request.getServletContext().getContextPath()%>
			/gestores/dashboard.jsp> Volver.</a>
	</div>
	<%
	if (null != request.getAttribute("gestor"))
		out.println("-->");
	%>



</body>
</html>