package uwr.onlinejudge.server.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uwr.onlinejudge.server.models.Group;
import uwr.onlinejudge.server.models.User;
import uwr.onlinejudge.server.models.form.GroupForm;
import uwr.onlinejudge.server.repositories.GroupRepository;

import java.util.Collection;

@Service
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, ObjectMapper objectMapper) {
        this.groupRepository = groupRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Collection<Group> getGroups(User user) {
        return groupRepository.findByUser(user);
    }

    @Override
    public Group getGroup(long groupId) {
        return groupRepository.getOne(groupId);
    }

    @Override
    public void save(GroupForm groupForm) {
        groupRepository.save(objectMapper.convertValue(groupForm, Group.class));
    }
}
