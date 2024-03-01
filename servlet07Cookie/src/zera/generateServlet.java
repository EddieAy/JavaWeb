package zera;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = {"/generateCookie"})
public class generateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie c1 = new Cookie("productID","123321");
        Cookie c2 = new Cookie("wealth","infinite");

        c1.setMaxAge(-1);
        c2.setMaxAge(-1);
        c1.setPath(request.getContextPath());
        c2.setPath(request.getContextPath());
        response.addCookie(c1);
        response.addCookie(c2);

    }
}
