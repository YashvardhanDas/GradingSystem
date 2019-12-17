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

public class AddCategoryPage extends JFrame{


    JLabel label = new JLabel("Enter new Category Name: ");
    JButton back = new JButton("Back");
    JTextField percentage = new JTextField();
    JButton updatePer = new JButton("Create");

    DefaultTableModel defaultSheet;
    JTable tSheet;
    JScrollPane sp;

    CourseStructureTableModel cSheet;

    //public static CategoryPercent selected;


    //DatabaseManager db= new DatabaseManager();

    public AddCategoryPage(int CourseID) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {








                label.setPreferredSize(new Dimension(350, 30));
                updatePer.setPreferredSize(new Dimension(350, 30));
                percentage.setPreferredSize(new Dimension(350, 30));
                Container contentPane = getContentPane();
                contentPane.setLayout(null);

                label.setBounds(40, 50, 500,30);
//                percentageLabel.setBounds(40, 90, 140,  30);
//                category.setBounds(250, 50, 250, 30);
                percentage.setBounds(100, 90, 250, 30);
//                if (sp != null)
//                    sp.setBounds(40, 130, 500,180);
//                update.setBounds(500, 50, 140, 30);
                back.setBounds(440, 320, 140, 50);
                updatePer.setBounds(400, 90, 140, 30);

                back.addActionListener(e -> {
                    dispose();
                    new AssignmentAdditionPage(CourseID);
                });

                updatePer.addActionListener(e -> {
                    String s = percentage.getText();
                    boolean flag = true;

                    //TODO: uncomment this part for db

                    DatabaseManager db = new DatabaseManager();
                    List<Category> list = db.getAllCategories();
                    for (Category cat : list) {
                        if (cat.getName().equals(s)) {
                            flag = false;
                            break;
                        }
                    }


                    if (flag) {
                        Category newCat = new Category(s);
                        db.add(newCat);
                    }

                    JOptionPane.showMessageDialog(null,"Category added!");
                    dispose();
                    new AssignmentAdditionPage(CourseID);



                });


                add(label);
                add(percentage);
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
        AddCategoryPage page = new AddCategoryPage(0);
    }
}
