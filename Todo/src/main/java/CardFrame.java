package Todolist.Todo.src.main.java;

import com.formdev.flatlaf.FlatClientProperties;
import component.CustomPasswordField;
import component.CustomTextField;
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
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPasswordField txtPin = new CustomPasswordField("PIN",new ImageIcon(getClass().getResource("/resources/lock.png")));
   
        String TextFieldProperty = "arc:20;focusWidth:0;innerFocusWidth:0;";
        
 

        JTextField txtUser = new CustomTextField("Username",new ImageIcon(getClass().getResource("/resources/mail.png")));
        
        txtUser.setAlignmentX(Box.CENTER_ALIGNMENT); 
        txtPin.setAlignmentX(Box.CENTER_ALIGNMENT); 
        
        
        JButton btnLogin = new JButton("Login");
        btnLogin.setBackground(Color.orange);
        btnLogin.setForeground(Color.white);
        btnLogin.putClientProperty(FlatClientProperties.STYLE, TextFieldProperty);
        btnLogin.setMinimumSize(CustomTextField.FIXEDSIZE);
        btnLogin.setPreferredSize(CustomTextField.FIXEDSIZE);
        btnLogin.setMaximumSize(CustomTextField.FIXEDSIZE);
        btnLogin.setAlignmentX(Box.CENTER_ALIGNMENT); 
        
        
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
        footer.setBackground(Color.white);
        footer.setBorder(null);
        footer.add(new JLabel("Don't have an account?"));
        JButton btnGoRegister = new JButton("Register");
        btnGoRegister.setForeground(Color.orange);
        btnGoRegister.setMargin(new Insets(5,5,5,5));
        btnGoRegister.setBorder(null);
        footer.add(btnGoRegister);
        
        
        JLabel title = new JLabel(Greeting.getWord() + "!");
        title.setFont(new Font("Inter",Font.BOLD,24));
        title.setForeground(Color.black);
        title.setAlignmentX(Box.CENTER_ALIGNMENT); 
        
        
        JLabel desc = new JLabel("Sign In to get Started");
        desc.setFont(new Font("Inter",Font.PLAIN,16));
        desc.setForeground(Color.gray);
        desc.setAlignmentX(Box.CENTER_ALIGNMENT); 
        
        panel.add(title,FlowLayout.LEFT);
        panel.add(desc);
        panel.add(Etc.boxFiller(10,50));
        


        
        panel.add(txtUser);
        panel.add(Etc.boxFiller(10,10));
        panel.add(txtPin);
        panel.add(Etc.boxFiller(10,30));
        panel.add(btnLogin);
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