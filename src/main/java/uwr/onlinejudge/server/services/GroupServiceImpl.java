package uwr.onlinejudge.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uwr.onlinejudge.server.models.Group;
import uwr.onlinejudge.server.models.User;
import uwr.onlinejudge.server.repositories.GroupRepository;

import java.util.Collection;

@Service
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Collection<Group> getGroups(User user) {
        return groupRepository.findByUser(user);
    }

    @Override
    public Group getGroup(long groupId) {
        return groupRepository.getOne(groupId);
    }
}
