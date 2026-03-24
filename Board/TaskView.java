/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Todolist.Board;

import Todolist.Priority_Manage.*;
import Todolist.Tag_Manage.Tag;
import com.formdev.flatlaf.FlatLightLaf;
import component.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.util.ArrayList;
import resources.Etc;

/**
 *
 * @Kanin
 */
public class TaskView extends JPanel implements ActionListener {
    private JLabel header,desc;
    private JPanel content;
    private ComBox assigneeBox,deadlineBox,priorityBox,tagBox;
    
    
    
    public TaskView(Task task){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        header = new JLabel("<html>"+task.getTitle()+"</html>");desc = new JLabel("<html>"+task.getDesc()+"</html>");
        
        header.setFont(new Font("Inter",Font.BOLD,24));
        desc.setFont(new Font("Inter",Font.PLAIN,14));
        
        add(Etc.boxFiller(20, 20));
        add(header);
        add(Etc.boxFiller(0, 10));
        add(desc);
        add(Etc.boxFiller(0, 10));
        
        ComBoxGroup body = new ComBoxGroup();
        
        add(body);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static void main(String[] args) {
        FlatLightLaf.setup();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ArrayList<Tag> a = new ArrayList<>();
        LocalDateTime specificDate = LocalDateTime.of(2027, Month.JANUARY, 1, 10, 10, 30);
        frame.add(new TaskView(new Task("Fix Visual bug","user can't see task when render","K",new Priority("Low","Green",0),0,a,specificDate)));
        frame.setSize(new Dimension(450,700));
        frame.setVisible(true);
    }
    
}