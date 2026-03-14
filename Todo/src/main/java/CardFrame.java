package Todolist.Todo.src.main.java;

import com.formdev.flatlaf.FlatClientProperties;
import component.*;
import javax.swing.*;
import java.awt.*;
import resources.Etc;

public class CardFrame extends JInternalFrame {

    private CardLayout cardLayout;
    private JPanel mainContainer;

    public CardFrame() {
        setTitle("TodoList");
        setSize(350, 300);
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        JPanel loginPanel = createLoginPanel();
        JPanel registerPanel = createRegisterPanel();

        mainContainer.add(loginPanel, "LOGIN_SCREEN");
        mainContainer.add(registerPanel, "REGISTER_SCREEN");

        add(mainContainer);

        cardLayout.show(mainContainer, "LOGIN_SCREEN");

        setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        final String TextFieldProperty = "arc:20;focusWidth:0;innerFocusWidth:0;";
        
        JTextField txtUser = new ComTextField("Username",new ImageIcon(getClass().getResource("/resources/mail.png")));
        JPasswordField txtPin = new ComPasswordField("PIN",new ImageIcon(getClass().getResource("/resources/lock.png")));
        JPanel header = new ComHeader("Welcome back","Sign In to get Started");
        
        
        txtUser.setAlignmentX(Box.CENTER_ALIGNMENT); 
        txtPin.setAlignmentX(Box.CENTER_ALIGNMENT); 
        
        JButton btnLogin = new ComButton("Sign in",Color.ORANGE,Color.white);
        
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
        footer.setBackground(Color.white);
        footer.setBorder(null);
        footer.add(new JLabel("Don't have an account? "));
        JButton btnGoRegister = new JButton("Register");
        btnGoRegister.setForeground(Color.orange);
        btnGoRegister.setMargin(new Insets(5,5,5,5));
        btnGoRegister.setBorder(null);
        footer.add(btnGoRegister);
        
        panel.add(Etc.boxFiller(10,20));
        panel.add(header);
        panel.add(Etc.boxFiller(10,15));
        panel.add(txtUser);
        panel.add(Etc.boxFiller(10,10));
        panel.add(txtPin);
        panel.add(Etc.boxFiller(10,20));
        panel.add(btnLogin);
        panel.add(Etc.boxFiller(10,10));
        panel.add(footer);

        btnGoRegister.addActionListener(e -> {
            cardLayout.show(mainContainer, "REGISTER_SCREEN");
        });

        btnLogin.addActionListener(e -> {
            String username = txtUser.getText();
            String password = new String(txtPin.getPassword());

            boolean isSuccess = UserLogin.authenticate(username,password);
            if (isSuccess) {
                JOptionPane.showMessageDialog(this, "Logging in!...");
                // dashborad open ???
            }
            else
                JOptionPane.showMessageDialog(this,"Incorrect User");

        });

        return panel;
    }

    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBackground(Color.white);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        JTextField txtName = new ComTextField("Name",new ImageIcon(getClass().getResource("/resources/user.png")));
        JTextField txtEmail = new ComTextField("Email",new ImageIcon(getClass().getResource("/resources/mail.png")));
        JPasswordField txtPin = new ComPasswordField("PIN",new ImageIcon(getClass().getResource("/resources/lock.png")));
        JPanel header = new ComHeader("Create an account","Sign up to get Started");
        JButton btnRegister = new ComButton("Sign up",Color.ORANGE,Color.WHITE);
        JButton btnBackToLogin = new JButton("Sign in");
        

        
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
        footer.setBackground(Color.white);
        footer.setBorder(null);
        footer.add(new JLabel("Already have an account?"));
        btnBackToLogin.setForeground(Color.orange);
        btnBackToLogin.setMargin(new Insets(5,5,5,5));
        btnBackToLogin.setBorder(null);
        footer.add(btnBackToLogin);
        
        
        panel.add(Etc.boxFiller(10,20));
        panel.add(header);
        panel.add(Etc.boxFiller(10,15));
        panel.add(txtName);
        panel.add(Etc.boxFiller(10,10));
        panel.add(txtEmail);
        panel.add(Etc.boxFiller(10,10));
        panel.add(txtPin);
        panel.add(Etc.boxFiller(10,20));
        panel.add(btnRegister);
        panel.add(footer);

        

        btnBackToLogin.addActionListener(e -> {
            cardLayout.show(mainContainer, "LOGIN_SCREEN");
        });

        btnRegister.addActionListener(e -> {
            String name = txtName.getText();
            String email = txtEmail.getText();
            String pin = new String(txtPin.getPassword());
            boolean isSuccess = UserRegistration.registerUser(name, email, pin);

            if (isSuccess) {
                JOptionPane.showMessageDialog(this, "Register success!");
                cardLayout.show(mainContainer, "LOGIN_SCREEN");
            } else {
                JOptionPane.showMessageDialog(this, "Duplicate email", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }
    
    private JPanel createRegisterPanel2() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtName = new JTextField();
        JTextField txtEmail = new JTextField();
        JPasswordField txtPin = new JPasswordField();
        JButton btnRegister = new JButton("Register");
        JButton btnBackToLogin = new JButton("Login");

        panel.add(new JLabel("Name:"));
        panel.add(txtName);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("PIN (4 Digit):"));
        panel.add(txtPin);
        panel.add(btnBackToLogin);
        panel.add(btnRegister);

        btnBackToLogin.addActionListener(e -> {
            cardLayout.show(mainContainer, "LOGIN_SCREEN");
        });

        btnRegister.addActionListener(e -> {
            String name = txtName.getText();
            String email = txtEmail.getText();
            String pin = new String(txtPin.getPassword());
            boolean isSuccess = UserRegistration.registerUser(name, email, pin);

            if (isSuccess) {
                JOptionPane.showMessageDialog(this, "Register success!");
                cardLayout.show(mainContainer, "LOGIN_SCREEN");
            } else {
                JOptionPane.showMessageDialog(this, "Duplicate email", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    
    public static void main(String[] args) {
        new CardFrame();
    }
}