package subway.view;

import static subway.message.PrintMessage.INFO_SPLITTER;
import static subway.message.PrintMessage.INFO_TOTAL_DISTANCE;
import static subway.message.PrintMessage.INFO_TOTAL_DURATION;
import static subway.message.PrintMessage.RESULT_PATH;

import java.util.List;
import subway.domain.Station;
import subway.message.PrintMessage;

public class OutputView {
    private static final String DISTANCE_UNIT = "km";
    private static final String DURATION_UNIT = "분";

    public static void println(String message) {
        System.out.println(message);
    }

    public static void println() {
        System.out.println();
    }
    public static void println(PrintMessage printMessage) {
        println(printMessage.getMessage());
    }

    public static void printPath(List<Station> shortestPath, String totalDistance, String totalDuration) {
        println(RESULT_PATH);
        println(INFO_SPLITTER.getMessage());
        println(INFO_TOTAL_DISTANCE.getMessage() + totalDistance + DISTANCE_UNIT);
        println(INFO_TOTAL_DURATION.getMessage() + totalDuration + DURATION_UNIT);
        println(INFO_SPLITTER);
        shortestPath.forEach(station -> OutputView.println(station.getName()));
    }
}
