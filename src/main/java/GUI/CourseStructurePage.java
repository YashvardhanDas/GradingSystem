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
    ArrayList values;

    DefaultTableModel defaultSheet;
    JTable tSheet;

    public CourseStructurePage() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                values = new ArrayList<>();
                /* TODO: Add category in ComboBox, Change Default to real
                values.add("Homework");
                values.add("Quiz");
                values.add("Exam");
                 */
                JComboBox<String> category = new JComboBox<>(new DefaultComboBoxModel<>());
                category.setSelectedItem(null);
                //TODO: Add functional table
                defaultSheet = new DefaultTableModel();
                tSheet = new JTable(defaultSheet);

                category.setPreferredSize(new Dimension(350, 30));
                percentage.setPreferredSize(new Dimension(350, 30));
                Container contentPane = getContentPane();
                contentPane.setLayout(null);

                categoryLabel.setBounds(40, 50, 140,30);
                percentageLabel.setBounds(40, 90, 140,  30);
                category.setBounds(250, 50, 400, 30);
                percentage.setBounds(250, 90, 400, 30);
                tSheet.setBounds(40, 130, 200,180);
                update.setBounds(40, 320, 140, 50);
                back.setBounds(440, 320, 140, 50);

                add(categoryLabel);
                add(percentageLabel);
                add(category);
                add(percentage);
                add(tSheet);
                add(update);
                add(back);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setSize(700, 400);
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
