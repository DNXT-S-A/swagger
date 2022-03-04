package pt.dnxt.demo.swagger.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.driverClassName}")
    private String datasourceDriver;

    @Value("${spring.datasource.username}")
    private String datasourceUsername;

    @Value("${spring.datasource.password}")
    private String datasourcePassword;

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(datasourceDriver);
        dataSourceBuilder.url(datasourceUrl);
        dataSourceBuilder.username(datasourceUsername);
        dataSourceBuilder.password(datasourcePassword);
        return dataSourceBuilder.build();
    }
}
