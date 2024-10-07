package org.aszjch.demoapp.extendwith.tcconfig;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.MinIOContainer;
import org.testcontainers.utility.DockerImageName;

public class MinIOContainerExtension implements BeforeAllCallback {

    static MinIOContainer minIOContainer;

    @Override
    public void beforeAll(final ExtensionContext context) throws Exception {
        minIOContainer = new MinIOContainer(DockerImageName.parse("minio/minio:latest"));
        minIOContainer.start();

        System.setProperty("minio.url", minIOContainer.getS3URL());
        System.setProperty("minio.username", minIOContainer.getUserName());
        System.setProperty("minio.password", minIOContainer.getPassword());
        System.setProperty("minio.bucket", "test-bucket");
    }
}
