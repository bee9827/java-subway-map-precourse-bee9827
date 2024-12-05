package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class StationRepository {
    private static final String NOT_FOUND_STATION_EXCEPTION = "없는 지하철 역 입니다.";
    private static final String DUPLICATE_STATION_EXCEPTION = "중복된 지하철 역 입니다.";

    private static final List<Station> stations = new ArrayList<>();

    private StationRepository() {
    }

    public static List<Station> stations() {
        return Collections.unmodifiableList(stations);
    }

    public static Station findByName(String name) {
        return stations.stream()
                .filter(station -> Objects.equals(station.getName(), name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_STATION_EXCEPTION));
    }

    public static void addStation(Station station) {
        if (contains(station)) {
            throw new IllegalArgumentException(DUPLICATE_STATION_EXCEPTION);
        }
        stations.add(station);
    }

    public static void deleteStation(Station station) {
        if (!contains(station)) {
            throw new IllegalArgumentException(NOT_FOUND_STATION_EXCEPTION);
        }
        stations.remove(station);
    }

    public static boolean deleteStation(String name) {
        return stations.removeIf(station -> Objects.equals(station.getName(), name));
    }

    private static boolean contains(Station station) {
        return stations.stream().anyMatch(s -> Objects.equals(s.getName(), station.getName()));
    }
}
