<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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


			<form class="w3-container w3-card-4" method="post">
				<h2>Logowanie</h2>
				<div class="w3-section">
					Login <input class="w3-input" type="text" name="username" />
				</div>
				<div class="w3-section">
					Hasło <input class="w3-input" type="password" name="password" />
				</div>
				<div class="w3-section">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /> <input class="w3-btn w3-theme"
						type="submit" value='Zaloguj się!'/>
				</div>
			</form>
		</div>
	</div>
	<hr>
	<hr>
	<%@ include file="jspf/footer.jspf"%>
</body>
</html>