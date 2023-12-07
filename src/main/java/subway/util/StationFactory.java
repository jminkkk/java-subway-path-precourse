package subway.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import subway.domain.Station;

public class StationFactory {
    public static List<Station> createStations(String stationNames) {
        return Arrays.stream(stationNames.split(", "))
                .map(Station::new)
                .collect(Collectors.toList());
    }
}
