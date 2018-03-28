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

	<form:form modelAttribute="boardGame" method="post">
		<div>
			<form:label path="title">Tytuł</form:label>
			<form:input type="text" path="title" placeholder="Tytuł" />
			<form:errors path="title"></form:errors>
		</div>
		<div>
			<form:label path="description">Opis</form:label>
			<form:textarea path="description" placeholder="Opis" />
			<form:errors path="description"></form:errors>
		</div>
		<div>
			<form:label path="category">Kategoria</form:label>
			<form:select path="category">
				<form:option value="">Wybierz kategorie</form:option>
				<form:options items="${categories}"></form:options>
			</form:select>
			<form:errors path="category"></form:errors>
		</div>
		<div>
			<form:label path="publisher">Wydawca</form:label>
			<form:select path="publisher">
				<form:option value="">Wybierz wydawcę</form:option>
				<form:options items="${publishers}"></form:options>
			</form:select>
			<form:errors path="publisher"></form:errors>
		</div>
		<div>
			<form:label path="minNumOfPlayers">Minimalna liczba graczy</form:label>
			<form:input path="minNumOfPlayers" type="number" placeholder="Minimum 2 graczy" />
			<form:errors path="minNumOfPlayers"></form:errors>
		</div>
		<div>
			<form:label path="maxNumOfPlayers">Maksymalna liczba graczy</form:label>
			<form:input path="maxNumOfPlayers" type="number" placeholder="Maksiumum 30 graczy" />
			<form:errors path="maxNumOfPlayers"></form:errors>
		</div>
		<div>
			<form:label path="ageRange">Przedział wiekowy</form:label>
			<form:input path="ageRange" placeholder="Przedział wiekowy" />
			<form:errors path="ageRange"></form:errors>
		</div>
		<div>
			<form:label path="gameLength">Długość rozgrywki</form:label>
			<form:input path="gameLength" type="number"/>
			<form:errors path="gameLength"></form:errors>
		</div>
		<div>
			<input type="submit" />
		</div>
	</form:form>

	<%@ include file="jspf/footer.jspf"%>
</body>
</html>