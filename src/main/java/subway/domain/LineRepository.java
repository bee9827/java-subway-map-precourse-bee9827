package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LineRepository {
    private static final String NOT_FOUND_LINE_EXCEPTION = "없는 지하철 노선 입니다.";
    private static final String DUPLICATE_LINE_EXCEPTION = "중복된 지하철 노선 입니다.";

    private static final List<Line> lines = new ArrayList<>();

    public static List<Line> lines() {
        return Collections.unmodifiableList(lines);
    }

    public static void addLine(Line line) {
        if (contains(line)) {
            throw new IllegalArgumentException(DUPLICATE_LINE_EXCEPTION);
        }
        lines.add(line);
    }

    public static Line findByName(String name) {
        return lines().stream()
                .filter(line -> Objects.equals(line.getName(), name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_LINE_EXCEPTION));
    }

    public static void deleteLine(Line line) {
        if (!contains(line)) {
            throw new IllegalArgumentException(NOT_FOUND_LINE_EXCEPTION);
        }
        lines.remove(line);
    }

    public static boolean deleteLineByName(String name) {
        return lines.removeIf(line -> Objects.equals(line.getName(), name));
    }

    private static boolean contains(Line line) {
        return lines.stream().anyMatch(l -> Objects.equals(l.getName(), line.getName()));
    }
}
