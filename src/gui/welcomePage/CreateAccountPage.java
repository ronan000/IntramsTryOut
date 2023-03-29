package gui.welcomePage;

import gui.ImagePanel;
import gui.coachMenu.CoachRegister;
import gui.studentMenu.StudentMenu;

import javax.swing.*;
import java.awt.*;

public class CreateAccountPage {
    private JFrame frame;
    private ImageIcon image;
    private ImagePanel panel;
    private JTextArea welcome, wLine2, wLine3, wLine4, welcome2;
    private JButton studentReg, coachReg, orgLogIn, back;

    public CreateAccountPage(){
        image = new ImageIcon("src/res/images/background.jpg");
        panel = new ImagePanel(image.getImage());
        panel.setLayout(null);
        frame = new JFrame("SAMCIS Create Account Page");
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

        studentReg = new JButton("Student Player Registration");
        studentReg.setBackground(Color.decode("#1f2b37"));
        studentReg.setFont(new Font("Sans Serif", Font.BOLD, 20));
        studentReg.setForeground(Color.WHITE);
        studentReg.setBounds(100,270,720,40);
        studentReg.addActionListener(e -> {
            try {
                StudentMenu studentMenu = new StudentMenu();
                frame.setVisible(false);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(studentReg);

        coachReg = new JButton("Coach Registration");
        coachReg.setBackground(Color.decode("#1f2b37"));
        coachReg.setFont(new Font("Sans Serif", Font.BOLD, 20));
        coachReg.setForeground(Color.WHITE);
        coachReg.setBounds(100,320,720,40);
        coachReg.addActionListener(e -> {
            CoachRegister coachRegister = new CoachRegister();
            frame.setVisible(false);
        });
        panel.add(coachReg);

        /*orgLogIn = new JButton("Organization Login");
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
*/





        frame.pack();
        frame.setVisible(true);
    }
}
