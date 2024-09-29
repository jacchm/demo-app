package org.aszjch.demoapp.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "temp")
@Getter
@Setter
public class TempFilesProperties {

    private String dir = System.getProperty("java.io.tmpdir");

}
