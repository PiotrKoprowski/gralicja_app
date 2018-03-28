<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="jspf/head_config.jspf"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gralicja App</title>
</head>
<body>
	<%@ include file="jspf/header.jspf"%>
	<%@ include file="jspf/main_menu.jspf"%>

	<form:form modelAttribute="user" method="post">
		<div>
			<form:label path="username">Username</form:label>
			<form:input path="username" placeholder="username"/>
			<form:errors path="username"></form:errors>
		</div>
		<div>
			<form:label path="password">Password</form:label>
			<form:password path="password" placeholder="password"/>
			<form:errors path="password"></form:errors>
		</div>
		<div>
			<form:label path="email">Email</form:label>
			<form:input type="mail" path="email" placeholder="email"/>
			<form:errors path="email"></form:errors>
		</div>
		<div>
			<input type="submit"/>
		</div>
	</form:form>

	<%@ include file="jspf/footer.jspf"%>
</body>
</html>