package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginPage extends JFrame {
    private JLabel label1;
    private JLabel label2;

    private JLabel cardNum;
    private JLabel password;
    private JTextField cardNumText;
    private JPasswordField pswText;
    private JButton login;

    // Constructor
    public LoginPage() {

        label1 = new JLabel("Grading System");
        Font labelFont1 = new Font(Font.DIALOG,  Font.BOLD, 30);
        label1.setFont(labelFont1);

        label2 = new JLabel("Welcome, Christine!");
        Font labelFont2 = new Font(Font.DIALOG,  Font.BOLD, 20);
        label2.setFont(labelFont2);

        cardNum = new JLabel("Username:");
        password = new JLabel("Password:");
        cardNumText = new JTextField();
        pswText = new JPasswordField();
        login = new JButton("Log in");

        label1.setBounds(30,10, 300, 50);
        label2.setBounds(45, 70, 300, 50);
        cardNum.setBounds(10, 130, 300, 30);
        cardNumText.setBounds(10, 170, 300, 30);
        password.setBounds(10, 210, 300, 30);
        pswText.setBounds(10, 250, 300, 30);
        login.setBounds(60, 310, 200, 50);



        // Create the panel to place the buttons on
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Add each button to the panel
        panel.add(label1);
        panel.add(label2);
        panel.add(login);
        panel.add(cardNum);
        panel.add(cardNumText);
        panel.add(password);
        panel.add(pswText);
        panel.add(login);

        // Add the panel to the frame
        add(panel);

        // Initialize frame information
        setTitle( "Welcome" );
        setSize( 320, 570 );
        setLocation( 200, 100 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );

        // Turn it on
        setVisible( true );

        login.addActionListener(e -> {
            if(cardNumText.getText().equals("cpk") && String.valueOf(pswText.getPassword()).equals("123456")) {
                new SelectCoursePage();
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(null,
                        "Incorrect username or password!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

    }

    public static void main(String[] args) {
        LoginPage loginPage = new LoginPage();
    }


}