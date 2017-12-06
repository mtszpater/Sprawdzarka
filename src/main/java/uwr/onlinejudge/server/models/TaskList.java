package uwr.onlinejudge.server.models;

import javax.persistence.*;

@Entity
public class TaskList {
    @Id
    @SequenceGenerator(name = "taskListSequence", sequenceName = "task_list_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taskListSequence")
    private long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Group group;

    @Column(nullable = false)
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
