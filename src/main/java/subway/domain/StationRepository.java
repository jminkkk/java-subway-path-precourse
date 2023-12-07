package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class StationRepository {
    private static final List<Station> stations = new ArrayList<>();

    public Station findByName(String name) {
        return stations.stream()
                .filter(station -> Objects.equals(station.getName(), name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 역입니다."));
    }
    public static List<Station> stations() {
        return Collections.unmodifiableList(stations);
    }

    public static void addStation(Station station) {
        stations.add(station);
    }

    public static void addStations(List<Station> newStations) {
        stations.addAll(newStations);
    }

    public static boolean deleteStation(String name) {
        return stations.removeIf(station -> Objects.equals(station.getName(), name));
    }

    public static void deleteAll() {
        stations.clear();
    }
}
