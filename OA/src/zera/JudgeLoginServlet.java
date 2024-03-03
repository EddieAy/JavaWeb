package zera;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import util.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(value = {"/judgeLogin"})
public class JudgeLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            String f = request.getParameter("f");
            if("1".equals(f)){
                Cookie c1 = new Cookie("loginName",username);
                Cookie c2 = new Cookie("passWord",password);
                c1.setMaxAge(60 * 60 * 24 * 10);
                c2.setMaxAge(60 * 60 * 24 * 10);
                c1.setPath(request.getContextPath());
                c2.setPath(request.getContextPath());
                response.addCookie(c1);
                response.addCookie(c2);
            }
            response.sendRedirect(request.getContextPath()+"/employee");
        }else {
            HttpSession httpSession = request.getSession();
            httpSession.removeAttribute("username");
            response.sendRedirect(request.getContextPath()+"/error.jsp");
        }
    }
}
