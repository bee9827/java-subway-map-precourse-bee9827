package subway.file;

import subway.domain.*;

import java.util.ArrayList;
import java.util.List;

public class RouteLoader {
    private static final String ROUTE_PATH = "src/main/resources/route.md";
    private final List<Route> routes;

    public RouteLoader() {
        routes = FileDataLoader.getObjects(ROUTE_PATH, this::createRoute);
    }

    public List<Route> getRoutes() {
        return routes;
    }

    private Route createRoute(String[] args) {
        Line line = LineRepository.findByName(args[0]);
        List<Station> stations = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            Station station = StationRepository.findByName(args[i]);
            stations.add(station);
        }
        return new Route(line, stations);
    }
}
