<%--
  Created by IntelliJ IDEA.
  User: ZERA
  Date: 2024/3/1
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
  <head>
    <title>Cookie</title>
  </head>
  <body>
  <h1>
    <a href="<%=request.getContextPath()%>/generateCookie">
      点击我，由服务器的JVM创造Cookie，发送到浏览器
    </a>
    <br>
    <a href="<%=request.getContextPath()%>/getCookie">
      点击我，把在浏览器的Cookie，发送到服务器
    </a>
  </h1>
  </body>
</html>
