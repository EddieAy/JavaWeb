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

@WebServlet(value = "/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String username = null;
        String password = null;
        if(cookies != null){
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if("username".equals(name)){
                    username = cookie.getValue();
                }else if ("password".equals(name)){
                    password = cookie.getValue();
                }
            }
            //跳转
        }

        if(username != null && password != null){
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            boolean loginSuccess = false;
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
                HttpSession session = request.getSession();
                session.setAttribute("username",username);
                response.sendRedirect(request.getContextPath()+"/employee");
            }else {
                response.sendRedirect(request.getContextPath()+"/error.jsp");

            }
        }else {
            response.sendRedirect(request.getContextPath()+"/login");
        }
    }
}
