package zera;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;

@WebListener
public class HttpSessionListener implements jakarta.servlet.http.HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("Session 方法执行");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("Session 方法销毁");
    }
}
