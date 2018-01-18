package uwr.onlinejudge.server.util;

public enum TestType {
    PUBLIC("publiczny test", 0),
    PRIVATE("prywatny test", 1),
    HIDDEN("ukryty test", 2);

    private String title;
    private int id;

    TestType(String title, int id) {
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
