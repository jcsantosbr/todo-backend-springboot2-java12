package com.jcs.todobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class TodobackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(TodobackendApplication.class, args);
  }

}
