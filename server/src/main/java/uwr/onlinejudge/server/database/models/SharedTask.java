package uwr.onlinejudge.server.database.models;

import javax.persistence.*;
import java.io.File;

@Entity
public class SharedTask {
    @Id
    @SequenceGenerator(name = "sharedTaskSequence", sequenceName = "sharedtask_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sharedTaskSequence")
    private long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @Column(nullable = false)
    private File content;

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

    public File getContent() {
        return content;
    }

    public void setContent(File content) {
        this.content = content;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
