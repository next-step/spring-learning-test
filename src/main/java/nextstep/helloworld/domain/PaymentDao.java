package nextstep.helloworld.domain;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentDao {
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Payment> rowMapper = (resultSet, rowNum) -> new Payment(
            resultSet.getLong("id"),
            resultSet.getString("member_name"),
            resultSet.getInt("amount")
    );

    public PaymentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Payment payment) {
        String sql = "insert into payment (member_name, amount) values (?, ?)";
        jdbcTemplate.update(sql, payment.getMemberName(), payment.getAmount());
    }

    public List<Payment> findAll() {
        String sql = "SELECT id, member_name, amount from payment;";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Payment findById(Long id) {
        String sql = "SELECT id, member_name, amount from payment where id = ?;";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void updateName(Long id, String newName) {
        String sql = "update payment set member_name = ? where id = ?";
        jdbcTemplate.update(sql, newName, id);
    }
}
