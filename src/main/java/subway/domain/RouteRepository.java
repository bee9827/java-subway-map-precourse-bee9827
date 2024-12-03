package subway.domain;

import subway.file.RouteLoader;

import java.util.*;

public class RouteRepository {
    private static final int START_POINT = 1;
    private static final String NOT_FOUND_LINE_EXCEPTION = "해당 구간에 노선이 존재 하지 않습니다.";
    private static final String DUPLICATE_STATION_EXCEPTION = "해당 구간에 노선이 존재 하지 않습니다.";
    private static final String NOT_FOUND_STATION_EXCEPTION = "해당 구간에 역이 존재 하지 않습니다.";
    private static final String INVALID_LOCATION_EXCEPTION = "해당 역의 위치가 잘못 되었습니다.";
    private static final List<Route> routes = new RouteLoader().getRoutes();

    public static List<Route> routes() {
        return Collections.unmodifiableList(routes);
    }

    public static void addRoute(Line line, Station startStation, Station endStation) {
        routes.add(new Route(line,List.of(startStation,endStation)));
    }

    public static void addStation(Line line, Station station, int location) {
        validRoute(line, station, location);
        findStationsByLine(line).add(location - START_POINT, station);
    }

    public static List<Station> findStationsByLine(Line line) {
        return routes.stream()
                .filter(r -> r.getLine().equals(line))
                .map(Route::getStations)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_LINE_EXCEPTION));
    }

    public static Route findRouteByLine(Line line){
        return routes.stream()
                .filter(r -> r.getLine().equals(line))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_LINE_EXCEPTION));
    }

    public static boolean isPresentStation(Station station) {
        return routes.stream()
                .anyMatch(route -> route.getStations().contains(station));
    }

    public static void deleteStation(Line line, Station station) {
        validStation(line, station);
        findStationsByLine(line).remove(station);
    }

    public static void deleteByLine(Line line) {
        findStationsByLine(line);
        Route route = findRouteByLine(line);
        routes.remove(route);
    }

    private static void validRoute(Line line, Station station, int location) {
        validDuplicateStation(line, station);
        if (location < START_POINT || findStationsByLine(line).size() < location - START_POINT) {
            throw new IllegalArgumentException(INVALID_LOCATION_EXCEPTION);
        }
    }

    private static void validDuplicateStation(Line line, Station station) {
        if (findStationsByLine(line).contains(station)) {
            throw new IllegalArgumentException(DUPLICATE_STATION_EXCEPTION);
        }
    }

    private static void validStation(Line line, Station station) {
        if (!findStationsByLine(line).contains(station)) {
            throw new IllegalArgumentException(NOT_FOUND_STATION_EXCEPTION);
        }
    }


}
