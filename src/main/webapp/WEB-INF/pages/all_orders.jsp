<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<html>
<head>


    <title><spring:message code="all_orders.title"/></title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">

</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="container" style="margin-top: 10px">
    <c:if test="${not empty accountList}">
        <c:if test="${message != null}">
            <div class="alert alert-danger" role="alert">
                <spring:message code="all_orders.error"/>
            </div>
        </c:if>
        <c:if test="${not empty accountDetails}">
            <h3><spring:message code="all_orders.customer_information"/></h3>
            <p><strong><spring:message code="all_orders.email"/>:</strong> ${accountDetails.email}</p>
            <p><strong><spring:message code="all_orders.first_name"/>:</strong> ${accountDetails.firstName}</p>
            <p><strong><spring:message code="all_orders.last_name"/>:</strong> ${accountDetails.lastName}</p>
            <h3><spring:message code="all_orders.order_information"/></h3>
            <p><strong><spring:message code="all_orders.total_summary"/>:</strong> ${totalSummary} $</p>
            <p><strong><spring:message code="all_orders.paid"/>:</strong> ${paid}</p>
            <a class="btn btn-info" href="${contextPath}/all_orders"><spring:message code="all_orders.reset"/> </a>
            <hr>
        </c:if>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>#</th>
                <th><spring:message code="all_orders.order_date"/></th>
                <th><spring:message code="all_orders.customer_email"/></th>
                <th><spring:message code="all_orders.customer_phone_number"/></th>
                <th><spring:message code="all_orders.detail_view"/></th>
                <th><spring:message code="all_orders.confirm_order"/></th>
                <th><spring:message code="all_orders.block_user"/></th>
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
                    <td><a href="${contextPath}/all_orders?accountEmail=${account.email}"><spring:message
                            code="all_orders.detail_view"/> </a></td>
                    <td><a href="${contextPath}/confirm_orders?accountEmail=${account.email}"><spring:message
                            code="all_orders.confirm_order"/> </a></td>
                    <c:if test="${account.role.roleId != 3}">
                        <td><a href="${contextPath}/block_user?accountEmail=${account.email}"><spring:message
                                code="all_orders.block_user"/> </a></td>
                    </c:if>
                    <c:if test="${account.role.roleId == 3}">
                        <td><a href="${contextPath}/unblock_user?accountEmail=${account.email}"><spring:message
                                code="all_orders.unblock_user"/> </a></td>
                    </c:if>
                </tr>
                </tbody>
            </c:forEach>
        </table>

        <nav aria-label="Page navigation" style="margin-top: 20px">
            <ul class="pagination justify-content-center">
                <c:url value="${contextPath}/all_orders" var="prev">
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
                            <c:url value="${contextPath}/all_orders" var="url">
                                <c:param name="page" value="${i.index}"/>
                            </c:url>
                            <li class="page-item"><a class="page-link" href='<c:out value="${url}" />'>${i.index}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:url value="${contextPath}/all_orders" var="next">
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
    </c:if>
    <c:if test="${empty accountList}">
        <div class="text-center" style="margin-top: 30px">
            <h4><spring:message code="all_orders.no_orders"/></h4>
        </div>
    </c:if>
</div>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>