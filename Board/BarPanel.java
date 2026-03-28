/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Todolist.Board;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author User
 */
public class BarPanel extends JPanel {
    public JLabel welcome;
    public BarPanel(String text){
        setLayout(new BorderLayout());
        JPanel wrapper = new JPanel();
        welcome = new JLabel("<html><nobr>Hello, <font color='#ff0000'>"+capitalize(text)+"</font></nobr></html>");
        welcome.setFont(new Font("Inria Serif", Font.PLAIN, 32));
        wrapper.add(welcome);
        wrapper.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(wrapper,BorderLayout.WEST);
        add(new Clock(),BorderLayout.EAST);
        
    }
    
String capitalize(String str) {
    char firstChar = Character.toUpperCase(str.charAt(0)); 
    return firstChar + str.substring(1); 
    }

}
