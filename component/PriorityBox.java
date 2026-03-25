/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Todolist.component;

import Todolist.Priority_Manage.Priority;
import com.formdev.flatlaf.*;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author User
 */
public class PriorityBox extends ComBox{
    
    public PriorityBox(Priority TT){
        super("PRIORITY");
        JPanel Prioritycollection = new JPanel(new FlowLayout(FlowLayout.LEFT));
        Prioritycollection.add(TT);
        Prioritycollection.putClientProperty(FlatClientProperties.STYLE, "background:#ffffff;");
        Prioritycollection.setBorder(BorderFactory.createEmptyBorder(0, -10, 0, 0));
        Prioritycollection.setAlignmentX(Component.LEFT_ALIGNMENT);
        super.pane.add(Prioritycollection);
    }
    public static void main(String[] args) {
        FlatLightLaf.setup();
        JFrame fr = new JFrame();
        fr.setSize(700,700);
        fr.setLayout(new FlowLayout(FlowLayout.LEFT));
        fr.add(new PriorityBox(new Priority("Low","Green",0)));
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
    }
    
}
