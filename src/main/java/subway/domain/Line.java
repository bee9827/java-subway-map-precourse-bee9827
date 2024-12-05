package subway.domain;

public class Line {
    private static final int NAME_MIN_LENGTH = 2;
    private final String name;

    public Line(String name) {
        validName(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void validName(String name) {
        if (name.length() < NAME_MIN_LENGTH) {
            throw new IllegalArgumentException("이름은 " + NAME_MIN_LENGTH + "자 이상 이어야 합니다.");
        }
    }
    // 추가 기능 구현
}
