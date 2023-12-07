package subway.controller;

import static subway.util.LineFactory.createLines;
import static subway.util.SectionInfoFactory.createSectionInfo;
import static subway.util.StationFactory.createStations;

import java.util.List;
import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Section;
import subway.domain.SectionInfo;
import subway.domain.SectionInfoRepository;
import subway.domain.SectionRepository;
import subway.domain.Station;
import subway.domain.StationRepository;

public class InitController {
    private final StationRepository stationRepository;
    private final LineRepository lineRepository;
    private final SectionInfoRepository sectionInfoRepository;
    private final SectionRepository sectionRepository;

    public InitController(SectionInfoRepository sectionInfoRepository, StationRepository stationRepository, SectionRepository sectionRepository, LineRepository lineRepository) {
        this.stationRepository = stationRepository;
        this.lineRepository = lineRepository;
        this.sectionInfoRepository = sectionInfoRepository;
        this.sectionRepository = sectionRepository;
    }

    public void initInfo() {
        List<Station> stations = createStations("교대역, 강남역, 역삼역, 남부터미널역, 양재역, 양재시민의숲역, 매봉역");
        stationRepository.addStations(stations);

        List<Line> lines = createLines("2호선, 3호선, 신분당선");
        lineRepository.addLines(lines);

        List<String> lineInfos = List.of("2호선: 교대역 - ( 2km / 3분 ) - 강남역 - ( 2km / 3분 ) - 역삼역",
                "3호선: 교대역 - ( 3km / 2분 ) - 남부터미널역 - ( 6km / 5분 ) - 양재역 - ( 1km / 1분 ) - 매봉역",
                "신분당선: 강남역 - ( 2km / 8분 ) - 양재역 - ( 10km / 3분 ) - 양재시민의숲역");
        lineInfos.forEach(this::initSection);
    }

    private void initSection(String lineInfo) {
        String[] lineAndInfo = lineInfo.split(":");
        Line line = lineRepository.findByName(lineAndInfo[0]);

        String[] sectionsInfo = lineAndInfo[1].split("-");

        for (int i = 0; i < sectionsInfo.length - 2; i += 2) {
            Section section = createSection(line, sectionsInfo[i].trim(), sectionsInfo[i + 2].trim());
            sectionRepository.addSection(section);

            SectionInfo sectionInfo = createSectionInfo(section, sectionsInfo[i + 1]);
            sectionInfoRepository.addSectionInfo(sectionInfo);
        }
    }

    // TODO: 다른 팩토리랑 통일성
    public Section createSection(Line line, String startStationName, String finishStationName) {
        Station startStation = stationRepository.findByName(startStationName);
        Station finishStation = stationRepository.findByName(finishStationName);
        return new Section(line, startStation, finishStation);
    }
}