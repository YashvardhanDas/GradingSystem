import DatabaseManager.DatabaseManager;
import Entities.*;

import java.util.LinkedList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager();
//         Test Data
        Course cs591p1 = new Course("CS591 P1", null);
        db.add(cs591p1);
        Category hw = new Category("Homework");
        db.add(hw);
        Category project = new Category("Project");
        db.add(project);
        Category exam = new Category("Exam");
        db.add(exam);

        CategoryPercent hwThis  = new CategoryPercent(0.5, hw, cs591p1);
        db.add(hwThis);
        CategoryPercent projThis  = new CategoryPercent(0.3, project, cs591p1);
        db.add(projThis);
        CategoryPercent examThis  = new CategoryPercent(0.2, exam, cs591p1);
        db.add(examThis);

        Assignment hw1 = new Assignment(0.5,  "Homework1", hwThis);
        db.add(hw1);
        Assignment hw2 = new Assignment(0.5,  "Homework2", hwThis);
        db.add(hw2);
        Assignment project1 = new Assignment(1,  "Project1", projThis);
        db.add(project1);
        Assignment midterm = new Assignment(0.6,  "Midterm", examThis);
        db.add(midterm);
        Assignment finalExam = new Assignment(0.5,  "Final Exam", examThis);
        db.add(finalExam);

        Student s1 = new GraduateStudent("Tian", "Gao", "U809", "gaotian@bu.edu",cs591p1);
        Student s2 = new GraduateStudent("Xinyue", "Li", "U555", "xili33@bu.edu",cs591p1);
        Student s3 = new GraduateStudent("Dou", "Bao", "U233", "doubao@bu.edu",cs591p1);

        db.addStudent(s1);
        db.addStudent(s2);
        db.addStudent(s3);


//        s1.setCourse(cs591p1);
//        s2.setCourse(cs591p1);
//        s3.setCourse(cs591p1);
//
//        List<Student> students = new LinkedList<>();
//        students.add(s1);
//        students.add(s2);
//        students.add(s3);
//
//
//
//
//
//        List<Assignment> homeworks = new LinkedList<>();
//        List<Assignment> projects = new LinkedList<>();
//        List<Assignment> exams = new LinkedList<>();
//        homeworks.add(hw1);
//        homeworks.add(hw2);
//        projects.add(project1);
//        exams.add(midterm);
//        exams.add(finalExam);
//        hwThis.setAssignments(homeworks);
//        projThis.setAssignments(projects);
//        examThis.setAssignments(exams);
//
//        List<CategoryPercent> categoryPercents = new LinkedList<>();
//        categoryPercents.add(hwThis);
//        categoryPercents.add(projThis);
//        categoryPercents.add(examThis);
//
//        cs591p1.setCategoryPercents(categoryPercents);
//
//        Grades hw1Grade  = new Grades();
//        hw1Grade.setAssignment(hw1);
//        hw1Grade.setGrade(95);
//        hw1Grade.setGraded(true);
//        Grades hw2Grade  = new Grades();
//        hw2Grade.setAssignment(hw2);
//        hw2Grade.setGrade(60);
//        hw2Grade.setGraded(true);
//        Grades proj1Grade  = new Grades();
//        proj1Grade.setAssignment(project1);
//        proj1Grade.setGrade(88);
//        proj1Grade.setGraded(true);
//        Grades midtermGrade  = new Grades();
//        midtermGrade.setAssignment(midterm);
//        midtermGrade.setGrade(100);
//        midtermGrade.setGraded(true);
//        Grades finalGrade  = new Grades();
//        finalGrade.setAssignment(finalExam);
//        finalGrade.setGrade(0);
//        finalGrade.setGraded(false);
//
//        List<Grades> grades = new LinkedList<>();
//        grades.add(hw1Grade);
//        grades.add(hw2Grade);
//        grades.add(proj1Grade);
//        grades.add(midtermGrade);
//        grades.add(finalGrade);
//
//        s1.setGrades(grades);
//        s2.setGrades(grades);
//        s3.setGrades(grades);

    }
}
