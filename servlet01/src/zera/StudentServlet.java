package zera;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class StudentServlet implements Servlet{

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("Hello Servlet");
        servletResponse.setContentType("text/html");
        PrintWriter out = servletResponse.getWriter();

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
            String url = "jdbc:mysql://localhost:3306/zera"; //https://
            String user = "root";
            String password = "zera";
            connection = DriverManager.getConnection(url, user, password);
            String sql = "select * from emp where sal > ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, 1000);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("ename");
                String sal = resultSet.getString("sal");
                out.print(name + "'s sal is " + sal + "<br>");
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
