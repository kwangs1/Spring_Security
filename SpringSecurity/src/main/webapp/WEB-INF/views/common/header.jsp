<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>JBCP Pets: ${pageTitle}</title>
</head>
<body>
<div id="header">
<%-- End of Ch 3 --%>
	<div class="username">
		<c:if test="${principal.username}">
	    	Welcome,  <strong><sec:authentication property="principal.username"/></strong  >
	    </c:if>
	    <c:if test="${!principal.username}">
	    	Welcome,  <strong><sec:authentication property="name"/></strong>
	    </c:if>
	</div>
<ul>
	<c:url value="/logout" var="logoutUrl"/>
	<li><a href="${logoutUrl}">Log Out</a></li>
</ul>

</div>

