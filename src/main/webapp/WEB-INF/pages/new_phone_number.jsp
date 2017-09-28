<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>

    <title><spring:message code="new_phone_number.title"/> </title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="container">
    <h3 style="margin-top: 10px"><spring:message code="new_phone_number.title"/> </h3>
    <form action="<c:url value="/home/new_phone_number"/> " method="POST">
        <c:if test="${wrongFormat != null}">
            <div class="alert alert-danger" role="alert">
                <spring:message code="new_phone_number.error.format"/>
            </div>
        </c:if>
        <c:if test="${noMatches != null}">
            <div class="alert alert-danger" role="alert">
                <spring:message code="new_phone_number.error.no_matches"/>
            </div>
        </c:if>
        <div class="form-group">
            <label for="inputNewPhoneNumber"><spring:message code="new_phone_number.title"/> </label>
            <input type="text" name="newPhoneNumber" class="form-control" id="inputNewPhoneNumber"
                   placeholder="<spring:message code="new_phone_number.placeholder"/> " autofocus="autofocus">
        </div>
        <div class="form-group">
            <label for="inputConfirmNewPhoneNumber"><spring:message code="new_phone_number.confirm_phone_number"/> </label>
            <input type="text" name="confirmNewPhoneNumber" class="form-control" id="inputConfirmNewPhoneNumber"
                   placeholder="<spring:message code="new_phone_number.placeholder"/> ">
        </div>
        <button type="submit" class="btn btn-success"><spring:message code="new_phone_number.button.change"/> </button>
        <a href="${contextPath}/home" role="button" class="btn btn-info"><spring:message code="new_phone_number.button.back"/> </a>
        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
    </form>
</div>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
