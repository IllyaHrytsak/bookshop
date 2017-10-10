<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<html>
<head>


    <title><spring:message code="shopping_cart.title"/></title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">

</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="container" style="margin-top: 10px;">
    <c:if test="${not empty orderList}">
        <c:if test="${noMoney != null}">
            <div class="alert alert-danger" role="alert">
                <spring:message code="shopping_cart.error.no_money"/>
            </div>
        </c:if>
        <c:if test="${wrongAmount != null}">
            <div class="alert alert-danger" role="alert">
                <spring:message code="shopping_cart.error.wrong_amount"/>
            </div>
        </c:if>
        <div class="row justify-content-around">
            <c:forEach items="${orderList}" var="order">
                <div class="card">
                    <form action="${contextPath}/shopping_cart/amount?orderId=${order.orderId}" method="POST">
                        <div class="card-body">
                            <h4 class="card-title"><strong><spring:message code="shopping_cart.book_name"/>:
                            </strong> ${order.book.bookTitle}</h4>
                            <p class="card-text"><strong><spring:message code="shopping_cart.book_author"/>:
                            </strong> ${order.book.bookAuthor} </p>
                            <p class="card-text"><strong><spring:message code="shopping_cart.amount"/> </strong><input
                                    name="newAmount" type="text"
                                    value="${order.amount}"></p>
                            <p class="card-text"><strong><spring:message code="shopping_cart.book_price"/>:
                            </strong> ${order.price} $</p>
                            <p class="card-text"><strong><spring:message code="shopping_cart.total_price"/>:
                            </strong> ${order.totalPrice} $</p>
                        </div>
                        <div class="card-footer text-right">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button class="btn btn-info btn-sm" type="submit"><spring:message
                                    code="shopping_cart.button.update_quantity"/></button>
                            <a class="btn btn-danger btn-sm"
                               href="${contextPath}/remove_order?code=${order.book.id}&order=${order.orderId}"><spring:message
                                    code="shopping_cart.button.remove_order"/> </a>
                        </div>
                    </form>
                </div>

            </c:forEach>
        </div>
        <nav aria-label="Page navigation" style="margin-top: 20px">
            <ul class="pagination justify-content-center">
                <c:url value="${contextPath}/shopping_cart" var="prev">
                    <c:param name="page" value="${page-1}"/>
                </c:url>
                <c:choose>
                    <c:when test="${page > 1}">
                        <li class="page-item">
                            <a class="page-link" href="<c:out value="${prev}"/>" tabindex="-1"><spring:message
                                    code="pagination.prev"/></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item disabled">
                            <span class="page-link"><spring:message code="pagination.prev"/> </span>
                        </li>
                    </c:otherwise>
                </c:choose>
                <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
                    <c:choose>
                        <c:when test="${page == i.index}">
                            <li class="page-item active">
                            <span class="page-link">
                                ${i.index}
                                <span class="sr-only">(current)</span>
                            </span>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <c:url value="${contextPath}/shopping_cart" var="url">
                                <c:param name="page" value="${i.index}"/>
                            </c:url>
                            <li class="page-item"><a class="page-link" href='<c:out value="${url}" />'>${i.index}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:url value="${contextPath}/shopping_cart" var="next">
                    <c:param name="page" value="${page+1}"/>
                </c:url>
                <c:choose>
                    <c:when test="${page + 1 <= maxPages}">
                        <li class="page-item">
                            <a class="page-link" href="<c:out value="${next}"/>"><spring:message
                                    code="pagination.next"/></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item disabled">
                            <span class="page-link"><spring:message code="pagination.next"/></span>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>
        <hr>
        <div class="text-right" style="margin-top: 10px;">
            <h3><spring:message code="shopping_cart.total_amount"/> : ${totalAmount} $</h3>
            <a href="${contextPath}/booklist" class="btn btn-info btn-lg"><spring:message
                    code="shopping_cart.button.continue_buying"/> </a>
            <a href="${contextPath}/pay_orders" class="btn btn-info btn-lg"><spring:message
                    code="shopping_cart.button.pay"/> </a>
            <a href="${contextPath}/remove_all" class="btn btn-danger btn-lg"><spring:message
                    code="shopping_cart.button.remove_all"/> </a>
        </div>

    </c:if>
    <c:if test="${empty orderList}">
        <div class="text-center" style="margin-top: 30px">
            <h3><spring:message code="shopping_cart.info.no_books"/></h3>
            <a href="${contextPath}/booklist" class="h4 link"><spring:message
                    code="shopping_cart.href.go_shopping"/> </a>
        </div>
    </c:if>
</div>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>

