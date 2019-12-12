package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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

    public AddCoursePage(){
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        semesterInput.addItem("Fall 2019");
        semesterInput.addItem("Spring 2019");
        semesterInput.addItem("Spring 2020");
        templateInput.addItem("T1");
        templateInput.addItem("T2");
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
        });
    }

//    public void actionPerformed(ActionEvent e){
//        if(e.getSource() == next){
//            name = nameInput.getText();
//            lecturerName = lecturerInput.getText();
//            semesterName = semesterInput.getText();
//            String nameCheck, lecturerCheck, semesterCheck;
//            nameCheck = name.replaceAll(" ","");
//            lecturerCheck = lecturerName.replaceAll(" ","");
//            semesterCheck = semesterName.replaceAll(" ","");
//            if (nameCheck.length() != 0 && lecturerCheck.length() != 0 && semesterCheck.length() != 0){
//                for (int i = 0; i < Select_Course_UI.course.length; i++){
//                    if (Select_Course_UI.course[i] == null){
//                        Select_Course_UI.course[i] = name;
//                        break;
//                    }
//                }
//                dispose();
//                new Add_Assignment_info_UI(grading_system,name, lecturerName, semesterName);
//            } else {
//                JOptionPane.showMessageDialog(null,"Please input valid information");
//            }
//        } else if(e.getSource() == cancel){
//            dispose();
//            new Select_Course_UI(grading_system);
//        }
//    }

    public static void main(String[] args) {
        AddCoursePage addCoursePage = new AddCoursePage();
    }

}
