package subway.service;

import subway.domain.*;

import java.util.List;
import java.util.Map;

public class RouteService {
    private static final int MIN_ROUTE_SIZE = 2;
    private static final String ROUTE_SIZE_EXCEPTION = String.format("지하철 노선은 %d개 이상 이어야 합니다.", MIN_ROUTE_SIZE);

    public void addStation(String lineName, String stationName, int locaiton){
        Station station = StationRepository.findByName(stationName);
        Line line = LineRepository.findByName(lineName);

        RouteRepository.addStation(line, station, locaiton);
    }

    public void deleteStation(String lineName, String stationName) {
        Station station = StationRepository.findByName(stationName);
        Line line = LineRepository.findByName(lineName);

        List<Station> stations = RouteRepository.findStationsByLine(line);
        validSize(stations);
        RouteRepository.deleteStation(line, station);
    }

    private static void validSize(List<Station> stations) {
        if (stations.size() == MIN_ROUTE_SIZE) {
            throw new IllegalArgumentException(ROUTE_SIZE_EXCEPTION);
        }
    }

    public Map<Line,List<Station>> findAll(){
        return RouteRepository.routes();
    }
}
