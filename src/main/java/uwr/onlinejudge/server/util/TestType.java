package uwr.onlinejudge.server.util;

public enum TestType {
    PUBLIC("publiczny test", 0, "badge-primary"),
    PRIVATE("prywatny test", 1, "badge-secondary"),
    HIDDEN("ukryty test", 2, "badge-dark");

    private String title;
    private int id;
    private String badgeClass;

    TestType(String title, int id, String badgeClass) {
        this.title = title;
        this.id = id;
        this.badgeClass = badgeClass;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBadgeClass() {
        return badgeClass;
    }
}
