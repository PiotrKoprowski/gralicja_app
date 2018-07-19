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

===============================================================================================
<h1>To jest Restowa Wersja Aplikacji</h1>

  <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="">Gralicja</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="">Home</a></li>
      </ul>
    </div>
  </div>
</nav>

<div class="jumbotron">
  <div class="container text-center">
    <h1>My Gralicja</h1>      
    <p>Some text about Gralicja</p>
  </div>
</div>

<!-- 
<div class="form container">
	<h4>Add a book</h4>
	<form id="myForm">
		<input id="id" name="id" value="" readonly="readonly" type="hidden" />
		<input name="title" placeholder="title" />
		<input name="author" placeholder="author" />
		<input name="publisher" placeholder="publisher" />
		<input name="type" placeholder="type" />
		<input name="isbn" placeholder="isbn" />
		<button id="submit" type="submit" >Add a book</button>
	</form>
</div>
 -->
 
<div class="container">
  <h2>Game Table List: </h2>
  <div class="list-group">
			  <!-- 
			    <a class="list-group-item">
			      <h4 class="list-group-item-heading">Third List Group Item Heading</h4>
			      <div>Napis 2</div>
			    </a>
			    -->
  </div>
</div>

<script type="text/javascript" src="app.js"></script>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"
        integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
        crossorigin="anonymous"></script>
        
        
===========================================================================================

<%@ include file="jspf/footer.jspf"%>
</body>
</html>
