package zera;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class ContextServlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        servletResponse.setContentType("text/html");
        PrintWriter out = servletResponse.getWriter();

        ServletContext application = this.getServletContext();
        out.print("ServletContext is + " + application);
        out.print("<br>");

        Enumeration<String> names = application.getInitParameterNames();
        while (names.hasMoreElements()) {
            String key = names.nextElement();
            String value = application.getInitParameter(key);
            out.print(key + " = " + value);
            out.print("<br>");
        }

        String contextPath = application.getContextPath();
        out.print(contextPath);
        out.print("<br>");

        String filePath = application.getRealPath("hello.html");
        out.print(filePath + "<br>");

        application.log("你访问一次，我记录一次");
        int age = 17;
        if (age < 18){
            application.log("小屁孩快走开",new RuntimeException());
        }
    }
}
