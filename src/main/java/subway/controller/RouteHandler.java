package subway.controller;

import subway.service.RouteService;
import subway.view.Inputview;
import subway.view.Outputview;
import subway.view.feature.RouteFeature;

import java.util.EnumMap;

public class RouteHandler implements Handler {
    private final Inputview inputView;
    private final Outputview outputView;
    private final EnumMap<RouteFeature, Runnable> routeRunnable = new EnumMap<>(RouteFeature.class);
    private final RouteService routeService = new RouteService();

    public RouteHandler(Inputview inputView, Outputview outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        initializeRouteHandler();
    }

    @Override
    public void run() {
        outputView.printInstruction("구간 관리");
        outputView.printRouteScreen();
        handle();
    }

    @Override
    public void enroll() {
        String lineName = inputView.ask(() -> outputView.printInstruction("노선을 입력하세요"));
        String stationName = inputView.ask(() -> outputView.printInstruction("역이름을 입력하세요"));
        int location = Integer.parseInt(inputView.ask(() -> outputView.printInstruction("순서를 입력하세요.")));
        routeService.addStation(lineName, stationName, location);
        outputView.printString("지하철 역이 등록되었습니다.");
    }

    @Override
    public void delete() {
        String lineName = inputView.ask(() -> outputView.printInstruction("삭제할 구간의 노선을 입력하세요."));
        String stationName = inputView.ask(() -> outputView.printInstruction("삭제할 구간의 역을 입력하세요."));
        routeService.deleteStation(lineName, stationName);
        outputView.printString("구간이 삭제되었습니다.");
    }

    @Override
    public void printAll() {
        // TODO document why this method is empty
        //  다른 곳에서 사용.
    }

    @Override
    public void handle() {
        runWithRetry(() -> {
            outputView.printInstruction("원하는 기능을 선택하세요.");
            routeRunnable.get(inputView.askRoute()).run();
        }, outputView);
    }

    private void initializeRouteHandler() {
        routeRunnable.put(RouteFeature.ENROLL, this::enroll);
        routeRunnable.put(RouteFeature.DELETE, this::delete);
        routeRunnable.put(RouteFeature.BACK, () -> {
        });
    }


}
