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

    private final EnumMap<MainFeature, Runnable> mainHandler = new EnumMap<>(MainFeature.class);
    private final EnumMap<StationFeature, Runnable> stationHandler = new EnumMap<>(StationFeature.class);
    private final EnumMap<LineFeature, Runnable> lineHandler = new EnumMap<>(LineFeature.class);
    private final EnumMap<RouteFeature, Runnable> routeHandler = new EnumMap<>(RouteFeature.class);


    private final StationService stationService = new StationService();
    private final LineService lineService = new LineService();
    private final RouteService routeService = new RouteService();

    public Controller(Inputview inputView, Outputview outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        runWithRetry(() -> {

        });
    }

    private void initializeMainHandler() {
        System.out.println(HEADER + "원하는 기능을 선택하세요.");

        System.out.println(HEADER + "역 관리 화면");
        System.out.println(HEADER + "노선 관리 화면");
        System.out.println(HEADER + "구간 관리 화면");


        mainHandler.put(MainFeature.STATION, () -> handleStation(inputView.askStation()));
        mainHandler.put(MainFeature.LINE, () -> handleLine(inputView.askLine()));
        mainHandler.put(MainFeature.ROUTE, () -> handleRoute(inputView.askRoute()));
        mainHandler.put(MainFeature.ROUTE_PRINT, this::printAllRoute);
        mainHandler.put(MainFeature.QUIT, this::quit);
    }

    private void initializeStationHandler() {
        stationHandler.put(StationFeature.ENROLL, () -> enrollStation(inputView.ask("등록할 역 이름을 입력하세요.")));
        stationHandler.put(StationFeature.DELETE, () -> deleteStation(inputView.ask("삭제할 역 이름을 입력하세요.")));
        stationHandler.put(StationFeature.CHECK, () -> printAllStation());
        stationHandler.put(StationFeature.BACK, () -> inputView.askMain());
    }

    private void quit() {
    }

    private void printAllRoute() {

    }

    private void handleRoute(RouteFeature selected) {
        runWithRetry(() -> routeHandler.get(selected).run());
    }

    private void handleLine(LineFeature selected) {
        runWithRetry(() -> lineHandler.get(selected).run());
    }

    private void handleStation(StationFeature selected) {
        runWithRetry(() -> stationHandler.get(selected).run());
    }

    private void enrollStation(String stationName) {
        stationService.addStation(stationName);
        outputView.printString("지하철 역이 등록되었습니다.");
    }

    private void deleteStation(String stationName) {
        stationService.deleteStation(stationName);
        outputView.printString("지하철 역이 삭제되었습니다.");
    }

    private void printAllStation() {
        outputView.printStationList(stationService.findAll());
    }


    private void runWithRetry(Runnable runnable) {
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
