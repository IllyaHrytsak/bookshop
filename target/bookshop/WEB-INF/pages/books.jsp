<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>


    <title><spring:message code="books.title"/></title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">

</head>
<body>

<jsp:include page="_header.jsp"/>

<div class="container" style="margin-top: 10px">

    <h1><spring:message code="books.mini_title"/></h1>

    <c:if test="${!empty listBooks}">
        <table class="table table-bordered">
            <tr>
                <th width="80"><spring:message code="books.id"/></th>
                <th width="120"><spring:message code="books.book_name"/></th>
                <th width="150"><spring:message code="books.book_author"/></th>
                <th width="120"><spring:message code="books.book_price"/></th>
                <th width="60"><spring:message code="books.book_edit"/></th>
                <th width="60"><spring:message code="books.book_delete"/></th>
            </tr>
            <c:forEach items="${listBooks}" var="book">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.bookTitle}</td>
                    <td>${book.bookAuthor}</td>
                    <td>${book.bookPrice} $</td>
                    <td><a href="${contextPath}/edit_book?bookId=${book.id}"><spring:message
                            code="books.book_edit"/> </a></td>
                    <td><a href="${contextPath}/remove_book?bookId=${book.id}"><spring:message
                            code="books.book_delete"/> </a></td>
                </tr>
            </c:forEach>
        </table>
        <nav aria-label="Page navigation" style="margin-top: 20px">
            <ul class="pagination justify-content-center">
                <c:url value="${contextPath}/books" var="prev">
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
                            <c:url value="${contextPath}/books" var="url">
                                <c:param name="page" value="${i.index}"/>
                            </c:url>
                            <li class="page-item"><a class="page-link" href='<c:out value="${url}" />'>${i.index}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:url value="${contextPath}/books" var="next">
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
    <c:if test="${empty listBooks}">
        <div class="text-center" style="margin-top: 30px">
            <h3><spring:message code="books.info.no_books"/></h3>
            <a href="${contextPath}/add_book" class="h4 link"><spring:message code="books.info.add_book"/> </a>
        </div>
    </c:if>

</div>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
