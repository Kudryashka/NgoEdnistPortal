<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title }</title>
<link rel="stylesheet" type="text/css" href="/resources/css/page.css">
</head>
<body>
	<div class="header">
		<img alt="${selfInfo.shortName }" src="${selfInfo.logoPath }" class="logo"><!-- 
	  	--><div><!--
	  		--><div class="organization-label-container">${selfInfo.name }</div><!--  
	  		--><ul class="hor-menu-list"><!-- 
				--><c:forEach var="menuItem" items="${navigationMenu }"><!--
				--><li><a href="${menuItem.uri }">${menuItem.name }</a></li><!-- 
				--></c:forEach>
			</ul>
		</div>
	</div>
	<div>
		<h1>About us</h1>
	</div>
</body>
</html>