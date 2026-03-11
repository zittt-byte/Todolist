package Todolist.Todo.src.main.java;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

public class MainMDI {
    public JDesktopPane desktopPane;
    public JFrame frame;
    public  MainMDI () {
        FlatLightLaf.setup();
        UIManager.put("defaultFont", new Font("Inter", Font.PLAIN, 14));
        frame = new JFrame("Main MDi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        desktopPane = new JDesktopPane();
        DatabaseSetup.DBSetup();
        
        CardFrame LoginUI = new CardFrame();

        frame.setSize(700,700);
        LoginUI.setSize(500,500);
        frame.add(desktopPane);

        desktopPane.setBackground(Color.gray);
        desktopPane.setSize(900,900);
        desktopPane.add(LoginUI);
        desktopPane.setVisible(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        MainMDI esan = new MainMDI();
    }

}
