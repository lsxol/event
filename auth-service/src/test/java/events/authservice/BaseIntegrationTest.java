package events.authservice;

import events.authservice.infrastracture.adapters.driven.jpa.SpringDataUzytkownikRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public abstract class BaseIntegrationTest {

  @Container
  static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:17")
      .withDatabaseName("auth-db")
      .withUsername("test")
      .withPassword("test")
      .withInitScript("init.sql");
  @Autowired
  private SpringDataUzytkownikRepository repo;

  @DynamicPropertySource
  static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
    registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    registry.add("spring.liquibase.default-schema", () -> "auth");
    registry.add("spring.jpa.properties.hibernate.default_schema", () -> "auth");
    registry.add("spring.liquibase.change-log", () -> "classpath:/db/changelog/changelog-master.yaml");
    registry.add("application.jwt.secret-key", () -> "MzE5d0FzN0ZQcDJyOUlYZnlpU2R5V0FLQXhGNWRtMHc");
    registry.add("application.jwt.expiration-ms", () -> "3600000");
    registry.add("application.system.user.uuid", () -> "3f4b1a54-8e3e-4e3c-9f31-0b4a4d7d8d21");
  }

  @BeforeEach
  void sprzatanie() {
    repo.deleteAll();
  }

}
