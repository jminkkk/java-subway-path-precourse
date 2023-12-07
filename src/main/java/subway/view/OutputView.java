package subway.view;

import subway.message.PrintMessage;

public class OutputView {
    public static void println(String message) {
        System.out.println(message);
    }
    public static void println(PrintMessage printMessage) {
        println(printMessage.getMessage());
    }
}
