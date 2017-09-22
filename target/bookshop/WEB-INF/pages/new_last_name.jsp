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

    <title>New last name</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="container">
    <h3 style="margin-top: 10px">New last name</h3>
    <form action="<c:url value="${contextPath}/home/new_last_name"/> " method="POST">
        <c:if test="${error != null}">
            <div class="alert alert-danger" role="alert">
                <strong>Oh snap!</strong> ${error}
            </div>
        </c:if>
        <div class="form-group">
            <label for="inputNewLastName">New last name</label>
            <input type="text" name="newLastName" class="form-control" id="inputNewLastName"
                   placeholder="Hrytsak" autofocus="autofocus">
        </div>
        <div class="form-group">
            <label for="inputConfirmNewLastName">Confirm new last name</label>
            <input type="text" name="confirmNewLastName" class="form-control" id="inputConfirmNewLastName" placeholder="Hrytsak">
        </div>
        <button type="submit" class="btn btn-success">Change last name</button>
        <a href="${contextPath}/home" role="button" class="btn btn-info">Back</a>
        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
    </form>
</div>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
