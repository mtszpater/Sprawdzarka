package uwr.onlinejudge.server.database.models;

import javax.persistence.*;

@Entity
public class TaskDescription {
    @Id
    @SequenceGenerator(name = "sharedTaskSequence", sequenceName = "shared_task_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sharedTaskSequence")
    private long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @Column(nullable = false)
    @Lob
    private String content;

    @Column(nullable = false)
    private boolean isPrivate;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
