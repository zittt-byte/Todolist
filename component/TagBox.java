/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Todolist.component;

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
    public TagBox(ArrayList<Tag> TT){
        super("TAGS");
        JPanel Tagcollection = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (Tag tag : TT){
            Tagcollection.add(tag);
        }
        Tagcollection.putClientProperty(FlatClientProperties.STYLE, "background:#ffffff;");
        Tagcollection.setAlignmentX(Component.LEFT_ALIGNMENT);
        Tagcollection.setBorder(BorderFactory.createEmptyBorder(0, -10, 0, 0));
        super.pane.add(Tagcollection);
    }
    
    public static void main(String[] args) {
        FlatLightLaf.setup();
        JFrame fr = new JFrame();
        fr.setSize(700,700);
        fr.setLayout(new FlowLayout(FlowLayout.LEFT));
        ArrayList<Tag> ta = new ArrayList<>(Arrays.asList(new Tag("UI","green"),new Tag("UX","red"),new Tag("Refactor","orange")));
        fr.add(new TagBox(ta));
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
    }
    
}
