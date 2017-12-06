package uwr.onlinejudge.server.models;

import javax.persistence.*;

@Entity
public class Language {
    @Id
    @SequenceGenerator(name = "languageSequence", sequenceName = "language_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "languageSequence")
    private long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Task task;

    @Column(nullable = false)
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
