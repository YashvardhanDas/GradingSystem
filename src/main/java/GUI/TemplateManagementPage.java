package GUI;

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

    public TemplateManagementPage(List<Template> inputList) {

        List<Template> templateList = inputList;

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                /* TODO: Add category in ComboBox, Change Default to real
                values.add("Homework");
                values.add("Quiz");
                values.add("Exam");
                 */
                JComboBox<String> templateBox = new JComboBox<>(new DefaultComboBoxModel<>());
                templateBox.setSelectedItem(null);
                JComboBox<String> course = new JComboBox<>(new DefaultComboBoxModel<>());

                //TODO: Add functional table
                //defaultSheet = new DefaultTableModel(templateList);

                //List<String> colNameList = Arrays.asList("Name", "Categories", "Cat Percentage", "Assignment Number", "Assignment Percentage");
                List<String> colNameList = new ArrayList<>();
                colNameList.add("Name");
                colNameList.add("Category");
                colNameList.add("name");
                colNameList.add("name");
                colNameList.add("name");


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
        List<Template> list = new ArrayList<>();
        Template temp = new Template("template 1", "{Homework, Exam}", "{50, 50}", "3", "{30, 30, 30}", "");

        list.add(temp);

        TemplateManagementPage templateManagementPage = new TemplateManagementPage(list);
    }
}
