package GUI;

import Entities.*;
import TableModels.CourseStructureTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import java.util.List;

public class CourseStructurePage extends JFrame{

    JLabel categoryLabel = new JLabel("Choose  category ");
    JLabel percentageLabel = new JLabel("Category percentage ");


    JButton back = new JButton("Back");
    JButton update = new JButton("Update");
    JTextField percentage = new JTextField();

    DefaultTableModel defaultSheet;
    JTable tSheet;

    CourseStructureTableModel cSheet;


    //DatabaseManager db= new DatabaseManager();

    public CourseStructurePage() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                //TODO db action here
                //List<Course> courseList = db.getAllCourses();
                //List<Category> categories = db.get
                //List<Assignments> assignments = db.get


                //Start of the fake data part

                List<Course> courseList = new ArrayList<>();

                Course course = new Course("course 591", new Semester());
                courseList.add(course);


                List<Category> categories = new ArrayList<>();
//                values.add("Homework");
//                values.add("Quiz");
//                values.add("Exam");
                categories.add(new Category("Homework"));
                categories.add(new Category("Exam"));

                List<Assignment> assignments = new ArrayList<>();
                assignments.add(new Assignment(30, "homework1", new CategoryPercent(), 50.0));
                assignments.add(new Assignment(40, "homework2", new CategoryPercent(), 50.0));

                //Ending the fake data part



                List<String> colNameList = new ArrayList<>();
                colNameList.add("Homework");
                colNameList.add("percentage");

                cSheet = new CourseStructureTableModel(colNameList, assignments);
                tSheet = new JTable(cSheet);
                tSheet.setAutoCreateRowSorter(true);
                JScrollPane sp = new JScrollPane(tSheet);





                JComboBox<Category> category = new JComboBox<>(new DefaultComboBoxModel<>());
                category.setSelectedItem(null);
                for (Category c : categories) {
                    category.addItem(c);
                }


                category.setPreferredSize(new Dimension(350, 30));
                percentage.setPreferredSize(new Dimension(350, 30));
                Container contentPane = getContentPane();
                contentPane.setLayout(null);

                categoryLabel.setBounds(40, 50, 140,30);
                percentageLabel.setBounds(40, 90, 140,  30);
                category.setBounds(250, 50, 400, 30);
                percentage.setBounds(250, 90, 400, 30);
                sp.setBounds(40, 130, 500,180);
                update.setBounds(40, 320, 140, 50);
                back.setBounds(440, 320, 140, 50);

                back.addActionListener(e -> {
                    dispose();
                });

                add(categoryLabel);
                add(percentageLabel);
                add(category);
                add(percentage);
                add(sp);
                add(update);
                add(back);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setSize(700, 400);
                setLocation(200, 100);
                setTitle("Course Structure");
                setResizable(false);
                setVisible(true);
            }
        });

    }
    public static void main(String[] args) {
        CourseStructurePage courseStructurePage = new CourseStructurePage();
    }
}
