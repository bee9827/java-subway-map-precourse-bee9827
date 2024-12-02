package subway.view;

import subway.view.feature.MainFeature;
import subway.view.feature.LineFeature;
import subway.view.feature.RouteFeature;
import subway.view.feature.StationFeature;

import java.util.Scanner;

public class Inputview {
    private static final String HEADER = "## ";
    private final Scanner scanner;

    public Inputview(Scanner scanner) {
        this.scanner = scanner;
    }

    public MainFeature askMain(){
        printMainScreen();
        System.out.println(HEADER + "원하는 기능을 선택하세요.");
        return MainFeature.getFeature(scanner.nextLine());
    }

    public StationFeature askStation(){
        printStationScreen();
        System.out.println(HEADER + "원하는 기능을 선택하세요.");
        return StationFeature.getFeature(scanner.nextLine());
    }

    public LineFeature askLine(){
        printLineScreen();
        System.out.println(HEADER + "원하는 기능을 선택하세요.");
        return LineFeature.getFeature(scanner.nextLine());
    }

    public RouteFeature askRoute(){
        printRouteScreen();
        System.out.println(HEADER + "원하는 기능을 선택하세요.");
        return RouteFeature.getFeature(scanner.nextLine());
    }

    public String ask(String s){
        System.out.println(HEADER + s);
        return scanner.nextLine();
    }

    private void printMainScreen() {
        System.out.println(HEADER + "메인 화면");
        for (MainFeature mainFeature : MainFeature.values()) {
            System.out.printf("%s. %s%n", mainFeature.getInput(), mainFeature.getName());
        }
        System.out.println();
    }

    private void printStationScreen() {
        System.out.println(HEADER + "역 관리 화면");
        for (StationFeature stationFeature : StationFeature.values()) {
            System.out.printf("%s. %s%n", stationFeature.getInput(), stationFeature.getName());
        }
        System.out.println();
    }

    private void printLineScreen() {
        System.out.println(HEADER + "노선 관리 화면");
        for (LineFeature lineFeature : LineFeature.values()) {
            System.out.printf("%s. %s%n", lineFeature.getInput(), lineFeature.getName());
        }
        System.out.println();
    }

    private void printRouteScreen() {
        System.out.println(HEADER + "구간 관리 화면");
        for (RouteFeature routeFeature : RouteFeature.values()) {
            System.out.printf("%s. %s%n", routeFeature.getInput(), routeFeature.getName());
        }
        System.out.println();
    }
}
