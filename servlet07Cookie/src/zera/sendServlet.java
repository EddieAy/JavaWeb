package zera;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = {"/getCookie"})
public class sendServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cos = request.getCookies();

        if (cos != null) {
            for (Cookie co : cos) {
                String name = co.getName();
                String value = co.getValue();
                System.out.println("name : "+name+" value : "+value);
            }
        }
    }
}
