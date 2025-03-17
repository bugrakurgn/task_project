package com.example.task_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.task_project.client")
@EnableCaching
public class TaskProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskProjectApplication.class, args);
    }

}
