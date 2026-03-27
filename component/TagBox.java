/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Todolist.component;

import Todolist.Board.Task;
import Todolist.Tag_Manage.Tag;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author User
 */
public class TagBox extends ComBox {
    public TagBox(Task task){
        super("TAGS");
        JPanel Tagcollection = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (Tag tag : task.getTag()){
            Tagcollection.add(tag);
        }
        Tagcollection.putClientProperty(FlatClientProperties.STYLE, "background:#ffffff;");
        Tagcollection.setAlignmentX(Component.LEFT_ALIGNMENT);
        Tagcollection.setBorder(BorderFactory.createEmptyBorder(0, -10, 0, 0));
        super.pane.add(Tagcollection);
    } 
}
