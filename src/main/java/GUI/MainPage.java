package GUI;

import DatabaseManager.DatabaseManager;
import Entities.*;
import TableModels.MainPageTableModel;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

public class MainPage extends JFrame {
    private JLabel className;
    private JLabel filter;
    private JLabel notGraded;
    private JLabel statistic;
    private JLabel category;
    private JComboBox categoriesBox;
    private JCheckBox notGradedBox;
    private JTable table;
    private JScrollPane sp;
    private JButton back;
    private JButton apply;
    private JButton addAssignment;
    private JButton studentManagement;
    private JButton curve;
    private JButton courseStructure;
    private JButton export;
    private JButton deleteAssignment;
    private JSeparator separator;
    private int index;
    private double sum = 0;
    private double average = 0;
    private double median = 0;
    private double std = 0;
    private int count = 0;
    List<Double> gradeList = new LinkedList<>();
    private List<Student> students;
    private Course course;
    private DatabaseManager databaseManager = new DatabaseManager();

    // Constructor
    public MainPage(int courseId) {
        this.course = databaseManager.findCourse(courseId);
        this.students = course.getStudents();

        className = new JLabel(course.toString() + " " + course.getSemester().toString());
        Font labelFont1 = new Font(Font.DIALOG, Font.BOLD, 20);
        className.setFont(labelFont1);

        filter = new JLabel("Filters:");
        Font labelFont2 = new Font(Font.DIALOG, Font.BOLD, 15);
        filter.setFont(labelFont2);

        notGraded = new JLabel("Not graded:");
        statistic = new JLabel("Average: 0.0  Median: 0.0  Standard Deviation: 0.0");
        category = new JLabel("Category:");

        categoriesBox = new JComboBox();
        categoriesBox.addItem("All");
        for (CategoryPercent cp : course.getCategoryPercents()) {
            categoriesBox.addItem(cp.getCategory().getName());
        }

        notGradedBox = new JCheckBox("Not Graded");


        // Test Data
//        Course cs591p1 = new Course("CS591 P1", null);
//
//        Student s1 = new GraduateStudent("Tian", "Gao", "U809", "gaotian@bu.edu");
//        Student s2 = new GraduateStudent("Xinyue", "Li", "U555", "xili33@bu.edu");
//        Student s3 = new GraduateStudent("Dou", "Bao", "U233", "doubao@bu.edu");
//
//        s1.setCourse(cs591p1);
//        s2.setCourse(cs591p1);
//        s3.setCourse(cs591p1);
//
//        List<Student> students = new LinkedList<>();
//        students.add(s1);
//        students.add(s2);
//        students.add(s3);
//
//        Category hw = new Category("Homework", null);
//        Category project = new Category("Project", null);
//        Category exam = new Category("Exam", null);
//
//        CategoryPercent hwThis  = new CategoryPercent(0.5, hw, cs591p1);
//        CategoryPercent projThis  = new CategoryPercent(0.3, project, cs591p1);
//        CategoryPercent examThis  = new CategoryPercent(0.2, exam, cs591p1);
//
//        Assignment hw1 = new Assignment(0.5,  "Homework1", hwThis);
//        Assignment hw2 = new Assignment(0.5,  "Homework2", hwThis);
//        Assignment project1 = new Assignment(1,  "Project1", projThis);
//        Assignment midterm = new Assignment(0.6,  "Midterm", examThis);
//        Assignment finalExam = new Assignment(0.5,  "Final Exam", examThis);
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


        List<List<Object>> rowDataList = new LinkedList<>();
        List<String> columnNames  = new LinkedList<>();
        Map<String, List<Integer>> columnToIndex = new HashMap<>();
        columnNames.add("Student Name");


        // if there is no student in the course, get the column from Course
        if (students == null) {
            index = 0;
            for (CategoryPercent cp : course.getCategoryPercents()) {
                String categoryName = cp.getCategory().getName();
                for (Assignment assignment : cp.getAssignments()) {
                    String assignmentName = assignment.getName();
                    columnNames.add(assignmentName);
                    System.out.println(assignmentName);
                    index++;

                    if (!columnToIndex.containsKey(categoryName)) {
                        columnToIndex.put(categoryName, new LinkedList<>());
                    }

                    columnToIndex.get(categoryName).add(index);
                }
            }
        } else {
            updateStatistics();
            index = 0;
            for (Student s : students) {
                List<Object> tmp = new LinkedList<>();
                tmp.add(s);
                for (Grades g : s.getGrades()) {
                    tmp.add(g);
                    //System.out.println(g.getAssignment().getName());

                }
                tmp.add(s.getTotalGrade());
                tmp.add(s.getLetterScore());
                rowDataList.add(tmp);
            }

            for (Grades g : students.get(0).getGrades()) {
                String categoryName = g.getAssignment().getCategoryPercent().getCategory().getName();
                columnNames.add(g.getAssignment().getName());
                index++;
                if (!columnToIndex.containsKey(categoryName)) {
                    columnToIndex.put(categoryName, new LinkedList<>());
                }

                columnToIndex.get(categoryName).add(index);
            }
        }
        columnNames.add("Total");
        columnNames.add("Letter Grade");
        columnToIndex.put("Total", new LinkedList<>());
        columnToIndex.put("Letter Grade", new LinkedList<>());
        columnToIndex.get("Total").add(index + 1);
        columnToIndex.get("Letter Grade").add(index + 2);

        MainPageTableModel mainPageTableModel = new MainPageTableModel(columnNames, rowDataList, this.students);

        //table = new JTable(mainPageTableModel);
        table = new JTable(mainPageTableModel);
        table.setAutoCreateRowSorter(true);
        table.getModel().addTableModelListener(e -> {
            System.out.println(e.getColumn());
            System.out.println(e.getFirstRow());
        });

        sp = new JScrollPane(table);

        back = new JButton("Back");
        apply = new JButton("Apply");
        addAssignment = new JButton("Add Assignment");
        studentManagement = new JButton("Student Management");
        curve = new JButton("Curve Grade");
        courseStructure = new JButton("Course Structure");
        export = new JButton("Export");
        deleteAssignment = new JButton("Delete Assignment");

        separator = new JSeparator();


        className.setBounds(50, 20, 800, 50);
        back.setBounds(750, 20, 200, 50);
        separator.setBounds(50, 80, 900, 10);
        filter.setBounds(50, 100, 100, 30);
        category.setBounds(160, 100, 100, 30);
        categoriesBox.setBounds(220, 100, 150, 30);
        notGradedBox.setBounds(380, 100, 200, 30);
        apply.setBounds(600, 100, 100, 30);
        sp.setBounds(50, 140, 650, 400);
        statistic.setBounds(200, 550, 700, 50);
        addAssignment.setBounds(750, 140, 200, 50);
        deleteAssignment.setBounds(750, 220, 200, 50);
        courseStructure.setBounds(750, 300, 200, 50);
        studentManagement.setBounds(750, 380, 200, 50);
        curve.setBounds(750, 460, 200, 50);
        export.setBounds(750, 540, 200, 50);

        // Create the panel to place the buttons on
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Add each button to the panel
        panel.add(className);
        panel.add(filter);
        panel.add(notGraded);
        panel.add(statistic);
        panel.add(category);
        panel.add(categoriesBox);
        panel.add(notGradedBox);
        panel.add(sp);
        panel.add(back);
        panel.add(addAssignment);
        panel.add(courseStructure);
        panel.add(studentManagement);
        panel.add(apply);
        panel.add(export);
        panel.add(deleteAssignment);
        panel.add(curve);
        panel.add(separator);


        // Add the panel to the frame
        add(panel);

        // Initialize frame information
        setTitle("CS 591P1");
        setSize(1000, 650);
        setLocation(200, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Turn it on
        setVisible(true);

        apply.addActionListener(e -> {
            String categoryToShow = (String) categoriesBox.getSelectedItem();
            updateStatistics();

            System.out.println(notGradedBox.isSelected());
            if (notGradedBox.isSelected()) {
                table.setDefaultRenderer(Object.class, new CustomRenderer());
            } else {
                table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer());
            }

            TableColumnModel tcm = table.getColumnModel();
            for (int i = 1; i <= index + 2; i++) {
                tcm.getColumn(i).setMaxWidth(0);
                tcm.getColumn(i).setMinWidth(0);
                tcm.getColumn(i).setPreferredWidth(0);
            }


            if (categoryToShow.equals("All")) {
                for (int i = 1; i <= index + 2; i++) {
                    tcm.getColumn(i).setMaxWidth(2147483647);
                    tcm.getColumn(i).setMinWidth(15);
                    tcm.getColumn(i).setPreferredWidth(75);
                }
            } else {
                for (int i : columnToIndex.get(categoryToShow)) {
                    System.out.println(i);
                    tcm.getColumn(i).setMaxWidth(2147483647);
                    tcm.getColumn(i).setMinWidth(15);
                    tcm.getColumn(i).setPreferredWidth(75);
                }
            }

            table.repaint();
        });

        back.addActionListener(e -> {
            dispose();
        });

        studentManagement.addActionListener(e -> {
            StudentManagePage studentManagePage = new StudentManagePage();
        });

        deleteAssignment.addActionListener(e -> {
            DeleteAssignmentPage deleteAssignmentPage = new DeleteAssignmentPage(courseId);
        });

        courseStructure.addActionListener(e -> {
            CourseStructurePage courseStructurePage = new CourseStructurePage();
        });

        addAssignment.addActionListener(e -> {
            AssignmentAdditionPage assignmentAdditionPage = new AssignmentAdditionPage();
        });

        curve.addActionListener(e -> {
            String stringValue = JOptionPane.showInputDialog(this, "Curve value:");
            if (stringValue != null) {
                if (!isNumeric(stringValue)) {
                    JOptionPane.showMessageDialog(null,
                            "Input should be numbers!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    double doubleValue = Double.valueOf(stringValue);
                    updateStatistics();
                    ((MainPageTableModel)table.getModel()).curveTotalGrade(doubleValue);
                    table.repaint();
                }
            }
        });

        statistic.addMouseListener(new MouseListener() {
            String categoryToShow = (String) categoriesBox.getSelectedItem();
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("click");
                updateStatistics();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("press");
                updateStatistics();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("release");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("enter");
                updateStatistics();
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    private boolean isNumeric(String str) {
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // change the background color if not graded
    class CustomRenderer extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (value instanceof Grades) {
                if (!((Grades) value).isGraded()) {
                    comp.setBackground(Color.RED);
                    comp.setForeground(Color.WHITE);
                } else {
                    comp.setBackground(Color.WHITE);
                    comp.setForeground(Color.BLACK);
                }
            } else {
                if (value.equals("")) {
                    comp.setBackground(Color.RED);
                    comp.setForeground(Color.WHITE);
                } else {
                    comp.setBackground(Color.WHITE);
                    comp.setForeground(Color.BLACK);
                }
            }
            return comp;
        }
    }

    private void updateStatistics() {
        String categoryToShow = (String) categoriesBox.getSelectedItem();
        double sum = 0;
        double average = 0;
        double median = 0;
        double std = 0;
        int count = 0;
        List<Double> gradeList = new LinkedList<>();

        if (students != null) {
            // calculate statistics
            for (Student s : students) {
                if (!s.isFreezed()) {
                    for (Grades g : s.getGrades()) {
                        String categoryName = g.getAssignment().getCategoryPercent().getCategory().getName();
                        if (categoryToShow.equals("All") || categoryToShow.equals(categoryName)) {
                            gradeList.add(g.getGrade());
                            count++;
                            sum += g.getGrade();
                        }
                    }
                }
            }

            if (count != 0) {
                average = sum / count;
                Collections.sort(gradeList);
                median = gradeList.get(count / 2);
                double tmpSum = 0;
                for (Double g : gradeList) {
                    tmpSum += Math.pow(g - average, 2);
                }
                std = Math.sqrt(tmpSum / count);
            }
        }

        DecimalFormat df = new DecimalFormat("##.##");
        String avgStr = df.format(average);
        String medianStr = df.format(median);
        String stdStr = df.format(std);
        System.out.println(avgStr);
        System.out.println(medianStr);
        System.out.println(stdStr);
        statistic.setText("Average: " +  avgStr + "  Median: " + medianStr + "  Standard Deviation: " + stdStr);
        statistic.repaint();
    }

    public static void main(String[] args) {
        MainPage mainPage = new MainPage(1);
    }
}
