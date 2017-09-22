<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<html>
<head>


    <title><spring:message code="add_book.title"/> </title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">

</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="container">

    <c:url value="${contextPath}/add_book" var="addBook"/>

    <h4 style="margin-top: 10px"><spring:message code="add_book.title"/> </h4>
    <form:form action="${addBook}" commandName="book">
        <table class="table">
            <tr>
                <td>
                    <form:label path="bookTitle">
                        <spring:message code="add_book.book_name"/>
                    </form:label>
                </td>
                <td>
                    <spring:bind path="bookTitle">
                        <div class="form-group">
                            <form:input path="bookTitle" cssClass="form-control"/>
                            <form:errors path="bookTitle" cssStyle="color: red"/>
                        </div>
                    </spring:bind>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="bookAuthor">
                        <spring:message code="add_book.book_author"/>
                    </form:label>
                </td>
                <td>
                    <spring:bind path="bookAuthor">
                        <div class="form-group">
                            <form:input path="bookAuthor" cssClass="form-control"/>
                            <form:errors path="bookAuthor" cssStyle="color: red"/>
                        </div>
                    </spring:bind>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="bookPrice">
                        <spring:message code="add_book.book_price"/>
                    </form:label>
                </td>
                <td>
                    <spring:bind path="bookPrice">
                        <div class="form-group">
                            <form:input path="bookPrice" cssClass="form-control"/>
                            <form:errors path="bookPrice" cssStyle="color: red"/>
                        </div>
                    </spring:bind>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" class="btn btn-primary"
                           value="<spring:message code="add_book.button.add_book"/>"/>
                </td>
                <td>
                </td>
            </tr>
        </table>
    </form:form>

</div>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>

