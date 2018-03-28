<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@ include file="jspf/head_config.jspf"%>

<title>Gralicja App</title>
</head>
<body>
	<%@ include file="jspf/header.jspf"%>
	<%@ include file="jspf/main_menu.jspf"%>
	<p class='error'>${msg}</p>
	<a href="${pageContext.request.contextPath}/boardGames/add">Dodaj gre</a>
	<a href="${pageContext.request.contextPath}/gameTable/add">Załóż stolik z grą</a>
	Strona Domowa
	<%@ include file="jspf/footer.jspf"%>
</body>
</html>