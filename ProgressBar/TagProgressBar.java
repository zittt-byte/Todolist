package Todolist.ProgressBar;


/**
 *
 * @author apimookweerakunwattana
 */
import Todolist.Priority_Manage.CusColor;
import Todolist.Priority_Manage.Priority;
import Todolist.Tag_Manage.Tag;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.awt.*;


public class TagProgressBar extends JPanel {
    public JPanel toppn;
    public JLabel task;
    public JPanel pbur;
    public Priority priority;
    private int sum;
    public List<Map.Entry<Tag, Integer>> entry;
    
    public TagProgressBar(List<Map.Entry<Tag, Integer>> entry) {
        FlatLightLaf.setup();
        setLayout(new BorderLayout());
        this.entry = entry; 
        toppn = new JPanel(new GridLayout(6,1));
        task = new JLabel("Tag");
        task.setFont(new Font("Inter",Font.PLAIN,24));
        sum = 0;
        
        for (Map.Entry<Tag, Integer> toptag : entry) {
            int count = toptag.getValue();
            sum += count;
        }      
        
        toppn.add(task);
        for (Map.Entry<Tag, Integer> toptag : entry) {
            Tag tag = toptag.getKey();
            int count = toptag.getValue();
            JPanel bar  = createBar(count,tag.getName(), CusColor.hexToColorObject(tag.getColor().textColor));
            toppn.add(bar);
        }  
        
        add(toppn, BorderLayout.CENTER);
        toppn.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
        
    }
    private JPanel createBar(int value,String name,Color color) {
        JPanel pane = new JPanel(new BorderLayout(8, 4));
        JPanel top = new JPanel(new BorderLayout());
        
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Inter",Font.PLAIN,16));
        JLabel dot = new JLabel("● ");
        dot.setFont(new Font("Inter",Font.PLAIN,16));
        dot.setForeground(color);
        
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        wrapper.add(dot);
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