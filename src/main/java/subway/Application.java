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
        Controller controller = new Controller(inputview, outputview);

        controller.run();
    }
}
