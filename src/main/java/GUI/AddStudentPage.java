package GUI;

import DatabaseManager.DatabaseManager;
import Entities.Course;
import Entities.GraduateStudent;
import Entities.Student;
import Entities.UndergraduateStudent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentPage extends JFrame {
    JPanel infoP = new JPanel();
    JPanel bntP = new JPanel();
    JPanel radioPanel = new JPanel();
    JLabel firstName = new JLabel("First Name: ");
    JTextField fname = new JTextField(20);
    JLabel midName = new JLabel("Middle Initial (optional): ");
    JTextField mname = new JTextField(20);
    JLabel lastName = new JLabel("Last Name: ");
    JTextField lname = new JTextField(20);
    JLabel studentID = new JLabel("Student ID: ");
    JTextField id = new JTextField(20);
    JLabel buEmail = new JLabel("Email: ");
    JTextField email = new JTextField(20);
    ButtonGroup G2 = new ButtonGroup();
    JRadioButton grad = new JRadioButton("Graduate");
    JRadioButton under = new JRadioButton("Undergraduate");
    JButton confirm = new JButton("Confirm");
    JButton ret = new JButton("Cancel");
    private DatabaseManager databaseManager = new DatabaseManager();

    public AddStudentPage(int courseId){
        Course course = databaseManager.findCourse(courseId);

        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);
        infoP.setLayout(new GridLayout(5,2));
        infoP.add(firstName);
        infoP.add(fname);
        infoP.add(midName);
        infoP.add(mname);
        infoP.add(lastName);
        infoP.add(lname);
        infoP.add(studentID);
        infoP.add(id);
        infoP.add(buEmail);
        infoP.add(email);

        radioPanel.setLayout(new GridLayout(1,2));
        radioPanel.add(grad);
        radioPanel.add(under);
        this.grad.setActionCommand("graduate");
        this.under.setActionCommand("undergraduate");
        G2.add(grad);
        G2.add(under);
        under.setSelected(true);

        radioPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Student Type"));

        bntP.add(confirm);
        bntP.add(ret);

        infoP.setBounds(100,50, 450,150 );
        radioPanel.setBounds(100,200,450,50);
        bntP.setBounds(115,275,400,50);
        contentPane.add(infoP);
        contentPane.add(radioPanel);
        contentPane.add(bntP);


        this.setTitle("New Student");
        setSize(640, 360);
        setLocation(200, 100);
        setResizable(false);
        setVisible(true);

        ret.addActionListener(e -> {
            dispose();
            new StudentManagePage(courseId);
        });

        confirm.addActionListener(e -> {
            String studentFName = fname.getText();
            String studentMName = mname.getText();
            String studentLName = lname.getText();
            String studentID = id.getText();
            String studentEmail = email.getText();
            String studentType = this.G2.getSelection().getActionCommand();

            if(studentFName.isEmpty() || studentLName.isEmpty() || studentID.isEmpty()
                    || studentEmail.isEmpty() || studentType.isEmpty()){
                JOptionPane.showMessageDialog(null,"Please fill all the blank!");
            } else {
                Student newStudent;
                if (studentType.equals("Graduate")) {
                    newStudent = new GraduateStudent(studentFName, studentLName, studentID, studentEmail, course);
                } else {
                    newStudent = new UndergraduateStudent(studentFName, studentLName, studentID, studentEmail, course);
                }
                newStudent.setCourse(course);
                databaseManager.addStudent(newStudent);
                JOptionPane.showMessageDialog(null,"Success!");
                dispose();
                new StudentManagePage(courseId);
            }
        });
    }

    public static void main(String[] args) {
        //AddStudentPage addStudentPage = new AddStudentPage();
    }

}

