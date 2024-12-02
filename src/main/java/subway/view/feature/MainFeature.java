package subway.view.feature;

public enum MainFeature {
    STATION("1", "역 관리"),
    LINE("2","노선 관리"),
    ROUTE("3","구간 관리"),
    ROUTE_PRINT("4","지하철 노선도 출력"),
    QUIT("Q", "종료");

    private String input;
    private String name;

    MainFeature(String input, String name) {
        this.input = input;
        this.name = name;
    }

    public static MainFeature getFeature(String input) {
        for (MainFeature feature : values()) {
            if (feature.input.equals(input)) {
                return feature;
            }
        }
        return null;
    }

    public String getInput() {
        return input;
    }

    public String getName() {
        return name;
    }
}
