package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SectionRepository {
    private static final List<Section> sections = new ArrayList<>();

    public static List<Section> sections() {
        return Collections.unmodifiableList(sections);
    }

    public static void addSection(Section section) {
        sections.add(section);
    }

    public static boolean deleteSection(Section deleteSection) {
        return sections.removeIf(section -> Objects.equals(section, deleteSection));
    }

    public static void deleteAll() {
        sections.clear();
    }
}
