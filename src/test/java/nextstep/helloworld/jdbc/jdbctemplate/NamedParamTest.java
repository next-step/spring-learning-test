package nextstep.helloworld.jdbc.jdbctemplate;

import nextstep.helloworld.jdbc.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class NamedParamTest {
    private NamedParamDAO namedParamDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @BeforeEach
    void setUp() {
        namedParamDAO = new NamedParamDAO(namedParameterJdbcTemplate);

        jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE customers(" +
                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

        List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);
    }

    @Test
    void useMapSqlParameterSource() {
        int count = namedParamDAO.useMapSqlParameterSource("John");

        assertThat(count).isEqualTo(1);
    }

    @Test
    void useBeanPropertySqlParameterSource() {
        int count = namedParamDAO.useBeanPropertySqlParameterSource(new Customer("John", "Woo"));

        assertThat(count).isEqualTo(1);
    }
}
