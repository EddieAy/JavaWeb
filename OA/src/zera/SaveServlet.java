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

public class SaveServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
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
            req.getRequestDispatcher("/error.html").forward(req, response);
        }
    }
}
