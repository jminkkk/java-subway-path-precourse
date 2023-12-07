package subway.controller;

import static subway.message.PrintMessage.INFO_SPLITTER;
import static subway.message.PrintMessage.INFO_TOTAL_DISTANCE;
import static subway.message.PrintMessage.INFO_TOTAL_DURATION;
import static subway.message.PrintMessage.INPUT_END_STATION;
import static subway.message.PrintMessage.INPUT_OPTION;
import static subway.message.PrintMessage.INPUT_START_STATION;
import static subway.message.PrintMessage.MAIN_MENU;
import static subway.message.PrintMessage.PATH_MENU;
import static subway.message.PrintMessage.RESULT_PATH;

import java.util.List;
import subway.domain.Station;
import subway.view.InputView;
import subway.view.OutputView;

public class MainController {
    private static final String QUIT_OPTION = "Q";
    private static final String BACK_OPTION = "B";
    private final InputView inputView;
    private final InitController initController;
    private final PathController pathController;
    public MainController(InputView inputView, InitController initController,
                          PathController pathController) {
        this.inputView = inputView;
        this.initController = initController;
        this.pathController = pathController;
    }

    public void run() {
        initController.initInfo();

        while (true) {
            OutputView.println(MAIN_MENU);
            OutputView.println(INPUT_OPTION);
            if (inputView.readMenuOption().equals(QUIT_OPTION)) {
                break;
            }

            runPath();
        }
    }

    private void runPath() {
        OutputView.println(PATH_MENU);
        OutputView.println(INPUT_OPTION);
        String pathType = inputView.readPathType();

        if (pathType.equals(BACK_OPTION)) {
            return;
        }

        OutputView.println(INPUT_START_STATION);
        String start = inputView.readStation();
        OutputView.println(INPUT_END_STATION);
        String end = inputView.readStation();

        List<Station> shortestPath = pathController.getMinimumPath(pathType, start, end);
        printPath(shortestPath);
    }

    private void printPath(List<Station> shortestPath) {
        OutputView.println(RESULT_PATH);
        OutputView.println(INFO_SPLITTER.getMessage());
        OutputView.println(INFO_TOTAL_DISTANCE.getMessage() + pathController.getTotalValueByType(shortestPath, "DISTANCE") + "km");
        OutputView.println(INFO_TOTAL_DURATION.getMessage() + pathController.getTotalValueByType(shortestPath, "DISTANCE") + "분");
        OutputView.println(INFO_SPLITTER);
        shortestPath.forEach(station -> OutputView.println(station.getName()));
    }
}
