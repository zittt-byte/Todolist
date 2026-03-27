/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Todolist.component;

import Todolist.Board.Task;
import com.formdev.flatlaf.*;
import java.awt.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import resources.Etc;

/**
 *
 * @author User
 */
public class DeadLineBox extends ComBox{
    public DeadLineBox(Task task){
        super("DUE DATE");
        JPanel pane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("MMM d, yyyy  HH:mm:ss");
        JLabel text = new JLabel();
        if (task.getDeadline() != null){
            text.setText(task.getDeadline().format(myFormat));
        }
        text.setFont(new Font("Inter",Font.PLAIN,16));
        JPanel wrapper = new JPanel();
        text.setIcon(Etc.resizeImageIcon(new ImageIcon(getClass().getResource("/Todolist/resources/calender.png")), 24, 24));
        pane.putClientProperty(FlatClientProperties.STYLE, "background:#ffffff;");
        pane.add(text);
        pane.setBorder(BorderFactory.createEmptyBorder(0, -5, 0, 0));
        pane.setAlignmentX(Component.LEFT_ALIGNMENT);
        super.pane.add(pane);
    }
    
}
