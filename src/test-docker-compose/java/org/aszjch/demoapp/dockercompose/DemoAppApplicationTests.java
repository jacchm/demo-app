package org.aszjch.demoapp.dockercompose;

import org.aszjch.demoapp.DemoAppApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.MinIOContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.PostgreSQLContainerProvider;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(classes = DemoAppApplication.class)
class DemoAppApplicationTests {

    private static final PostgreSQLContainer<?> postgresContainer;
    private static final KafkaContainer kafkaContainer;
    private static final MinIOContainer minIOContainer;

    static {
        final PostgreSQLContainerProvider postgreSQLContainerProvider = new PostgreSQLContainerProvider();
        postgresContainer = (PostgreSQLContainer<?>) postgreSQLContainerProvider
                .newInstance("latest")
                .withInitScript("db/init.sql");
        postgresContainer.start();

        minIOContainer = new MinIOContainer(DockerImageName.parse("minio/minio:latest"));
        minIOContainer.start();

        kafkaContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));
        kafkaContainer.start();
    }

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);

        registry.add("minio.url", minIOContainer::getS3URL);
        registry.add("minio.username", minIOContainer::getUserName);
        registry.add("minio.password", minIOContainer::getPassword);
        registry.add("minio.bucket", () -> "test-bucket");

        registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);
    }

    @Test
    void contextLoads() {
    }
}
