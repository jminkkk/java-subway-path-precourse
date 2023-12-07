package subway.domain;

import java.util.Objects;

/**
 * 연결 구간에 대한 정보
 * 구간, 거리, 소요 시간
 */
public class SectionInfo {
    private final Section section;
    private final int distance;
    private final int duration;

    public SectionInfo(Section section, int distance, int duration) {
        this.section = section;
        this.distance = distance;
        this.duration = duration;
    }

    public Section getSection() {
        return section;
    }

    public int getValue(String pathType) {
        if (pathType.equals("DISTANCE")) {
            return distance;
        }
        return duration;
    }

    public int getWeight(String pathType) {
        if (pathType.equals("1")) {
            return distance;
        }
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SectionInfo that = (SectionInfo) o;
        return Objects.equals(section, that.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(section);
    }
}
