package gui.welcomePage;


import gui.coachMenu.CoachLogIn;
import gui.organizerMenu.OrganizerLogIn;
import gui.studentMenu.StudentLogIn;
import gui.ImagePanel;

import javax.swing.*;
import java.awt.*;

public class SignInPage {
    private JFrame frame;
    private ImageIcon image;
    private ImagePanel panel;
    private JTextArea welcome, wLine2, wLine3, wLine4, welcome2;
    private JButton studentSignIn, coachSignIn, orgLogIn, back;

    public SignInPage(){
        image = new ImageIcon("src/res/images/background.jpg");
        panel = new ImagePanel(image.getImage());
        panel.setLayout(null);
        frame = new JFrame("SAMCIS Sign In Page");
        frame.setResizable(false);
        /*Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);*/
        frame.setLocation(300,150);
        frame.getContentPane().add(panel);

        ImageIcon icon = new ImageIcon("src/res/images/back icon.png");
        Image image1 = icon.getImage();
        icon = new ImageIcon(image1);

        back = new JButton(icon);

        back.setBounds(20,50,20,20);
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.addActionListener(e -> {
            try {
                WelcomePage welcomePage = new WelcomePage();
                frame.setVisible(false);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(back);

        welcome = new JTextArea("                                                                     Welcome to the");
        welcome.setBackground(Color.decode("#f1ca54"));
        welcome.setForeground(Color.decode("#110e10"));
        welcome.setBounds(0,20,933,25);
        welcome.setFont(new Font("Sans Serif", Font.PLAIN,20));
        welcome.setAlignmentY(JTextArea.CENTER_ALIGNMENT);
        welcome.setEditable(false);
        panel.add(welcome);

        wLine2 = new JTextArea("                                              SAMCIS INTRAMURALS SPORTS FEST");
        wLine2.setBackground(Color.decode("#f1ca54"));
        wLine2.setForeground(Color.decode("#110e10"));
        wLine2.setBounds(0,40,933,25);
        wLine2.setFont(new Font("Sans Serif", Font.BOLD,20));
        wLine2.setEditable(false);
        panel.add(wLine2);

        wLine3 = new JTextArea("                                                                         TRYOUTS");
        wLine3.setBackground(Color.decode("#f1ca54"));
        wLine3.setForeground(Color.decode("#110e10"));
        wLine3.setBounds(0,60,933,25);
        wLine3.setFont(new Font("Sans Serif", Font.BOLD,20));
        wLine3.setEditable(false);
        panel.add(wLine3);

        wLine4 = new JTextArea("                                                                             2023!");
        wLine4.setBackground(Color.decode("#f1ca54"));
        wLine4.setForeground(Color.decode("#110e10"));
        wLine4.setBounds(0,80,933,25);
        wLine4.setFont(new Font("Sans Serif", Font.PLAIN,20));
        wLine4.setEditable(false);
        panel.add(wLine4);

        welcome2 = new JTextArea("                                                                         Welcome !");
        welcome2.setBackground(Color.decode("#f1ca54"));
        welcome2.setForeground(Color.decode("#110e10"));
        welcome2.setBounds(0,140,933,25);
        welcome2.setFont(new Font("Sans Serif", Font.BOLD,20));
        welcome2.setEditable(false);
        panel.add(welcome2);


        studentSignIn = new JButton("Student Player Log In");
        studentSignIn.setBackground(Color.decode("#1f2b37"));
        studentSignIn.setFont(new Font("Sans Serif", Font.BOLD, 20));
        studentSignIn.setForeground(Color.WHITE);
        studentSignIn.setBounds(100,270,720,40);
        studentSignIn.addActionListener(e -> {
            try {
                StudentLogIn studentLogIn = new StudentLogIn();
                frame.setVisible(false);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(studentSignIn);

        coachSignIn = new JButton("Coach Log In");
        coachSignIn.setBackground(Color.decode("#1f2b37"));
        coachSignIn.setFont(new Font("Sans Serif", Font.BOLD, 20));
        coachSignIn.setForeground(Color.WHITE);
        coachSignIn.setBounds(100,320,720,40);
        coachSignIn.addActionListener(e -> {
            CoachLogIn coachLogIn = new CoachLogIn();
            frame.setVisible(false);
        });
        panel.add(coachSignIn);

        orgLogIn = new JButton("Organization Login");
        orgLogIn.setBackground(Color.decode("#1f2b37"));
        orgLogIn.setFont(new Font("Sans Serif", Font.BOLD, 20));
        orgLogIn.setForeground(Color.WHITE);
        orgLogIn.setBounds(100,370,720,40);
        orgLogIn.addActionListener(e -> {
            try {
                OrganizerLogIn organizerLogIn = new OrganizerLogIn();
                frame.setVisible(false);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(orgLogIn);

        frame.pack();
        frame.setVisible(true);


    }
}
