package org.aszjch.demoapp.infrastructure.config;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class TempFilesProperties {

    private final String dir = System.getProperty("java.io.tmpdir");

}
