package Todolist.Todo.src.main.LoginRegister;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @Yasitt
 */



public class MainMDI {
    public JFrame frame;
    public  MainMDI () {
        FlatLightLaf.setup();
        
        Font defaultFont = new Font("Inter", Font.PLAIN, 14);
        UIManager.put("Label.font", defaultFont);
        UIManager.put("TextField.font", defaultFont);
        UIManager.put("TextArea.font", defaultFont);
        UIManager.put("ComboBox.font", defaultFont);
        UIManager.put("CheckBox.font", defaultFont);
        UIManager.put("RadioButton.font", defaultFont);
        UIManager.put("Menu.font", defaultFont);
        UIManager.put("MenuItem.font", defaultFont);
        UIManager.put("Table.font", defaultFont);
        UIManager.put("List.font", defaultFont);
        
        frame = new JFrame("Main MDi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DatabaseSetup.DBSetup();
        
        CardFrame LoginUI = new CardFrame();

        frame.setSize(700,500);

        frame.setBackground(Color.gray);
        frame.setSize(900,900);
        frame.add(LoginUI);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        MainMDI esan = new MainMDI();
    }

}
