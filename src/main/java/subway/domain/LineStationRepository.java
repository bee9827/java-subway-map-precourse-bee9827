package subway.domain;

import java.util.*;

public class LineStationRepository {
    private static final int START_POINT = 1;
    private static final String NOT_FOUND_LINE_EXCEPTION = "해당 구간에 노선이 존재 하지 않습니다.";
    private static final String DUPLICATE_STATION_EXCEPTION = "해당 구간에 노선이 존재 하지 않습니다.";
    private static final String NOT_FOUND_STATION_EXCEPTION = "해당 구간에 역이 존재 하지 않습니다.";
    private static final String INVALID_LOCATION_EXCEPTION = "해당 역의 위치가 잘못 되었습니다.";
    private static final Map<Line, List<Station>> lineStations = new HashMap<>();

    public static Map<Line, List<Station>> lineStations() {
        return Collections.unmodifiableMap(lineStations);
    }

    public static void addLineStations(Line line, Station startStation, Station endStation) {
        lineStations.put(line, List.of(startStation, endStation));
    }

    public static void addStation(Line line, Station station, int location) {
        validStationLocation(line, station, location);
        lineStations.get(line).add(location - START_POINT, station);
    }

    private static void validStationLocation(Line line, Station station, int location) {
        validDuplicateStation(line, station);
        if (location < START_POINT || lineStations.get(line).size() < location - START_POINT) {
            throw new IllegalArgumentException(INVALID_LOCATION_EXCEPTION);
        }
    }

    private static void validDuplicateStation(Line line, Station station) {
        validLine(line);
        if (lineStations.get(line).contains(station)) {
            throw new IllegalArgumentException(DUPLICATE_STATION_EXCEPTION);
        }
    }

    public static List<Station> findStationsByLine(Line line) {
        validLine(line);
        return lineStations.get(line);
    }

    public static boolean isPresentStation(Station station) {
        return lineStations.values()
                .stream()
                .anyMatch(stations -> stations.contains(station));
    }

    public static void deleteStation(Line line, Station station) {
        validStation(line, station);
        lineStations.get(line).remove(station);
    }

    public static void deleteLine(Line line) {
        validLine(line);
        lineStations.remove(line);
    }

    private static void validStation(Line line, Station station) {
        validLine(line);
        if (!lineStations.get(line).contains(station)) {
            throw new IllegalArgumentException(NOT_FOUND_STATION_EXCEPTION);
        }
    }

    private static void validLine(Line line) {
        if (!lineStations.containsKey(line)) {
            throw new IllegalArgumentException(NOT_FOUND_LINE_EXCEPTION);
        }
    }

}
