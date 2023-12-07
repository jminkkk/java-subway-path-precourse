package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SectionInfoRepository {
    private static final List<SectionInfo> sectionInfos = new ArrayList<>();
    public static List<SectionInfo> sectionInfos() {
        return Collections.unmodifiableList(sectionInfos);
    }

    public static void addSectionInfo(SectionInfo sectionInfo) {
        sectionInfos.add(sectionInfo);
    }

    public static boolean deleteSectionInfo(Section section) {
        return sectionInfos.removeIf(sectionInfo -> Objects.equals(sectionInfo.getSection(), section));
    }

    public static void deleteAll() {
        sectionInfos.clear();
    }
}
