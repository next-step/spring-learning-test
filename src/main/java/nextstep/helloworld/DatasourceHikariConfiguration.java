package nextstep.helloworld;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Profile("hikari")
@Configuration
public class DatasourceHikariConfiguration {
    @Bean
    public DataSource getDataSource() {
        HikariConfig hikariConfig = new HikariConfig();

        return new HikariDataSource(hikariConfig);
    }
}
