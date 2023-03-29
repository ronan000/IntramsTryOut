package gui.studentMenu;

import gui.ImagePanel;
import gui.commons.ListOfSports;
import gui.welcomePage.CreateAccountPage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class StudentMenu {
    private JFrame frame;
    private ImageIcon image;
    private ImagePanel panel;
    private JTextArea welcome, wLine2, wLine3, wLine4, welcome2;
    private JButton viewListSports, register, gameIndex, back;

    public StudentMenu(){
        image = new ImageIcon("src/res/images/background.jpg");
        panel = new ImagePanel(image.getImage());
        panel.setLayout(null);
        frame = new JFrame("SAMCIS Student Menu");
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
                CreateAccountPage createAccountPage = new CreateAccountPage();
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
        wLine2.setForeground(Color.decode("#110e10"));
        wLine2.setBackground(Color.decode("#f1ca54"));
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

        welcome2 = new JTextArea("                                                                   STUDENT'S MENU");
        welcome2.setBackground(Color.decode("#f1ca54"));
        welcome2.setForeground(Color.decode("#110e10"));
        welcome2.setBounds(0,140,933,25);
        welcome2.setFont(new Font("Sans Serif", Font.BOLD,20));
        welcome2.setEditable(false);
        panel.add(welcome2);

        viewListSports = new JButton("VIEW LIST OF SPORTS");
        viewListSports.setBackground(Color.decode("#1f2b37"));
        viewListSports.setFont(new Font("Sans Serif", Font.BOLD, 20));
        viewListSports.setForeground(Color.WHITE);
        viewListSports.setBounds(100,270,720,40);
        viewListSports.addActionListener(e -> {
            try{
                ListOfSports listOfSports = new ListOfSports();
                frame.setVisible(false);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });
        panel.add(viewListSports);

        register= new JButton("REGISTER FOR TRYOUTS");
        register.setBackground(Color.decode("#1f2b37"));
        register.setFont(new Font("Sans Serif", Font.BOLD, 20));
        register.setForeground(Color.WHITE);
        register.setBounds(100,320,720,40);
        register.addActionListener(e -> {
            try {
                StudentRegister studentRegister = new StudentRegister();
                frame.setVisible(false);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(register);





        gameIndex = new JButton("GAME INDEX");
        gameIndex.setBackground(Color.decode("#1f2b37"));
        gameIndex.setFont(new Font("Sans Serif", Font.BOLD, 20));
        gameIndex.setForeground(Color.WHITE);
        gameIndex.setBounds(100,370,720,40);
        gameIndex.addActionListener(e -> {});
        panel.add(gameIndex);


        frame.pack();
        frame.setVisible(true);
    }

}
