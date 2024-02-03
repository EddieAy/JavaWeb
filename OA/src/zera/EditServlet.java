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

public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String projectName = request.getContextPath();

        out.println("        <!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>修改部门信息 - OA系统</title>");
        out.println("    <style>");
        out.println("                body {");
        out.println("            display: flex;");
        out.println("            flex-direction: column;");
        out.println("            justify-content: center;");
        out.println("            align-items: center;");
        out.println("            height: 100vh;");
        out.println("            margin: 0;");
        out.println("            background-color: #f7f7f7; /* 页面背景色 */");
        out.println("            font-family: Arial, sans-serif; /* 字体 */");
        out.println("        }");
        out.println("        form {");
        out.println("            display: flex;");
        out.println("            flex-direction: column;");
        out.println("            align-items: flex-start; /* 左对齐 */");
        out.println("            background-color: #fff; /* 表单背景色 */");
        out.println("            padding: 20px; /* 表单内边距 */");
        out.println("            border-radius: 8px; /* 圆角边框 */");
        out.println("            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 阴影效果 */");
        out.println("            max-width: 300px; /* 表单最大宽度 */");
        out.println("        }");
        out.println("        h1 {");
        out.println("            color: #333; /* 标题颜色 */");
        out.println("            margin-bottom: 20px; /* 与表单的间距 */");
        out.println("        }");
        out.println("        input[type='text'],");
        out.println("                input[type='submit'] {");
        out.println("            width: calc(100% - 20px); /* 输入框宽度 */");
        out.println("            padding: 10px; /* 输入框内边距 */");
        out.println("            margin-bottom: 10px; /* 输入框间距 */");
        out.println("            border: 1px solid #ccc; /* 边框颜色 */");
        out.println("            border-radius: 5px; /* 圆角 */");
        out.println("        }");
        out.println("        input[type='submit'] {");
        out.println("            background-color: #5cb85c; /* 提交按钮背景色 */");
        out.println("            color: white; /* 提交按钮文字颜色 */");
        out.println("            cursor: pointer; /* 鼠标形状 */");
        out.println("            border-color: #4cae4c; /* 边框颜色 */");
        out.println("        }");
        out.println("        input[type='submit']:hover {");
        out.println("            background-color: #449d44; /* 鼠标悬停时背景色 */");
        out.println("        }");
        out.println("readonly-field {");
        out.println("            background-color: #f3f3f3; /* 只读字段的背景色 */");
        out.println("            border: 1px solid #ddd; /* 边框颜色 */");
        out.println("            padding: 10px; /* 内边距 */");
        out.println("            margin-bottom: 10px; /* 间距 */");
        out.println("            border-radius: 5px; /* 圆角 */");
        out.println("        }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>修改部门信息</h1>");

        String empno = request.getParameter("empno");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from emp2 where empno = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,empno);
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            if(resultSet.next()){
                String ename = resultSet.getString("ename");
                String hiredate = resultSet.getString("hiredate");
//                String empno2 = resultSet.getString("empno");

                out.println("<form action='"+projectName+"/update' method='post'>");
                out.println("    <!-- 注意：id字段是隐藏的，因为用户不应修改它 -->");
                out.println("    <input type='hidden' id='id' name='id' value='部门ID'>");

                out.println("<input type='hidden' name='empno' value="+empno+">");
                out.println("<input type='hidden' name='ename' value="+ename+">");
                out.println("<input type='hidden' name='hiredate' value="+hiredate+">");

                out.println("    <label>员工编号:</label><input type='text' class='readonly-field' value="+empno+" readonly><br>");
                out.println("    <label>员工姓名:</label><input type='text' class='readonly-field' value="+ename+" readonly><br>");
                out.println("    <label>招聘日期:</label><input type='text' class='readonly-field' value="+hiredate+" readonly><br>");
                out.println("    <label for='job'>职位:</label><input type='text' id='job' name='job'>");
                out.println("    <label for='sal'>工资:</label><input type='text' id='sal' name='sal'>");
                out.println("    <label for='mgr'>经理:</label><input type='text' id='mgr' name='mgr'>");
                out.println("    <label for='deptno'>部门编号:</label><input type='text' id='deptno' name='deptno'>");
                out.println("    <input type='submit' value='提交'>");
                out.println("</form>");
                out.println("</body>");
                out.println("</html>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }
    }
}
