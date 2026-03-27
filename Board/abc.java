/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Todolist.Board;

/**
 *
 * @author User
 */
import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;
import javax.swing.*;
public class abc implements ActionListener{


    public abc() {
        JFrame j;
        JPanel Filter;
        JRadioButton name,priority,person,time_left,age;
        ButtonGroup group;
        j = new JFrame("Filter");
        j.setLayout(new BorderLayout());
        
        group = new ButtonGroup();

        Filter = new JPanel(new GridLayout(5,1));
        name = new JRadioButton("Name");
        priority = new JRadioButton("Priority");
        person = new JRadioButton("Person");
        time_left = new JRadioButton("Due Time ");
        age = new JRadioButton("Age");

        group.add(name);
        group.add(priority);
        group.add(person);
        group.add(time_left);
        group.add(age);
        
        Filter.add(name);
        Filter.add(priority);
        Filter.add(person);
        Filter.add(time_left);
        Filter.add(age);
        j.add(Filter);
        
        name.addActionListener(this);
        priority.addActionListener(this);
        person.addActionListener(this);
        time_left.addActionListener(this);
        age.addActionListener(this);
        
        priority.setSelected(true);

        j.pack();
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setVisible(true);
    }
    
    public String getSelectedButton() {
        for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }
    
    
    public static void main(String[] args) {
        new abc();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(getSelectedButton());
    }
} 