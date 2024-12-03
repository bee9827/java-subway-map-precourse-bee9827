package subway.view.feature;

public enum StationFeature implements Feature {
    ENROLL("1", "역 등록"),
    DELETE("2", "역 삭제"),
    CHECK("3", "역 조회"),
    BACK("B", "돌아가기");

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
        throw new IllegalArgumentException(INVALID_FEATURE_EXCEPTION);
    }

    @Override
    public String getInput() {
        return input;
    }

    @Override
    public String getName() {
        return name;
    }
}
