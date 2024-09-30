package org.aszjch.demoapp.config;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.testcontainers.service.connection.ContainerConnectionDetailsFactory;
import org.springframework.boot.testcontainers.service.connection.ContainerConnectionSource;
import org.testcontainers.containers.MinIOContainer;

@Slf4j
@NoArgsConstructor
public class MinIOContainerConnectionDetailsFactory extends ContainerConnectionDetailsFactory<MinIOContainer, MinIOConnetionDetails> {

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
            String url = getContainer().getS3URL();
            log.debug("Url retrieved from MinIO container: [{}]", url);
            return url;
        }

        @Override
        public String username() {
            String userName = getContainer().getUserName();
            log.debug("Username retrieved from MinIO container: [{}]", userName);
            return userName;
        }

        @Override
        public String password() {
            String password = getContainer().getPassword();
            log.debug("Password retrieved from MinIO container: [{}]", password);
            return password;
        }
    }
}
