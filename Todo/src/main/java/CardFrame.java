import javax.swing.*;
import java.awt.*;

public class CardFrame extends JInternalFrame {

    private CardLayout cardLayout;
    private JPanel mainContainer;

    public CardFrame() {
        setTitle("TodoList");
        setSize(350, 300);
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
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPasswordField txtPin = new JPasswordField();
        JTextField txtUser = new JTextField();
        JButton btnLogin = new JButton("Login");
        JButton btnGoRegister = new JButton("สร้างบัญชีใหม่");

        panel.add(new JLabel("Username or Email:"));
        panel.add(txtUser);
        panel.add(new JLabel("Password :"));
        panel.add(txtPin);
        panel.add(btnLogin);
        panel.add(btnGoRegister);

        btnGoRegister.addActionListener(e -> {
            cardLayout.show(mainContainer, "REGISTER_SCREEN");
        });

        btnLogin.addActionListener(e -> {
            String username = txtUser.getText();
            String password = new String(txtPin.getPassword());

            boolean isSuccess = UserLogin.authenticate(username,password);
            if (isSuccess) {
                JOptionPane.showMessageDialog(this, "กำลังเข้าสู่ระบบ...");
                // dashborad open ???
            }
            else
                JOptionPane.showMessageDialog(this,"ผู้ใช้ไม่ถูกต้อง");

        });

        return panel;
    }

    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtName = new JTextField();
        JTextField txtEmail = new JTextField();
        JPasswordField txtPin = new JPasswordField();
        JButton btnRegister = new JButton("สมัครสมาชิก");
        JButton btnBackToLogin = new JButton("กลับไปหน้า Login");

        panel.add(new JLabel("ชื่อ:"));
        panel.add(txtName);
        panel.add(new JLabel("อีเมล:"));
        panel.add(txtEmail);
        panel.add(new JLabel("รหัส PIN (4 หลัก):"));
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
                JOptionPane.showMessageDialog(this, "สมัครสมาชิกสำเร็จ!");
                cardLayout.show(mainContainer, "LOGIN_SCREEN");
            } else {
                JOptionPane.showMessageDialog(this, "ข้อมูลไม่ถูกต้อง หรืออีเมลซ้ำ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    public static void main(String[] args) {
        new CardFrame();
    }
}