package subway.util;

public class PathValidator {
    public static void validatePath(String start, String end) {
        if (start.equals(end)) {
            throw new IllegalArgumentException("[ERROR] 출발역과 도착역이 동일합니다.");
        }
    }
}
