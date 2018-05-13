<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="indexBean" type="ru.levelp.myapp.web.IndexBean" scope="application"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello!</h1>

<p>${header['User-Agent']}</p>

<div>
    <table>
        <tbody>

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

    <a href="addPart.jsp">Add part...</a>
</div>
</body>
</html>
