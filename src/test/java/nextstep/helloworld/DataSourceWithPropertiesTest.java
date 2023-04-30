package nextstep.helloworld;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DataSourceWithPropertiesTest {
    @Autowired
    DataSource dataSource;

    @Test
    void datasourceWithJavaConfig() {
        HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
        String url = hikariDataSource.getJdbcUrl();
        assertThat(url).isEqualTo("jdbc:h2:~/test;");
    }
}
