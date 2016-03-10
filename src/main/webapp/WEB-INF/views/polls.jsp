<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="${homeUri}">Go back</a>
	<h1>Polls list</h1>
	<c:choose>
		<c:when test="${fn:length(polls) > 0}">
			<ul>
			<c:forEach var="poll" items="${polls}">
				<li><a href="${requestScope['javax.servlet.forward.request_uri']}/${poll.uriAlias}">${poll.name}</a></li>
			</c:forEach>
			</ul>
		</c:when>
		<c:otherwise>
			<strong>Not found any poll.</strong>
		</c:otherwise>
	</c:choose>
</body>
</html>