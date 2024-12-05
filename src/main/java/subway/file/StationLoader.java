package subway.file;

import subway.domain.Station;

import java.util.ArrayList;
import java.util.List;

public class StationLoader {
    private static final String STATION_PATH = "src/main/resources/station.md";
    private static final List<Station> stations = new ArrayList<>();

    private StationLoader() {}

    public static List<Station> getStations() {
        stations.addAll(FileDataLoader.getObjects(STATION_PATH, StationLoader::createStation));
        return stations;
    }

    private static Station createStation(String[] args) {
        String name = args[0];
        return new Station(name);
    }
}
