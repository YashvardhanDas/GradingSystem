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




    JButton back = new JButton("Back");
    JButton finish = new JButton("Finish");
    JButton addNewCategory = new JButton("Add New Category");
    JTextField assignmentField = new JTextField();
    JTextField scoreField = new JTextField();
    JRadioButton percentage = new JRadioButton("Percentage");
    JRadioButton deduction = new JRadioButton("Deduction");




    public AssignmentAdditionPage() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                JComboBox<String> category = new JComboBox<>(new DefaultComboBoxModel<>());
                category.setSelectedItem(null);


                assignmentField.setPreferredSize(new Dimension(350, 30));
                category.setPreferredSize(new Dimension(350, 30));
                totalScore.setPreferredSize(new Dimension(350, 30));
                Container contentPane = getContentPane();
                contentPane.setLayout(null);

                assignmentName.setBounds(40, 50, 140,30);
                selectCategory.setBounds(40, 90, 140,  30);
                totalScore.setBounds(40, 130, 140, 30);
                assignmentField.setBounds(200, 50, 200, 30);
                category.setBounds(200, 90, 200, 30);
                addNewCategory.setBounds(420,90, 140,30);
                scoreField.setBounds(200, 130, 50,30);
                scoringMethods.setBounds(300,130, 150, 30);
                percentage.setBounds(450, 130,150,30);
                deduction.setBounds(450, 170,150,30);
                finish.setBounds(40, 320, 140, 50);
                back.setBounds(440, 320, 140, 50);

                add(assignmentName);
                add(selectCategory);
                add(totalScore);
                add(assignmentField);
                add(category);
                add(addNewCategory);
                add(scoreField);
                add(scoringMethods);
                add(percentage);
                add(deduction);
                add(finish);
                add(back);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setSize(700, 400);
                setTitle("Assignment Addtion");
                setResizable(false);
                setVisible(true);
            }
        });

    }
    public static void main(String[] args) {
        AssignmentAdditionPage assignmentAdditionPage = new AssignmentAdditionPage();
    }
}
