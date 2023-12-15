package subway.util;

import subway.domain.Station;

public class PathValidator {
    public static void validatePath(Station start, Station end) {
        if (start.equals(end)) {
            throw new IllegalArgumentException("[ERROR] 출발역과 도착역이 동일합니다.");
        }
    }
}
