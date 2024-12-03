package subway.file;

import subway.domain.Station;

import java.util.List;

public class StationLoader {
    private static final String STATION_PATH = "src/main/resources/station.md";
    private final List<Station> stations;

    public StationLoader() {
        stations = FileDataLoader.getObjects(STATION_PATH, this::createStation);
    }

    public List<Station> getStations() {
        return stations;
    }

    private Station createStation(String[] args) {
        String name = args[0];
        return new Station(name);
    }
}
