package subway.view;


import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readMenuOption() {
        String input = scanner.next();
        if (input.equals("1") || input.equals("Q")) {
            return input;
        }

        throw new IllegalArgumentException("올바른 입력이 아닙니다.");
    }

    public String readPathType() {
        String input = scanner.next();
        if (input.equals("1") || input.equals("2") || input.equals("B")) {
            return input;
        }

        throw new IllegalArgumentException("올바른 입력이 아닙니다.");
    }

    public String readStation() {
        return scanner.next();
    }
}
