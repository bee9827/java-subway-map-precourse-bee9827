package subway.controller;

import subway.view.Outputview;
import subway.view.feature.Feature;
import subway.view.feature.LineFeature;

public interface Handler{

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

    void enroll();
    void delete();
    void printAll();
}
