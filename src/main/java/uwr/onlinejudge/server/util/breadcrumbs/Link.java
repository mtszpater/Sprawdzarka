package uwr.onlinejudge.server.util.breadcrumbs;

public class Link {
    public String id;
    public String label;
    public String family;
    public String parent;

    public Link(String label, String family, String parent, String id) {
        this.id = id;
        this.label = label;
        this.family = family;
        this.parent = parent;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
