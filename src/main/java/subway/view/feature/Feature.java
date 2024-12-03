package subway.view.feature;

public interface Feature {
    public static final String INVALID_FEATURE_EXCEPTION = "선택할 수 없는 기능입니다.";
    String getInput();
    String getName();
}
