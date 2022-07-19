<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
	<h1>Please Log In to Your Account</h1>
<p>
	Please use the form below to log in to your account.
</p>
<form action="j_spring_security_check" method="post">
	<label for="loginid">Login</label>:
	<input id="loginid" name="loginid" size="20" maxlength="50" type="text"/>
	<br />
	
	
	<label for="loginpwd">Password</label>:
	<input id="loginpwd" name="loginpwd" size="20" maxlength="50" type="password"/>
	<br />

	<input id="_spring_security_remember_me" name="_spring_security_remember_me" type="checkbox" value="true" />
	<label for="_spring_security_remember_me">Remeber Me?</label>
	<br/>
	<input type="submit" value="login" />
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

</body>
</html>


