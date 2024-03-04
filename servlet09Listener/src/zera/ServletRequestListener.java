package zera;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ServletRequestListener implements jakarta.servlet.ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("request 启动");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("request 销毁");
    }
}
