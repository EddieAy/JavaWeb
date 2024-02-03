package zera;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class Bserv extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object sysTime = req.getAttribute("systemTime");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("Current time is : " +sysTime + "<br>");
    }
}
