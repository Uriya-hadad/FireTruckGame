import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    private static JFrame frame;
    private static ArrayList<String> userArrayList;
    private static JList<String> userList;
    private static File usersFile;


    public static void main(String[] args) {
        init();
    }

    static void init() {
        frame = new JFrame("Login");
        JButton select = new JButton("Select");
        JButton create = new JButton("Create new user");
        userArrayList = new ArrayList<>();
        userList = new JList<>();
        userList.setPreferredSize(new Dimension(DataForGame.userListWidth, DataForGame.userListHeight));
        usersFile = new File("files/users.txt");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().setLayout(new FlowLayout());
        frame.setIconImage(new ImageIcon("Pngs/icon.jpg").getImage());
        frame.setSize(DataForGame.DriverFrameWidth, DataForGame.DriverFrameHeight);
        frame.setLocationRelativeTo(null);
        getNamesFromFile();
        String[] userNamesList = userArrayList.toArray(new String[]{});
        userList.setListData(userNamesList);
        create.addActionListener(e1 -> createNewUser());
        select.addActionListener(e1 -> {
            if (userList.isSelectionEmpty()) {
                JOptionPane.showMessageDialog(frame, "choose an user or create a new one!");
            } else {
                frame.dispose();
                new Game(userList.getSelectedValue());
            }
        });
        frame.add(userList);
        frame.add(select);
        frame.add(create);
        frame.setVisible(true);
    }

    private static void getNamesFromFile() {
        try {
            Scanner scanner = new Scanner(usersFile);
            while (scanner.hasNextLine()) {
                String userName = scanner.nextLine();
                userArrayList.add(userName);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void createNewUser() {
        String userName = JOptionPane.showInputDialog(frame, "Enter a user name:");
        if (userName != null && userName.length() > 0) {
            FileWriter writer;
            try {
                writer = new FileWriter(usersFile, true);
                writer.write(userName);
                writer.write("\n");
                writer.flush();
                updateList(userArrayList, userList, userName);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    private static void updateList(ArrayList<String> userArrayList, JList<String> userList, String userName) {
        userArrayList.add(userName);
        String[] userNamesList = userArrayList.toArray(new String[]{});
        userList.setListData(userNamesList);

    }
}
