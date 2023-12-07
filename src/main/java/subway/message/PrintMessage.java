package subway.message;

public enum PrintMessage {
    MAIN_MENU("## 메인 화면\n" +
            "1. 경로 조회\n" +
            "Q. 종료"),

    INPUT_OPTION("## 원하는 기능을 선택하세요."),
    PATH_MENU("## 경로 기준\n" +
            "1. 최단 거리\n" +
            "2. 최소 시간\n" +
            "B. 돌아가기"),
    INPUT_START_STATION("## 출발역을 입력하세요."),
    INPUT_END_STATION("## 도착역을 입력하세요."),
    RESULT_PATH("## 조회 결과"),
    INFO("[INFO] "),
    INFO_SPLITTER(INFO.getMessage() + "---"),
    INFO_TOTAL_DISTANCE(INFO.getMessage() + "총 거리: "),
    INFO_TOTAL_DURATION(INFO.getMessage() + "총 소요 시간: "),
    ;
    private String message;

    PrintMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
