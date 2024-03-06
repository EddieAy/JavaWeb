package mvc;

import exceptions.AppException;
import exceptions.MoneyNotEnough;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/transfer")
public class AccountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fromActno = request.getParameter("fromActno");
        String toActno = request.getParameter("toActno");
        double money = Double.parseDouble(request.getParameter("moneyValue"));
        AccountService accountService = new AccountService();//这个是MVC 中的model部分 用于处理业务
        //Controller 不处理业务
        try {
            accountService.transfer(fromActno,toActno,money);
            response.sendRedirect(request.getContextPath() +"/success.jsp");
        } catch (MoneyNotEnough e) {
            response.sendRedirect(request.getContextPath() +"/fail.jsp");
        } catch (AppException e) {
            response.sendRedirect(request.getContextPath() +"/fail.jsp");

        }



    }
}
