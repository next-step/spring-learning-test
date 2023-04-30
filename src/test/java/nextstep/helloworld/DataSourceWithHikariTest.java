package nextstep.helloworld;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("hikari")
@SpringBootTest
public class DataSourceWithHikariTest {
    @Autowired
    DataSource dataSource;

    @Test
    void datasourceWithJavaConfig() {
        HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
        int maximumPoolSize = hikariDataSource.getMaximumPoolSize();
        assertThat(maximumPoolSize).isEqualTo(20);
    }
}
