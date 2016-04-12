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
	<div class="page-content">
		<h1>${pageName }</h1>
		<c:choose>
		<c:when test="${fn:length(projects) > 0 }">
			<c:forEach var="project" items="${projects }">
			<div class="content-list-item">
				<div class="control-link-container">
					<a href="/admin/projects/${project.uriAlias }/edit" class="control-link">Edit</a>
					<a href="/projects/${project.uriAlias }" class="control-link">Go to project page</a>
				</div>
				<h2><a href="/projects/${project.uriAlias }">${project.name }</a></h2>
				<p class="meta-info">${project.description }
			</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<p><center><strong>No one project found</strong></center>
		</c:otherwise>
		</c:choose>
	</div>
</body>
</html>