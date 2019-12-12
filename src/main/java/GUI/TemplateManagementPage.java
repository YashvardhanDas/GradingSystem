package GUI;

import DatabaseManager.DatabaseManager;
import Entities.Course;
import Entities.Semester;
import Entities.Template;
import TableModels.TemplateTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import java.util.List;

public class TemplateManagementPage extends JFrame{

    JLabel templateName = new JLabel("Template Name");
    JLabel selectCourse = new JLabel("Select Course ");
    JLabel template = new JLabel("Template");

    JButton addTemplate = new JButton("Add Template");
    JButton deleteTemplate = new JButton("Delete Template");
    JButton cancel = new JButton("Back");
    JButton update = new JButton("Update");
    JTextField templateField = new JTextField();
    JSeparator s = new JSeparator();

    DefaultTableModel defaultSheet;
    TemplateTableModel templateSheet;
    JTable tSheet;

    //DatabaseManager db= new DatabaseManager();

    public TemplateManagementPage() {

        //TODO
        //Starting here is the fake data part
        //to be switched by reading from databse
        List<Template> list = new ArrayList<>();
        Template temp = new Template("template 1", "{Homework, Exam}", "{50, 50}", "3", "{30, 30, 30}", "100");

        list.add(temp);

        List<Course> listc = new ArrayList<>();
        Course course = new Course("course 591", new Semester());
        listc.add(course);


        //Ending the fake data part

        List<Template> templateList = list;
        List<String> listcourseName = new ArrayList<>();
        for (Course course1 : listc) {
            listcourseName.add(course1.getName());
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JComboBox<Template> templateBox = new JComboBox<>(new DefaultComboBoxModel<>());
                for (Template t : templateList) {
                    templateBox.addItem(t);
                }

                templateBox.setSelectedItem(null);
                JComboBox<Course> course = new JComboBox<>(new DefaultComboBoxModel<>());
                for (Course c : listc) {
                    course.addItem(c);
                }


                //List<String> colNameList = Arrays.asList("Name", "Categories", "Cat Percentage", "Assignment Number", "Assignment Percentage");
                List<String> colNameList = new ArrayList<>();
                colNameList.add("Name");
                colNameList.add("Category");
                colNameList.add("Category Percentage");
                colNameList.add("Assignment Number");
                colNameList.add("Assignment Percentage");
                colNameList.add("Assignment Total");


                templateSheet = new TemplateTableModel(colNameList, templateList);
                tSheet = new JTable(templateSheet);
                tSheet.setAutoCreateRowSorter(true);
                JScrollPane sp = new JScrollPane(tSheet);

                templateField.setPreferredSize(new Dimension(350, 30));
                course.setPreferredSize(new Dimension(350, 30));
                templateBox.setPreferredSize(new Dimension(350, 30));
                Container contentPane = getContentPane();
                contentPane.setLayout(null);

                templateName.setBounds(40, 50, 140,30);
                selectCourse.setBounds(40, 90, 140,  30);
                template.setBounds(40, 130, 140,  30);

                templateField.setBounds(200, 50, 300, 30);
                course.setBounds(200, 90, 300, 30);
                templateBox.setBounds(200, 130, 300, 30);

                addTemplate.setBounds(520, 50, 140,30);
                deleteTemplate.setBounds(520, 90, 140,30);


                sp.setBounds(40, 160, 500,300);
                update.setBounds(40, 470, 140, 50);
                cancel.setBounds(450, 470, 140, 50);

                add(templateField);
                add(course);
                add(addTemplate);
                add(deleteTemplate);
                add(templateName);
                add(selectCourse);
                add(templateBox);
                add(s);
                add(template);
                //add(tSheet);
                add(sp);
                add(update);
                add(cancel);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setSize(700, 550);
                setTitle("Course Structure");
                setResizable(false);
                setVisible(true);
            }
        });

    }
    public static void main(String[] args) {



        TemplateManagementPage templateManagementPage = new TemplateManagementPage();
    }
}
