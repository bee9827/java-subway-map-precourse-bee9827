package subway.service;

import subway.domain.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RouteService {
    private static final int MIN_ROUTE_SIZE = 2;
    private static final String ROUTE_SIZE_EXCEPTION = String.format("지하철 노선은 %d개 이상 이어야 합니다.", MIN_ROUTE_SIZE);

    public Map<String,List<String>> findAll(){
        Map<String,List<String>> routeName = new LinkedHashMap<>();
        RouteRepository.routes().forEach((v) ->
                routeName.put(
                        v.getLine().getName(),
                        v.getStations().stream().map(Station::getName).collect(Collectors.toList())
                )
        );

        return routeName;
    }

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
}
