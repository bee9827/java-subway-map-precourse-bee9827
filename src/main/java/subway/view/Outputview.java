package subway.view;

import subway.domain.Station;

import java.util.List;

public class Outputview {
    private static final String HEADER = "[INFO] ";
    private static final String ERROR_HEADER = "[ERROR] ";

    public void printList(List<Station> stations) {
        stations.forEach(s -> System.out.println(HEADER + s.getName()));
    }

    public void printString(String string) {
        System.out.println(HEADER + string);
    }

    public void printError(String error) {
        System.out.println(ERROR_HEADER + error);
    }
}
