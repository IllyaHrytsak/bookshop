<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Books Page</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">

</head>
<body>

<jsp:include page="_header.jsp"/>

<div class="container" style="margin-top: 10px">

    <h1>Book List</h1>

    <c:if test="${!empty listBooks}">
        <table class="table table-bordered">
            <tr>
                <th width="80">ID</th>
                <th width="120">Title</th>
                <th width="150">Author</th>
                <th width="120">Price</th>
                <th width="60">Edit</th>
                <th width="60">Delete</th>
            </tr>
            <c:forEach items="${listBooks}" var="book">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.bookTitle}</td>
                    <td>${book.bookAuthor}</td>
                    <td>${book.bookPrice} $</td>
                    <td><a href="${contextPath}/edit_book?bookId=${book.id}">Edit</a></td>
                    <td><a href="${contextPath}/remove_book?bookId=${book.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty listBooks}">
        <div class="text-center" style="margin-top: 30px">
            <h3>There is no books</h3>
            <a href="${contextPath}/add_book" class="h4 link">Add book!</a>
        </div>
    </c:if>
</div>


<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
