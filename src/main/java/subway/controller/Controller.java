package subway.controller;

import subway.service.LineService;
import subway.service.RouteService;
import subway.service.StationService;
import subway.view.Inputview;
import subway.view.Outputview;
import subway.view.feature.LineFeature;
import subway.view.feature.MainFeature;
import subway.view.feature.RouteFeature;
import subway.view.feature.StationFeature;

import java.util.EnumMap;

public class Controller {
    private final Inputview inputView;
    private final Outputview outputView;

    private final StationService stationService = new StationService();
    private final LineService lineService = new LineService();
    private final RouteService routeService = new RouteService();

    public Controller(Inputview inputView, Outputview outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        runWithRetry(() -> {
            handleMain(inputView.askMain());
        });
    }

    private void handleMain(MainFeature selected) {
        EnumMap<MainFeature, Runnable> actions = new EnumMap<>(MainFeature.class);

        actions.put(MainFeature.STATION, () -> handleStation(inputView.askStation()));
        actions.put(MainFeature.LINE, () -> handleLine(inputView.askLine()));
        actions.put(MainFeature.ROUTE, () -> handleRoute(inputView.askRoute()));
        actions.put(MainFeature.ROUTE_PRINT, this::printAllRoute);
        actions.put(MainFeature.QUIT, this::quit);

        Runnable action = actions.get(selected);
        action.run();
    }

    private void quit() {
    }

    private void printAllRoute() {

    }

    private void handleRoute(RouteFeature routeFeature) {

    }

    private void handleLine(LineFeature lineFeature) {

    }

    private void handleStation(StationFeature selected) {
        EnumMap<StationFeature, Runnable> actions = new EnumMap<>(StationFeature.class);
        actions.put(StationFeature.ENROLL, () -> enrollStation());
        actions.put(StationFeature.DELETE, () -> deleteStation());
        actions.put(StationFeature.CHECK, () -> printStation());
        actions.put(StationFeature.BACK, () -> inputView.askMain());

        Runnable action = actions.get(selected);
        action.run();
    }


    private void printStation() {
        outputView.printList(stationService.findAll());
    }

    private void deleteStation() {
        runWithRetry(() -> {
            String stationName = inputView.ask("삭제할 역 이름을 입력하세요.");
            stationService.deleteStation(stationName);
        });
    }

    private void enrollStation() {
        runWithRetry(() -> {
            String stationName = inputView.ask("등록할 역 이름을 입력하세요.");
            stationService.addStation(stationName);
            outputView.printString("지하철 역이 등록되었습니다.");
        });
    }

    public void runWithRetry(Runnable runnable) {
        while (true) {
            try {
                runnable.run();
                return;
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
