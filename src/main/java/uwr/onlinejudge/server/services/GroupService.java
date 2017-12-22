package uwr.onlinejudge.server.services;

import uwr.onlinejudge.server.models.Group;
import uwr.onlinejudge.server.models.User;
import uwr.onlinejudge.server.models.form.GroupForm;
import uwr.onlinejudge.server.util.UserRole;

import java.util.Collection;

public interface GroupService {

    Collection<Group> getGroups(User user);

    Group getGroup(long groupId);

    void save(GroupForm groupForm);

    void registerUser(User user, Group group, UserRole userRole);

    boolean isUserRegistered(User user, Group group);
}
