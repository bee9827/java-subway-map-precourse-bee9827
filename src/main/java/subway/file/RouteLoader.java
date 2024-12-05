package subway.file;

import subway.domain.*;

import java.util.ArrayList;
import java.util.List;

public class RouteLoader {
    private static final String ROUTE_PATH = "src/main/resources/route.md";
    private static final List<Route> routes = new ArrayList<>();

    private RouteLoader() {}

    public static List<Route> getRoutes() {
        routes.addAll(FileDataLoader.getObjects(ROUTE_PATH, RouteLoader::createRoute));
        return routes;
    }

    private static Route createRoute(String[] args) {
        Line line = LineRepository.findByName(args[0]);
        List<Station> stations = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            Station station = StationRepository.findByName(args[i].trim());
            stations.add(station);
        }
        return new Route(line, stations);
    }
}
