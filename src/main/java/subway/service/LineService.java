package subway.service;

import subway.domain.*;

import java.util.List;

public class LineService {

    public List<Line> findAll(){
        return LineRepository.lines();
    }

    public void addLine(String lineName, String startStationName, String endStationName) {
        Station startStation = StationRepository.findByName(startStationName);
        Station endStation = StationRepository.findByName(endStationName);

        Line line = new Line(lineName);
        LineRepository.addLine(line);
        LineStationRepository.addLineStations(line, startStation, endStation);
    }

    public void deleteLine(String lineName) {
        Line line = LineRepository.findByName(lineName);
        LineRepository.deleteLine(line);
    }
}
