package nextstep.helloworld.jdbc.simpleinsert;

import nextstep.helloworld.jdbc.Customer;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class SimpleInsertDao {
    private SimpleJdbcInsert insertActor;

    public SimpleInsertDao(DataSource dataSource) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("customers")
                .usingGeneratedKeyColumns("id");
    }

    /**
     * Map +
     * insertActor.executeAndReturnKey
     */
    public Customer insertWithMap(Customer customer) {
        return null;
    }

    /**
     * SqlParameterSource +
     * insertActor.executeAndReturnKey
     */
    public Customer insertWithBeanPropertySqlParameterSource(Customer customer) {
        return null;
    }
}
