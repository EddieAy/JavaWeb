package zera;

import jakarta.servlet.annotation.WebServlet;

public class ReflectionAnnotations {
    public static void main(String[] args) throws Exception{
        Class<?> welcomeClass = Class.forName("zera.HelloServlet");
        boolean isAnno = welcomeClass.isAnnotationPresent(WebServlet.class);
        if(isAnno){
            WebServlet webServletAnnotation = welcomeClass.getAnnotation(WebServlet.class);
            String[] values = webServletAnnotation.value();
            System.out.println(values[0]);
        }
    }
}
