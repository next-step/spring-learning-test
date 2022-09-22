package nextstep.helloworld.domain;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class PlayDao {
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Play> rowMapper = (resultSet, rowNum) -> new Play(
            resultSet.getLong("id"),
            resultSet.getString("member_name"),
            resultSet.getTimestamp("datetime").toLocalDateTime()
    );

    public PlayDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Play play) {
        String sql = "insert into play (member_name, datetime) values (?, ?)";
        jdbcTemplate.update(sql, play.getMemberName(), Timestamp.valueOf(play.getDateTime()));
    }

    public List<Play> findAll() {
        String sql = "SELECT id, member_name, datetime from play;";
        return jdbcTemplate.query(sql, rowMapper);
    }
}
