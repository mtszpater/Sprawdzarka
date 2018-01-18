package uwr.onlinejudge.server.util;

public enum TestState {
    TLE("Timelimit", 0),
    RE("Runtime error", 1),
    WA("Wrong answer", 2),
    OK("Correct answer", 3);

    private int id;
    private String title;

    TestState(String title, int id) {
        this.title = title;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

}
