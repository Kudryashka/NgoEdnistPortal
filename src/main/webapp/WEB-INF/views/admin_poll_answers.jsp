<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Poll answers</h1>
	<p>Answers total count: ${fn:length(answers)}</p>
	<table border="1">
		<caption>Answers table</caption>
		<tr>
			<th>Questions</th>
			<c:forEach var="answer" items="${answers}">
				<th>Answer id: ${answer.id}</th>
			</c:forEach>
		</tr>
		<c:forEach var="block" items="${poll.pollBlocks}">
			<tr>
				<td colspan="${fn:length(answers) + 1}">Block: ${block.name}</td>
			</tr>
			<c:forEach var="question" items="${block.blockQuestions}">
				<tr>
					<td>${question.name}</td>
					<c:forEach var="answer" items="${answers}">
						<td>
							<c:forEach var="questionAnswer" items="${answer.pollQuestionAnswers}">
								<c:if test="${questionAnswer.relativePollQuestion.id == question.id}">
									${questionAnswer.answerValue}
									<c:if test="${not empty questionAnswer.additionalInput}">
										<hr>
										${questionAnswer.additionalInput}
									</c:if>
								</c:if>
							</c:forEach>
						</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</c:forEach>
	</table>
</body>
</html>