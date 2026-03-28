package Todolist.ProgressBar;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author apimookweerakunwattana
 */
import Todolist.Priority_Manage.CusColor;
import Todolist.Priority_Manage.Priority;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;



public class TaskProgressBar extends JPanel {
    public JPanel toppn,ptask,pur,phi,pmid,plo;
    public JLabel task;
    public JPanel pbur,pbhi,pbmid,pblo;
    public Priority priority;
    private int sum;
    public ArrayList<Integer> status;
    public TaskProgressBar(ArrayList<Integer> status){
        FlatLightLaf.setup();
        this.status = status; 
        toppn = new JPanel(new GridLayout(5,1));ptask = new JPanel(new BorderLayout());pur = new JPanel(new BorderLayout());phi = new JPanel(new BorderLayout());pmid = new JPanel(new BorderLayout());plo = new JPanel(new BorderLayout());
        task = new JLabel("Task Priorities");
        task.setFont(new Font("Inter",Font.PLAIN,24));

        
        sum = 0;
        for (int number : status) {
            sum += number;
        }
                
        
        int urgentCount = status.get(3);
        int highCount = status.get(2);
        int mediumCount = status.get(1);
        int lowCount = status.get(0);

        System.out.println("LLLLLLLLL         "+lowCount);

        
        
        pbur  = createBar(urgentCount,"Urgent", CusColor.hexToColorObject(Priority.priority.get(3).getColor().textColor)); //Urgent
        pbhi  = createBar(highCount, "High",CusColor.hexToColorObject(Priority.priority.get(2).getColor().textColor)); //High
        pbmid = createBar(mediumCount,"Medium", CusColor.hexToColorObject(Priority.priority.get(1).getColor().textColor)); //Medium
        pblo  = createBar(lowCount,"Low", CusColor.hexToColorObject(Priority.priority.get(0).getColor().textColor)); //Low
        
        

        
        toppn.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        ptask.add(task);
        pur.add(pbur,BorderLayout.SOUTH);
        phi.add(pbhi,BorderLayout.SOUTH);
        pmid.add(pbmid,BorderLayout.SOUTH);
        plo.add(pblo,BorderLayout.SOUTH);
        
        toppn.add(ptask);
        toppn.add(pur);toppn.add(phi);toppn.add(pmid);toppn.add(plo);
        add(toppn);
        
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
