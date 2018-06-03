<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@ include file="jspf/head_config.jspf"%>

</head>
<body>
	<%@ include file="jspf/header.jspf"%>
	<%@ include file="jspf/main_menu.jspf"%>

	<div style="height: 50px"></div>

	<div class="w3-row-padding ">
<div class="w3-half">
<div class="w3-card white">
<br>
<h1 style='text-align:center'>Dodawanie gry</h1>
  <div class="w3-container">
		<form:form class="w3-container w3-card-4" modelAttribute="boardGame"
			method="post">
			<div class="w3-section">
				<form:label path="title">Tytuł</form:label>
				<form:input class="w3-input" type="text" path="title" />
				<form:errors path="title"></form:errors>
			</div>
			<div class="w3-section">
				<form:label path="description">Opis</form:label>
				<form:textarea class="w3-input" path="description" />
				<form:errors path="description"></form:errors>
			</div>
			<div class="w3-section">
				<form:label path="category">Kategoria</form:label>
				<form:select class="w3-input" path="category">
					<form:option value="">Wybierz kategorie</form:option>
					<form:options items="${categories}"></form:options>
				</form:select>
				<form:errors path="category"></form:errors>
			</div>
			<div class="w3-section">
				<form:label path="publisher">Wydawca</form:label>
				<form:select class="w3-input" path="publisher">
					<form:option value="">Wybierz wydawcę</form:option>
					<form:options items="${publishers}"></form:options>
				</form:select>
				<form:errors path="publisher"></form:errors>
			</div>
			<div class="w3-section">
				<form:label path="minNumOfPlayers">Minimalna liczba graczy</form:label>
				<form:input class="w3-input" path="minNumOfPlayers" type="number" />
				<form:errors path="minNumOfPlayers"></form:errors>
			</div>
			<div class="w3-section">
				<form:label path="maxNumOfPlayers">Maksymalna liczba graczy</form:label>
				<form:input class="w3-input" path="maxNumOfPlayers" type="number" />
				<form:errors path="maxNumOfPlayers"></form:errors>
			</div>
			<div class="w3-section">
				<form:label path="minPlayerAge">Minimalny wiek gracza</form:label>
				<form:input class="w3-input" path="minPlayerAge" />
				<form:errors path="minPlayerAge"></form:errors>
			</div>
			<div class="w3-section">
				<form:label path="maxPlayerAge">Maksymalny wiek gracza</form:label>
				<form:input class="w3-input" path="maxPlayerAge" />
				<form:errors path="maxPlayerAge"></form:errors>
			</div>
			<div class="w3-section">
				<form:label path="gameLength">Długość rozgrywki</form:label>
				<form:input class="w3-input" path="gameLength" type="number" />
				<form:errors path="gameLength"></form:errors>
			</div>
			<div class="w3-section">
				<input class="w3-btn w3-theme" type="submit" value="Dodaj grę!" />
			</div>
		</form:form>
	</div>
	<br>
	</div>
	</div>
	</div>
	
	<hr>
<%@ include file="jspf/footer.jspf"%>
</body>
</html>