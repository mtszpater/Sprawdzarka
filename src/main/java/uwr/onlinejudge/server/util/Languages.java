package uwr.onlinejudge.server.util;

public enum Languages {
    PYTHON("python", 0, "badge-warning"),
    C("C/C++", 7, "badge-dark"),
    JAVA("java", 8, "badge-danger");

    private String name;
    private int id;
    private String badgeClass;

    Languages(String name, int id, String badgeClass) {
        this.name = name;
        this.id = id;
        this.badgeClass = badgeClass;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBadgeClass() {
        return badgeClass;
    }
}
