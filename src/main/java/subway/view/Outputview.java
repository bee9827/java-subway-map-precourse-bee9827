package subway.view;

import subway.domain.Station;
import subway.view.feature.*;

import java.util.List;
import java.util.Map;

public class Outputview {
    private static final String INFO = "[INFO] ";
    private static final String ERROR_HEADER = "[ERROR] ";
    private static final String HEADER = "## ";


    public void printInfo(String line, List<String> station) {
            System.out.println(INFO + line);
            System.out.println(INFO + "---");
            printInfo(station);
    }

    public void printInfo(List<String> list) {
        list.forEach(name -> System.out.println(INFO + name));
    }

    public void printInfo(String body){
        System.out.println(INFO + body);
    }

    public void lineSeparator(){
        System.out.println();
    }

    public void printInstruction(String body){
        System.out.println(HEADER + body);
    }

    public void printString(String string) {
        System.out.println(INFO + string);
    }

    public void printError(String error) {
        System.out.println(ERROR_HEADER + error);
    }

    public void printMainScreen() {
        for (MainFeature mainFeature : MainFeature.values()) {
            System.out.printf("%s. %s%n", mainFeature.getInput(), mainFeature.getName());
        }
        System.out.println();
    }

    public void printStationScreen() {
        for (StationFeature stationFeature : StationFeature.values()) {
            System.out.printf("%s. %s%n", stationFeature.getInput(), stationFeature.getName());
        }
        System.out.println();
    }

    public void printLineScreen() {
        for (LineFeature lineFeature : LineFeature.values()) {
            System.out.printf("%s. %s%n", lineFeature.getInput(), lineFeature.getName());
        }
        System.out.println();
    }

    public void printRouteScreen() {
        for (RouteFeature routeFeature : RouteFeature.values()) {
            System.out.printf("%s. %s%n", routeFeature.getInput(), routeFeature.getName());
        }
        System.out.println();
    }

}
