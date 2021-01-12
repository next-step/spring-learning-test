package nextstep.helloworld.core.di;

public class StationFieldService {
    private StationRepository stationRepository;

    public String sayHi() {
        return stationRepository.sayHi();
    }
}
