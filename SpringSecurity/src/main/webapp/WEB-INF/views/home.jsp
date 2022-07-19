<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<div id="header">
		<%-- End of Ch 3 --%>
		<div class="username">
			<c:if test="${principal.username}">
	    	Welcome,  <strong><sec:authentication
						property="principal.username" /></strong>
			</c:if>
			<c:if test="${!principal.username}">
	    	Welcome,  <strong><sec:authentication property="name" /></strong>
			</c:if>
		</div>
		<ul>
			<c:url value="/logout" var="logoutUrl" />
			<li><a href="${logoutUrl}">Log Out</a></li>
		</ul>
	</div>

<P>  The time on the server is ${serverTime}. </P>

</body>
</html>