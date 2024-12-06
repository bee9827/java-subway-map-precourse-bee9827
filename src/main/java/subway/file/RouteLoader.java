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

    private static Route createRoute(List<String> args) {
        Line line = LineRepository.findByName(args.get(0));
        List<Station> stations = new ArrayList<>();
        for (int i = 1; i < args.size(); i++) {
            Station station = StationRepository.findByName(args.get(i));
            stations.add(station);
        }
        return new Route(line, stations);
    }
}
