package subway.view.feature;

public enum RouteFeature {
    ENROLL("1", "구간 등록"),
    DELETE("2","구간 삭제"),
    BACK("B","돌아가기");

    private String input;
    private String name;

    RouteFeature(String input, String name) {
        this.input = input;
        this.name = name;
    }

    public static RouteFeature getFeature(String input) {
        for (RouteFeature feature : RouteFeature.values()) {
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
