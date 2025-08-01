package com.gamic.Endurably.Gamic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;



@SpringBootApplication
@EnableCaching
public class EndurablyGamicApplication {

    public static void main(String[] args) {
        SpringApplication.run(EndurablyGamicApplication.class, args);   
    }
}