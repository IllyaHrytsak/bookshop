<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>

    <title><spring:message code="home.title"/></title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="container">
    Language : <a href="?lang=en">English</a> | <a href="?lang=uk">Ukrainian</a>
    <h2 style="margin-top: 10px"><spring:message code="home.account_info"/></h2>
    <table class="table table-bordered">
        <tr>
            <th width="100"><spring:message code="home.table.email"/></th>
            <td width="200">${account.email}</td>
            <td width="120"></td>
        </tr>
        <tr>
            <th width="100"><spring:message code="home.table.first_name"/></th>
            <td width="200">${account.firstName}</td>
            <td width="120"><a href="<c:url value="${contextPath}/home/new_first_name"/> "><spring:message
                    code="home.table.change_first_name"/> </a></td>
        </tr>
        <tr>
            <th width="100"><spring:message code="home.table.last_name"/></th>
            <td width="200">${account.lastName}</td>
            <td width="120"><a href="<c:url value="${contextPath}/home/new_last_name"/> "><spring:message
                    code="home.table.change_last_name"/> </a></td>
        </tr>
        <tr>
            <th width="100"><spring:message code="home.table.phone_number"/></th>
            <td width="200">${account.phoneNumber}</td>
            <td width="120"><a href="<c:url value="${contextPath}/home/new_phone_number"/> "><spring:message
                    code="home.table.change_phone_number"/> </a>
            </td>
        </tr>
        <tr>
            <th width="100"><spring:message code="home.table.card"/></th>
            <td width="200">${account.card} $</td>
            <td width="120"><a href="<c:url value="${contextPath}/home/deposit_money"/> "><spring:message
                    code="home.table.deposit_money"/> </a></td>
        </tr>
    </table>

    <c:if test="${firstName != null}">
        <div class="alert alert-success" role="alert">
            <spring:message code="home.table.success_first_name"/>
        </div>
    </c:if>

    <c:if test="${lastName != null}">
        <div class="alert alert-success" role="alert">
            <spring:message code="home.table.success_last_name"/>
        </div>
    </c:if>

    <c:if test="${phoneNumber != null}">
        <div class="alert alert-success" role="alert">
            <spring:message code="home.table.success_phone_number"/>
        </div>
    </c:if>

    <c:if test="${cardNumber != null}">
        <div class="alert alert-success" role="alert">
            <spring:message code="home.table.success_deposit_money"/>
        </div>
    </c:if>
</div>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
