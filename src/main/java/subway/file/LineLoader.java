package subway.file;

import subway.domain.Line;

import java.util.List;

public class LineLoader {
    private static final String LINE_PATH = "src/main/resources/line.md";
    private final List<Line> lines;

    public LineLoader() {
        lines = FileDataLoader.getObjects(LINE_PATH, this::createLine);
    }

    public List<Line> getLines() {
        return lines;
    }

    private Line createLine(String[] args) {
        String name = args[0];
        return new Line(name);
    }
}
