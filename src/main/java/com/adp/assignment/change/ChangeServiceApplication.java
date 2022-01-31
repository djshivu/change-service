package com.adp.assignment.change;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.adp.assignment"})
public class ChangeServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ChangeServiceApplication.class, args);
  }

}
