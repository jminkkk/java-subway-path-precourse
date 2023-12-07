package subway.domain;

import java.util.Objects;

/**
 * 연결 구간
 * 몇호선, 어느 구간
 */
public class Section {
    private final Line line;
    private final Station startStation;
    private final Station endStation;

    public Section(Line line, Station startStation, Station endStation) {
        this.line = line;
        this.startStation = startStation;
        this.endStation = endStation;
    }

    public Line getLine() {
        return line;
    }

    public Station getStartStation() {
        return startStation;
    }

    public Station getEndStation() {
        return endStation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Section section = (Section) o;
        return Objects.equals(line, section.line) && Objects.equals(startStation, section.startStation)
                && Objects.equals(endStation, section.endStation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(line, startStation, endStation);
    }

    public boolean isSameSection(Station start, Station end) {
        return startStation == start
                && this.endStation == end;
    }
}
