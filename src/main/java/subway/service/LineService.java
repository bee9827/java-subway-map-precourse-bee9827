package subway.service;

import subway.domain.Line;
import subway.domain.LineRepository;

public class LineService {

    public void addLine(Line line){
        LineRepository.addLine(line);
    }
}
