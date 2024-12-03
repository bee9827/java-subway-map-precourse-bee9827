package subway.controller;

import subway.service.LineService;
import subway.view.Inputview;
import subway.view.Outputview;
import subway.view.feature.LineFeature;

import java.util.EnumMap;

public class LineHandler implements Handler {
    private final Inputview inputView;
    private final Outputview outputView;
    private final EnumMap<LineFeature, Runnable> lineRunnable = new EnumMap<>(LineFeature.class);
    private final LineService lineService = new LineService();

    public LineHandler(Inputview inputView, Outputview outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        initializeLineHandler();
    }

    @Override
    public void run() {
        outputView.printInstruction("노선 관리 화면");
        outputView.printLineScreen();
        handle();
    }

    @Override
    public void enroll() {
        String lineName = inputView.ask(() -> outputView.printInstruction("등록할 노선 이름을 입력하세요."));
        String startStationName = inputView.ask(() -> outputView.printInstruction("등록할 노선 상행 종점역 이름을 입력하세요."));
        String endStationName = inputView.ask(() -> outputView.printInstruction("등록할 노선 하행 종점역 이름을 입력하세요."));
        lineService.addLine(lineName, startStationName, endStationName);
        outputView.printString("지하철 노선이 등록되었습니다.");
    }

    @Override
    public void delete() {
        String lineName = inputView.ask(() -> outputView.printInstruction("삭제할 노선 이름을 입력하세요."));
        lineService.deleteLine(lineName);
        outputView.printString("지하철 노선이 삭제되었습니다.");
    }

    @Override
    public void printAll() {
        outputView.printInstruction("노선 목록");
        outputView.printInfo(lineService.findAll());
    }

    @Override
    public void handle() {
        runWithRetry(() -> {
            lineRunnable.get(inputView.askLine()).run();
        }, outputView);
    }

    private void initializeLineHandler() {
        lineRunnable.put(LineFeature.ENROLL, this::enroll);
        lineRunnable.put(LineFeature.DELETE, this::delete);
        lineRunnable.put(LineFeature.CHECK, this::printAll);
        lineRunnable.put(LineFeature.BACK, () -> {
        });
    }


}
