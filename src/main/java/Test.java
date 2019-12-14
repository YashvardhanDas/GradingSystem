import DatabaseManager.DatabaseManager;
import Entities.*;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Test {

    public static void main(String[] args) throws IOException {
        DatabaseManager db = new DatabaseManager();
//        Semester sem=new Semester("Fall_2019");
//         Test Data
//        Template temp =db.findTemplate(1);
//       db.createCourseByTemplate(temp,"Check1",sem);
//        Course course = db.findCourse(1);
//        db.createTemplate(course,"TestTemplate");

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

//        Semester semester = new Semester("Fall_2019");
//
//        Course course = new Course("CS 591 P1",semester);
//
//        Category category = new Category("Homework");
//        Category category2 = new Category("Project");
//        Category category3 = new Category("Exam");
//
//        CategoryPercent categoryPercent1 = new CategoryPercent(30.0,category,course);
//        CategoryPercent categoryPercent2 = new CategoryPercent(50.0,category2,course);
//        CategoryPercent categoryPercent3 = new CategoryPercent(20.0,category3,course);
//
//        Assignment hw1 = new Assignment(50.0,"HW1",categoryPercent1,50.0);
//        Assignment hw2 = new Assignment(50.0,"HW1",categoryPercent1,100.0);
//        Assignment pr = new Assignment(100.0,"Project",categoryPercent2,100.0);
//        Assignment midterm = new Assignment(50.0,"Midterm",categoryPercent3,100.0);
//        Assignment finalExam = new Assignment(50.0,"Final",categoryPercent3,100.0);
//
//        db.add(hw1);
//        db.add(hw2);
//        db.add(pr);
//        db.add(midterm);
//        db.add(finalExam);

        Course course =db.findCourse(2);
        System.out.println(course);

//        Student stud1 = new GraduateStudent("Yernur","Alimkhanov","1","Yernura",course);
//        db.addStudent(stud1);
//        db.createTemplate(course,"testTemplate");
        db.exportToCsv(course,"C:\\Users\\Сулпак\\Desktop\\BOSTON MASTER\\Java\\test.csv");
    }
}
