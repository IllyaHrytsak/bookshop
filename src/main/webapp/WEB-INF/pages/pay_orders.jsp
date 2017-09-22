<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<html>
<head>


    <title><spring:message code="pay_orders.title"/></title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">

</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="container">
    <c:if test="${not empty orderList}">

        <table class="table">
            <thead>
            <tr>
                <th>#</th>
                <th><spring:message code="pay_orders.table.book_name"/></th>
                <th><spring:message code="pay_orders.table.book_author"/></th>
                <th><spring:message code="pay_orders.table.book_price"/></th>
                <th><spring:message code="pay_orders.table.amount"/></th>
                <th><spring:message code="pay_orders.table.total_price"/></th>
            </tr>
            </thead>
            <c:forEach items="${orderList}" var="order">
                <tbody>
                <tr>
                    <th scope="row">${order.book.id}</th>
                    <td>${order.book.bookTitle}</td>
                    <td>${order.book.bookAuthor}</td>
                    <td>${order.price} $</td>
                    <td>${order.amount}</td>
                    <td>${order.totalPrice} $</td>
                </tr>
                </tbody>
            </c:forEach>
        </table>
        <hr>
        <div class="form-inline text-left" style="margin-top: 10px;">
            <h3><spring:message code="pay_orders.info.total_amount"/> : ${totalAmount} $</h3>
            <form method="POST" action="${contextPath}/pay_orders">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-success"><spring:message
                        code="pay_orders.button.confirm"/></button>
                <a href="${contextPath}/shopping_cart" class="btn btn-danger"><spring:message
                        code="pay_orders.button.back"/> </a>
            </form>
        </div>

    </c:if>
    <c:if test="${empty orderList}">
        <div class="text-center" style="margin-top: 30px">
            <h3><spring:message code="pay_orders.info.no_books"/></h3>
            <a href="${contextPath}/booklist" class="h4 link"><spring:message code="pay_orders.href.go_shopping"/> </a>
        </div>
    </c:if>
</div>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
