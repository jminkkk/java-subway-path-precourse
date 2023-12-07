package subway.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import subway.domain.Line;

public class LineFactory {
    public static Line createLine(String lineName) {
        return new Line(lineName);
    }
    public static List<Line> createLines(String lineNames) {
        return Arrays.stream(lineNames.split(", "))
                .map(Line::new)
                .collect(Collectors.toList());
    }
}
