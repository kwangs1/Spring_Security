<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="common/header.jsp">
	<jsp:param name="pageTitle" value="Login"/>
</jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

<h1>Please Log In to Your Account</h1>
<p>
	Please use the form below to log in to your account.
</p>
<!-- form action 은 UsernamePasswordAuthenticationFilter 서블릿 필터에서 설정한 액션과 일치 -->
<form action="j_spring_security_check" method="post">
	<label for="j_username">ID</label>:
	<input id="j_username" name="j_username" size="20" maxlength="50" type="text" />
	<br />

	<label for="j_password">Password</label>:
	<input id="j_password" name="j_password" size="20" maxlength="50" type="password" />
	<br />
	
	<input type="submit" value="Login" />
</form>
</body>
</html>