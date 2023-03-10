<%--
  Created by IntelliJ IDEA.
  User: ВладимирЛиповский
  Date: 10/03/2023
  Time: 10:29 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<form method="post" action="login">
    <dl>
        <dt>Login:</dt>
        <dd><input name="login" required></dd>
    </dl>
    <dl>
        <dt>Password:</dt>
        <dd><input name="password" required></dd>
    </dl>
    <button type="submit">Submit</button>
</form>

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
