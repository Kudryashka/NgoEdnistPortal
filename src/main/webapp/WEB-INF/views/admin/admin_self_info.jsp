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
	<div class="navigation-panel">
		<p><strong>Admin panel</strong>
		<div>
			<ul>
				<c:forEach var="menuItem" items="${navigationMenu }">
				<li class="navigation-item"><a href="${menuItem.uri }">${menuItem.name }</a></li>
				</c:forEach> 
			</ul>
		</div>
		<br>
	</div>
	<div class="control-panel">
		<c:forEach var="controlItem" items="${controls }">
		<a href="${controlItem.uri }" class="control-link">${controlItem.name }</a>
		</c:forEach>
	</div>
	<div class="page-content">
		<h1>${pageName }</h1>
		<img alt="Logo image should be here." src="${selfInfo.logoPath }" class="logo-preview">
		<p><strong>NGO name:</strong> ${selfInfo.name }</p>
		<p><strong>NGO short name:</strong> ${selfInfo.shortName }</p>
		<p class="overview-box-label">NGO overview:</p>
		<div class="overview-box">${selfInfo.overview }</div>
	</div>
</body>
</html>