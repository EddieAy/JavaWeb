package zera;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        String servletPath = request.getServletPath();
        HttpSession session = request.getSession(false);
        if("/welcome".equals(servletPath) || "/welcome.html".equals(servletPath) ||
                "/login".equals(servletPath) || "/logout".equals(servletPath) || "/judgeLogin".equals(servletPath) ||
                session != null && session.getAttribute("user") != null){
            filterChain.doFilter(request,response);
        }else {
            response.sendRedirect(request.getContextPath()+"/login");
        }

/*        if("/welcome".equals(servletPath) || "/welcome.html".equals(servletPath) ||
                "/login".equals(servletPath) || "/logout".equals(servletPath) || "/judgeLogin".equals(servletPath) ||
                session != null && session.getAttribute("username") != null){
            filterChain.doFilter(request,response);
        }else {
            response.sendRedirect(request.getContextPath()+"/login");
        }*/

    }
}
