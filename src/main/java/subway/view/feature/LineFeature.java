package subway.view.feature;

public enum LineFeature implements Feature {
    ENROLL("1", "노선 등록"),
    DELETE("2","노선 삭제"),
    CHECK("3","노선 조회"),
    BACK("B","돌아가기");

    private String input;
    private String name;

    LineFeature(String input, String name) {
        this.input = input;
        this.name = name;
    }

    public static LineFeature getFeature(String input) {
        for (LineFeature feature : LineFeature.values()) {
            if (feature.input.equals(input)) {
                return feature;
            }
        }
        return null;
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
