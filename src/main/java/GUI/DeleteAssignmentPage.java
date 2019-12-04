package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class DeleteAssignmentPage extends JFrame {
    JPanel label = new JPanel();
    JPanel courseList = new JPanel();
    JPanel buttons = new JPanel();

    static String[] assignments;
    JLabel courseLabel = new JLabel("Assignments: ");


    JButton delete = new JButton("Delete");
    JButton logout = new JButton("Back");


    public DeleteAssignmentPage() {
        assignments = new String[] {"Homework1", "Homework2", "Midterm", "Final Exam", "Project1", "Project2"};
        JComboBox courses = new JComboBox(assignments);
        courses.setPreferredSize(new Dimension(350, 30));
        Container contentPane = this.getContentPane();

        contentPane.setLayout(null);
        delete.setForeground(Color.RED);

//        String defaultMessage = "please select";
//        ComboBoxEditor editor = courses.getEditor();
//        courses.configureEditor(editor, defaultMessage);
        // courses.setSelectedItem(null);
//        courses.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                if (e.getStateChange() == ItemEvent.SELECTED) {
//                    getSelect = courses.getSelectedIndex();
//                }
//            }
//        });

//        label.add(courseLabel);
//        label.setBounds(50, 100, 50, 30);
//        contentPane.add(label);
//
//        this.courseList.add(courses);
//        this.courseList.setBounds(0, 95, 600, 30);
//        contentPane.add(this.courseList);

        courseLabel.setBounds(40, 100, 90,30);
        courses.setBounds(150, 100, 400, 30);
        add(courseLabel);
        add(courses);

        buttons.setLayout(new GridLayout(4, 1));
        buttons.add(delete);
        buttons.add(logout);
        buttons.setBounds(200, 200, 200, 120);
        contentPane.add(buttons);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(640, 360);
        setLocation(200, 100);
        setTitle("Delete Assignment");
        setResizable(false);
        setVisible(true);

        logout.addActionListener(e -> {
            dispose();
        });
    }

    public static void main(String[] args) {
        DeleteAssignmentPage deleteAssignmentPage = new DeleteAssignmentPage();
    }
}
