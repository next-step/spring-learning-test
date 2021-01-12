package nextstep.helloworld;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HelloDao {
    private JdbcTemplate jdbcTemplate;

    public HelloDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(String name) {
        String SQL = "insert into HELLO (name) values (?)";
        jdbcTemplate.update(SQL, new Object[]{name});
    }

    public int countByName(String name) {
        String sql = "select count(1) from HELLO where name=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, name);
    }
}