package GUI;

import DatabaseManager.DatabaseManager;
import Entities.Course;
import Entities.Semester;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SelectCoursePage extends JFrame {

    JPanel buttons = new JPanel();

    static String[] course;
    JLabel courseLabel = new JLabel("Course: ");
    JLabel semesterLabel = new JLabel("Semester: ");


    JButton enter = new JButton("Enter Course");
    JButton templateManage = new JButton("Template Management");
    JButton add = new JButton("Create New Course");
    JButton logout = new JButton("Log out");

    int getSelect = -1;

    private static Map<String, List<Course>> values;
    private List<Semester> semesters;
    private DatabaseManager databaseManager = new DatabaseManager();

    public SelectCoursePage() {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                values = new HashMap<>();
                semesters = databaseManager.getAllSemester();
                for (Semester s : semesters) {
                    values.put(s.toString(), databaseManager.getCoursesBySemester(s));
                }

                JComboBox<Semester> semestersBox = new JComboBox<>();
                for (Semester s : semesters) {
                    semestersBox.addItem(s);
                }
                semestersBox.setSelectedItem(null);
                JComboBox<Course> courses = new JComboBox<>(new DefaultComboBoxModel<>());

                semestersBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Semester value = (Semester) semestersBox.getSelectedItem();
                        List<Course> secondValues = values.get(value);

                        DefaultComboBoxModel model = (DefaultComboBoxModel) courses.getModel();
                        model.removeAllElements();
                        for (Course c : secondValues) {
                            model.addElement(c);
                        }
                    }
                });

                //JComboBox courses = new JComboBox();
                courses.setPreferredSize(new Dimension(350, 30));
                //JComboBox semesters = new JComboBox();
                semestersBox.setPreferredSize(new Dimension(350, 30));
                Container contentPane = getContentPane();

                contentPane.setLayout(null);

                logout.setForeground(Color.RED);

//        courses.addItem("CS591 P1");
//        courses.addItem("CS506");
//
//        semesters.addItem("Fall 2019");
//        semesters.addItem("Spring 2019");



//        String defaultMessage ="please select";
//        ComboBoxEditor editor= courses.getEditor();
//        courses.configureEditor(editor,defaultMessage);
//        courses.setSelectedItem(null);
//        courses.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                if (e.getStateChange() == ItemEvent.SELECTED) {
//                    getSelect = courses.getSelectedIndex();
//                }
//            }
//        });

                semesterLabel.setBounds(50, 100, 70,30);
                courseLabel.setBounds(50, 140, 70,  30);
                semestersBox.setBounds(130, 100, 400, 30);
                courses.setBounds(130, 140, 400, 30);

                add(semesterLabel);
                add(courseLabel);
                add(semestersBox);
                add(courses);

//        add.addActionListener(this);
//        enter.addActionListener(this);
//        logout.addActionListener(this);
//        delete.addActionListener(this);

                buttons.setLayout(new GridLayout(4, 1));
                buttons.add(enter);
                buttons.add(templateManage);
                buttons.add(add);
                buttons.add(logout);
                buttons.setBounds(200, 200, 200, 120);
                contentPane.add(buttons);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setSize(640, 360);
                setLocation(200, 100);
                setTitle("Select Course");
                setResizable(false);
                setVisible(true);

                logout.addActionListener(e -> {
                    dispose();
                    new LoginPage();
                });

                enter.addActionListener(e -> {
                    if (courses.getSelectedItem() != null) {
                        int courseId = ((Course) courses.getSelectedItem()).getId();
                        new MainPage(courseId);
                    }
                });

                add.addActionListener(e -> {
                    new AddCoursePage();
                });

                templateManage.addActionListener(e -> {
                    //TODO: pass the list of template into the page
                    //TemplateManagementPage templateManagementPage = new TemplateManagementPage();
                });
            }
        });

    }


//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == add) {
//            dispose();
//            new Add_Class_UI(grading_system);
//        } else if (e.getSource() == enter) {
//            if (getSelect == -1){
////                dispose();
////                new Select_Course_UI(grading_system);
//                JOptionPane.showMessageDialog(null,"Please select a course");
//            }
//            for (int i = 0; i < course.length; i++) {
//                if (getSelect == i) {
//                    dispose();
//                    new GradeSheet_UI(grading_system,grading_system.getCourses().get(i));
//                }
//            }
//        } else if (e.getSource() == logout) {
//            dispose();
//            new Grading_System_UI(grading_system);
//        } else if (e.getSource() == delete){
//            if (getSelect == -1){
//                JOptionPane.showMessageDialog(null,"Please select a course");
//            } else {
//                int input = JOptionPane.showConfirmDialog(null, "Are you sure to delete this course?");
//                if (input == JOptionPane.YES_OPTION){
//                    grading_system.deleteCourse(getSelect);
//                    dispose();
//                    new Select_Course_UI(grading_system);
//                } else {
//                    dispose();
//                    new Select_Course_UI(grading_system);
//                }
//            }
//        }
//    }
    public static void main(String[] args) {
        SelectCoursePage selectCoursePage = new SelectCoursePage();
    }
}
