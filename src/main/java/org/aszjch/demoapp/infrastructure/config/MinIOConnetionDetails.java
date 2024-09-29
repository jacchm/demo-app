package org.aszjch.demoapp.infrastructure.config;

import org.springframework.boot.autoconfigure.service.connection.ConnectionDetails;

public interface MinIOConnetionDetails extends ConnectionDetails {

    String url();

    String username();

    String password();

    String bucket();
}
