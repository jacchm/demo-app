package org.aszjch.demoapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@Configuration
public class TestClockBean {

    @Bean
    @Primary
    Clock testClock() {
        return Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }
}
