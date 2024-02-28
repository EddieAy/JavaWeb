package zera;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.DBUtil;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(value = {"/loginPage"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String projectName = request.getContextPath();
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("    <title>登录</title>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <style>");
        out.println("                body {");
        out.println("            font-family: Arial, sans-serif;");
        out.println("            margin: 0;");
        out.println("            padding: 0;");
        out.println("            display: flex;");
        out.println("            justify-content: center;");
        out.println("            align-items: center;");
        out.println("            height: 100vh;");
        out.println("            background-color: #f0f0f0;");
        out.println("        }");
        out.println("form-container {");
        out.println("            display: flex;");
        out.println("            justify-content: center;");
        out.println("            width: 100%;");
        out.println("        }");
        out.println("        form {");
        out.println("            width: 100%;");
        out.println("            max-width: 300px; /* 限制表单的最大宽度 */");
        out.println("            background: #ffffff;");
        out.println("            padding: 20px;");
        out.println("            border-radius: 8px;");
        out.println("            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
        out.println("        }");
        out.println("        h1 {");
        out.println("            text-align: center;");
        out.println("            color: #333;");
        out.println("        }");
        out.println("        hr {");
        out.println("            margin-bottom: 20px;");
        out.println("        }");
        out.println("        input[type='text'],");
        out.println("                input[type='password'] {");
        out.println("            width: calc(100% - 22px);");
        out.println("            padding: 10px;");
        out.println("            margin-bottom: 20px;");
        out.println("            border: 1px solid #ccc;");
        out.println("            border-radius: 5px;");
        out.println("        }");
        out.println("        input[type='submit'] {");
        out.println("            width: 100%;");
        out.println("            padding: 10px;");
        out.println("            border: none;");
        out.println("            border-radius: 5px;");
        out.println("            background-color: #007bff;");
        out.println("            color: white;");
        out.println("            cursor: pointer;");
        out.println("        }");
        out.println("        input[type='submit']:hover {");
        out.println("            background-color: #0056b3;");
        out.println("        }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='form-container'>");
        out.println("    <form action='"+projectName+"/login' method='post'> <!-- 注意这里修正了action属性的值 -->");
        out.println("        <h1>用户登录</h1>");
        out.println("        <hr>");
        out.println("                用户名:<input type='text' name='username'><br>");
        out.println("                密码:<input type='password' name='password'><br>");
        out.println("        <input type='submit' value='login'>");
        out.println("    </form>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

        String username = request.getParameter("username");
        String password = request.getParameter("password");


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);

    }
}
