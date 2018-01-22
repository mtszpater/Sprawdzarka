package uwr.onlinejudge.server.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uwr.onlinejudge.server.models.Group;
import uwr.onlinejudge.server.models.Registration;
import uwr.onlinejudge.server.models.User;
import uwr.onlinejudge.server.models.form.GroupForm;
import uwr.onlinejudge.server.repositories.GroupRepository;
import uwr.onlinejudge.server.repositories.RegistrationRepository;
import uwr.onlinejudge.server.util.UserRole;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;
    private ObjectMapper objectMapper;
    private RegistrationRepository registrationRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, ObjectMapper objectMapper, RegistrationRepository registrationRepository) {
        this.groupRepository = groupRepository;
        this.objectMapper = objectMapper;
        this.registrationRepository = registrationRepository;
    }

    @Override
    public Collection<Group> getOwnGroups(User user) {
        return groupRepository.findByUser(user);
    }

    @Override
    public Collection<Group> getGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Collection<Group> getUserGroups(User user) {
        List<Registration> registrations = registrationRepository.findByUser(user);
        return registrations.stream().map(Registration::getGroup).collect(Collectors.toList());
    }

    @Override
    public Group getGroup(long groupId) {
        System.out.println(groupRepository.getOne(groupId).getDescription());
        return groupRepository.getOne(groupId);
    }

    @Override
    public Group save(GroupForm groupForm) {
        return groupRepository.save(objectMapper.convertValue(groupForm, Group.class));
    }

    @Override
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public void registerUser(User user, Group group, UserRole userRole) {
        Registration registration = new Registration();
        registration.setGroup(group);
        registration.setUser(user);
        registration.setRole(userRole.name());

        registrationRepository.save(registration);
    }

    @Override
    public boolean isUserRegistered(User user, Group group) {
        return registrationRepository.findByGroup(group).stream().anyMatch( r -> r.getUser().equals(user) );
    }

    @Override
    @Transactional
    public void unregisterUser(User user, Group group) {
        registrationRepository.deleteByUserAndGroup(user, group);
    }


}
