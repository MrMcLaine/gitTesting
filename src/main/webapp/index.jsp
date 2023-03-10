<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<h1>Hello It academy</h1>

<form method="post" action="registration">
    <dl>
        <dt>First Name:</dt>
        <dd><input name="firstName" required></dd>
    </dl>
    <dl>
        <dt>Last Name:</dt>
        <dd><input name="lastName" required></dd>
    </dl>
    <dl>
        <dt>Email:</dt>
        <dd><input type="email" name="email" required></dd>
    </dl>
    <dl>
        <dt>Password:</dt>
        <dd><input type="password" name="password" required></dd>
    </dl>
    <button type="submit">Submit</button>
</form>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
