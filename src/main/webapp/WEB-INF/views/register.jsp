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
	<p class='error'>${msg}</p>

	<div class="w3-row-padding ">

		<div class="w3-half">

			<form:form class="w3-container w3-card-4" modelAttribute="user"
				method="post" enctype="utf8">
				<h2>Rejesteracja</h2>
				<div class="w3-section">
					<form:label path="username">Login</form:label>
					<form:input class="w3-input" path="username" />
				</div>
				<div class="w3-section">
					<form:label path="email">Email</form:label>
					<form:input class="w3-input" type="email" path="email" />
				</div>
				<div class="w3-section">
					<form:label path="password">Hasło</form:label>
					<form:password class="w3-input" path="password" />
				</div>
				<div class="w3-section">
					<form:label path="matchingPassword">Powtórz hasło</form:label>
					<form:password class="w3-input" path="matchingPassword" />
				</div>

				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<div>
					<form:errors path="*" />
				</div>
				<button class="w3-btn w3-theme" type="submit">Zarejestruj się!</button>
				<hr>
			</form:form>
			<br>
		</div>
	</div>
	<%@ include file="jspf/footer.jspf"%>
</body>
</html>