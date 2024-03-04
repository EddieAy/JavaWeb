package zera;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

//@WebListener
public class HttpSessionAttributeListener implements jakarta.servlet.http.HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        System.out.println("session added");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        System.out.println("session removed");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        System.out.println("session replaced");
    }
}
