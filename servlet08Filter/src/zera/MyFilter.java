package zera;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter("/Aserv")
public class MyFilter implements Filter {

    public MyFilter() {
        System.out.println("无参方法执行");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init 方法执行");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter 方法执行");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("destroy 方法执行");
    }
}
