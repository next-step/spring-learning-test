package nextstep.helloworld.jdbc.jdbctemplate;

import nextstep.helloworld.jdbc.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class QueryingDaoTest {
    private QueryingDAO queryingDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        queryingDAO = new QueryingDAO(jdbcTemplate);

        jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE customers(" +
                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

        List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);
    }

    @Test
    void count() {
        int count = queryingDAO.count();

        assertThat(count).isEqualTo(4);
    }

    @Test
    void getLastName() {
        String lastName = queryingDAO.getLastName(1L);

        assertThat(lastName).isEqualTo("Woo");
    }

    @Test
    void findCustomerById() {
        Customer customer = queryingDAO.findCustomerById(1L);

        assertThat(customer).isNotNull();
        assertThat(customer.getLastName()).isEqualTo("Woo");
    }

    @Test
    void findAllCustomers() {
        List<Customer> customers = queryingDAO.findAllCustomers();

        assertThat(customers).hasSize(4);
    }

    @Test
    void findCustomerByFirstName() {
        List<Customer> customers = queryingDAO.findCustomerByFirstName("Josh");

        assertThat(customers).hasSize(2);
    }
}
