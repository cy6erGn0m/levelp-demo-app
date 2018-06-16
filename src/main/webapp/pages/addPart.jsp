<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:useBean id="addPartBean" type="ru.levelp.myapp.web.AddPartBean" scope="request" />
<html>
<head>
    <title>Add part</title>
</head>
<body>
    <form method="post" action="/admin/add-part" enctype="application/x-www-form-urlencoded">
        <p>Part ID: <input type="text" name="partId"> </p>
        <p>Title: <input type="text" name="title"> </p>

        <p>Supplier:
            <select name="supplier">
                <c:forEach var="supplier" items="${addPartBean.suppliers}">
                    <option value="${supplier.id}">${supplier.name}</option>
                </c:forEach>
            </select>
        </p>

        <security:csrfInput/>

        <input type="submit">
    </form>
</body>
</html>
