<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Login</title>

    <style type="text/css">
        .error {
            color: red;
        }
    </style>
</head>
<body>

<p class="error">${error}</p>

<form method="post" action="/signin">
    <p>Username: <input name="user" type="text"></p>
    <p>Password: <input name="pass" type="password"></p>

    <security:csrfInput/>

    <p><input type="submit"></p>
</form>
</body>
</html>
