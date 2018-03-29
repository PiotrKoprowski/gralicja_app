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
<div class="w3-quarter" onclick="location.href='${pageContext.request.contextPath}'">
  <div class="w3-card w3-container" style="min-height:220px">
  <h3>Lista gier</h3><br>
  <i class="fas fa-cube w3-margin-bottom w3-text-theme" style="font-size:120px"></i>
  </div>
</div>
<div class="w3-quarter" onclick="location.href='${pageContext.request.contextPath}'">
  <div class="w3-card w3-container" style="min-height:220px">
  <h3>Plan wydarzenia</h3><br>
  <i class="far fa-list-alt w3-margin-bottom w3-text-theme" style="font-size:120px"></i>
  </div>
</div>

<div class="w3-quarter" onclick="location.href='${pageContext.request.contextPath}'">
  <div class="w3-card w3-container" style="min-height:220px">
  <h3>Konkursy</h3><br>
  <i class="fas fa-chess-queen w3-margin-bottom w3-text-theme" style="font-size:120px"></i>
  </div>
</div>

<div class="w3-quarter" onclick="location.href='${pageContext.request.contextPath}'">
  <div class="w3-card w3-container" style="min-height:220px">
  <h3>Mapa</h3><br>
  <i class="fab fa-leanpub w3-margin-bottom w3-text-theme" style="font-size:120px"></i>
  </div>
</div>
</div>


<hr>
<h2 class="w3-center">Lista stolików z grami</h2>

<button onclick="location.href='${pageContext.request.contextPath}/gameTables/add'" class="w3-padding-16 w3-red w3-button w3-block w3-left-align">Załóż stolik</button>

<button onclick="myAccFunc('Demo2')" class="w3-padding-16 w3-theme w3-button w3-block w3-left-align">Open Section 2</button>
<div id="Demo2" class="w3-hide">
  <a href="#" class="w3-button w3-block w3-left-align">Link 1</a>
  <a href="#" class="w3-button w3-block w3-left-align">Link 2</a>
  <a href="#" class="w3-button w3-block w3-left-align">Link 3</a>
</div>
<button onclick="myAccFunc('Demo3')" class="w3-padding-16 w3-theme w3-button w3-block w3-left-align">Open Section 3</button>
<div id="Demo3" class="w3-hide w3-black">
  <div class="w3-container">
    
  </div>
</div>

<hr>
<%@ include file="jspf/footer.jspf"%>
<!-- Script for Sidebar, Tabs, Accordions, Progress bars and slideshows -->
<script>

//Accordions
function myAccFunc(id) {
    var x = document.getElementById(id);
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
    } else { 
        x.className = x.className.replace(" w3-show", "");
    }
}
function w3_open() {
    if (mySidebar.style.display === 'block') {
        mySidebar.style.display = 'none';
    } else {
        mySidebar.style.display = 'block';
    }
}
function w3_close() {
    mySidebar.style.display = "none";
}
</script>

<script defer src="https://use.fontawesome.com/releases/v5.0.9/js/all.js" integrity="sha384-8iPTk2s/jMVj81dnzb/iFR2sdA7u06vHJyyLlAd4snFpCl/SnyUjRrbdJsw1pGIl" crossorigin="anonymous"></script>
</body>
</html>
