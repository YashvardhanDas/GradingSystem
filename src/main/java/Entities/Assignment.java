package Entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private double percent;

    private double totalScore;

    @ManyToOne(fetch = FetchType.EAGER, cascade ={CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "categoryPercent_id")
    private CategoryPercent categoryPercent;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "assignment_id")
    private List<Grades> grades;

    public Assignment() {
    }

    public Assignment(double percent,String name, CategoryPercent categoryPercent,Double totalScore) {
        this.percent = percent;
        this.name=name;
        this.categoryPercent = categoryPercent;
        this.totalScore = totalScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public CategoryPercent getCategoryPercent() {
        return categoryPercent;
    }

    public void setCategoryPercent(CategoryPercent categoryPercent) {
        this.categoryPercent = categoryPercent;
    }

    public List<Grades> getGrades() {
        return grades;
    }

    public void setGrades(List<Grades> grades) {
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }
}
