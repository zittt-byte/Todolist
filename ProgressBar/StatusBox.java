/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Todolist.ProgressBar;

import Todolist.Priority_Manage.CusColor;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import static resources.Etc.boxFiller;

/**
 *
 * @author User
 */
public class StatusBox extends JPanel {
    private final ArrayList<CusColor> color = new ArrayList<>(Arrays.asList(CusColor.colorFromString("Gray"), CusColor.colorFromString("BLUE"), CusColor.colorFromString("GREEN")));
    
    public StatusBox(ArrayList<Integer> entry){
        setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
        add(Card("Not Started",entry.get(0),0));
        add(Card("In Progress",entry.get(1),1));
        add(Card("Finished",entry.get(2),2));
        
    }
    
    
    public JPanel Card(String titleText,int data,int index) {
        
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BorderLayout(8,16));
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));
        pane.add(boxFiller(10,0));
        JLabel title = new JLabel(titleText);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        pane.setPreferredSize(new Dimension(320, 160));
        title.setFont(new Font("Inter", Font.PLAIN, 32));
        pane.putClientProperty(FlatClientProperties.STYLE, "background:#ffffff;border: 6,6,6,6,#d1d1d1,1,16;");
        pane.add(title);
        wrapper.add(pane, BorderLayout.NORTH);
        JLabel count = new JLabel(String.valueOf(data));
        count.setAlignmentX(Component.LEFT_ALIGNMENT);
        count.setFont(new Font("Inter", Font.PLAIN, 64));
        count.setForeground(CusColor.hexToColorObject(color.get(index).textColor));
        pane.add(count, BorderLayout.SOUTH);
        
        return wrapper;
    }

    
}
    

