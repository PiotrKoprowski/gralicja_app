<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<%@ include file="jspf/head_config.jspf"%>

</head>
<style type="text/css">
.bgimg-2 {
    background-position: center;
    min-height: 100%;
    background-image: url("../resources/obraz2.png");
        width: 100%;
        height: 100%;
        padding: 0 0 50%;
        background-size: 85%;
        background-repeat: no-repeat;
        margin-left: auto;
        margin-right: auto;
}
</style>
<body>

<%@ include file="jspf/header.jspf"%>
<%@ include file="jspf/main_menu.jspf"%>
<p class='error'>${msg}</p>

<div style="height:30px">
</div>

<!-- Header -->
<header class="bgimg-2 w3-display-container w3-grayscale-min"  id="myHeaderGameList">
</header>

<hr>
<div class="w3-row-padding ">
<h1 class="w3-center">Lista gier</h1>


<button onclick="location.href='${pageContext.request.contextPath}/boardGames/add'" class="w3-padding-16 w3-green w3-button w3-block w3-left-align">Dodaj grę</button>

	<c:forEach items="${boardGameList}" var="boardGame">
		<button onclick="myAccFunc('gra${boardGame.id}')" class="w3-padding-16 w3-theme w3-button w3-block w3-left-align w3-threequarter">${boardGame.title} | ${boardGame.category}</button>
		<a href="${pageContext.request.contextPath}/gameTables/add/${boardGame.id}" class="w3-btn w3-padding-16 w3-red w3-button w3-block w3-center w3-quarter" style="text-decoration:none">Załóż stolik</a>
		<div id="gra${boardGame.id}" class="w3-hide w3-container">
		<p>Wydawca: <b>${boardGame.publisher}</b></p>
		<p>Maksymalna liczba graczy: <b>${boardGame.maxNumOfPlayers}</b></p>
		<p>Minimalna liczba graczy: <b>${boardGame.minNumOfPlayers}</b></p>
		<p>Przedział wiekowy: <b>${boardGame.ageRange}</b></p>
		<p>Długość rozgrywki: <b>${boardGame.gameLength}</b></p>
		<button onclick="myAccFunc('opis${boardGame.id}')" class="w3-padding-16  w3-light-blue w3-button w3-block w3-left-align ">Opis gry</button>
			<div id="opis${boardGame.id}" class="w3-hide w3-container w3-light-blue">
			<p>${boardGame.description}</p>
			</div>
		</div>
		<hr></hr>
	</c:forEach>
</div>
<hr>
<%@ include file="jspf/footer.jspf"%>
</body>
</html>