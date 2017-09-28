<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>


    <title><spring:message code="login.title"/></title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">


</head>

<body>
<jsp:include page="_header.jsp"/>
<div class="container">

    <form method="POST" action="${contextPath}/login">
        <div class="form-group" style="margin-top: 10px;">
            <label for="inputEmail"><spring:message code="login.label.email"/> </label>
            <input name="email" type="text" class="form-control" id="inputEmail" aria-describedby="emailHelp"
                   placeholder="<spring:message code="login.placeholder.email"/>" autofocus="autofocus">
            <small id="emailHelp" class="form-text text-muted"><spring:message code="login.label.email_help"/></small>
        </div>
        <div class="form-group">
            <label for="inputPassword"><spring:message code="login.label.password"/> </label>
            <input name="password" type="password" class="form-control" id="inputPassword"
                   placeholder="<spring:message code="login.label.password"/>">
        </div>
        <c:if test="${message != null}">
            <div class="alert alert-info" role="alert">
                <spring:message code="login.label.logout"/>
            </div>
        </c:if>
        <c:if test="${error != null}">
            <div class="alert alert-danger" role="alert">
                <spring:message code="login.label.error"/>
            </div>
        </c:if>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div style="margin-top:10px" class="form-group">
            <button type="submit" class="btn btn-success"><spring:message code="login.button.signin"/></button>
            <a href="${contextPath}/registration" role="button" class="btn btn-primary"><spring:message
                    code="login.button.signup"/> </a>
        </div>
    </form>

</div>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>