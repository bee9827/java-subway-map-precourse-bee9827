package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class StationRepository {
    private static final String NOT_EXIST_STATION_EXCEPTION = "없는 지하철 역 입니다.";
    private static final List<Station> stations = new ArrayList<>();

    public static List<Station> stations() {
        return Collections.unmodifiableList(stations);
    }

    public static boolean contains(Station station) {
        return stations.contains(station);
    }

    public static boolean contains(String name) {
        return stations.stream().anyMatch(station -> station.getName().equals(name));
    }

    public static Station findByName(String name) {
        return stations.stream()
                .filter(station -> station.getName().equals(name))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException(NOT_EXIST_STATION_EXCEPTION));
    }

    public static void addStation(Station station) {
        stations.add(station);
    }

    public static boolean deleteStation(String name) {
        return stations.removeIf(station -> Objects.equals(station.getName(), name));
    }
}
