<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>

    <title><spring:message code="registration.title"/></title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">


</head>

<body>
<jsp:include page="_header.jsp"/>

<div class="container">
    <form:form method="POST" modelAttribute="userForm" cssStyle="margin-top: 10px">
        <spring:bind path="email">
            <label for="inputEmail"><spring:message code="registration.label.email"/> </label>
            <div class="form-group">
                <spring:message code="registration.placeholder.email" var="emailPlaceholder"/>
                <form:input id="inputEmail" type="email" path="email" class="form-control"
                            placeholder="${emailPlaceholder}" autofocus="true"/>
                <form:errors path="email" cssStyle="color: red"/>

            </div>
        </spring:bind>

        <spring:bind path="password">
            <spring:message code="registration.label.password" var="passwordPlaceholder"/>
            <label for="inputPassword">${passwordPlaceholder} </label>
            <div class="form-group">
                <form:input id="inputPassword" type="password" path="password" class="form-control"
                            placeholder="${passwordPlaceholder}"/>
                <form:errors path="password" cssStyle="color: red"/>
            </div>
        </spring:bind>

        <spring:bind path="confirmPassword">
            <spring:message code="registration.label.confirm_password" var="confirmPasswordPlaceholder"/>
            <label for="confirmPassword">${confirmPasswordPlaceholder}</label>
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input id="confirmPassword" type="password" path="confirmPassword" class="form-control"
                            placeholder="${confirmPasswordPlaceholder}"/>
                <form:errors path="confirmPassword" cssStyle="color: red"/>
            </div>
        </spring:bind>

        <spring:bind path="firstName">
            <label for="inputFirstName"><spring:message code="registration.label.first_name"/> </label>
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="registration.placeholder.first_name" var="firstNamePlaceholder"/>
                <form:input id="inputFirstName" type="text" path="firstName" class="form-control"
                            placeholder="${firstNamePlaceholder}"/>
                <form:errors path="firstName" cssStyle="color: red"/>
            </div>
        </spring:bind>

        <spring:bind path="lastName">
            <label for="inputLastName"><spring:message code="registration.label.last_name"/> </label>
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="registration.placeholder.last_name" var="lastNamePlaceholder"/>
                <form:input id="inputLastName" type="text" path="lastName" class="form-control"
                            placeholder="${lastNamePlaceholder}"/>
                <form:errors path="lastName" cssStyle="color: red"/>
            </div>
        </spring:bind>

        <spring:bind path="phoneNumber">
            <label for="inputPhoneNumber"><spring:message code="registration.label.phone_number"/> </label>
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="registration.placeholder.phone_number" var="phoneNumberPlaceholder"/>
                <form:input id="inputPhoneNumber" type="text" path="phoneNumber" class="form-control"
                            placeholder="${phoneNumberPlaceholder}"/>
                <form:errors path="phoneNumber" cssStyle="color: red"/>
            </div>
        </spring:bind>
        <div style="margin-bottom: 10px">
            <button type="submit" class="btn btn-primary"><spring:message code="registration.button.submit"/></button>
        </div>
    </form:form>

</div>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>