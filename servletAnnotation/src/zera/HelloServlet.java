package zera;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(name="hello",value = {"/hello1","/hello2"},loadOnStartup = 1,
initParams = {@WebInitParam(name = "username",value = "123"),@WebInitParam(name = "password",value = "1234")})
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("servlet name = " + getServletName() + "<br>");

        out.println("servlet path = " + request.getServletPath() + "<br>");

        Enumeration<String> initParameterNames = getInitParameterNames();
        while (initParameterNames.hasMoreElements()){
            String name = initParameterNames.nextElement();
            String value = getInitParameter(name);
            out.println(name + " = " + value +"<br>");
        }
    }
}
