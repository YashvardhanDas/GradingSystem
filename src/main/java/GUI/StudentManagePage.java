package GUI;

import Entities.*;
import TableModels.StudentTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;

public class StudentManagePage extends JFrame {
    private JLabel title;
    private JLabel className;
    private JTable table;
    private JScrollPane sp;
    private JButton back;
    private JButton add;
    private JButton delete;
    private JButton freeze;
    private JSeparator separator;
    private List<Student> students;

    // Constructor
    public StudentManagePage() {

        title = new JLabel("Student Management");
        Font labelFont1 = new Font(Font.DIALOG, Font.BOLD, 20);
        title.setFont(labelFont1);

        className = new JLabel("CS 591P1 Fall 2019");
        Font labelFont2 = new Font(Font.DIALOG, Font.BOLD, 20);
        className.setFont(labelFont2);


        back = new JButton("Back");
        add = new JButton("Add");
        delete = new JButton("Delete");
        freeze = new JButton("Freeze");

        separator = new JSeparator();


        // Test Data
        Course cs591p1 = new Course("CS591 P1", null);

        Student s1 = new GraduateStudent("Tian", "Gao", "U809", "gaotian@bu.edu");
        Student s2 = new GraduateStudent("Xinyue", "Li", "U555", "xili33@bu.edu");
        Student s3 = new GraduateStudent("Dou", "Bao", "U233", "doubao@bu.edu");

        s1.setCourse(cs591p1);
        s2.setCourse(cs591p1);
        s3.setCourse(cs591p1);

        java.util.List<Student> students = new LinkedList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);

        Category hw = new Category("Homework", null);
        Category project = new Category("Project", null);
        Category exam = new Category("Exam", null);

        CategoryPercent hwThis  = new CategoryPercent(0.5, hw, cs591p1);
        CategoryPercent projThis  = new CategoryPercent(0.3, project, cs591p1);
        CategoryPercent examThis  = new CategoryPercent(0.2, exam, cs591p1);

        Assignment hw1 = new Assignment(0.5,  "Homework1", hwThis);
        Assignment hw2 = new Assignment(0.5,  "Homework2", hwThis);
        Assignment project1 = new Assignment(1,  "Project1", projThis);
        Assignment midterm = new Assignment(0.6,  "Midterm", examThis);
        Assignment finalExam = new Assignment(0.5,  "Final Exam", examThis);

        java.util.List<Assignment> homeworks = new LinkedList<>();
        java.util.List<Assignment> projects = new LinkedList<>();
        java.util.List<Assignment> exams = new LinkedList<>();
        homeworks.add(hw1);
        homeworks.add(hw2);
        projects.add(project1);
        exams.add(midterm);
        exams.add(finalExam);
        hwThis.setAssignments(homeworks);
        projThis.setAssignments(projects);
        examThis.setAssignments(exams);

        java.util.List<CategoryPercent> categoryPercents = new LinkedList<>();
        categoryPercents.add(hwThis);
        categoryPercents.add(projThis);
        categoryPercents.add(examThis);

        cs591p1.setCategoryPercents(categoryPercents);

        Grades hw1Grade  = new Grades();
        hw1Grade.setAssignment(hw1);
        hw1Grade.setGrade(95);
        hw1Grade.setGraded(true);
        Grades hw2Grade  = new Grades();
        hw2Grade.setAssignment(hw2);
        hw2Grade.setGrade(60);
        hw2Grade.setGraded(true);
        Grades proj1Grade  = new Grades();
        proj1Grade.setAssignment(project1);
        proj1Grade.setGrade(88);
        proj1Grade.setGraded(true);
        Grades midtermGrade  = new Grades();
        midtermGrade.setAssignment(midterm);
        midtermGrade.setGrade(100);
        midtermGrade.setGraded(true);
        Grades finalGrade  = new Grades();
        finalGrade.setAssignment(finalExam);
        finalGrade.setGrade(0);
        finalGrade.setGraded(false);

        List<Grades> grades = new LinkedList<>();
        grades.add(hw1Grade);
        grades.add(hw2Grade);
        grades.add(proj1Grade);
        grades.add(midtermGrade);
        grades.add(finalGrade);

        s1.setGrades(grades);
        s2.setGrades(grades);
        s3.setGrades(grades);

        this.students = students;

        StudentTableModel studentTableModel = new StudentTableModel(students);
        table = new JTable(studentTableModel);
        table.setAutoCreateRowSorter(true);
        sp = new JScrollPane(table);


        title.setBounds(50, 20, 800, 50);
        className.setBounds(750, 20, 200, 50);
        separator.setBounds(50, 80, 900, 10);
        sp.setBounds(50, 100, 690, 450);
        add.setBounds(750, 100, 200, 50);
        delete.setBounds(750, 180, 200, 50);
        freeze.setBounds(750, 260, 200, 50);
        back.setBounds(750, 500, 200, 50);

        // Create the panel to place the buttons on
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Add each button to the panel
        panel.add(className);
        panel.add(title);
        panel.add(add);
        panel.add(delete);
        panel.add(freeze);
        panel.add(sp);
        panel.add(back);
        panel.add(separator);


        // Add the panel to the frame
        add(panel);

        // Initialize frame information
        setTitle("Student Management");
        setSize(1000, 650);
        setLocation(200, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Turn it on
        setVisible(true);

        back.addActionListener(e -> {
            dispose();
        });

        add.addActionListener(e -> {
            AddStudentPage addStudentPage = new AddStudentPage();
        });

        delete.addActionListener(e -> {
            ((StudentTableModel)table.getModel()).deleteRow(table.getSelectedRow());
            JOptionPane.showMessageDialog(null, "Delete Successfully!");
        });

        freeze.addActionListener(e -> {
            ((StudentTableModel)table.getModel()).changeFreeze(table.getSelectedRow());
            if (((StudentTableModel)table.getModel()).getFreeze(table.getSelectedRow())) {
                freeze.setText("Unfreeze");
            } else {
                freeze.setText("Freeze");
            }
            freeze.repaint();
            JOptionPane.showMessageDialog(null, "Status Change Successfully!");
        });

        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table.getSelectedRow() != -1) {
                    if (((StudentTableModel)table.getModel()).getFreeze(table.getSelectedRow())) {
                        freeze.setText("Unfreeze");
                    } else {
                        freeze.setText("Freeze");
                    }
                    freeze.repaint();
                    System.out.println("!!");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

    public static void main(String[] args) {
        StudentManagePage studentManagePage = new StudentManagePage();
    }
}