package zera;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class ConfigServlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setContentType("text/html");
        PrintWriter out = servletResponse.getWriter();


        ServletConfig config = this.getServletConfig();
        out.print("servlet config 对象是 " + config + "<br>");

        out.print("servlet config 的 servlet Name 是 " + config.getServletName());
        out.print("<br>");

        Enumeration<String> names = this.getInitParameterNames();
        while (names.hasMoreElements()){
            String value = this.getInitParameter(names.nextElement());
            out.print(value);
            out.print("<br>");

        }
    }
}
