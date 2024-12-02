package subway.service;

import subway.domain.RouteRepository;
import subway.domain.Station;
import subway.domain.StationRepository;

import java.util.List;

public class StationService {
    public static final String REGISTERED_STATION_EXCEPTION = "노선에 등록 되어 있는 지하철 역 입니다.";

    public List<Station> findAll() {
        return StationRepository.stations();
    }

    public void addStation(String name) {
        StationRepository.addStation(new Station(name));
    }

    public void deleteStation(String name) {
        Station station = StationRepository.findByName(name);
        validLineStation(station);
        StationRepository.deleteStation(station);
    }

    private void validLineStation(Station station) {
        if(RouteRepository.isPresentStation(station)){
            throw new IllegalArgumentException(REGISTERED_STATION_EXCEPTION);
        }
    }

}
