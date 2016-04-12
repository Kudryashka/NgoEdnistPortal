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
		<c:choose>
		<c:when test="${fn:length(polls) > 0 }">
			<p>Polls count: ${fn:length(polls) }</p>
			<c:forEach var="poll" items="${polls }" varStatus="tagStatus">
			<div class="content-list-item">
				<div class="control-link-container">
					<a href="/admin/polls/${poll.uriAlias }/edit" class="control-link">Edit</a>
					<a href="/polls/${poll.uriAlias }" class="control-link">Go to poll</a>
				</div>
				<h2><a href="/polls/${poll.uriAlias }">${poll.name }</a></h2>
				<p style="color: green">Status: ${poll.status }
				<p class="meta-info">Dates create - last update: ${poll.insertTime } - ${poll.modifyTime }
				<p class="meta-info">Dates poll start - poll obsolete: ${poll.pollStartTime } - ${poll.pollObsoleteTime }
				<div style="text-align: right;">
					<a href="/admin/polls/${poll.uriAlias }/answers" class="control-link">Answers (${pollAnswerCounts[tagStatus.index] })</a>
				</div>
			</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<p><center><strong>No one poll found</strong></center>	
		</c:otherwise>
		</c:choose>
	</div>
</body>
</html>