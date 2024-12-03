package subway.controller;

import subway.service.StationService;
import subway.view.Inputview;
import subway.view.Outputview;
import subway.view.feature.StationFeature;

import java.util.EnumMap;

public class StationHandler implements Handler {
    private final Inputview inputView;
    private final Outputview outputView;
    private final EnumMap<StationFeature, Runnable> stationRunnable = new EnumMap<>(StationFeature.class);
    private final StationService stationService = new StationService();

    public StationHandler(Inputview inputView, Outputview outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        initializeStationHandler();
    }

    @Override
    public void run() {
        outputView.printInstruction("역 관리 화면");
        outputView.printStationScreen();
        handle();
    }

    @Override
    public void enroll() {
        String stationName = inputView.ask(() -> outputView.printInstruction("등록할 역 이름을 입력하세요."));
        stationService.addStation(stationName);
        outputView.printString("지하철 역이 등록되었습니다.");
    }

    @Override
    public void delete() {
        String stationName = inputView.ask(() -> outputView.printInstruction("삭제할 역 이름을 입력하세요."));
        stationService.deleteStation(stationName);
        outputView.printString("지하철 역이 삭제되었습니다.");
    }

    @Override
    public void printAll() {
        outputView.printInstruction("역 목록");
        outputView.printInfo(stationService.findAll());
    }

    @Override
    public void handle() {
        runWithRetry(() -> {
            outputView.printInstruction("원하는 기능을 선택하세요.");
            stationRunnable.get(inputView.askStation()).run();
        }, outputView);
    }

    private void initializeStationHandler() {
        stationRunnable.put(StationFeature.ENROLL, this::enroll);
        stationRunnable.put(StationFeature.DELETE, this::delete);
        stationRunnable.put(StationFeature.CHECK, this::printAll);
        stationRunnable.put(StationFeature.BACK, () -> {});
    }


}
