package subway.view;

import subway.view.feature.MainFeature;
import subway.view.feature.LineFeature;
import subway.view.feature.RouteFeature;
import subway.view.feature.StationFeature;

import java.util.Scanner;

public class Inputview {
    private final Scanner scanner;

    public Inputview(Scanner scanner) {
        this.scanner = scanner;
    }

    public MainFeature askMain(){
        printMainScreen();
        return MainFeature.getFeature(scanner.nextLine());
    }

    public StationFeature askStation(){
        printStationScreen();
        return StationFeature.getFeature(scanner.nextLine());
    }

    public LineFeature askLine(){
        printLineScreen();
        return LineFeature.getFeature(scanner.nextLine());
    }

    public RouteFeature askRoute(){
        printRouteScreen();
        return RouteFeature.getFeature(scanner.nextLine());
    }

    public String ask(){
        return scanner.nextLine();
    }

    public String ask(Runnable runnable){
        runnable.run();
        return scanner.nextLine();
    }

    private void printMainScreen() {
        for (MainFeature mainFeature : MainFeature.values()) {
            System.out.printf("%s. %s%n", mainFeature.getInput(), mainFeature.getName());
        }
        System.out.println();
    }

    private void printStationScreen() {
        for (StationFeature stationFeature : StationFeature.values()) {
            System.out.printf("%s. %s%n", stationFeature.getInput(), stationFeature.getName());
        }
        System.out.println();
    }

    private void printLineScreen() {
        for (LineFeature lineFeature : LineFeature.values()) {
            System.out.printf("%s. %s%n", lineFeature.getInput(), lineFeature.getName());
        }
        System.out.println();
    }

    private void printRouteScreen() {
        for (RouteFeature routeFeature : RouteFeature.values()) {
            System.out.printf("%s. %s%n", routeFeature.getInput(), routeFeature.getName());
        }
        System.out.println();
    }
}
