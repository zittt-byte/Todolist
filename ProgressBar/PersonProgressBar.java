/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Todolist.ProgressBar;

/**
 *
 * @author User
 */
import Todolist.PersonManger_src.Person;
import Todolist.Priority_Manage.CusColor;
import Todolist.Priority_Manage.Priority;
import Todolist.Tag_Manage.Tag;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.awt.*;


public class PersonProgressBar extends JPanel {
    public JPanel toppn;
    public JLabel task;
    public JPanel pbur,pbhi,pbmid,pblo;
    public Priority priority;
    private int sum;
    public List<Map.Entry<Person, Integer>> entry;
    
    public PersonProgressBar(List<Map.Entry<Person, Integer>> entry) {
        FlatLightLaf.setup();
        this.entry = entry; 
        toppn = new JPanel(new GridLayout(6,1));
        task = new JLabel("Person");
        task.setFont(new Font("Inter",Font.PLAIN,24));
        sum = 0;
        
        for (Map.Entry<Person, Integer> toptag : entry) {
            int count = toptag.getValue();
            sum += count;
        }      
        toppn.add(task,BorderLayout.NORTH);
        for (Map.Entry<Person, Integer> toptag : entry) {
            Person tag = toptag.getKey();
            int count = toptag.getValue();
            JPanel pane = new JPanel(new BorderLayout());
            pbur  = createBar(count,tag.getName(), Color.black);
            pane.add(pbur,BorderLayout.SOUTH);
            toppn.add(pbur);
            add(toppn);
        }  
        
        toppn.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
        
    }
    private JPanel createBar(int value,String name,Color color) {
        JPanel pane = new JPanel(new BorderLayout(8, 4));
        JPanel top = new JPanel(new BorderLayout());
        
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Inter",Font.PLAIN,16));
        
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        wrapper.add(nameLabel);
        
        JLabel nLabel = new JLabel(String.valueOf(value));
        nLabel.setFont(new Font("Inter",Font.PLAIN,16));
        
        top.add(wrapper, BorderLayout.WEST);
        top.add(nLabel, BorderLayout.EAST);

        
        JProgressBar bar = new JProgressBar(0, 100);
        bar.setValue((int)((value / (double) sum) * 100));

        bar.putClientProperty( FlatClientProperties.STYLE, "arc: 999" );

        bar.setForeground(color);
        bar.setBorderPainted(false);
        bar.setPreferredSize(new Dimension(300, 8));
        
        pane.add(top, BorderLayout.NORTH);
        pane.add(bar, BorderLayout.CENTER);

        pane.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
        return pane;
}
}