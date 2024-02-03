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

public class AddServlet extends HttpServlet {
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
        out.println("    <title>新增部门 - OA系统</title>");
        out.println("    <style>");
        out.println("                body {");
        out.println("            display: flex;");
        out.println("            flex-direction: column;");
        out.println("            justify-content: center;");
        out.println("            align-items: center;");
        out.println("            height: 100vh; /* 使body占满整个视口的高度 */");
        out.println("            margin: 0; /* 移除默认的margin */");
        out.println("            font-family: Arial, sans-serif; /* 设置字体 */");
        out.println("            background-color: #f4f4f4; /* 设置背景颜色 */");
        out.println("        }");
        out.println("        form {");
        out.println("            display: flex;");
        out.println("            flex-direction: column;");
        out.println("            align-items: flex-start; /* 左对齐 */");
        out.println("            background-color: white; /* 背景颜色 */");
        out.println("            padding: 20px; /* 内边距 */");
        out.println("            border-radius: 8px; /* 圆角边框 */");
        out.println("            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 阴影效果 */");
        out.println("            max-width: 400px; /* 最大宽度 */");
        out.println("        }");
        out.println("        h1 {");
        out.println("            margin-bottom: 20px; /* 与表单的间距 */");
        out.println("        }");
        out.println("        input[type='text'],");
        out.println("                input[type='submit'] {");
        out.println("            width: calc(100% - 20px); /* 宽度减去padding的值 */");
        out.println("            padding: 10px; /* 内边距 */");
        out.println("            margin-bottom: 10px; /* 间距 */");
        out.println("            border: 1px solid #ccc; /* 边框颜色 */");
        out.println("            border-radius: 5px; /* 圆角 */");
        out.println("        }");
        out.println("        input[type='submit'] {");
        out.println("            background-color: #4CAF50; /* 按钮背景色 */");
        out.println("            color: white; /* 按钮文字颜色 */");
        out.println("            cursor: pointer; /* 鼠标形状 */");
        out.println("        }");
        out.println("        input[type='submit']:hover {");
        out.println("            background-color: #45a049; /* 鼠标悬停时的背景色 */");
        out.println("        }");
        out.println("        label {");
        out.println("            font-weight: bold; /* 字体加粗 */");
        out.println("        }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>新增部门</h1>");
        out.println("<form action='"+projectName+"/save' method='post'>");
        out.println("    <label for='ename'>员工姓名:</label><input type='text' id='ename' name='ename'>");
        out.println("    <label for='empno'>员工编号:</label><input type='text' id='empno' name='empno'>");
        out.println("    <label for='job'>职位:</label><input type='text' id='job' name='job'>");
        out.println("    <label for='mgr'>上司:</label><input type='text' id='mgr' name='mgr'>");
        out.println("    <label for='hiredate'>招聘时间:</label><input type='text' id='hiredate' name='hiredate'>");
        out.println("    <label for='sal'>工资:</label><input type='text' id='sal' name='sal'>");
        out.println("    <label for='deptno'>部门编号:</label><input type='text' id='deptno' name='deptno'>");
        out.println("    <input type='submit' value='提交' align='center'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}

