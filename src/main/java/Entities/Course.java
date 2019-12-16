package Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private List<CategoryPercent> categoryPercents;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private List<Student> students;

    @ManyToOne(fetch = FetchType.EAGER, cascade ={CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "semester_id")
    private Semester semester;

    double curveValue=0.0;

    public Course() {
    }

    public Course(String name, List<Student> students, Semester semester) {
        this.name = name;
        this.students = students;
        this.semester = semester;
        this.students = new ArrayList<>();
        this.categoryPercents = new ArrayList<>();
    }

    public Course(String name, Semester semester) {
        this.name = name;
        this.semester = semester;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryPercent> getCategoryPercents() {
        return categoryPercents;
    }

    public void setCategoryPercents(List<CategoryPercent> categoryPercents) {
        this.categoryPercents = categoryPercents;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public double getCurveValue() {
        return curveValue;
    }

    public void setCurveValue(double curveValue) {
        this.curveValue = curveValue;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
