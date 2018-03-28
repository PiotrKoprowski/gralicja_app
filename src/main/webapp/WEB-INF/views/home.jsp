<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<%@ include file="jspf/head_config.jspf" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Gralicja App</title>
</head>
<body>
	<%@ include file="jspf/header.jspf" %>		
	<%@ include file="jspf/main_menu.jspf" %>

				<a href="${pageContext.servletContext.contextPath}/boardGames/add">Dodaj nową grę</a>
				<a href="${pageContext.servletContext.contextPath}/gameTables/add">Dodaj nowy stolik</a>

	
	<%@ include file="jspf/footer.jspf" %>
</body>
</html>