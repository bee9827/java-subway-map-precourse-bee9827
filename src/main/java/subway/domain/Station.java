package subway.domain;

public class Station {
    public static final int NAME_MIN_LENGTH = 2;
    private final String name;

    public Station(String name) {
        this.name = name;
        validName();
    }

    public String getName() {
        return name;
    }

    private void validName() {
        if (name.length() < NAME_MIN_LENGTH) {
            throw new IllegalArgumentException("이름은 " + NAME_MIN_LENGTH + "자 이상 이어야 합니다.");
        }
    }
    // 추가 기능 구현
}
