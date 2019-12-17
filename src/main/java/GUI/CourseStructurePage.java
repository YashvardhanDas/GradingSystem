package GUI;

import DatabaseManager.DatabaseManager;
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
    JButton update = new JButton("Load");
    JTextField percentage = new JTextField();
    JButton updatePer = new JButton("Update Percentage");

    DefaultTableModel defaultSheet;
    JTable tSheet;
    JScrollPane sp;

    CourseStructureTableModel cSheet;

    //public static CategoryPercent selected;


    DatabaseManager db= new DatabaseManager();

    public CourseStructurePage(int courseID, CategoryPercent selected) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                //TODO db action here
                Course course = db.findCourse(courseID);


                //Start of the fake data part

//                List<Course> courseList = new ArrayList<>();
//
//                Course course = new Course("course 591", new Semester());
//                courseList.add(course);
//
//
//                List<Category> categories = new ArrayList<>();
////                values.add("Homework");
////                values.add("Quiz");
////                values.add("Exam");
//                categories.add(new Category("Homework"));
//                categories.add(new Category("Exam"));
//
//                List<Assignment> assignments = new ArrayList<>();
//                assignments.add(new Assignment(30, "homework1", new CategoryPercent(), 50.0));
//                assignments.add(new Assignment(40, "homework2", new CategoryPercent(), 50.0));
//
//                CategoryPercent categoryPercent = new CategoryPercent();
//                categoryPercent.setAssignments(assignments);
//
//                CategoryPercent categoryPercent2 = new CategoryPercent();
//                categoryPercent.setAssignments(assignments);
//
//                List<CategoryPercent> listc = new ArrayList<>();
//                listc.add(categoryPercent);
//                listc.add(categoryPercent2);
//
//                course.setCategoryPercents(listc);

                //Ending the fake data part

                List<CategoryPercent> categoryPercents = course.getCategoryPercents();



                JComboBox<CategoryPercent> category = new JComboBox<>(new DefaultComboBoxModel<>());
                //category.setSelectedItem(0);
                for (CategoryPercent c : categoryPercents) {
                    category.addItem(c);
                }


                if (selected != null) {

                    List<String> colNameList = new ArrayList<>();
                    colNameList.add("Item Name");
                    colNameList.add("percentage");
                    colNameList.add("TotalScore");

                    List<Assignment> assignments = selected.getAssignments();
                    cSheet = new CourseStructureTableModel(colNameList, assignments);
                    tSheet = new JTable(cSheet);
                    tSheet.setAutoCreateRowSorter(true);
                    sp = new JScrollPane(tSheet);


                    //selected = (CategoryPercent)category.getSelectedItem();
                    percentage.setText(String.valueOf(selected.getPercent()));
                }

                updatePer.addActionListener(e -> {
                    Double per = Double.parseDouble(percentage.getText());
                    selected.setPercent(per);
                    db.update(selected);
                    JOptionPane.showMessageDialog(null,"Percentage Updated!");


                });

                update.addActionListener(e -> {
//                    categoryPercent = category.getSelectedItem();
//
//                    cSheet = new CourseStructureTableModel(colNameList, assignments);
//                    tSheet = new JTable(cSheet);
//                    tSheet.setAutoCreateRowSorter(true);

                    dispose();
                    CourseStructurePage newpage = new CourseStructurePage(courseID, (CategoryPercent) category.getSelectedItem());
                });


                category.setPreferredSize(new Dimension(350, 30));
                percentage.setPreferredSize(new Dimension(350, 30));
                Container contentPane = getContentPane();
                contentPane.setLayout(null);

                categoryLabel.setBounds(40, 50, 140,30);
                percentageLabel.setBounds(40, 90, 140,  30);
                category.setBounds(250, 50, 250, 30);
                percentage.setBounds(250, 90, 250, 30);
                if (sp != null)
                sp.setBounds(40, 130, 500,180);
                update.setBounds(500, 50, 140, 30);
                back.setBounds(440, 320, 140, 50);
                updatePer.setBounds(500, 90, 140, 30);

                back.addActionListener(e -> {
                    dispose();
                    new MainPage(courseID);
                });

                add(categoryLabel);
                add(percentageLabel);
                add(category);
                add(percentage);
                if (sp != null) add(sp);
                add(update);
                add(back);
                add(updatePer);
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
        CourseStructurePage courseStructurePage = new CourseStructurePage(0,null);
    }
}
