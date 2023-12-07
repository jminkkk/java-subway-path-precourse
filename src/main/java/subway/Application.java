package subway;

import java.util.Scanner;
import subway.controller.InitController;
import subway.controller.MainController;
import subway.controller.PathController;
import subway.domain.LineRepository;
import subway.domain.SectionInfoRepository;
import subway.domain.SectionRepository;
import subway.domain.StationRepository;
import subway.view.InputView;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final SectionInfoRepository sectionInfoRepository = new SectionInfoRepository();
        final StationRepository stationRepository = new StationRepository();
        final LineRepository lineRepository = new LineRepository();
        final SectionRepository sectionRepository = new SectionRepository();
        final MainController mainController = new MainController(
                new InputView(scanner),
                new InitController(sectionInfoRepository, stationRepository, sectionRepository, lineRepository),
                new PathController(sectionInfoRepository, stationRepository));

        mainController.run();
    }
}
