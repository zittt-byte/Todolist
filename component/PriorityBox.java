/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Todolist.component;

import Todolist.Board.Task;
import com.formdev.flatlaf.*;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author User
 */
public class PriorityBox extends ComBox{
    
    public PriorityBox(Task task){
        super("PRIORITY");
        JPanel Prioritycollection = new JPanel(new FlowLayout(FlowLayout.LEFT));
        Prioritycollection.add(task.getPriority());
        Prioritycollection.putClientProperty(FlatClientProperties.STYLE, "background:#ffffff;");
        Prioritycollection.setBorder(BorderFactory.createEmptyBorder(0, -10, 0, 0));
        Prioritycollection.setAlignmentX(Component.LEFT_ALIGNMENT);
        super.pane.add(Prioritycollection);
    }
}
