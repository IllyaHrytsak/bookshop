<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<html>
<head>


    <title><spring:message code="booklist.title"/></title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">

</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="container" style="margin-top: 10px;">
    <c:if test="${message != null}">
        <div class="alert alert-warning" role="alert">
            <spring:message code="booklist.error"/>
        </div>
    </c:if>
    <div class="row justify-content-around">

        <c:forEach items="${bookList}" var="book">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title"><strong><spring:message code="booklist.book_name"/>
                        :</strong> ${book.bookTitle}</h4>
                    <p class="card-text"><strong><spring:message code="booklist.book_author"/>
                        :</strong> ${book.bookAuthor} </p>
                    <p class="card-text"><strong><spring:message code="booklist.book_price"/>
                        :</strong> ${book.bookPrice} $</p>
                </div>
                <div class="card-footer text-right">
                    <div class="btn-group-sm">
                        <security:authorize access="hasRole('ROLE_ADMIN')">
                            <a class="btn btn-info" href="${contextPath}/edit_book?bookId=${book.id}"><spring:message
                                    code="booklist.button.edit_book"/> </a>
                        </security:authorize>
                        <a class="btn btn-primary"
                           href="${contextPath}/add_book_to_cart?code=${book.id}"><spring:message
                                code="booklist.button.add_to_cart"/> </a>
                    </div>
                </div>
            </div>

        </c:forEach>

    </div>
    <nav aria-label="Page navigation" style="margin-top: 20px">
        <ul class="pagination justify-content-center">
            <c:url value="${contextPath}/booklist" var="prev">
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
                        <c:url value="${contextPath}/booklist" var="url">
                            <c:param name="page" value="${i.index}"/>
                        </c:url>
                        <li class="page-item"><a class="page-link" href='<c:out value="${url}" />'>${i.index}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:url value="${contextPath}/booklist" var="next">
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
</div>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
