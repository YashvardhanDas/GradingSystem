package GUI;

import javax.swing.*;
import java.awt.*;

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

    // Constructor
    public MainPage() {

        className = new JLabel("CS 591P1 Fall 2019");
        Font labelFont1 = new Font(Font.DIALOG, Font.BOLD, 20);
        className.setFont(labelFont1);

        filter = new JLabel("Filters:");
        Font labelFont2 = new Font(Font.DIALOG, Font.BOLD, 15);
        filter.setFont(labelFont2);

        notGraded = new JLabel("Not graded:");
        statistic = new JLabel("Average: 75.0  Median: 74.8  Standard Deviation: 5.0");
        category = new JLabel("Category:");

        categoriesBox = new JComboBox();
        categoriesBox.addItem("All");
        categoriesBox.addItem("Homework");
        categoriesBox.addItem("Project");
        categoriesBox.addItem("Exam");

        notGradedBox = new JCheckBox("Not Graded");

        table = new JTable();
        table.setAutoCreateRowSorter(true);
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

        back.addActionListener(e -> {
            dispose();
        });

        studentManagement.addActionListener(e -> {
            StudentManagePage studentManagePage = new StudentManagePage();
        });

    }

    public static void main(String[] args) {
        MainPage mainPage = new MainPage();
    }
}
