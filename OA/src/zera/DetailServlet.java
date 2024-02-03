package zera;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.DBUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        String projectName = req.getContextPath();

        PrintWriter out = resp.getWriter();

        out.println("        <!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>部门详情 - OA系统</title>");
        out.println("    <style>");
        out.println("                body {");
        out.println("            display: flex;");
        out.println("            flex-direction: column;");
        out.println("            justify-content: center;");
        out.println("            align-items: center;");
        out.println("            height: 100vh;");
        out.println("            margin: 0;");
        out.println("            background: #f7f7f7; /* 背景色 */");
        out.println("            font-family: Arial, sans-serif; /* 字体 */");
        out.println("        }");
        out.println("details {");
        out.println("            background: #fff; /* 背景色 */");
        out.println("            padding: 20px;");
        out.println("            margin-bottom: 20px;");
        out.println("            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 阴影效果 */");
        out.println("            border-radius: 5px; /* 圆角边框 */");
        out.println("            width: 80%; /* 宽度 */");
        out.println("            max-width: 600px; /* 最大宽度 */");
        out.println("        }");
        out.println("details hr {");
        out.println("            border: 0;");
        out.println("            height: 1px;");
        out.println("            background: #e1e1e1;");
        out.println("            margin: 10px 0; /* 上下间距 */");
        out.println("        }");
        out.println("        a {");
        out.println("            color: #337ab7; /* 链接颜色 */");
        out.println("            text-decoration: none; /* 去掉下划线 */");
        out.println("            padding: 5px 10px;");
        out.println("            border: 1px solid #337ab7;");
        out.println("            border-radius: 5px; /* 圆角边框 */");
        out.println("            transition: background-color 0.3s, color 0.3s; /* 过渡效果 */");
        out.println("        }");
        out.println("        a:hover {");
        out.println("            background-color: #337ab7; /* 悬停背景色 */");
        out.println("            color: #fff; /* 悬停文字颜色 */");
        out.println("        }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='details'>");
        out.println("    <h1>部门详情</h1>");

        String empno = req.getParameter("empno");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from emp2 where empno = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,empno);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                String ENAME = resultSet.getString("ENAME");
                String JOB = resultSet.getString("JOB");
                String MGR = resultSet.getString("MGR");
                String HIREDATE = resultSet.getString("HIREDATE");
                String DEPTNO = resultSet.getString("DEPTNO");
                String SAL = resultSet.getString("SAL");

                out.println("    <hr>员工编号："+empno);
                out.println("                <hr>员工姓名："+ENAME);
                out.println("                <hr>职位："+JOB);
                out.println("                <hr>经理："+MGR);
                out.println("                <hr>招聘日期："+HIREDATE);
                out.println("                <hr>工资："+SAL);
                out.println("                <hr>部门编号："+DEPTNO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        out.println("                </div>");
        out.println("<a href='"+projectName+"/employee'>返回列表</a>");
        out.println("</body>");
        out.println("</html>");

    }
}
