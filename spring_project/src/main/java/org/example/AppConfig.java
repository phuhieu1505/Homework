package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example")
public class AppConfig {
//    @Bean
//    public SamSung getPhone(){
//        return new SamSung();
//    }
//    @Bean
//    public SnapDragon getProcessor(){
//        return new SnapDragon();
//    }
}
