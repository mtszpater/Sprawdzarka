package uwr.onlinejudge.server.models;

import javax.persistence.*;
import java.util.List;

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

    private String description;

    @OneToMany(mappedBy = "taskList")
    private List<Task> tasks;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

}
