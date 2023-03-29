package gui.welcomePage;

import gui.ImagePanel;

import javax.swing.*;
import java.awt.*;


public class WelcomePage {
    private JFrame frame;
    private ImageIcon image;
    private ImagePanel panel;
    private JTextArea welcome, wLine2, wLine3, wLine4, welcome2;
    private JButton signIn, createAcc;

    public WelcomePage(){
        image = new ImageIcon("src/res/images/background.jpg");
        panel = new ImagePanel(image.getImage());
        panel.setLayout(null);
        frame = new JFrame("SAMCIS Welcome Page");
        frame.setResizable(false);
        /*Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);*/
        frame.setLocation(300,150);
        frame.getContentPane().add(panel);

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

        signIn = new JButton("Sign In");
        signIn.setBackground(Color.decode("#1f2b37"));
        signIn.setFont(new Font("Sans Serif", Font.BOLD, 20));
        signIn.setForeground(Color.WHITE);
        signIn.setBounds(100,280,720,40);
        signIn.addActionListener(e -> {
            try{
                SignInPage signInPage = new SignInPage();
                frame.setVisible(false);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(signIn);

        createAcc = new JButton("Create Account");
        createAcc.setBackground(Color.decode("#1f2b37"));
        createAcc.setFont(new Font("Sans Serif", Font.BOLD, 20));
        createAcc.setForeground(Color.WHITE);
        createAcc.setBounds(100,330,720,40);
        createAcc.addActionListener(e -> {
            try {
                CreateAccountPage createAccountPage = new CreateAccountPage();
                frame.setVisible(false);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(createAcc);


        frame.pack();
        frame.setVisible(true);
    }
}

