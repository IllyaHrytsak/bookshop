<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>

    <title>403</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="container">

    <div class="text-center" style="margin-top: 30px">
        <h4>Access denied!</h4>
        <p style="color: red" class="h3">Sorry, you can not access this page!</p>
    </div>
</div>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
