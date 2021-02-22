package io.softwarestrategies.projectx.resource.config;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import io.softwarestrategies.projectx.resource.data.utils.DatabaseToProjectReadingConverter;
import io.softwarestrategies.projectx.resource.data.utils.ProjectConverters;
import io.softwarestrategies.projectx.resource.data.utils.ProjectStatusToStringWritingConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.reactive.TransactionalOperator;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableR2dbcRepositories
@EnableTransactionManagement
public class DatabaseConfig extends AbstractR2dbcConfiguration {

    @Value("${spring.r2dbc.host:localhost}")
    private String R2DBC_HOST;

    @Value("${spring.r2dbc.username}")
    private String R2DBC_USERNAME;

    @Value("${spring.r2dbc.password}")
    private String R2DBC_PASSWORD;

    @Value("${spring.r2dbc.port:5432}")
    private int R2DBC_PORT;

    @Bean
    @Primary
    public ConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .host(R2DBC_HOST)
                        .database("projectx")
                        .username(R2DBC_USERNAME)
                        .password(R2DBC_PASSWORD)
                        .port(R2DBC_PORT)
                        .build()
        );
    }

    @Bean
    public ReactiveTransactionManager reactiveTransactionManager
            (@Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

    @Bean
    public TransactionalOperator transactionalOperator(
            @Qualifier("reactiveTransactionManager") ReactiveTransactionManager reactiveTransactionManager) {
        return TransactionalOperator.create(reactiveTransactionManager);
    }

    @Override
    public List<Object> getCustomConverters() {
        return java.util.List.of(
                new DatabaseToProjectReadingConverter(),
                new ProjectStatusToStringWritingConverter()
        );
    }
}
