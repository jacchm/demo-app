package org.aszjch.demoapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.MinIOContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.PostgreSQLContainerProvider;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
@Slf4j
public class TestcontainersConfiguration {

    @Bean
    Network kafkaNetwork() {
        return Network.newNetwork();
    }

    @Bean
    @ServiceConnection
    KafkaContainer kafkaContainer(final Network kafkaNetwork) {
        final KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"))
                .withNetwork(kafkaNetwork)
                .withNetworkAliases("kafka");
        kafka.start();
        return kafka;
    }

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        final PostgreSQLContainerProvider postgreSQLContainerProvider = new PostgreSQLContainerProvider();
        return (PostgreSQLContainer<?>) postgreSQLContainerProvider
                .newInstance("latest")
                .withInitScript("db/init.sql");
    }

    @Bean
    @ServiceConnection
    MinIOContainer minioContainer() {
        return new MinIOContainer(DockerImageName.parse("minio/minio:latest"));
    }

    @Bean
    GenericContainer<?> kafkaUiContainer(final KafkaContainer kafkaContainer, final Network kafkaNetwork) {
        return new GenericContainer<>(DockerImageName.parse("provectuslabs/kafka-ui:latest"))
                .withNetwork(kafkaNetwork)
                .withExposedPorts(8080)
                .withEnv("KAFKA_CLUSTERS_0_NAME", "local")
                .withEnv("KAFKA_CLUSTERS_0_BOOTSTRAP_SERVERS", "kafka:9092")
                .dependsOn(kafkaContainer);
    }

}
