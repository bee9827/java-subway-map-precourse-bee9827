package subway.domain;

import java.util.List;

public class Route {
    private static final int STATION_START_POINT = 1;
    private static final int MIN_ROUTE_SIZE = 2;
    private static final String ROUTE_SIZE_EXCEPTION = String.format("지하철 노선은 %d개 이상 이어야 합니다.", MIN_ROUTE_SIZE);
    private static final String DUPLICATE_STATION_EXCEPTION = "해당 구간에 노선이 존재 하지 않습니다.";
    private static final String NOT_FOUND_STATION_EXCEPTION = "해당 구간에 역이 존재 하지 않습니다.";
    private static final String INVALID_LOCATION_EXCEPTION = "해당 역의 위치가 잘못 되었습니다.";

    private final Line line;
    private final List<Station> stations;

    public Route(Line line, List<Station> stations) {
        validSize(stations);
        this.line = line;
        this.stations = stations;
    }

    public Line getLine() {
        return line;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void addStation(int location, Station station) {
        validDuplicateStation(station);
        validLocationForAdd(location);
        stations.add(location - STATION_START_POINT, station);

    }

    public void deleteStation(Station station) {
        validSizeForDelete();
        validStationForDelete(station);
        stations.remove(station);
    }

    private void validSizeForDelete() {
        if (checkSize(stations.size() - 1)) throw new IllegalArgumentException(ROUTE_SIZE_EXCEPTION);
    }

    private void validLocationForAdd(int location) {
        if (location < STATION_START_POINT || stations.size() < location - STATION_START_POINT) {
            throw new IllegalArgumentException(INVALID_LOCATION_EXCEPTION);
        }
    }

    private void validDuplicateStation(Station station) {
        if (stations.contains(station)) {
            throw new IllegalArgumentException(DUPLICATE_STATION_EXCEPTION);
        }
    }

    private void validStationForDelete(Station station) {
        if (!stations.contains(station)) {
            throw new IllegalArgumentException(NOT_FOUND_STATION_EXCEPTION);
        }
    }

    private void validSize(List<Station> stations) {
        if (!checkSize(stations.size())) {
            throw new IllegalArgumentException(ROUTE_SIZE_EXCEPTION);
        }
    }

    private boolean checkSize(int size) {
        return size >= MIN_ROUTE_SIZE;
    }
}
