package subway.domain;

import java.util.*;

public class RouteRepository {
    private static final String NOT_FOUND_LINE_EXCEPTION = "해당 구간에 노선이 존재 하지 않습니다.";
    private static final String DUPLICATE_LINE_EXCEPTION = "해당 구간에 동일한 노선이 존재합니다.";
    private static final List<Route> routes = new ArrayList<>();

    private RouteRepository() {
    }

    public static List<Route> routes() {
        return Collections.unmodifiableList(routes);
    }

    public static void addRoute(Route route) {
        validRouteForAdd(route);
        routes.add(route);
    }

    public static void deleteRoute(Line line) {
        routes.remove(findRouteByLine(line));
    }

    public static boolean isPresentStation(Station station) {
        return routes.stream()
                .anyMatch(route -> route.getStations().contains(station));
    }

    public static void addStation(Line line, Station station, int location) {
        findRouteByLine(line).addStation(location, station);
    }

    public static void deleteStation(Line line, Station station) {
        findRouteByLine(line).deleteStation(station);
    }


    private static Route findRouteByLine(Line line) {
        return routes.stream()
                .filter(r -> r.getLine().equals(line))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_LINE_EXCEPTION));
    }

    private static void validRouteForAdd(Route route) {
        if (routes.stream().anyMatch(r -> r.getLine().equals(route.getLine()))) {
            throw new IllegalArgumentException(DUPLICATE_LINE_EXCEPTION);
        }
    }

}
