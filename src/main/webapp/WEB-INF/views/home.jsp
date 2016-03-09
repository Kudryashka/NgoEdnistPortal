<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Home page</h1>
	<ul>
		<li><a href="${projectsUri}">Projects</a></li>
		<li><a href="${pollsUri}">Polls</a></li>
	</ul>
</body>
</html>