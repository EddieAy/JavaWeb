package zera;

import jakarta.servlet.*;

import java.io.IOException;

public class Aservlet implements Servlet {

    public Aservlet(){
        System.out.println("无参执行");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("Aservlet init method executed");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("Aservlet's service method execute");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("Aservlet's destory method execute");
    }
}
