package com.itqgroup.service.app;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
abstract class TestContainers {

  static final String database = "itqgroup";

  @Container
  private static final PostgreSQLContainer POSTGRE_SQL_CONTAINER = new PostgreSQLContainer("postgres:13")
          .withDatabaseName(database)
          .withUsername("postgres")
          .withPassword("secret");

  static {
    POSTGRE_SQL_CONTAINER.withInitScript("v1.0__init.sql");
    POSTGRE_SQL_CONTAINER.start();

    System.setProperty("spring.datasource.url", POSTGRE_SQL_CONTAINER.getJdbcUrl());
    System.setProperty("spring.datasource.username", POSTGRE_SQL_CONTAINER.getUsername());
    System.setProperty("spring.datasource.password", POSTGRE_SQL_CONTAINER.getPassword());
  }
}
