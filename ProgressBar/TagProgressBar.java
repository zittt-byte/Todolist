package Todolist.ProgressBar;

import Todolist.Priority_Manage.CusColor;
import Todolist.Tag_Manage.Tag;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagProgressBar {
    public JFrame fr;
    public JPanel pa;
    public JLabel ltag;
    public Tag tag;
    
    //Main GUI
    public TagProgressBar(){
        fr = new JFrame();
        pa = new JPanel(); pa.setLayout(new BoxLayout(pa,BoxLayout.Y_AXIS));
        pa.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        ltag = new JLabel("Tag ProgressBar");
        
        
        
        pa.add(ltag);fr.add(pa);
        //Add row test
        addTagRow("Test 1", CusColor.GRAY,10);
        addTagRow("Test 2",CusColor.GREEN, 50);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.pack();
        fr.setVisible(true);
        
        
    }
    
    public void addTagRow(String name, CusColor color, int percent) {
        JPanel row = createRow(name, color, percent);
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        pa.add(row);
        pa.add(Box.createRigidArea(new Dimension(0, 10)));
    }
    
    //Create Row Method
    private JPanel createRow(String nameText, CusColor color, int percent) {
        JPanel container = new JPanel(new BorderLayout());
        container.setOpaque(false);

        JPanel top = new JPanel(new BorderLayout());

        JLabel name = new JLabel(nameText);
        JLabel value = new JLabel(String.valueOf(percent));

        top.add(name, BorderLayout.WEST);
        top.add(value, BorderLayout.EAST);

        JProgressBar bar = createBar(color, percent);
        
        container.add(top, BorderLayout.NORTH);
        container.add(bar, BorderLayout.SOUTH);

        return container;
    }
    
    //Create Bar Method
    private JProgressBar createBar(CusColor color, int value) {
        JProgressBar bar = new JProgressBar(0, 100);
        bar.setValue(value);
        
        bar.setUI(new javax.swing.plaf.basic.BasicProgressBarUI());
        bar.setForeground(CusColor.hexToColorObject(color.textColor));
        bar.setBorderPainted(false);
        bar.setPreferredSize(new Dimension(300, 8));

        return bar;
    }
    public static void main(String[] args) {
        new TagProgressBar();
    }
}