package uwr.onlinejudge.server.services;

import uwr.onlinejudge.server.models.Group;
import uwr.onlinejudge.server.models.User;

import java.util.Collection;

public interface GroupService {

    Collection<Group> getGroups(User user);

    Group getGroup(long groupId);

}
