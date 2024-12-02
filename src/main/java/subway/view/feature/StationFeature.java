package subway.view.feature;

public enum StationFeature {
    ENROLL("1", "역 등록"),
    DELETE("2","역 삭제"),
    CHECK("3","역 조회"),
    BACK("B","돌아가기");

    private String input;
    private String name;

    StationFeature(String input, String name) {
        this.input = input;
        this.name = name;
    }

    public static StationFeature getFeature(String input) {
        for (StationFeature feature : StationFeature.values()) {
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
