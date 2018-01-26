package uwr.onlinejudge.server.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Cascade;
import uwr.onlinejudge.server.util.Languages;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Task.class)
public class Task {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TaskList taskList;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TaskDescription taskDescription;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deadline;

    @Lob
    private String comment;

    @ElementCollection(targetClass = Languages.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "task_languages")
    private Collection<Languages> languages;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<Solution> solutions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @JsonManagedReference
    private List<Test> tests;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public TaskDescription getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(TaskDescription taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public Collection<Languages> getLanguages() {
        return languages;
    }

    public void setLanguages(Collection<Languages> languages) {
        this.languages = languages;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public List<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
    }
}
