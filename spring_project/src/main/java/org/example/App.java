package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        ApplicationContext factory = new AnnotationConfigApplicationContext(AppConfig.class);
        SamSung a8 = factory.getBean(SamSung.class);
        a8.config();
    }
}
