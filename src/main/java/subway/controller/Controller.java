package subway.controller;

import subway.domain.LineRepository;
import subway.domain.RouteRepository;
import subway.domain.StationRepository;
import subway.file.LineLoader;
import subway.file.RouteLoader;
import subway.file.StationLoader;
import subway.service.RouteService;
import subway.view.Inputview;
import subway.view.Outputview;
import subway.view.feature.MainFeature;

import java.util.EnumMap;
import static java.lang.System.exit;

public class Controller {
    private final Inputview inputView;
    private final Outputview outputView;

    private final StationHandler stationHandler;
    private final LineHandler lineHandler;
    private final RouteHandler routeHandler;

    private final EnumMap<MainFeature, Runnable> mainRunnable = new EnumMap<>(MainFeature.class);


    public Controller(Inputview inputView, Outputview outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        // Todo
        //  Config 이용 해서 싱글톤 관리 하기
        this.stationHandler = new StationHandler(inputView,outputView);
        this.lineHandler = new LineHandler(inputView,outputView);
        this.routeHandler = new RouteHandler(inputView,outputView);
        initializeMainRunnable();
        loadRepositoryData();
    }

    public void run() {
        while(true){
            outputView.printInstruction("메인 화면");
            outputView.printMainScreen();
            handle();
        }
    }

    private void initializeMainRunnable() {
        mainRunnable.put(MainFeature.STATION, stationHandler);
        mainRunnable.put(MainFeature.LINE, lineHandler);
        mainRunnable.put(MainFeature.ROUTE, routeHandler);
        mainRunnable.put(MainFeature.ROUTE_PRINT, routeHandler::printAll);
        mainRunnable.put(MainFeature.QUIT, () -> exit(0));
    }

    private void loadRepositoryData() {
        // 순서 주의!! Route가 마지막에 와야함
        StationLoader.getStations().forEach(StationRepository::addStation);
        LineLoader.getLines().forEach(LineRepository::addLine);
        RouteLoader.getRoutes().forEach(RouteRepository::addRoute);
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
            }
        }
    }
}
