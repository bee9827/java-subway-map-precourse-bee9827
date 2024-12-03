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
        return MainFeature.getFeature(scanner.nextLine());
    }

    public StationFeature askStation(){
        return StationFeature.getFeature(scanner.nextLine());
    }

    public LineFeature askLine(){
        return LineFeature.getFeature(scanner.nextLine());
    }

    public RouteFeature askRoute(){
        return RouteFeature.getFeature(scanner.nextLine());
    }

    public String ask(){
        return scanner.nextLine();
    }

    public String ask(Runnable runnable){
        runnable.run();
        return scanner.nextLine();
    }
}
