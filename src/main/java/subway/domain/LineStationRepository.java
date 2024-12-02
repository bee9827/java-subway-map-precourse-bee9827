package subway.domain;

import java.util.*;

public class LineStationRepository {
    private static final Map<Line,List<Station>> lineStations = new HashMap<>();
    private static final int STARTPOINT = 1;

    public static Map<Line,List<Station>> lineStations() {
        return Collections.unmodifiableMap(lineStations);
    }

    public static void addLineStations(Line line, Station startStation, Station endStation) {
        lineStations.put(line, List.of(startStation, endStation));
    }

    public static void addStation(Line line, Station station, int location) {
        lineStations.get(line).add(location - STARTPOINT,station);
    }

    public static boolean checkStation(Station station) {
        return lineStations.values()
                .stream()
                .anyMatch(stations -> stations.contains(station));
    }


    public static boolean deleteLine(Line line) {
        if(lineStations.containsKey(line)) {
            lineStations.remove(line);
            return true;
        }
        return false;
    }
}
