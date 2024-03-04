package bean;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

public class User implements HttpSessionBindingListener {

    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        ServletContext application = event.getSession().getServletContext();

        Integer onlineCount = (Integer) application.getAttribute("onlineCount");
        if (onlineCount == null) {
            onlineCount = 1;
        } else {
            onlineCount++;
        }
        application.setAttribute("onlineCount", onlineCount);
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        ServletContext application = event.getSession().getServletContext();
        Object onlineCount = application.getAttribute("onlineCount");
        int count = (Integer)onlineCount;
        application.setAttribute("onlineCount",--count);
    }
}
