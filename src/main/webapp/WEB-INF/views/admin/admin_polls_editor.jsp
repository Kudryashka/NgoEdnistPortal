<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title }</title>
<link rel="stylesheet"  type="text/css" href="/resources/css/admin-panel.css">
</head>
<body>
	<div class="control-panel">
		<c:forEach var="controlItem" items="${controls }">
		<a href="${controlItem.uri }" class="control-link">${controlItem.name }</a>
		</c:forEach>
	</div>
	<h1>Polls Editor</h1>
	<p>//TODO
</body>
</html>