package subway.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class StationTest {

    @ParameterizedTest
    @CsvSource({"일, false","두, false","두글자 이상, true"})
    void getName(String name, boolean expected) {
        if(!expected) {
            assertThrows(IllegalArgumentException.class, () -> {
                        Station station = new Station(name);
                    }
            );
        }
        else{
            Station station = new Station(name);
            assertEquals(name, station.getName());
        }
    }
}