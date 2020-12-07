package com.Project.PetBook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PetBookApplication {

//     extends SpringBootServletInitializer
    
    public static void main(String[] args) {
        SpringApplication.run(PetBookApplication.class, args);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(applicationClass);
//    }
//
//    private static Class<PetBookApplication> applicationClass = PetBookApplication.class;

}
