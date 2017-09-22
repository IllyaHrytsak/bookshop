<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<form id="logoutForm" method="POST" action="${contextPath}/logout">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<div class="card">
    <div class="card-header">
        <nav class="navbar navbar-expand-sm navbar-light bg-faded">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#nav-content"
                    aria-controls="nav-content" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>


            <div class="collapse navbar-collapse" id="nav-content">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="${contextPath}/home"><spring:message code="header.home"/> </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${contextPath}/booklist"><spring:message
                                code="header.book_list"/> </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${contextPath}/shopping_cart"><spring:message
                                code="header.cart"/> </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${contextPath}/contacts"><spring:message code="header.contacts"/> </a>
                    </li>
                    <security:authorize access="hasRole('ROLE_ADMIN')">
                        <li class="nav-item">
                            <a class="nav-link" href="${contextPath}/all_orders"><spring:message
                                    code="header.all_orders"/> </a>
                        </li>
                    </security:authorize>
                    <security:authorize access="hasRole('ROLE_ADMIN')">
                        <li class="nav-item">
                            <a class="nav-link" href="${contextPath}/add_book"><spring:message
                                    code="header.add_book"/> </a>
                        </li>
                    </security:authorize>
                    <security:authorize access="hasRole('ROLE_ADMIN')">
                        <li class="nav-item">
                            <a class="nav-link" href="${contextPath}/books"><spring:message code="header.books"/> </a>
                        </li>
                    </security:authorize>
                </ul>


                <form class="form-inline" style="margin-top: 15px">
                    <c:if test="${pageContext.request.userPrincipal.name != null}">
                        <a href="${contextPath}/home">
                                ${pageContext.request.userPrincipal.name} </a>
                        &nbsp;|&nbsp;
                        <a onclick="document.forms['logoutForm'].submit()" class="btn-link"><spring:message
                                code="header.href.logout"/> </a>
                    </c:if>
                    <c:if test="${pageContext.request.userPrincipal.name == null}">
                        <a href="${contextPath}/login" class="btn btn-primary"><spring:message
                                code="header.button.login"/> </a>
                    </c:if>
                </form>
            </div>
        </nav>
    </div>
    <div class="card-body text-center">
        <h3 class="card-title"><spring:message code="header.card.book_shop"/></h3>
    </div>
</div>

