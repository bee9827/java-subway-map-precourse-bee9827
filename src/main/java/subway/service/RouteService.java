package subway.service;

import subway.domain.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RouteService {

    public Map<String,List<String>> findAll(){
        Map<String,List<String>> routeName = new LinkedHashMap<>();
        RouteRepository.routes().forEach(v ->
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

        RouteRepository.deleteStation(line, station);
    }
}
