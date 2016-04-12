<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title }</title>
<link rel="stylesheet" type="text/css" href="/resources/css/admin-panel.css">
<link rel="stylesheet" type="text/css" href="/resources/css/admin-constructor.css">
</head>
<body>
	<div class="control-panel">
		<c:forEach var="controlItem" items="${controls }">
		<a href="${controlItem.uri }" class="control-link">${controlItem.name }</a>
		</c:forEach>
	</div>
	<h1>${pageName }</h1>
	<form:form modelAttribute="selfInfo" enctype="multipart/form-data">
		<div class="content-with-image-preview">
			<div class="image-preview">
				<div>
					<img id="preview-image" alt="NGO logo shoulf be shown here." src="${selfInfo.logoPath }">
					<p><span class="label-at-top">Change logo:</span><form:input type="file" path="logoFile" onchange="loadPreview(event)"/></p>
				</div>
			</div>
			<div class="content-near-image-preview">
				<p class="value-box"><span class="label-at-left">NGO name:</span><form:input path="name"/></p>
				<p class="value-box"><span class="label-at-left">NGO short name:</span><form:input path="shortName"/></p>
				<p class="value-box"><span class="label-at-top">NGO overview:</span><form:textarea path="overview"/></p>
			</div>
		</div> 
		<input type="submit" value="Update">
	</form:form>
	<script type="text/javascript">
		var loadPreview = function(event) {
			var img = document.getElementById('preview-image');
			img.src = URL.createObjectURL(event.target.files[0]);
		}
	</script>
</body>
</html>