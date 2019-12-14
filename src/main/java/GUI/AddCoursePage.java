package GUI;

import DatabaseManager.DatabaseManager;
import Entities.Course;
import Entities.Semester;
import Entities.Template;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;


public class AddCoursePage extends JFrame {
    JPanel labels = new JPanel();
    JPanel inputs = new JPanel();
    JPanel buttons = new JPanel();

    JLabel courseName = new JLabel("Course Name: ");
    JLabel semester = new JLabel("Semester: ");
    JLabel template = new JLabel("Template (optional): ");

    JTextField nameInput = new JTextField();
    JComboBox semesterInput = new JComboBox();
    JComboBox templateInput = new JComboBox();

    JButton next = new JButton("Next");
    JButton load = new JButton("Load Students (optional)");
    JButton cancel = new JButton("Back");


    public static String name;
    public static String lecturerName;
    public static String semesterName;

    private DatabaseManager databaseManager = new DatabaseManager();
    private String path = "";

    public AddCoursePage(){
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        Semester fall2019 = new Semester("Fall 2019");
        Semester spring2019 = new Semester("Spring 2019");
        Semester spring2020 = new Semester("Spring 2020");

        Template T1 = new Template("template 1", "{Homework, Exam}",
                "{50, 50}", "3", "{30, 30, 30}", "100");
        Template T2 = new Template("template 2", "{Homework, Exam}",
                "{50, 50}", "3", "{30, 30, 30}", "100");

        List<Template> templateList = databaseManager.getTemplate();
        List<Semester> semesterList = databaseManager.getAllSemester();

        for (Semester s : semesterList) {
            semesterInput.addItem(s);
        }

        for (Template t : templateList) {
            templateInput.addItem(t);
        }
        templateInput.setSelectedItem(null);

        labels.setLayout(new GridLayout(3, 1));
        labels.add(courseName);
        labels.add(semester);
        labels.add(template);
        labels.setBounds(30,40,130,180);
        contentPane.add(labels);

        inputs.setLayout(new GridLayout(7, 1));
        inputs.add(new JLabel());
        inputs.add(nameInput);
        inputs.add(new JLabel());
        inputs.add(semesterInput);
        inputs.add(new JLabel());
        inputs.add(templateInput);
        inputs.add(new JLabel());
        inputs.setBounds(170,30,400,205);
        contentPane.add(inputs);

        next.setForeground(Color.BLUE);
//        next.addActionListener(this);
//        cancel.addActionListener(this);
        buttons.setLayout(new GridLayout(1,3));
        buttons.add(load);
        buttons.add(cancel);
        buttons.add(next);
        buttons.setBounds(30,250,560,50);
        contentPane.add(buttons);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(640, 360);
        setLocation(200, 100);
        setTitle("Add Course");
        setResizable(false);
        setVisible(true);

        cancel.addActionListener(e -> {
            dispose();
            new SelectCoursePage();
        });

        load.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            int returnValue = jfc.showOpenDialog(this);
            // int returnValue = jfc.showSaveDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                path = selectedFile.getAbsolutePath();
            }
            System.out.println(path);
        });

        next.addActionListener(e -> {
            if (nameInput.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null,"Please fill all the blank!");
            } else {
                if (path.equals("") && templateInput.getSelectedItem() == null) {
                    Course newCourse = new Course(nameInput.getText(), (Semester) semesterInput.getSelectedItem());
                    databaseManager.add(newCourse);
                } else {
                    if (templateInput.getSelectedItem() == null && !path.equals("")) {
                        databaseManager.createCourseFromCsv( new Course(nameInput.getText(), (Semester) semesterInput.getSelectedItem())
                                , path);
                    } else if(templateInput.getSelectedItem() != null && path.equals("")) {
                        databaseManager.createCourseByTemplate((Template)templateInput.getSelectedItem(), nameInput.getText(),
                                (Semester) semesterInput.getSelectedItem());
                    }else{
                        databaseManager.createCourseByCsvAndTemplate( new Course(nameInput.getText(), (Semester) semesterInput.getSelectedItem()),(Template)templateInput.getSelectedItem(),path);
                    }

                }
                JOptionPane.showMessageDialog(null,"Success!");
                dispose();
                new SelectCoursePage();
            }
        });


    }


    public static void main(String[] args) {
        AddCoursePage addCoursePage = new AddCoursePage();
    }

}
