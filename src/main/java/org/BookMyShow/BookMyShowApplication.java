package org.BookMyShow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BookMyShowApplication {
    public static void main(String[] args) {

        System.out.println("Hello, World!");
        SpringApplication.run(BookMyShowApplication.class,args);
    }
}