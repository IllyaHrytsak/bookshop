<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>

    <title><spring:message code="deposit_money.title"/></title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="container">
    <h3 style="margin-top: 10px"><spring:message code="deposit_money.title"/></h3>
    <form action="<c:url value="${contextPath}/home/deposit_money"/> " method="POST">
        <c:if test="${wrongCardNumber != null}">
            <div class="alert alert-danger" role="alert">
                <spring:message code="deposit_money.error.card_number"/>
            </div>
        </c:if>
        <c:if test="${wrongCVV != null}">
            <div class="alert alert-danger" role="alert">
                <spring:message code="deposit_money.error.cvv"/>
            </div>
        </c:if>
        <c:if test="${wrongAmount != null}">
            <div class="alert alert-danger" role="alert">
                <spring:message code="deposit_money.error.amount"/>
            </div>
        </c:if>
        <div class="form-group">
            <label for="inputCardNumber"><spring:message code="deposit_money.label.card_number"/> </label>
            <input type="text" name="cardNumber" class="form-control" id="inputCardNumber"
                   placeholder="<spring:message code="deposit_money.placeholder.car_number"/> " autofocus="autofocus">
        </div>
        <div class="form-group">
            <label for="inputCVV"><spring:message code="deposit_money.label.cvv"/> </label>
            <input type="password" name="CVV" class="form-control"
                   id="inputCVV" placeholder="***">
        </div>
        <div class="form-group">
            <label for="inputMoneyAmount"><spring:message code="deposit_money.label.deposit_amount"/> </label>
            <input type="password" name="money" class="form-control"
                   id="inputMoneyAmount"
                   placeholder="<spring:message code="deposit_money.placeholder.deposit_amount"/> ">
        </div>
        <button type="submit" class="btn btn-success"><spring:message code="deposit_money.button.accept"/></button>
        <a href="${contextPath}/home" role="button" class="btn btn-info"><spring:message
                code="deposit_money.button.back"/> </a>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</div>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
