<%--
  Created by IntelliJ IDEA.
  User: ZERA
  Date: 2024/2/22
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <h1>This is my first JSP page</h1>
  </body>
</html>

<%
  System.out.println("this is in sout method");
  System.out.println("From this private variable qu = " + qu );
  out.write("From this private variable qu = " + qu);
%>

<%!
  private int qu = 3;
%>