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

public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            request.getRequestDispatcher("/employee").forward(request,response);
        }

/*        else {
            request.getRequestDispatcher("./error.html").forward(request,response);
        }*/
    }

}
