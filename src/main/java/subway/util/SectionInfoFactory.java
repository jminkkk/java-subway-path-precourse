package subway.util;

import subway.domain.Section;
import subway.domain.SectionInfo;

public class SectionInfoFactory {
    public static SectionInfo createSectionInfo(Section section, String distanceAndDuration) {
        String[] sectionInfos = distanceAndDuration
                .replaceAll("[()]", "")
                .split("/");

        return new SectionInfo(section,
                Integer.parseInt(sectionInfos[0].replaceAll("km", "").trim()),
                Integer.parseInt(sectionInfos[1].replaceAll("분", "").trim())
        );
    }
}