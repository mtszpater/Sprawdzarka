package uwr.onlinejudge.server.database.models;

import javax.persistence.*;
import java.io.File;
import java.util.Date;

@Entity
public class Solution {
    @Id
    @SequenceGenerator(name = "solutionSequence", sequenceName = "solution_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "solutionSequence")
    private long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Task task;

    @Column(nullable = false)
    private File solution;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfSending;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Language language;

    @Lob
    private String comment;

    private int bonus;

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

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public File getSolution() {
        return solution;
    }

    public void setSolution(File solution) {
        this.solution = solution;
    }

    public Date getDateOfSending() {
        return dateOfSending;
    }

    public void setDateOfSending(Date dateOfSending) {
        this.dateOfSending = dateOfSending;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}
