<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<html>
<head>


    <title><spring:message code="success.title"/></title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">

</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="container">

    <div class="text-center" style="margin-top: 30px">
        <h3><spring:message code="success.info"/></h3>
        <a href="${contextPath}/home" class="h4 link"><spring:message code="success.go_home"/> </a>
    </div>

</div>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>

