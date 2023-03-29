package gui.studentMenu;

import gui.ImagePanel;

import javax.swing.*;
import java.awt.*;

public class StudentRegister {
    private JFrame frame;
    private ImageIcon image;
    private ImagePanel panel;
    private JTextArea header, header2;
    private JLabel instruction, studID, fullName, course, gender, sports;
    private JTextField ID, fName;
    private JComboBox co, gen, sp;
    private JButton register, back;

    public StudentRegister(){
        image = new ImageIcon("src/res/images/background.jpg");
        panel = new ImagePanel(image.getImage());
        panel.setLayout(null);
        frame = new JFrame("SAMCIS Student Registration");
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

        header2 = new JTextArea("   STUDENT REGISTRATION");
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

        studID = new JLabel("Student ID: ");
        studID.setBounds(115,130,500,20);
        studID.setForeground(Color.decode("#110e10"));
        studID.setFont(new Font("Sans Serif", Font.BOLD,16));
        panel.add(studID);

        fullName = new JLabel("Full Name: ");
        fullName.setBounds(115,180,500,20);
        fullName.setForeground(Color.decode("#110e10"));
        fullName.setFont(new Font("Sans Serif", Font.BOLD,16));
        panel.add(fullName);

        course = new JLabel("Course: ");
        course.setBounds(115,230,500,20);
        course.setForeground(Color.decode("#110e10"));
        course.setFont(new Font("Sans Serif", Font.BOLD,16));
        panel.add(course);

        gender = new JLabel("Gender: ");
        gender.setBounds(115,280,500,20);
        gender.setForeground(Color.decode("#110e10"));
        gender.setFont(new Font("Sans Serif", Font.BOLD,16));
        panel.add(gender);

        sports = new JLabel("Sports: ");
        sports.setBounds(115,330,500,20);
        sports.setForeground(Color.decode("#110e10"));
        sports.setFont(new Font("Sans Serif", Font.BOLD,16));
        panel.add(sports);


        register= new JButton("Register");
        register.setBackground(Color.decode("#ececec"));
        register.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        register.setForeground(Color.decode("#2a5b84"));
        register.setBounds(570,420,130,28);
        register.addActionListener(e -> {
            try {
                if(!fName.getText().isEmpty() && !ID.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Are you registering with student ID " + ID.getText() + ", player for the " +
                            sp.getSelectedItem() + " team, " + gen.getSelectedItem() + " category?", "Confirm Register", JOptionPane.QUESTION_MESSAGE);
                    JOptionPane.showMessageDialog(null,"You are now successfully registered for SAMCIS Tryouts 2023!", "Sign In Successful", JOptionPane.INFORMATION_MESSAGE);

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
                StudentMenu studentMenu = new StudentMenu();
                frame.setVisible(false);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(back);

        ID = new JTextField();
        ID.setBounds(270,125,500,30);
        ID.setForeground(Color.decode("#110e10"));
        ID.setFont(new Font("Sans Serif", Font.PLAIN,16));
        panel.add(ID);

        fName = new JTextField();
        fName.setBounds(270,175,500,30);
        fName.setForeground(Color.decode("#110e10"));
        fName.setFont(new Font("Sans Serif", Font.PLAIN,16));
        panel.add(fName);

        String courses[] = {"", "BSIT", "BSCS", "BMMA", "BSAc" , "BSHM", "BSTM", "BSM", "BSBA"};
        co = new JComboBox(courses);
        co.setBounds(270,225,500,30);
        co.setForeground(Color.decode("#110e10"));
        co.setFont(new Font("Sans Serif", Font.PLAIN,16));
        panel.add(co);

        String gender[] = {"", "Male", "Female"};
        gen = new JComboBox(gender);
        gen.setBounds(270,275,500,30);
        gen.setForeground(Color.decode("#110e10"));
        gen.setFont(new Font("Sans Serif", Font.PLAIN,16));
        panel.add(gen);

        String spo[] = {"", "Basketball", "Volleyball", "Sepak Takraw", "Badminton", "Table Tennis"};
        sp = new JComboBox(spo);
        sp.setBounds(270,325,500,30);
        sp.setForeground(Color.decode("#110e10"));
        sp.setFont(new Font("Sans Serif", Font.PLAIN,16));
        panel.add(sp);




        frame.pack();
        frame.setVisible(true);
    }

}
