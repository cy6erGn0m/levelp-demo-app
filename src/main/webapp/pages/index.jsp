<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="indexBean" type="ru.levelp.myapp.web.IndexBean" scope="request"/>
<html>
<head>
    <title>Title</title>

    <script src="/scripts/index.js" type="text/javascript"></script>
</head>
<body>
<h1>Hello!</h1>

<div>
    <p>
        <c:choose>
            <c:when test="${not empty userName}">
                Hello, ${userName}!
                <a href="/logout">Logout</a>
            </c:when>
            <c:otherwise>
                <a href="/login">Login</a>
            </c:otherwise>
        </c:choose>
    </p>
</div>

<div>
    <input type="text" id="searchField">
    <button onclick="onSearch()">Search</button>
</div>
<div>
    <table>
        <tbody id="resultsTable">

        <c:forEach var="part" items="${indexBean.parts}">
            <tr>
                <td>${part.partId}</td>
                <td>${part.title}</td>
                <td>
                    <c:if test="${part.legacy}">Legacy</c:if>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

    <p>
        <a href="/admin/add-part">Add part...</a>
    </p>
</div>
</body>
</html>
