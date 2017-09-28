<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>

    <title><spring:message code="new_last_name.title"/></title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="container">
    <h3 style="margin-top: 10px"><spring:message code="new_last_name.title"/></h3>
    <form action="<c:url value="${contextPath}/home/new_last_name"/> " method="POST">
        <c:if test="${length != null}">
            <div class="alert alert-danger" role="alert">
                <spring:message code="new_last_name.error.length"/>
            </div>
        </c:if>
        <c:if test="${noMatches != null}">
            <div class="alert alert-danger" role="alert">
                <spring:message code="new_last_name.error.no_matches"/>
            </div>
        </c:if>
        <div class="form-group">
            <label for="inputNewLastName"><spring:message code="new_last_name.title"/> </label>
            <input type="text" name="newLastName" class="form-control" id="inputNewLastName"
                   placeholder="<spring:message code="new_last_name.placeholder"/> " autofocus="autofocus">
        </div>
        <div class="form-group">
            <label for="inputConfirmNewLastName"><spring:message code="new_last_name.confirm_last_name"/> </label>
            <input type="text" name="confirmNewLastName" class="form-control" id="inputConfirmNewLastName"
                   placeholder="<spring:message code="new_last_name.placeholder"/> ">
        </div>
        <button type="submit" class="btn btn-success"><spring:message code="new_last_name.button.change"/></button>
        <a href="${contextPath}/home" role="button" class="btn btn-info"><spring:message
                code="new_last_name.button.back"/> </a>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</div>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
