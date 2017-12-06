package uwr.onlinejudge.server.models;

import javax.persistence.*;

@Entity(name = "Groups")
public class Group {
    @Id
    @SequenceGenerator(name = "groupSequence", sequenceName = "group_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groupSequence")
    private long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;

    @Lob
    private String description;

    private String password;

    @Column(nullable = false)
    private boolean isOpen;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
