package subway.controller;

import static subway.message.PrintMessage.INPUT_END_STATION;
import static subway.message.PrintMessage.INPUT_OPTION;
import static subway.message.PrintMessage.INPUT_START_STATION;
import static subway.message.PrintMessage.MAIN_MENU;
import static subway.message.PrintMessage.PATH_MENU;
import static subway.util.PathValidator.validatePath;

import java.util.Collections;
import java.util.List;
import subway.domain.Station;
import subway.view.InputView;
import subway.view.OutputView;

public class MainController {
    private static final String QUIT_OPTION = "Q";
    private static final String BACK_OPTION = "B";
    private static final String PATH_TYPE_DISTANCE = "DISTANCE";
    private static final String PATH_TYPE_DURATION = "DURATION";
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
        initController.initSubwayInfo();

        while (true) {
            OutputView.println(MAIN_MENU);
            OutputView.println();
            if (inputOption().equals(QUIT_OPTION)) {
                break;
            }
            OutputView.println();
            List<Station> shortestPath = getShortestPath();
            printPath(shortestPath);
            OutputView.println();
        }
    }

    private String inputOption() {
        OutputView.println(INPUT_OPTION);
        try {
            return inputView.readMenuOption();
        } catch (IllegalArgumentException e) {
            OutputView.println(e.getMessage());
            return inputOption();
        }
    }
    private List<Station> getShortestPath() {
        String pathType = inputPathType();
        if (pathType.equals(BACK_OPTION)) {
            return Collections.emptyList();
        }

        try {
            Station start = inputStartStation();
            Station end = inputEndStation();
            validatePath(start, end);

            return pathController.getMinimumPath(pathType, start, end);
        } catch (IllegalArgumentException e) {
            OutputView.println(e.getMessage());
            return getShortestPath();
        }
    }

    private String inputPathType() {
        OutputView.println(PATH_MENU);
        OutputView.println();
        OutputView.println(INPUT_OPTION);
        try {
            return inputView.readPathType();
        } catch (IllegalArgumentException e) {
            OutputView.println(e.getMessage());
            return inputPathType();
        }
    }

    private Station inputStartStation() {
        OutputView.println(INPUT_START_STATION);
        try {
            String start = inputView.readStation();
            return initController.getStation(start);
        } catch (IllegalArgumentException e) {
            OutputView.println(e.getMessage());
            return inputStartStation();
        }
    }

    private Station inputEndStation() {
        OutputView.println(INPUT_END_STATION);
        try {
            String end = inputView.readStation();
            return initController.getStation(end);
        } catch (IllegalArgumentException e) {
            OutputView.println(e.getMessage());
            return inputEndStation();
        }
    }

    private void printPath(List<Station> shortestPath) {
        try {
            String totalDistance = pathController.getTotalValueByType(shortestPath, PATH_TYPE_DISTANCE);
            String totalDuration = pathController.getTotalValueByType(shortestPath, PATH_TYPE_DURATION);
            OutputView.printPath(shortestPath, totalDistance, totalDuration);
        } catch (IllegalArgumentException e) {
            OutputView.println(e.getMessage());
        }
    }
}
