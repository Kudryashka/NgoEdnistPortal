<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${initParam['ngoName']} - ${poll.name}</title>
	<link rel="stylesheet"  type="text/css" href="/resources/css/poll-page-0.0.1.css">
	<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.1.min.js"></script>
	<script type="text/javascript" src="/resources/js/poll-page-0.0.1.js"></script>
</head>
<body>
	<div id="banner-container">
		<img alt="alt" src="/resources/banner1.jpg" class="banner">
		<img alt="alt" src="/resources/banner2.jpg" class="banner">
		<img alt="alt" src="/resources/banner3.jpg" class="banner">
		<img alt="alt" src="/resources/ngo-ednist-banner.jpg" class="banner">
	</div>
	<h1>${poll.name}</h1>
	<c:if test="${not empty poll.description}">
		<p><em>${poll.description}</em></p>
	</c:if>
	<hr>
	<form:form modelAttribute="pollAnswerForm">
		<c:choose>
		<c:when test="${fn:length(poll.pollBlocks) > 0}">
			<c:forEach var="block" items="${poll.pollBlocks}">
				<div>
					<h2>${block.name}</h2>
					<c:if test="${not empty block.description}">
						<p><em>${block.description}</em></p>
					</c:if>
					<c:choose>
					<c:when test="${fn:length(block.blockQuestions) > 0}">
						<ol>
							<c:forEach var="question" items="${block.blockQuestions}">
								<li>
									<h3>${question.name}</h3>
									<c:if test="${not empty question.description}">
										<p><em>${question.description}</em></p>
									</c:if>
									<c:choose>
									<c:when test="${fn:length(question.questionVariants) > 0}">
										<ul class="selectable-list">
											<c:forEach var="variant" items="${question.questionVariants}">
												<c:choose>
												<c:when test="${question.answerType == 'single'}">
													<li>
														<form:radiobutton path="blocks[${block.id}].singleTypeAnswers['${question.id}']" value="${variant.varValue}"/>${variant.name}
														<c:if test="${not empty variant.onChooseRelativeInfo}">
															<div class="on-choose-relative-info">${variant.onChooseRelativeInfo}</div>
														</c:if>
													</li>
												</c:when>
												<c:otherwise>
													<li>
														<form:checkbox path="blocks[${block.id}].multyTypeAnswers['${question.id}']" value="${variant.varValue}"/>${variant.name}
														<c:if test="${not empty variant.onChooseRelativeInfo}">
															<div class="on-choose-relative-info">${variant.onChooseRelativeInfo}</div>
														</c:if>
													</li>
												</c:otherwise>
												</c:choose>
											</c:forEach>
										</ul>
										<c:if test="${question.existsAdditionalInput}">
											<form:textarea path="blocks[${block.id}].textTypeAnswers['${question.id}']" class="additional-input" />
										</c:if>
									</c:when>
									<c:otherwise>
										<form:textarea path="blocks[${block.id}].textTypeAnswers['${question.id}']" class="textarea-item" />
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
			<input type="submit" value="Відправити" class="submit-button no-print">
		</c:when>
		<c:otherwise>
			<p>No questions</p>
		</c:otherwise>
		</c:choose>
	</form:form>
</body>
</html>