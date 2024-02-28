<%--
  Created by IntelliJ IDEA.
  User: ZERA
  Date: 2024/2/28
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <h1>用户登录</h1>
    <hr>
    <form action="<%=request.getContextPath()%>/login>" method="post">
        username:<input type="text" name="username"><br>
        password:<input type="password" name="password"><br>
        <input type="submit" value="login">
    </form>
</body>
</html>
