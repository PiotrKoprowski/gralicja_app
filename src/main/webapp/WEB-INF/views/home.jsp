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
.bgimg-1 {
    background-position: center;
    min-height: 100%;
    background-image: url("resources/obraz.png");
        width: 100%;
        height: 100%;
        padding: 0 0 30%;
        background-size: 90%;
        background-repeat: no-repeat;
        margin-left: auto;
        margin-right: auto;
}
.przycisk:first-child{
	display: none;
}
.przycisk:last-child{
	display: inline-block;
}
</style>
<body>

<%@ include file="jspf/header.jspf"%>
<%@ include file="jspf/main_menu.jspf"%>
<p class='error'>${msg}</p>

<div style="height:30px">
</div>

<!-- Header -->
<header class="bgimg-1 w3-display-container w3-grayscale-min"  height:200px id="myHeader">
</header>

<h1>TEST CSSA</h1>
<div class="w3-row-padding w3-center w3-margin-top">
<a class="w3-quarter" href="${pageContext.request.contextPath}/boardGames/show" style="text-decoration:none">
  <div class="w3-card w3-container" style="min-height:220px">
  <h3>Lista gier</h3><br>
  <i class="fas fa-cube w3-margin-bottom w3-text-theme" style="font-size:120px"></i>
  </div>
</a>

<a class="w3-quarter" href="${pageContext.request.contextPath}" style="text-decoration:none">
  <div class="w3-card w3-container" style="min-height:220px">
  <h3>Plan wydarzenia</h3><br>
  <i class="far fa-list-alt w3-margin-bottom w3-text-theme" style="font-size:120px"></i>
  </div>
</a>

<a class="w3-quarter" href="${pageContext.request.contextPath}" style="text-decoration:none">
  <div class="w3-card w3-container" style="min-height:220px">
  <h3>Konkursy</h3><br>
  <i class="fas fa-chess-queen w3-margin-bottom w3-text-theme" style="font-size:120px"></i>
  </div>
</a>

<a class="w3-quarter" href="${pageContext.request.contextPath}" style="text-decoration:none">
  <div class="w3-card w3-container" style="min-height:220px">
  <h3>Mapa</h3><br>
  <i class="fab fa-leanpub w3-margin-bottom w3-text-theme" style="font-size:120px"></i>
  </div>
</a>
</div>


<hr>
<div class="w3-row-padding ">
<h2 class="w3-center" id="tableList">Lista stolików z grami</h2>

<a href="${pageContext.request.contextPath}/gameTables/add" class="w3-padding-16 w3-red w3-button w3-block w3-left-align" style="text-decoration:none">Załóż stolik</a>

	<c:forEach items="${gameTables}" var="gameTable">
		<button onclick="myAccFunc('stolik${gameTable.id}')" class="w3-padding-16 w3-theme w3-button w3-block w3-left-align">${gameTable.tableName} | ${gameTable.boardGame.title} | ${gameTable.day} | ${gameTable.startingHour}</button>
		<div id="stolik${gameTable.id}" class="w3-hide w3-container">
		<p>Maksymalna liczba graczy: <b>${gameTable.maxNumOfPlayers}</b></p>
		<p>Aktualna liczba graczy: <b>${gameTable.actualNumOfPlayers}</b></p>
		<p>Stolik: <b>${gameTable.numberOfTable}</b></p>
		<div>
		<div class="w3-container">
		<div class="w3-card w3-quarter">
			<p>Lista graczy</p>
			<c:forEach items="${gameTable.users}" var="u">
			<ul>
			<li>${u.username}</li>
			</ul>
			</c:forEach>
		</div>		
		</div>
			<br>
			<div>
			<c:if test="${gameTable.actualNumOfPlayers < gameTable.maxNumOfPlayers}">
			<a id="przycisk${gameTable.id}${principal.username}" href="${pageContext.request.contextPath}/gameTables/addToTable/${gameTable.id}/${principal.username}" class="w3-btn w3-green przycisk" style="text-decoration:none">Dołącz do stolika</a>
			</c:if>
			
			<c:forEach items="${gameTable.users}" var="u">
				<c:if test="${principal.username.equals(u.username)}">
				<a href="${pageContext.request.contextPath}/gameTables/deleteFromTable/${gameTable.id}/${principal.username}" class="w3-btn w3-red przycisk" style="text-decoration:none">Odejdź ze stolika</a>	
				</c:if>	
			</c:forEach>
			</div>
			
			<c:if test="${gameTable.actualNumOfPlayers == gameTable.maxNumOfPlayers}">
				<p id="przycisk${gameTable.id}${principal.username}" class="w3-btn w3-blue">Stolik jest pełny!</p>
			</c:if>
		</div>
		<br>
		</div>
	</c:forEach>
	
	
	
	
</div>
<hr>
<%@ include file="jspf/footer.jspf"%>
</body>
</html>
