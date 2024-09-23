package org.aszjch.demoapp.config;

import org.springframework.boot.autoconfigure.service.connection.ConnectionDetails;

public interface MinIOConnetionDetails extends ConnectionDetails {
    String url();

    String username();

    String password();
}
