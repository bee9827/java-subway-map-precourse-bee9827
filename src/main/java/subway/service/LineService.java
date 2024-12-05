package subway.service;

import subway.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class LineService {

    public List<String> findAll(){
        return LineRepository.lines()
                .stream()
                .map(Line::getName)
                .collect(Collectors.toList());
    }

    public void addLine(String lineName, String startStationName, String endStationName) {
        Station startStation = StationRepository.findByName(startStationName);
        Station endStation = StationRepository.findByName(endStationName);
        Line line = new Line(lineName);
        Route route = new Route(line,List.of(startStation, endStation));

        LineRepository.addLine(line);
        RouteRepository.addRoute(route);
    }

    public void deleteLine(String lineName) {
        Line line = LineRepository.findByName(lineName);
        LineRepository.deleteLine(line);
        RouteRepository.deleteRoute(line);
    }
}
