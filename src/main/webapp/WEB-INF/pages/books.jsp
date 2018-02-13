<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Books Page</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg {
            background-color: #f9f9f9
        }
        div {
            display: inline-block;
        }
    </style>
</head>
<body>
<a href="../../index.jsp">Back to main menu</a>

<h2>Book List</h2>

<c:if test="${!empty listBooks}">
    <table class="tg">
        <tr>
            <th width="8">ID</th>
            <th width="120">Title</th>
            <th width="120">Author</th>
            <th width="120">ISBN</th>
            <th width="10">Year</th>
            <th width="10">Is read?</th>
            <th width="60">Edit</th>
            <th width="60">Read</th>
            <th width="60">Delete</th>
        </tr>
        <%-- Paginating --%>
        <c:set var="books" scope="request" value="${listBooks}"/>
        <c:set var="totalCount" scope="request" value="${listBooks.size()}"/>
        <c:set var="perPage" scope="request"  value="${10}"/>
        <c:set var="pageStart" value="${param.start}"/>
        <c:set var="pageEnd" value="${pageStart + 10}"/>
        <c:if test="${empty pageStart or pageStart < 0}">
            <c:set var="pageStart" value="0"/>
        </c:if>
        <c:if test="${totalCount < pageStart}">
            <c:set var="pageStart" value="${pageStart - perPage}"/>
        </c:if>
        <c:if test="${(pageStart + perPage) > totalCount}">
            <c:set var="pageEnd" value="${totalCount}"/>
        </c:if>
        <a href="?start=${pageStart - perPage}"><<</a>${pageStart + 1} - ${pageEnd}
        <a href="?start=${pageStart + perPage}">>></a>

        <c:forEach var="book" items="${books}"
                   begin="${pageStart}" end="${pageStart + perPage - 1}">
                    <tr>
                        <td>${book.id}</td>
                        <td><a href="/bookdata/${book.id}">${book.title}</a></td>
                        <td>${book.author}</td>
                        <td>${book.isbn}</td>
                        <td>${book.printYear}</td>
                        <td>${book.readAlready}</td>
                        <td><a href="<c:url value='/edit/${book.id}'/>">Edit</a></td>
                        <td><a href="<c:url value='/read/${book.id}'/>">Read</a></td>
                        <td><a href="<c:url value='/remove/${book.id}'/>">Delete</a></td>
                    </tr>
                </c:forEach>
    </table>

</c:if>

<div align="left">
<h2>Add/Update a Book</h2>

<c:url var="addAction" value="/books/add"/>

<form:form action="${addAction}" commandName="book">
    <table>
        <c:if test="${!empty book.title}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="title">
                    <spring:message text="Title"/>
                </form:label>
            </td>
            <td>
                <form:input path="title"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="author">
                    <spring:message text="Author"/>
                </form:label>
            </td>
            <td>
                <c:choose>
                    <c:when test="${!empty book.title}">
                        <form:input path="author" readonly="true" disabled="true"/>
                        <form:hidden path="author"/>
                    </c:when>
                    <c:when test="${empty book.title}">
                        <form:input path="author"/>
                    </c:when>
                </c:choose>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="description">
                    <spring:message text="Description"/>
                </form:label>
            </td>
            <td>
                <form:input path="description"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="isbn">
                    <spring:message text="ISBN"/>
                </form:label>
            </td>
            <td>
                <form:input path="isbn"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="printYear">
                    <spring:message text="Print year"/>
                </form:label>
            </td>
            <td>
                <form:input path="printYear"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:hidden path="readAlready" value="false" onfocus="true"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty book.title}">
                    <input type="submit"
                           value="<spring:message text="Edit Book"/>"/>
                </c:if>
                <c:if test="${empty book.title}">
                    <input type="submit"
                           value="<spring:message text="Add Book"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
</div>
<div>
    <h2>Search</h2>

    <c:url var="searchAction" value="books"/>

    <form:form method="get" action="${searchAction}" commandName="book">
        <table>
            <tr>
                <td>
                    <input name="printyear" placeholder="printyear">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit"
                           value="<spring:message text="Search"/>"/>
                </td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
