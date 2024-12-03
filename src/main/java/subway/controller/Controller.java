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

    private final StationHandler stationHandler;
    private final LineHandler lineHandler;
    private final RouteHandler routeHandler;

    private final EnumMap<MainFeature, Runnable> mainRunnable = new EnumMap<>(MainFeature.class);
    private final RouteService routeService = new RouteService();


    public Controller(Inputview inputView, Outputview outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.stationHandler = new StationHandler(inputView,outputView);
        this.lineHandler = new LineHandler(inputView,outputView);
        this.routeHandler = new RouteHandler(inputView,outputView);
        initializeMainRunnable();
    }

    public void run() {
        runWithRetry(() -> {
            outputView.printInstruction("메인 화면");
            outputView.printMainScreen();
            handle();
        });
    }

    private void initializeMainRunnable() {
        mainRunnable.put(MainFeature.STATION, stationHandler::run);
        mainRunnable.put(MainFeature.LINE, lineHandler::run);
        mainRunnable.put(MainFeature.ROUTE, routeHandler::run);
        mainRunnable.put(MainFeature.ROUTE_PRINT, this::printAll);
        mainRunnable.put(MainFeature.QUIT, () -> {throw new IllegalStateException("종료");});
    }

    private void printAll() {
        outputView.printInstruction("지하철 노선도");
        routeService.findAll().forEach((k, v) -> {
            outputView.printInfo(k, v);
            outputView.lineSeparator();
        });
    }

    private void handle() {
        runWithRetry(() ->{
            outputView.printInstruction("원하는 기능을 선택하세요.");
            mainRunnable.get(inputView.askMain()).run();
        });
    }

    private void runWithRetry(Runnable runnable) {
        while (true) {
            try {
                runnable.run();
                outputView.lineSeparator();
                return;
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
                outputView.lineSeparator();
            } catch(IllegalStateException e) {
                return ;
            }
        }
    }
}
