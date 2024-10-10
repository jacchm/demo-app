package org.aszjch.demoapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@Configuration
@Profile("test-service-connection")
public class TestClockBean {

    @Bean
    @Primary
    Clock testClock() {
        return Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }
}
