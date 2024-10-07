package org.aszjch.demoapp.extendwith.tcconfig;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.PostgreSQLContainerProvider;

public class PostgresContainerExtension implements BeforeAllCallback {

    static PostgreSQLContainer<?> postgresContainer;

    @Override
    public void beforeAll(final ExtensionContext context) throws Exception {
        final PostgreSQLContainerProvider postgreSQLContainerProvider = new PostgreSQLContainerProvider();
        postgresContainer = (PostgreSQLContainer<?>) postgreSQLContainerProvider
                .newInstance("latest")
                .withInitScript("db/init.sql");
        postgresContainer.start();

        System.setProperty("spring.datasource.url", postgresContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgresContainer.getUsername());
        System.setProperty("spring.datasource.password", postgresContainer.getPassword());
    }
}
