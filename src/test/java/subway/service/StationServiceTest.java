package subway.service;

import org.junit.jupiter.api.Test;
import subway.domain.Station;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StationServiceTest {

    @Test
    void findAll() {
    }

    @Test
    void addStation() {
        List<Station> stations = new ArrayList<>();
        stations.add(0,new Station("테스트"));
        stations.add(1,new Station("테스트2"));
        assertEquals("테스트2",stations.get(1).getName());
    }

    @Test
    void deleteStation() {
    }
}