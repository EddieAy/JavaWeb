package zera;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.DBUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet(value = {"/employee","/detail","/add","/save","/delete","/edit","/update","/login","/judgeLogin"})
public class DeptServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] values = {"/employee","/detail","/add","/save","/delete","/edit","/update"};
        String servletPath = req.getServletPath();

        if(values[0].equals(servletPath)){
            doList(req,resp);
        }else if(values[1].equals(servletPath)){
            doDetail(req,resp);
        }else if(values[2].equals(servletPath)){
            doAdd(req,resp);
        }else if(values[3].equals(servletPath)){
            doSave(req,resp);
        }else if(values[4].equals(servletPath)){
            doMyDelete(req,resp);
        }else if(values[5].equals(servletPath)){
            doEdit(req,resp);
        }else if(values[6].equals(servletPath)){
            doUpdate(req,resp);
        }
    }

    /*private void doJudgeLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        boolean loginSuccess = false;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from oa_user where username = ? and password = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                loginSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }
        if(loginSuccess){
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("username",username);

            response.sendRedirect(request.getContextPath()+"/employee");
        }else {
            HttpSession httpSession = request.getSession();
            httpSession.removeAttribute("username");
            response.sendRedirect(request.getContextPath()+"/error.jsp");
        }

    }*/


//    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String projectName = request.getContextPath();
//        PrintWriter out = response.getWriter();
//        out.println("<!DOCTYPE html>");
//        out.println("<html>");
//        out.println("<head>");
//        out.println("    <title>登录</title>");
//        out.println("    <meta charset='UTF-8'>");
//        out.println("    <style>");
//        out.println("                body {");
//        out.println("            font-family: Arial, sans-serif;");
//        out.println("            margin: 0;");
//        out.println("            padding: 0;");
//        out.println("            display: flex;");
//        out.println("            justify-content: center;");
//        out.println("            align-items: center;");
//        out.println("            height: 100vh;");
//        out.println("            background-color: #f0f0f0;");
//        out.println("        }");
//        out.println("form-container {");
//        out.println("            display: flex;");
//        out.println("            justify-content: center;");
//        out.println("            width: 100%;");
//        out.println("        }");
//        out.println("        form {");
//        out.println("            width: 100%;");
//        out.println("            max-width: 300px; /* 限制表单的最大宽度 */");
//        out.println("            background: #ffffff;");
//        out.println("            padding: 20px;");
//        out.println("            border-radius: 8px;");
//        out.println("            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
//        out.println("        }");
//        out.println("        h1 {");
//        out.println("            text-align: center;");
//        out.println("            color: #333;");
//        out.println("        }");
//        out.println("        hr {");
//        out.println("            margin-bottom: 20px;");
//        out.println("        }");
//        out.println("        input[type='text'],");
//        out.println("                input[type='password'] {");
//        out.println("            width: calc(100% - 22px);");
//        out.println("            padding: 10px;");
//        out.println("            margin-bottom: 20px;");
//        out.println("            border: 1px solid #ccc;");
//        out.println("            border-radius: 5px;");
//        out.println("        }");
//        out.println("        input[type='submit'] {");
//        out.println("            width: 100%;");
//        out.println("            padding: 10px;");
//        out.println("            border: none;");
//        out.println("            border-radius: 5px;");
//        out.println("            background-color: #007bff;");
//        out.println("            color: white;");
//        out.println("            cursor: pointer;");
//        out.println("        }");
//        out.println("        input[type='submit']:hover {");
//        out.println("            background-color: #0056b3;");
//        out.println("        }");
//        out.println("    </style>");
//        out.println("</head>");
//        out.println("<body>");
//        out.println("<div class='form-container'>");
//        out.println("    <form action='"+projectName+"/judgeLogin' method='get'> <!-- 注意这里修正了action属性的值 -->");
//        out.println("        <h1>用户登录</h1>");
//        out.println("        <hr>");
//        out.println("                用户名:<input type='text' name='username'><br>");
//        out.println("                密码:<input type='password' name='password'><br>");
//        out.println("        <input type='submit' value='login'>");
//        out.println("    </form>");
//        out.println("</div>");
//        out.println("</body>");
//        out.println("</html>");
//
//
//    }


    private void doUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String projectName = req.getContextPath();

        String empno = req.getParameter("empno");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = -1;
        try {
            connection = DBUtil.getConnection();
            String sql = "update emp2 set job = ?,sal = ?,mgr = ?,deptno = ? where empno = ?";
            preparedStatement = connection.prepareStatement(sql);
            String job = req.getParameter("job");
            String sal = req.getParameter("sal");
            String mgr = req.getParameter("mgr");
            String deptno = req.getParameter("deptno");

            preparedStatement.setString(1,job);
            preparedStatement.setString(2,sal);
            preparedStatement.setString(3,mgr);
            preparedStatement.setString(4,deptno);
            preparedStatement.setString(5,empno);

            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(count == 1){
//            req.getRequestDispatcher("/employee").forward(req,resp);
            resp.sendRedirect(projectName + "/employee");

        }else {
            req.getRequestDispatcher("./error.jsp").forward(req,resp);
        }
    }

    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

    private void doMyDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String projectName = request.getContextPath();
        String empno = request.getParameter("empno");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int affectedRows = -1;
        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false);
            String sql = "DELETE FROM emp2 WHERE empno = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,empno);
            affectedRows = preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if(connection != null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }


        if(affectedRows == 1){
//            request.getRequestDispatcher("/employee").forward(request,response);
            response.sendRedirect(projectName + "/employee");
        }
    }

    private void doSave(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String ename = req.getParameter("ename"); // 对应表单中的<input name="ename">
        String empno = req.getParameter("empno"); // 对应表单中的<input name="empno">
        String job = req.getParameter("job"); //
        String mgr = req.getParameter("mgr"); //
        String hiredate = req.getParameter("hiredate"); //
        String sal = req.getParameter("sal"); //
        String deptno = req.getParameter("deptno"); //

        int affectRows = 0;
        try {
            connection = DBUtil.getConnection();
            String sql = "INSERT INTO emp2 (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, empno);
            preparedStatement.setString(2, ename);
            preparedStatement.setString(3, job);
            preparedStatement.setString(4, mgr);
            preparedStatement.setString(5, hiredate);
            preparedStatement.setString(6, sal);
            preparedStatement.setNull(7, java.sql.Types.DOUBLE);
            preparedStatement.setString(8, deptno);

            affectRows = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }

        if (affectRows == 1) {
            req.getRequestDispatcher("/employee").forward(req, response);
        } else {
            req.getRequestDispatcher("/error.jsp").forward(req, response);
        }
    }

    private void doAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
        out.println("<h1>新增员工</h1>");
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

    private void doDetail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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

    private void doList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        if(session != null && session.getAttribute("username") != null){
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
            out.println("                document.location.href = '"+projectName+"/delete?empno=' + empno");
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
            out.println("    <a href='"+projectName+"/add' id='add'>新增员工</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }else {
            resp.sendRedirect(req.getContextPath()+"/login");
        }


    }
}
