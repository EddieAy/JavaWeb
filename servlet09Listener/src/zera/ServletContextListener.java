package zera;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.annotation.WebListener;


@WebListener
public class ServletContextListener implements jakarta.servlet.ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext对象创建时 使用");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext对象销毁时 使用");
    }
}
