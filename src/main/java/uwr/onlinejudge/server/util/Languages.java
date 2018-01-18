package uwr.onlinejudge.server.util;

public enum Languages {
    PYTHON("python", 0),
    C("C/C++", 7),
    JAVA("java", 8);

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
