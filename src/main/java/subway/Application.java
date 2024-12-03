package subway;

import subway.controller.Controller;
import subway.controller.LineHandler;
import subway.controller.RouteHandler;
import subway.controller.StationHandler;
import subway.view.Inputview;
import subway.view.Outputview;
import subway.view.feature.RouteFeature;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        Inputview inputview = new Inputview(scanner);
        Outputview outputview = new Outputview();
        StationHandler stationHandler = new StationHandler(inputview, outputview);
        LineHandler lineHandler = new LineHandler(inputview, outputview);
        RouteHandler routeHandler = new RouteHandler(inputview, outputview);
        Controller controller = new Controller(inputview, outputview, stationHandler, lineHandler, routeHandler);

        controller.run();
        // TODO: 프로그램 구현
    }
}
