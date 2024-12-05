package subway.file;

import subway.domain.Line;

import java.util.ArrayList;
import java.util.List;

public class LineLoader {
    private static final String LINE_PATH = "src/main/resources/line.md";
    private static final List<Line> lines = new ArrayList<>();

    private LineLoader() {}

    public static List<Line> getLines() {
        lines.addAll(FileDataLoader.getObjects(LINE_PATH, LineLoader::createLine));
        return lines;
    }

    private static Line createLine(String[] args) {
        String name = args[0];
        return new Line(name);
    }
}
