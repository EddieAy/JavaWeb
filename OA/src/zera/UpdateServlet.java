package zera;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            req.getRequestDispatcher("/employee").forward(req,resp);
        }else {
            req.getRequestDispatcher("./error.html").forward(req,resp);
        }
    }
}
