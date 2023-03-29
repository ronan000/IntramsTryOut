package gui.organizerMenu;

import gui.ImagePanel;
import gui.welcomePage.SignInPage;

import javax.swing.*;
import java.awt.*;

public class OrganizerLogIn {
    private JFrame frame;
    private ImageIcon image;
    private ImagePanel panel;
    private JTextArea header, header2;
    private JLabel instruction, username, password;
    private JTextField uName;
    private JPasswordField pass;
    private JButton login, back;
    public OrganizerLogIn(){
        image = new ImageIcon("src/res/images/background.jpg");
        panel = new ImagePanel(image.getImage());
        panel.setLayout(null);
        frame = new JFrame("SAMCIS Organizer Login");
        frame.setResizable(false);
        /*Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);*/
        frame.setLocation(300,150);
        frame.getContentPane().add(panel);

        header = new JTextArea("                                                                    SAMCIS Intrams Tryouts");
        header.setBackground(Color.decode("#f1ca54"));
        header.setForeground(Color.decode("#110e10"));
        header.setBounds(0,0,933,25);
        header.setFont(new Font("Sans Serif", Font.BOLD,20));
        header.setEditable(false);
        panel.add(header);

        header2 = new JTextArea("   ORGANIZER LOGIN");
        header2.setBackground(Color.decode("#f1ca54"));
        header2.setForeground(Color.decode("#110e10"));
        header2.setBounds(0,40,933,30);
        header2.setFont(new Font("Sans Serif", Font.BOLD,25));
        header2.setEditable(false);
        panel.add(header2);

        instruction = new JLabel("Fill in the Required Information:");
        instruction.setBounds(30,90,500,20);
        instruction.setForeground(Color.decode("#110e10"));
        instruction.setFont(new Font("Sans Serif", Font.BOLD,16));
        panel.add(instruction);


        username = new JLabel("Username: ");
        username.setBounds(115,210,500,20);
        username.setForeground(Color.decode("#110e10"));
        username.setFont(new Font("Sans Serif", Font.BOLD,16));
        panel.add(username);

        password = new JLabel("Password: ");
        password.setBounds(115,260,500,20);
        password.setForeground(Color.decode("#110e10"));
        password.setFont(new Font("Sans Serif", Font.BOLD,16));
        panel.add(password);


        login = new JButton("Login");
        login.setBackground(Color.decode("#ececec"));
        login.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        login.setForeground(Color.decode("#2a5b84"));
        login.setBounds(570,420,130,28);
        login.addActionListener(e -> {
            try {
                if(!uName.getText().isEmpty() && !pass.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Successfully Login.", "Organizer Logged In", JOptionPane.INFORMATION_MESSAGE);
                    uName.setText("");
                    pass.setText("");
                }
                else {
                    JOptionPane.showMessageDialog(null,"Text boxes are blank." , "Null Values", JOptionPane.ERROR_MESSAGE);
                }
            } catch (HeadlessException ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(login);

        back = new JButton("Back");
        back.setBounds(720,420,130,28);
        back.setBackground(Color.decode("#ececec"));
        back.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        back.setForeground(Color.decode("#2a5b84"));
        back.addActionListener(e -> {
            try {
                SignInPage signInPage = new SignInPage();
                frame.setVisible(false);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(back);

        uName = new JTextField();
        uName.setBounds(270,205,500,30);
        uName.setForeground(Color.decode("#110e10"));
        uName.setFont(new Font("Sans Serif", Font.PLAIN,16));
        panel.add(uName);

        pass = new JPasswordField();
        pass.setBounds(270,255,500,30);
        pass.setForeground(Color.decode("#110e10"));
        pass.setFont(new Font("Sans Serif", Font.PLAIN,16));
        panel.add(pass);



        frame.pack();
        frame.setVisible(true);

    }


}
