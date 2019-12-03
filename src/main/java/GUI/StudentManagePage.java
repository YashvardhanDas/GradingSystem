package GUI;

import javax.swing.*;
import java.awt.*;

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

    // Constructor
    public StudentManagePage() {

        title = new JLabel("Student Management");
        Font labelFont1 = new Font(Font.DIALOG, Font.BOLD, 20);
        title.setFont(labelFont1);

        className = new JLabel("CS 591P1 Fall 2019");
        Font labelFont2 = new Font(Font.DIALOG, Font.BOLD, 20);
        className.setFont(labelFont2);

        table = new JTable();
        table.setAutoCreateRowSorter(true);
        sp = new JScrollPane(table);

        back = new JButton("Back");
        add = new JButton("Add");
        delete = new JButton("Delete");
        freeze = new JButton("Freeze");

        separator = new JSeparator();


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

    }

    public static void main(String[] args) {
        StudentManagePage studentManagePage = new StudentManagePage();
    }
}