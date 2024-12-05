package subway;

import subway.controller.Controller;
import subway.view.Inputview;
import subway.view.Outputview;

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
