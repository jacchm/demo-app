package org.aszjch.demoapp;

import com.redis.testcontainers.RedisContainer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.MinIOContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.PostgreSQLContainerProvider;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

    @Bean
    @ServiceConnection
    KafkaContainer kafkaContainer() {
        return new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));
    }

    @Bean
    @ServiceConnection(name = "redis")
    RedisContainer redisContainer() {
        return new RedisContainer(DockerImageName.parse("redis:latest")).withExposedPorts(6379);
    }

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        PostgreSQLContainerProvider postgreSQLContainerProvider = new PostgreSQLContainerProvider();
        return (PostgreSQLContainer<?>) postgreSQLContainerProvider.newInstance("latest");
    }

    @Bean
//    @ServiceConnection
    MinIOContainer minioContainer() {
        // TODO 23.09.2024: własne service connection pod minio
        return new MinIOContainer(DockerImageName.parse("minio/minio:latest"));
    }

}
