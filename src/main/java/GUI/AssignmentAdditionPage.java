package GUI;

import Entities.Assignment;
import Entities.Category;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import java.util.List;

public class AssignmentAdditionPage extends JFrame {

    JLabel assignmentName = new JLabel("Assignment Name");
    JLabel selectCategory = new JLabel("Select Category");
    JLabel totalScore = new JLabel("Total Score");
    JLabel scoringMethods = new JLabel("Scoring Methods");

    ButtonGroup G = new ButtonGroup();

    JButton back = new JButton("Back");
    JButton finish = new JButton("Finish");
    JTextField assignmentField = new JTextField();
    JTextField scoreField = new JTextField();
    JRadioButton percentage = new JRadioButton("Percentage");
    JRadioButton deduction = new JRadioButton("Deduction");




    public AssignmentAdditionPage() {

        //Start of the fake data part
        //Starting here is the fake data part
        //to be switched by reading from databse
        List<Category> listCatagory = new ArrayList<>();
        Category cate1 = new Category("homework");
        Category cate2 = new Category("quiz");
        listCatagory.add(cate1);
        listCatagory.add(cate2);

        //Ending the fake data part

        List<String> listcategoryName = new ArrayList<>();
        for (Category cate : listCatagory) {
            listcategoryName.add(cate.getName());
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                JComboBox<Category> category = new JComboBox<>(new DefaultComboBoxModel<>());
                for (Category c : listCatagory) {
                    category.addItem(c);
                }
                category.setSelectedItem(null);



                assignmentField.setPreferredSize(new Dimension(350, 30));
                category.setPreferredSize(new Dimension(350, 30));
                totalScore.setPreferredSize(new Dimension(350, 30));
                Container contentPane = getContentPane();
                contentPane.setLayout(null);

                assignmentName.setBounds(40, 50, 140, 30);
                selectCategory.setBounds(40, 90, 140, 30);
                totalScore.setBounds(40, 130, 140, 30);
                assignmentField.setBounds(200, 50, 200, 30);
                category.setBounds(200, 90, 200, 30);
                scoreField.setBounds(200, 130, 50, 30);
                scoringMethods.setBounds(300, 130, 150, 30);
                percentage.setBounds(450, 130, 150, 30);
                deduction.setBounds(450, 170, 150, 30);
                finish.setBounds(40, 320, 140, 50);
                back.setBounds(440, 320, 140, 50);

                add(assignmentName);
                add(selectCategory);
                add(totalScore);
                add(assignmentField);
                add(category);
                add(scoreField);
                add(scoringMethods);
                add(percentage);
                add(deduction);
                add(finish);
                add(back);
                G.add(percentage);
                G.add(deduction);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setSize(700, 400);
                setLocation(200, 100);
                setTitle("Assignment Addtion");
                setResizable(false);
                setVisible(true);

                back.addActionListener(e -> {
                    dispose();
                });



                finish.addActionListener(e -> {
                    String assignmentName = assignmentField.getText();
                    String assignmentScore = scoreField.getText();
                    Category assignmnetCatagory = (Category) category.getSelectedItem();

                    String qual = " ";

                    // If condition to check if jRadioButton2 is selected.
                    if (percentage.isSelected()) {
                        qual = "percentage";
                    }
                    else if (deduction.isSelected()) {
                        qual = "deduction";
                    }
                    else {
                        qual = "NO Button selected";
                    }
                    if (assignmentName.isEmpty() || assignmentScore.isEmpty() || assignmnetCatagory.equals(null)) {
                        JOptionPane.showMessageDialog(null,"Please fill all the blank!");
                    }
                    else {
                        Assignment newAssignment;
                        int score = Integer.parseInt(assignmentScore);
                        newAssignment = new Assignment(score, assignmentName, null,0.0);
                    }

                });
            }
        });

    }

    public static void main(String[] args) {
        AssignmentAdditionPage assignmentAdditionPage = new AssignmentAdditionPage();
    }
}
