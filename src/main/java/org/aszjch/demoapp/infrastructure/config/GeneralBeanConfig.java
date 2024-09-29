package org.aszjch.demoapp.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
class GeneralBeanConfig {

    @Bean
    Clock clock() {
        return Clock.systemUTC();
    }
}
