package Board;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Todolist.Priority_Manage.CusColor;
import com.formdev.flatlaf.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.time.*;
/**
 *
 * @Kanin 68070224
 */
public class Column extends JPanel implements ActionListener {
    private final String title;
    private final String color;
    private int count = 0;
    private ArrayList<Task> contains;
    
    
    public JLabel count_label,title_label;
    public JButton add_button;
    public JPanel body;

    
    
    public Column(String name,String color){
        
        this.title = name;this.color = color;
        this.setLayout(new BorderLayout());
        
        
        // Header
        
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        this.putClientProperty( FlatClientProperties.STYLE,Config.cfgGroup(this.color));
        title_label = new JLabel(this.title);
        title_label.setFont(new Font("Inter",4,12));
        count_label = new JLabel(String.valueOf(count));
        count_label.setFont(new Font("Inter",4,16));
//        title_label.putClientProperty(FlatClientProperties.STYLE,CusColor.colorFromString(color).labelColor);
//        header.putClientProperty( FlatClientProperties.STYLE,CusColor.colorFromString(color).inTextColor);
        header.add(title_label);header.add(count_label);
        header.setMaximumSize(new Dimension(10000,50));
        header.setBackground(new Color(255,0,0));
        
        
        // Body
        body = new JPanel(); 
        body.setLayout(new BoxLayout(body,BoxLayout.Y_AXIS));
        body.setBackground(new Color(255,0,255));
        
        
        
        // Button
        
        add_button = new JButton("+ New Task");
//        add_button.putClientProperty(FlatClientProperties.STYLE,Config.cfgButton(this.color));
        add_button.setFocusable(false);
        add_button.setMaximumSize(new Dimension(500000, 50)); 
        add_button.setPreferredSize(new Dimension(0, 50));
        add_button.setMinimumSize(new Dimension(500000, 50)); 
        
        add_button.addActionListener(this);
        
        // Footer
       
        JPanel footer = new JPanel();
        footer.setPreferredSize(new Dimension(0, 50));
        footer.setMinimumSize(new Dimension(0, 50)); 
        footer.setMaximumSize(new Dimension(500000, 50)); 
        footer.setLayout(new BoxLayout(footer,BoxLayout.X_AXIS));
        
        footer.add(add_button);
        body.add(footer);
        
        
        ////
        
        
        
        this.add(header,BorderLayout.NORTH);
        this.add(body,BorderLayout.CENTER);
    }
    
    public void updateUi(){
        count_label.setText(String.valueOf(count));
        body.revalidate();
        body.repaint();
    }
    
    public int addTask(Task task){
        count++;
        contains.add(task);
        body.add(task,body.getComponentCount() - 1);
        updateUi();
        return 0;
    }
    
    public int removeTask(Task task){
        count--;
        contains.remove(task);
        body.remove(task);
        updateUi();
        return 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("+ New Task")) {
        }
    }


}

