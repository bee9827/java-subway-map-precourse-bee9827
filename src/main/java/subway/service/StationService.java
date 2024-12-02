package subway.service;

import subway.domain.LineStationRepository;
import subway.domain.Station;
import subway.domain.StationRepository;

import java.util.List;

public class StationService {
    public static final String DUPLICATE_STATION_EXCEPTION = "중복된 지하철 역 이름 입니다.";
    public static final String REGISTERED_STATION_EXCEPTION = "노선에 등록 되어 있는 지하철 역 입니다.";

    public List<Station> findAll() {
        return StationRepository.stations();
    }

    public void addStation(Station station) {
        validDuplicate(station);
        StationRepository.addStation(station);
    }

    public void deleteStation(String name) {
        Station station = StationRepository.findByName(name);
        validLineStation(station);
        StationRepository.deleteStation(name);
    }

    private void validLineStation(Station station) {
        if(LineStationRepository.checkStation(station)){
            throw new IllegalArgumentException(REGISTERED_STATION_EXCEPTION);
        }
    }

    private void validDuplicate(Station station) {
        if (StationRepository.contains(station))
            throw new IllegalArgumentException(DUPLICATE_STATION_EXCEPTION);
    }
}
