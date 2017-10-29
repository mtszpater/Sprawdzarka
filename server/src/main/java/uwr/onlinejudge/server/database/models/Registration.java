package uwr.onlinejudge.server.database.models;

import uwr.onlinejudge.server.database.util.UserRole;

import javax.persistence.*;

@Entity
public class Registration {
    @Id
    @SequenceGenerator(name = "registrationSequence", sequenceName = "registration_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Group group;

    @Column(nullable = false)
    private String role = UserRole.USER.toString();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
