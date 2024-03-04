package zera;

import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

public class User implements jakarta.servlet.http.HttpSessionBindingListener {
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("Binding 方法绑定数据");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("Binding 方法解绑数据");
    }

    private String name;
    private String gender;

    public User() {
    }

    public User(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
