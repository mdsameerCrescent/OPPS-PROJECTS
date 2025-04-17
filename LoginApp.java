import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class UserManager {
    private Map<String, String> users = new HashMap<>();

    public void loadUsers() {
        try (Scanner sc = new Scanner(new File("users.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] userData = line.split(",");
                users.put(userData[0], userData[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveUsers() {
        try (PrintWriter out = new PrintWriter(new FileWriter("users.txt"))) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                out.println(entry.getKey() + "," + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean login(String username, String password) {
        return password.equals(users.get(username));
    }

    public boolean register(String username, String password) {
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, password);
        return true;
    }
}

class LoginUI extends JFrame {
    private UserManager userManager;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginUI() {
        userManager = new UserManager();
        userManager.loadUsers();

        setTitle("Login / Signup (OOP)");
        setLayout(new FlowLayout());

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        JButton loginBtn = new JButton("Login");
        JButton registerBtn = new JButton("Register");

        loginBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (userManager.login(username, password)) {
                JOptionPane.showMessageDialog(this, "Login Successful");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials");
            }
        });

        registerBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (userManager.register(username, password)) {
                JOptionPane.showMessageDialog(this, "Registration Successful");
            } else {
                JOptionPane.showMessageDialog(this, "User already exists");
            }
        });

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginBtn);
        add(registerBtn);

        setSize(250, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new LoginUI();
    }
}
