<%--
  Created by IntelliJ IDEA.
  User: ZERA
  Date: 2024/3/6
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
  <head>
<%--      <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">--%>
<%--    <base href="http://localhost:8080/mvc">--%>

    <title>银行转账</title>
  </head>
  <body>
    <form action="transfer" method="post">
        转出账户:<input type="text" name="fromActno"><br>
        转入账户:<input type="text" name="toActno"><br>
        转入金额:<input type="text" name="moneyValue"><br>
        <input type="submit" value="确认转账">
    </form>
  </body>
</html>
