package uwr.onlinejudge.server.util.breadcrumbs;

import java.util.LinkedList;
import java.util.List;

public class BreadCrumbLink {
    private BreadCrumbLink previous;
    private List<BreadCrumbLink> next = new LinkedList<>();
    private String id;
    private String url;
    private String family;
    private String label;
    private String parentKey;
    private BreadCrumbLink parent;

    public BreadCrumbLink(String family, String label, String parentKey, String id) {
        this.family = family;
        this.label = label;
        this.parentKey = parentKey;
        this.id = id;
    }

    public BreadCrumbLink getPrevious() {
        return previous;
    }

    public void setPrevious(BreadCrumbLink previous) {
        this.previous = previous;
    }

    public List<BreadCrumbLink> getNext() {
        return next;
    }

    public void addNext(BreadCrumbLink next) {
        this.next.add(next);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getParentKey() {
        return parentKey;
    }

    public void setParentKey(String parentKey) {
        this.parentKey = parentKey;
    }

    public BreadCrumbLink getParent() {
        return parent;
    }

    public void setParent(BreadCrumbLink parent) {
        this.parent = parent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
