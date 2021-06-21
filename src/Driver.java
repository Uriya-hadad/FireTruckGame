import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        init();
    }

    static void init() {
        JFrame frame = new JFrame("Login");
        frame.getContentPane().setLayout(new FlowLayout());
        JButton select = new JButton("Select");
        JButton create = new JButton("Create new user");
        frame.setIconImage(new ImageIcon("Pngs/icon.jpg").getImage());
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        ArrayList<String> userArrayList = new ArrayList<>();
        JList<String> userList = new JList<>();
        userList.setPreferredSize(new Dimension(380, 220));
        File usersFile = new File("files/users.txt");

        Scanner scanner;
        try {
            scanner = new Scanner(usersFile);
            while (scanner.hasNextLine()) {
                String userName = scanner.nextLine();
                userArrayList.add(userName);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] userNamesList = userArrayList.toArray(new String[]{});
        userList.setListData(userNamesList);
        create.addActionListener(e1 -> {
            String userName = JOptionPane.showInputDialog(frame, "Enter a user name:");
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
        });
        select.addActionListener(e1 -> {
            if (userList.isSelectionEmpty()) {
                JOptionPane.showMessageDialog(frame,"choose an user or create a new one!");
            }else{
                frame.dispose();
                new Game(userList.getSelectedValue());
            }
        });
        frame.add(userList);
        frame.add(select);
        frame.add(create);
        frame.setVisible(true);
    }

    private static void updateList(ArrayList<String> userArrayList, JList<String> userList, String userName) {
        userArrayList.add(userName);
        String[] userNamesList = userArrayList.toArray(new String[]{});
        userList.setListData(userNamesList);

    }
}
