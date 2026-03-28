/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Todolist.component;

import Todolist.Board.Task;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.*;
import javax.swing.*;
import resources.Etc;

/**
 *
 * @author User
 */
public class PersonBox extends ComBox {
    
    public PersonBox(Task task){
        super("ASSIGNEE");
        JPanel pane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel text = new JLabel();
        text.setFont(new Font("Inter",Font.PLAIN,16));
        JPanel wrapper = new JPanel();
        if (task.getAssignee() != null){
            text.setText(task.getAssignee().getName());
            if (task.getAssignee() != null){
                text.setIcon(Etc.resizeImageIcon(task.getAssignee().getIcon(), 24, 24));
            }
        }
        
        
        pane.putClientProperty(FlatClientProperties.STYLE, "background:#ffffff;");
        pane.add(text);
        pane.setBorder(BorderFactory.createEmptyBorder(0, -5, 0, 0));
        pane.setAlignmentX(Component.LEFT_ALIGNMENT);
        super.pane.add(pane);
    }
    
    
}
