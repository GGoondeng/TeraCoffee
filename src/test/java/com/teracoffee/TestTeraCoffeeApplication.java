package com.teracoffee;

import org.springframework.boot.SpringApplication;

public class TestTeraCoffeeApplication {

    public static void main(String[] args) {
        SpringApplication.from(TeraCoffeeApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
