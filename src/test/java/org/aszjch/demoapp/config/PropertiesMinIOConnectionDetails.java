package org.aszjch.demoapp.config;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PropertiesMinIOConnectionDetails implements MinIOConnetionDetails {

    private final MinIOProperties minIOProperties;

    @Override
    public String url() {
        return minIOProperties.url();
    }

    @Override
    public String username() {
        return minIOProperties.username();
    }

    @Override
    public String password() {
        return minIOProperties.password();
    }
}
