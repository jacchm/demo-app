package org.aszjch.demoapp.infrastructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
class KafkaTopicsConfig {

    @Bean
    public NewTopic articlesTopic() {
        return TopicBuilder.name("articles")
                .partitions(1)
                .replicas(1)
                .build();
    }


}
