<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<html>
<head>


    <title>Success</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">

</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="container" style="margin-top: 10px">

    <div class="row">
        <div class="col-md-8">
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2542.3425568882767!2d30.653395688454353!3d50.416088979470665!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40d4c4e2a6a3824b%3A0xf22ff3f2cc9d9770!2z0LLRg9C70LjRhtGPINCi0YDQvtGB0YLRj9C90LXRhtGM0LrQsCwgONCRLCDQmtC40ZfQsg!5e0!3m2!1sru!2sua!4v1505309889384" width="600" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
        </div>
        <div class="col-md-4">
            <h2>Contact us</h2>
            <p><strong>Address:</strong> 8b Trostyanetskaya st., Kyiv, Ukraine</p>
            <p><strong>Email:</strong> illyagrytsak@gmail.com</p>
            <p><strong>Phone number:</strong> +380935215824</p>
            <p><strong>VK:</strong> vk.com/grytsak</p>
        </div>
    </div>

</div>

</body>
</html>

