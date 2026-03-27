/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Todolist.component;

import Todolist.Board.Task;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author User
 */
public class BoxGroup extends JPanel {
    private ArrayList<ComBox> col = new ArrayList<>();
    
    public BoxGroup(Task task){
        setLayout(new FlowLayout(FlowLayout.CENTER,4,4));
        add(new PersonBox(task));
        add(new DeadLineBox(task));
        add(new PriorityBox(task));
        add(new TagBox(task));
    }
    
    public BoxGroup(ComBox comp){
        add(comp);
    }
}
