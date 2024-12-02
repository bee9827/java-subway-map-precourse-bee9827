package subway.domain;

import java.util.*;

public class RouteRepository {
    private static final int START_POINT = 1;
    private static final String NOT_FOUND_LINE_EXCEPTION = "해당 구간에 노선이 존재 하지 않습니다.";
    private static final String DUPLICATE_STATION_EXCEPTION = "해당 구간에 노선이 존재 하지 않습니다.";
    private static final String NOT_FOUND_STATION_EXCEPTION = "해당 구간에 역이 존재 하지 않습니다.";
    private static final String INVALID_LOCATION_EXCEPTION = "해당 역의 위치가 잘못 되었습니다.";
    private static final Map<Line, List<Station>> routes = new HashMap<>();

    public static Map<Line, List<Station>> routes() {
        return Collections.unmodifiableMap(routes);
    }

    public static void addRoute(Line line, Station startStation, Station endStation) {
        routes.put(line, List.of(startStation, endStation));
    }

    public static void addStation(Line line, Station station, int location) {
        validRoute(line, station, location);
        routes.get(line).add(location - START_POINT, station);
    }

    public static List<Station> findStationsByLine(Line line) {
        validLine(line);
        return routes.get(line);
    }

    public static boolean isPresentStation(Station station) {
        return routes.values()
                .stream()
                .anyMatch(stations -> stations.contains(station));
    }

    public static void deleteStation(Line line, Station station) {
        validStation(line, station);
        routes.get(line).remove(station);
    }

    public static void deleteLine(Line line) {
        validLine(line);
        routes.remove(line);
    }

    private static void validRoute(Line line, Station station, int location) {
        validDuplicateStation(line, station);
        if (location < START_POINT || routes.get(line).size() < location - START_POINT) {
            throw new IllegalArgumentException(INVALID_LOCATION_EXCEPTION);
        }
    }

    private static void validDuplicateStation(Line line, Station station) {
        validLine(line);
        if (routes.get(line).contains(station)) {
            throw new IllegalArgumentException(DUPLICATE_STATION_EXCEPTION);
        }
    }

    private static void validStation(Line line, Station station) {
        validLine(line);
        if (!routes.get(line).contains(station)) {
            throw new IllegalArgumentException(NOT_FOUND_STATION_EXCEPTION);
        }
    }

    private static void validLine(Line line) {
        if (!routes.containsKey(line)) {
            throw new IllegalArgumentException(NOT_FOUND_LINE_EXCEPTION);
        }
    }

}
