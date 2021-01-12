package nextstep.helloworld.core.di;

public class StationSetterService {
    private StationRepository stationRepository;

    public String sayHi() {
        return stationRepository.sayHi();
    }
}
