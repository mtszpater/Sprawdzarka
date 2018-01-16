package uwr.onlinejudge.server.util.breadcrumbs;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BreadCrumbs {

    private final String BREAD_CRUMB_LINKS = "breadCrumb";

    public BreadCrumbs() {
    }

    public void add(HttpServletRequest request, HttpSession session, Link link) {
        List<BreadCrumbLink> breadCrumbs = getBreadCrumbLinksFromSession(session);

        if (breadCrumbs == null) {
            breadCrumbs = new LinkedList<>();
        }

        List<BreadCrumbLink> familyList = breadCrumbs.stream()
                .filter(breadCrumb -> breadCrumb.getFamily().equals(link.getFamily()))
                .collect(Collectors.toCollection(LinkedList::new));

        BreadCrumbLink breadCrumbLink = getBreadCrumbLink(request, link, familyList);
        LinkedList<BreadCrumbLink> currentBreadCrumb = new LinkedList<>();
        generateBreadCrumbsRecursively(breadCrumbLink, currentBreadCrumb);
        session.setAttribute(BREAD_CRUMB_LINKS, currentBreadCrumb);
    }

    @SuppressWarnings("unchecked")
    private List<BreadCrumbLink> getBreadCrumbLinksFromSession(HttpSession session) {
        return (List<BreadCrumbLink>) session.getAttribute(BREAD_CRUMB_LINKS);
    }

    private BreadCrumbLink getBreadCrumbLink(HttpServletRequest request, Link link,
                                             List<BreadCrumbLink> familyList) {
        BreadCrumbLink breadCrumbLink;
        BreadCrumbLink breadCrumbObject;
        breadCrumbObject = familyList.stream()
                .filter(crumbLink -> crumbLink.getLabel().equals(link.getLabel()))
                .findFirst()
                .orElse(null);
        if (breadCrumbObject != null) {
            breadCrumbLink = breadCrumbObject;
        } else {
            breadCrumbLink = new BreadCrumbLink(link.getFamily(), link.getLabel(), link.getParent(), link.getId());
            String fullURL = request.getRequestURL().append((request.getQueryString() == null) ? "" : "?" + request.getQueryString()).toString();
            breadCrumbLink.setUrl(fullURL);
            createRelationships(familyList, breadCrumbLink);
            familyList.add(breadCrumbLink);
        }
        return breadCrumbLink;
    }

    private void generateBreadCrumbsRecursively(BreadCrumbLink link, List<BreadCrumbLink> breadCrumbLinks) {
        if (link.getPrevious() != null) {
            generateBreadCrumbsRecursively(link.getPrevious(), breadCrumbLinks);
        }
        breadCrumbLinks.add(link);
    }

    private void createRelationships(List<BreadCrumbLink> familyList, BreadCrumbLink newLink) {
        for (BreadCrumbLink breadCrumbLink : familyList) {
            if (breadCrumbLink.getId().equalsIgnoreCase(newLink.getParentKey())) {
                breadCrumbLink.addNext(newLink);
                newLink.setPrevious(breadCrumbLink);
                newLink.setParent(breadCrumbLink);
            }
        }
    }
}
