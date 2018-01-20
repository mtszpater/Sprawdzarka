package uwr.onlinejudge.server.util;

public enum TestState {
    TLE("Timelimit", 0, "text-info"),
    RE("Runtime error", 1, "text-warning"),
    WA("Wrong answer", 2, "text-danger"),
    OK("Correct answer", 3, "text-success");

    private String title;
    private int id;
    private String textClass;

    TestState(String title, int id, String textClass) {
        this.title = title;
        this.id = id;
        this.textClass = textClass;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getTextClass() {
        return textClass;
    }
}
