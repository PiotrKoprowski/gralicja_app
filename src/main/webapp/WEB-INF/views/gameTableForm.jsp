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

		<form:form class="w3-container w3-card-4" modelAttribute="gameTable"
			method="post">
			<div class="w3-section">
				<form:label path="tableName">Nazwa stolika</form:label>
				<form:input class="w3-input" type="text" path="tableName"
					placeholder="Nazwa" />
				<form:errors path="tableName"></form:errors>
			</div>
			<div class="w3-section">
				<form:label path="boardGame.id">Gra</form:label>
				<form:select class="w3-input" path="boardGame">
					<form:option value="${games}">Wybierz gre</form:option>
					<form:options items="${games}" itemValue="id" itemLabel="title"></form:options>
				</form:select>
				<form:errors path="boardGame"></form:errors>
			</div>
			<div class="w3-section">
				<form:label path="maxNumOfPlayers">Liczba graczy</form:label>
				<form:input class="w3-input" path="maxNumOfPlayers" type="number" />
				<form:errors path="maxNumOfPlayers"></form:errors>
			</div>
			<div class="w3-section">
				<form:label path="familiarWithGame">Znasz zasady gry?</form:label>
				<form:checkbox class="w3-check" path="familiarWithGame" />
				<form:errors path="familiarWithGame"></form:errors>
			</div>
			<div class="w3-section">
				<form:label path="day">Dzień</form:label>
				<form:select class="w3-input" path="day">
					<form:option value="">Wybierz dzień</form:option>
					<form:options items="${days}"></form:options>
				</form:select>
				<form:errors path="day"></form:errors>
			</div>
			<div class="w3-section">
				<form:label path="startingHour">Godzina</form:label>
				<form:select class="w3-input" path="startingHour">
					<form:option value="">Wybierz godzine</form:option>
					<form:options items="${hours}"></form:options>
				</form:select>
				<form:errors path="startingHour"></form:errors>
			</div>
			<div class="w3-section">
				<input class="w3-btn w3-theme" type="submit" value="Załóż stolik!" />
			</div>
		</form:form>
	</div>
	<hr>
<%@ include file="jspf/footer.jspf"%>
</body>
</html>