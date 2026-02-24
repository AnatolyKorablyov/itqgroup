package com.itqgroup.service.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaRepositories
@SpringBootApplication
public class ITQGroupApplication {

  public static void main(String[] args) {
    SpringApplication.run(ITQGroupApplication.class, args);
  }
}
