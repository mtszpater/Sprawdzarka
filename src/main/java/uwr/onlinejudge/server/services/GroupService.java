package uwr.onlinejudge.server.services;

import uwr.onlinejudge.server.models.Group;
import uwr.onlinejudge.server.models.User;
import uwr.onlinejudge.server.models.form.GroupForm;
import uwr.onlinejudge.server.util.UserRole;

import java.util.Collection;

public interface GroupService {

    Collection<Group> getOwnGroups(User user);

    Collection<Group> getGroups();

    Collection<Group> getUserGroups(User user);

    Group getGroup(long groupId);

    Group save(GroupForm groupForm);

    Group save(Group group);

    void registerUser(User user, Group group, UserRole userRole);

    boolean isUserRegistered(User user, Group group);

    void unregisterUser(User user, Group group);
}
