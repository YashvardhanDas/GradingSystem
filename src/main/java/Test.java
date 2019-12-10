import DatabaseManager.DatabaseManager;
import Entities.*;
import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager();
//         Test Data
        Course temp =db.findCourse(1);
        db.createTemplate(temp,"temp");


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
