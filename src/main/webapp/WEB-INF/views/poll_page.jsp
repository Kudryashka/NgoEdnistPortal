<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet"  type="text/css" href="/resources/poll_page.css">
</head>
<body>
	<!-- Display header -->
	<h1>${poll.name}</h1>
	<c:if test="${not empty poll.description}">
		<p><em>${poll.description}</em></p>
	</c:if>
	<hr>
	<form action="/polls/${poll.uriAlias}/answers" method="post">
		<c:choose>
		<c:when test="${fn:length(poll.pollBlocks) > 0}">
			<!-- Display all poll blocks and submit button-->
			<c:forEach var="block" items="${poll.pollBlocks}">
				<div>
					<h2>${block.name}</h2>
					<p><em>${block.description}</em></p>
					<c:choose>
					<c:when test="${fn:length(block.blockQuestions) > 0}">
						<!-- Display questions -->
						<ol>
							<c:forEach var="question" items="${block.blockQuestions}">
								<li>
									<h3>${question.name}</h3>
									<p><em>${question.description}</em></p>
									<c:choose>
									<c:when test="${fn:length(question.questionVariants) > 0}">
										<!-- Display selectable variants -->
										<ul class="selectable-list">
											<c:forEach var="variant" items="${question.questionVariants}">
												<c:choose>
												<c:when test="${question.answerType == 'single'}">
													<li><input type="radio" name="${question.id}">${variant.name}</li>
												</c:when>
												<c:otherwise>
													<li><input type="checkbox" name="${question.id}">${variant.name}</li>
												</c:otherwise>
												</c:choose>
											</c:forEach>
										</ul>
									</c:when>
									<c:otherwise>
										<!-- Display textarea -->
										<textarea name="${question.id}" class="textarea-item"></textarea>
									</c:otherwise>
									</c:choose>
								</li>
							</c:forEach>
						</ol>
					</c:when>
					<c:otherwise>
						<p>No questions</p>
					</c:otherwise>
					</c:choose>
				</div>
				<hr>
			</c:forEach>
			<input type="submit" value="Send" class="submit-button no-print">
		</c:when>
		<c:otherwise>
			<!-- Display no poll block message -->
			<p>No questions</p>
		</c:otherwise>
		</c:choose>
	</form>
</body>
</html>