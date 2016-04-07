<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<div class="navigation-panel">
		<p><strong>Admin panel</strong></p>
		<div>
			<ul>
				<c:forEach var="menuItem" items="${navigationMenu }">
				<li class="navigation-item"><a href="${menuItem.uri }">${menuItem.name }</a></li>
				</c:forEach> 
			</ul>
		</div>
	</div>
	<div class="control-panel">
		<c:forEach var="controlItem" items="${controls }">
		<a href="${controlItem.uri }" class="control-link">${controlItem.name }</a>
		</c:forEach>
	</div>
	<h1>${pageName }</h1>
</body>
</html>