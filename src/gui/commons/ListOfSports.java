package gui.commons;

import gui.IconColorSetter;
import gui.ImagePanel;
import gui.JSearchTextField;
import gui.studentMenu.StudentMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListOfSports {
    private JFrame frame;
    private ImageIcon image;

    private ImagePanel panel;
    private JTextArea header;
    private JSearchTextField search;
    private JComboBox filter;
    private JButton add, remove, back;
    private JTable table;
    private JTable model;
    static List<Sports> sportsList;
    public ListOfSports() throws IOException {
        sportsList = new ArrayList<>();
        /*String DB_URL = "jdbc:mysql://localhost:3306/tryouts";
        String USER = "root";
        String PASS = "";
        String QUERY = "SELECT * FROM sport";
        PreparedStatement ps;
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            ps = conn.prepareStatement(QUERY);
            ResultSet rs = ps.executeQuery();
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                System.out.print("sportID: " + rs.getInt("sportID"));
                int ID = rs.getInt("sportID");
                System.out.print(", sportDescription: " + rs.getString("sportDescription"));
                String desc = rs.getString("sportDescription");
                System.out.print(", sCategory: " + rs.getString("sCategory"));
                String cat = rs.getString("sCategory");
                System.out.println(", sType: " + rs.getString("sType"));
                String type = rs.getString("sType");
                sportsList.add(new Sports(ID, desc, cat, type));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/

        image = new ImageIcon("src/res/images/background.jpg");
        panel = new ImagePanel(image.getImage());
        panel.setLayout(null);
        frame = new JFrame("SAMCIS List of Sports Page");
        frame.setResizable(false);
        /*Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);*/
        frame.setLocation(300, 150);
        frame.getContentPane().add(panel);


        header = new JTextArea("                                                                    SAMCIS Intrams Tryouts");
        header.setBackground(Color.decode("#f1ca54"));
        header.setForeground(Color.decode("#110e10"));
        header.setBounds(0, 0, 933, 25);
        header.setFont(new Font("Sans Serif", Font.BOLD, 20));
        header.setEditable(false);
        panel.add(header);

        ImageIcon icon = new ImageIcon("src/res/images/back icon.png");
        Image image1 = icon.getImage();
        icon = new ImageIcon(image1);

        back = new JButton(icon);

        back.setBounds(20, 50, 20, 20);
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.addActionListener(e -> {
            try {
                StudentMenu studentMenu = new StudentMenu();
                frame.setVisible(false);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(back);

        IconColorSetter iconColorSetter = new IconColorSetter();
        BufferedImage ic = iconColorSetter.setIconColor("src/res/images/search icon.png", "#2a5b84");
        ImageIcon icon1 = new ImageIcon(ic);

        search = new JSearchTextField();
        search.setIcon(icon1);
        search.setForeground(Color.decode("#110e10"));
        search.setFont((new Font("Sans Serif", Font.PLAIN, 14)));
        search.setBounds(250, 50, 250, 30);
        panel.add(search);

        String[] header = {"Sports ID", "Description", "Category", "Type"};
        model = new JTable();

        model.setBounds(100, 250, 400, 400);
        JScrollPane sp = new JScrollPane(model);
        panel.add(sp);


        String[] fill = {"Sport Name", "Category", "Type"};
        filter = new JComboBox<>(fill);
        //filter.setToolTipText("Filter by");
        filter.setBounds(530, 50, 200, 30);
        filter.setForeground(Color.decode("#110e10"));
        filter.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        panel.add(filter);

        add = new JButton("Add Sports");
        add.setBounds(250, 420, 150, 28);
        add.setBackground(Color.decode("#ececec"));
        add.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        add.setForeground(Color.decode("#2a5b84"));
        add.addActionListener(e -> {
            try {
                //SignInPage signInPage = new SignInPage();
                frame.setVisible(false);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(add);


        remove = new JButton("Remove Sports");
        remove.setBounds(520, 420, 150, 28);
        remove.setBackground(Color.decode("#ececec"));
        remove.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        remove.setForeground(Color.decode("#2a5b84"));
        remove.addActionListener(e -> {
            try {
                //SignInPage signInPage = new SignInPage();
                frame.setVisible(false);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(remove);


        frame.pack();
        frame.setVisible(true);


    }

    public static void main(String[] args) throws IOException {
        ListOfSports list = new ListOfSports();
    }

}
