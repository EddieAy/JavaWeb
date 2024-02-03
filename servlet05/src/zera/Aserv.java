package zera;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

public class Aserv extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date nowTime= new Date();

        req.setAttribute("systemTime",nowTime);

        Object systemTime = req.getAttribute("systemTime");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("Current time is : " +systemTime + "<br>");

//        Map<String, String[]> map = req.getParameterMap();
        Enumeration<String> names = req.getParameterNames();
        while (names.hasMoreElements()){
            String name = names.nextElement();
            String value = req.getParameter(name);
            out.println(name +"  " +value +"<br>");
        }

        String IP = req.getRemoteAddr();
        out.println("Your IP is :" + IP);

/*        out.println(req.getRemoteUser());
        out.println(req.getRemoteHost());
        out.println(req.getRemotePort());*/

        out.println("<br>根目录： " + req.getContextPath());
        out.println("<br>URI： " + req.getRequestURI());
        out.println("<br>servlet path： " +req.getServletPath());

    }
}
