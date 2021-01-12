package nextstep.helloworld.core.di;

import org.springframework.stereotype.Service;

@Service
public class StationConstructorService {
    private StationRepository stationRepository;

    public StationConstructorService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public String sayHi() {
        return stationRepository.sayHi();
    }
}
