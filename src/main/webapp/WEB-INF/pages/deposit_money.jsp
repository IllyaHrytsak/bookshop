<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Deposit money</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="container">
    <h3 style="margin-top: 10px">Deposit money</h3>
    <form action="<c:url value="${contextPath}/home/deposit_money"/> " method="POST">
        <c:if test="${error != null}">
            <div class="alert alert-danger" role="alert">
                <strong>Oh snap!</strong> ${error}
            </div>
        </c:if>
        <div class="form-group">
            <label for="inputCardNumber">Input card number</label>
            <input type="text" name="cardNumber" class="form-control" id="inputCardNumber"
                   placeholder="1111 1111 1111 1111" autofocus="autofocus">
        </div>
        <div class="form-group">
            <label for="inputCVV">Input CVV</label>
            <input type="password" name="CVV" class="form-control"
                   id="inputCVV" placeholder="***">
        </div>
        <div class="form-group">
            <label for="inputMoneyAmount">Input amount of your deposit</label>
            <input type="password" name="money" class="form-control"
                   id="inputMoneyAmount" placeholder="1$ - 10000$">
        </div>
        <button type="submit" class="btn btn-success">Deposit money</button>
        <a href="${contextPath}/home" role="button" class="btn btn-info">Back</a>
        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
    </form>
</div>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
