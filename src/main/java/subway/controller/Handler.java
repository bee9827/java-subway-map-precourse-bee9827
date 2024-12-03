package subway.controller;

import subway.view.Inputview;
import subway.view.Outputview;
import subway.view.feature.StationFeature;

public interface Handler {

    default void runWithRetry(Runnable runnable, Outputview outputView) {
        while (true) {
            try {
                runnable.run();
                outputView.lineSeparator();
                return;
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
                outputView.lineSeparator();
            }
        }
    }
    void run();
    void enroll();
    void delete();
    void printAll();
    void handle();
}
