package gui.coachMenu;

import gui.ImagePanel;
import gui.welcomePage.CreateAccountPage;

import javax.swing.*;
import java.awt.*;

public class CoachRegister {
    private JFrame frame;
    private ImageIcon image;
    private ImagePanel panel;
    private JTextArea header, header2;
    private JLabel instruction, firstName, lastName, sports, category;
    private JTextField fName, lName;
    private JComboBox sp, cat;
    private JButton register, back;

    public CoachRegister(){
        image = new ImageIcon("src/res/images/background.jpg");
        panel = new ImagePanel(image.getImage());
        panel.setLayout(null);
        frame = new JFrame("SAMCIS Coach Registration");
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

        header2 = new JTextArea("   COACH REGISTRATION");
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

        firstName = new JLabel("First Name: ");
        firstName.setBounds(115,160,500,20);
        firstName.setForeground(Color.decode("#110e10"));
        firstName.setFont(new Font("Sans Serif", Font.BOLD,16));
        panel.add(firstName);

        lastName = new JLabel("Last Name: ");
        lastName.setBounds(115,210,500,20);
        lastName.setForeground(Color.decode("#110e10"));
        lastName.setFont(new Font("Sans Serif", Font.BOLD,16));
        panel.add(lastName);

        sports = new JLabel("Choose Sport: ");
        sports.setBounds(115,260,500,20);
        sports.setForeground(Color.decode("#110e10"));
        sports.setFont(new Font("Sans Serif", Font.BOLD,16));
        panel.add(sports);

        category = new JLabel("Choose Category: ");
        category.setBounds(115,310,500,20);
        category.setForeground(Color.decode("#110e10"));
        category.setFont(new Font("Sans Serif", Font.BOLD,16));
        panel.add(category);

        register= new JButton("Register");
        register.setBackground(Color.decode("#ececec"));
        register.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        register.setForeground(Color.decode("#2a5b84"));
        register.setBounds(570,420,130,28);
        register.addActionListener(e -> {
            try {
                if(!fName.getText().isEmpty() && !lName.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Are you registering as " + fName.getText() +  " " +lName.getText() +  ", coach for the " +
                            sp.getSelectedItem() + " team, " + cat.getSelectedItem() + " category?", "Confirm Register", JOptionPane.QUESTION_MESSAGE);
                    JOptionPane.showMessageDialog(null,"Your status as coach is PENDING. Please check the list of coaches interface within the week for updates " +
                            "regarding your status.", "Sign In Successful", JOptionPane.INFORMATION_MESSAGE);

                }
                else {
                    JOptionPane.showMessageDialog(null,"Text boxes are blank." , "Null Values", JOptionPane.ERROR_MESSAGE);
                }
            } catch (HeadlessException ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(register);

        back = new JButton("Back");
        back.setBounds(720,420,130,28);
        back.setBackground(Color.decode("#ececec"));
        back.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        back.setForeground(Color.decode("#2a5b84"));
        back.addActionListener(e -> {
            try {
                CreateAccountPage createAccountPage = new CreateAccountPage();
                frame.setVisible(false);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(back);

        fName = new JTextField();
        fName.setBounds(270,155,500,30);
        fName.setForeground(Color.decode("#110e10"));
        fName.setFont(new Font("Sans Serif", Font.PLAIN,16));
        panel.add(fName);

        lName = new JTextField();
        lName.setBounds(270,205,500,30);
        lName.setForeground(Color.decode("#110e10"));
        lName.setFont(new Font("Sans Serif", Font.PLAIN,16));
        panel.add(lName);

        String courses[] = {"", "Basketball", "Volleyball", "Sepak Takraw", "Badminton", "Table Tennis"};
        sp = new JComboBox(courses);
        sp.setBounds(270,255,500,30);
        sp.setForeground(Color.decode("#110e10"));
        sp.setFont(new Font("Sans Serif", Font.PLAIN,16));
        panel.add(sp);

        String gender[] = {"", "Male", "Female", "Mixed"};
        cat = new JComboBox(gender);
        cat.setBounds(270,305,500,30);
        cat.setForeground(Color.decode("#110e10"));
        cat.setFont(new Font("Sans Serif", Font.PLAIN,16));
        panel.add(cat);




        frame.pack();
        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//        CoachRegister coachRegister = new CoachRegister();
//    }
}
