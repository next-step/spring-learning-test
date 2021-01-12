package nextstep.helloworld.core.di;

import org.springframework.stereotype.Repository;

@Repository
public class StationRepository {
    public String sayHi() {
        return "Hi";
    }
}
