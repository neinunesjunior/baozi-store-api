package com.baozi.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BaoziStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaoziStoreApplication.class, args);
        System.out.println("=========================================");
        System.out.println("  🥟 BAOZÍ STORE API - INICIADA! 🥟");
        System.out.println("  Acesse: http://localhost:8080");
        System.out.println("=========================================");
    }
}