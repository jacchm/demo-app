package org.aszjch.demoapp.config;

import org.springframework.boot.testcontainers.service.connection.ContainerConnectionDetailsFactory;
import org.springframework.boot.testcontainers.service.connection.ContainerConnectionSource;
import org.testcontainers.containers.MinIOContainer;

public class MinIOContainerConnectionDetailsFactory extends ContainerConnectionDetailsFactory<MinIOContainer, MinIOConnetionDetails> {
    MinIOContainerConnectionDetailsFactory() {
    }

    @Override
    protected MinIOConnetionDetails getContainerConnectionDetails(ContainerConnectionSource<MinIOContainer> source) {
        return new MinIOContainerConnectionDetails(source);
    }

    private static final class MinIOContainerConnectionDetails extends ContainerConnectionDetailsFactory.ContainerConnectionDetails<MinIOContainer> implements MinIOConnetionDetails {
        public MinIOContainerConnectionDetails(ContainerConnectionSource<MinIOContainer> source) {
            super(source);
        }

        @Override
        public String url() {
            return getContainer().getS3URL();
        }

        @Override
        public String username() {
            return getContainer().getUserName();
        }

        @Override
        public String password() {
            return getContainer().getPassword();
        }
    }
}
