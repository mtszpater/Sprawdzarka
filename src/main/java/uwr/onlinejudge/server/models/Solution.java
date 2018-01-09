package uwr.onlinejudge.server.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    @Lob
    private String solution;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfSending;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Language language;

    @Lob
    private String comment;

    private int bonus;

    @OneToMany(mappedBy = "solution")
    @JsonManagedReference
    private List<Score> scores;

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

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
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

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
}
