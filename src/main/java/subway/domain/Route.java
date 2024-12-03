package subway.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Route {
    private final Line line;
    private final List<Station> stations;

    public Route(Line line, List<Station> stations) {
        this.line = line;
        this.stations = stations;
    }

    public Line getLine() {
        return line;
    }

    public List<Station> getStations() {
        return stations;
    }
}
