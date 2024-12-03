package subway.view;

import subway.domain.Station;

import java.io.PrintStream;
import java.util.List;

public class Outputview {
    private static final String HEADER = "[INFO] ";
    private static final String ERROR_HEADER = "[ERROR] ";

    public void printStationList(List<Station> stations) {
        System.out.println();
        stations.forEach(s -> System.out.println(HEADER + s.getName()));
    }

    public void printBodyWithHeader(String body){
        System.out.println(HEADER + body);
    }

    public void printString(String string) {
        System.out.println(HEADER + string);
    }

    public void printError(String error) {
        System.out.println(ERROR_HEADER + error);
    }
}
