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

public class ListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String projectName = req.getContextPath();

        out.println("        <!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>员工列表 - OA系统</title>");
        out.println("    <style>");
        out.println("                html, body {");
        out.println("            height: 100%;");
        out.println("            margin: 0;");
        out.println("            font-family: Arial, sans-serif;");
        out.println("        }");
        out.println("        body {");
        out.println("            display: flex;");
        out.println("            justify-content: center;");
        out.println("            align-items: center;");
        out.println("            text-align: center;");
        out.println("        }");
        out.println("content {");
        out.println("            width: 80%; /* 或者你希望的宽度 */");
        out.println("        }");
        out.println("        table {");
        out.println("            width: 100%;");
        out.println("            margin-top: 20px;");
        out.println("            border-collapse: collapse;");
        out.println("        }");
        out.println("        table, th, td {");
        out.println("            border: 2px solid black ;");
        out.println("        }");
        out.println("        th, td {");
        out.println("            padding: 1px;");
        out.println("            text-align: center;");
        out.println("        }");
        out.println("        #add {");
        out.println("            margin-top: 100px;");
        out.println("            display: block;");
        out.println("        }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");

        out.println("<script>");
        out.println("                function del(empno){");
        out.println("            if(window.confirm('亲，确认要删除吗，删除后不可恢复')){");
        out.println("                document.location.href = '/oa/delete?empno=' + empno");
        out.println("            }");
        out.println("        }");
        out.println("</script>");

        out.println("<div class='content'>");
        out.println("    <h1>员工详情</h1>");
        out.println("    <table>");
        out.println("        <tr>");
        out.println("            <th>姓名</th>");
        out.println("            <th>员工编号</th>");
        out.println("            <th>职位</th>");
        out.println("            <th>工资</th>");
        out.println("            <th>操作</th>");
        out.println("        </tr>");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from emp2";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
//            out.println("连上了吗");
            while (resultSet.next()){
//                out.println("在这？");
                String ename = resultSet.getString("ename");
                String empno = resultSet.getString("empno");
                String job = resultSet.getString("job");
                String sal = resultSet.getString("sal");
                out.println("        <tr>");
                out.println("            <td>"+ename+"</td>");
                out.println("            <td>"+empno+"</td>");
                out.println("            <td>"+job+"</td>");
                out.println("            <td>"+sal+"</td>");
                out.println("            <td>");
                out.println("                <a href='javascript:void(0)' onclick=del("+empno+")>删除</a>");
                out.println("                <a href='"+projectName+"/edit?empno="+empno+"'>编辑</a>");
                out.println("                <a href='"+projectName+"/detail?empno="+empno+"'>详情</a>");  //href='oa
                out.println("            </td>");
                out.println("        </tr>");
            }
            System.out.println("你输出到这了吗");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }


        out.println("    </table>");
        out.println("    <a href='"+projectName+"/add' id='add'>新增部门</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}