package uwr.onlinejudge.server.util;

public enum Languages {
    PYTHON("python", 0),
    JAVA("java", 1);

    private String name;
    private int id;

    Languages(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
