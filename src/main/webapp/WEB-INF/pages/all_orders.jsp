<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>All orders</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">

</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="container" style="margin-top: 10px">
    <c:if test="${not empty accountList}">
        <c:if test="${message != null}">
            <div class="alert alert-danger" role="alert">
                <strong>Oh snap!</strong> ${message}
            </div>
        </c:if>
        <c:if test="${not empty accountDetails}">
            <h3>Customer information</h3>
            <p><strong>Email:</strong> ${accountDetails.email}</p>
            <p><strong>First name:</strong> ${accountDetails.firstName}</p>
            <p><strong>Last name:</strong> ${accountDetails.lastName}</p>
            <h3>Order information</h3>
            <p><strong>Total summary:</strong> ${totalSummary} $</p>
            <p><strong>Paid:</strong> ${paid}</p>
            <a class="btn btn-info" href="${contextPath}/all_orders">Reset</a>
            <hr>
        </c:if>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>#</th>
                <th>Order date</th>
                <th>Customer email</th>
                <th>Customer phone number</th>
                <th>Detail view</th>
                <th>Confirm order</th>
                <th>Block user</th>
            </tr>
            </thead>
            <c:forEach items="${accountList}" var="account">
                <tbody>
                <tr>
                    <th scope="row">${account.accountId}</th>
                    <td>
                        <c:forEach var="dateValue" items="${account.orders}" end="0">
                            <fmt:formatDate value="${dateValue.orderDate}" pattern="dd-MM-yyyy"/>
                        </c:forEach>
                    </td>
                    <td>${account.email}</td>
                    <td>${account.phoneNumber}</td>
                    <td><a href="${contextPath}/all_orders?accountEmail=${account.email}">Detail view</a> </td>
                    <td><a href="${contextPath}/confirm_orders?accountEmail=${account.email}">Confirm order</a> </td>
                    <c:if test="${account.role.roleId != 3}">
                        <td><a href="${contextPath}/block_user?accountEmail=${account.email}">Block user</a></td>
                    </c:if>
                    <c:if test="${account.role.roleId == 3}">
                        <td><a href="${contextPath}/unblock_user?accountEmail=${account.email}">Unblock user</a></td>
                    </c:if>
                </tr>
                </tbody>
            </c:forEach>
        </table>

    </c:if>
    <c:if test="${empty accountList}">
        <div class="text-center" style="margin-top: 30px">
            <h4>There is no orders</h4>
        </div>
    </c:if>
</div>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>