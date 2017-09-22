<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Edit book</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">

</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="container">

    <c:url value="${contextPath}/edit_book" var="editBook"/>

    <h4 style="margin-top: 10px">Edit book</h4>
    <form:form action="${editBook}" commandName="book">
        <table class="table">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" cssClass="form-control" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="bookTitle">
                        <spring:message text="Book title"/>
                    </form:label>
                </td>
                <td>
                    <spring:bind path="bookTitle">
                        <div class="form-group">
                            <form:input path="bookTitle" cssClass="form-control"/>
                            <form:errors path="bookTitle" cssStyle="color: red"></form:errors>
                        </div>
                    </spring:bind>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="bookAuthor">
                        <spring:message text="Book author"/>
                    </form:label>
                </td>
                <td>
                    <spring:bind path="bookAuthor">
                        <div class="form-group">
                            <form:input path="bookAuthor" cssClass="form-control"/>
                            <form:errors path="bookAuthor" cssStyle="color: red"></form:errors>
                        </div>
                    </spring:bind>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="bookPrice">
                        <spring:message text="Book price"/>
                    </form:label>
                </td>
                <td>
                    <spring:bind path="bookPrice">
                        <div class="form-group">
                            <form:input path="bookPrice" cssClass="form-control"/>
                            <form:errors path="bookPrice" cssStyle="color: red"></form:errors>
                        </div>
                    </spring:bind>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" class="btn btn-primary"
                           value="<spring:message text="Edit book"/>"/>
                </td>
                <td>
                </td>
            </tr>
        </table>
    </form:form>

</div>

</body>
</html>

