/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Todolist.Board;

import Todolist.PersonManger_src.Person;
import Todolist.Priority_Manage.CusColor;
import Todolist.Priority_Manage.Priority;
import Todolist.Tag_Manage.Tag;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.*;

/**
 *
 * @punnasin
 */
public class BoardBarPanel extends JPanel implements Bar,ActionListener{
    private final BoardView bv;
    public JPanel toolbar = new JPanel(new BorderLayout());
    public JButton reverseButton = new JButton("↩");
    public JButton refreshButton = new JButton("↻");
    public JButton eyeButton = new JButton("👁");
    public JButton SwapButton = new JButton("🔀");
    public JButton MagnifierButton = new JButton("🔍");
    public JButton prepleButton = new JButton("👤");
    public JButton settinButton = new JButton("⚙");
    public JButton burgerButton = new JButton("☰");
    private JPanel PriorityGroup;
    private ArrayList<Priority> prioritylist = new ArrayList<>();
    ButtonGroup SortGroup;
    JPanel center,right,left;
    private String sort = "Age";

    public BoardBarPanel(BoardView bv) {
        this.bv = bv;
        left = new JPanel(new FlowLayout());
        left.add(reverseButton);
        left.add(refreshButton);
        left.add(eyeButton);
        
        
        center = new JPanel(new FlowLayout());
        center.add(SwapButton);
        center.add(MagnifierButton);

        right = new JPanel(new FlowLayout());
        right.add(prepleButton);
        right.add(settinButton);
        right.add(burgerButton);

        toolbar.add(left, BorderLayout.WEST);
        toolbar.add(center, BorderLayout.CENTER);
        toolbar.add(right, BorderLayout.EAST);
        add(toolbar);
        reverseButton.addActionListener(bv);
        refreshButton.addActionListener(bv);
        eyeButton.addActionListener(bv);
        SwapButton.addActionListener(bv);
        MagnifierButton.addActionListener(bv);
        prepleButton.addActionListener(bv);
        settinButton.addActionListener(bv);
        burgerButton.addActionListener(bv);
        
        
        
    }
    
    public JPopupMenu priorityBox() {
        JPanel pane;
        JPopupMenu wrapper = new JPopupMenu();
        pane = new JPanel();
        pane.setLayout(new BorderLayout());
        JLabel l = new JLabel("Priority");
        PriorityGroup = new JPanel(new GridLayout(this.bv.board.getPriority().size(), 1));
        pane.add(l, BorderLayout.NORTH);
        for (Priority pri : this.bv.board.getPriority()) {
            JCheckBox temp = new JCheckBox(pri.getName(), false);
            temp.setForeground(CusColor.hexToColorObject(pri.getColor().textColor));
            temp.addActionListener(this);
            temp.putClientProperty("priority", pri);
            temp.putClientProperty("source", "Priority");
            PriorityGroup.add(temp);
        }
        pane.add(PriorityGroup, BorderLayout.CENTER);
        
        for (Component c : PriorityGroup.getComponents()) {
                if (prioritylist.contains((Priority)((JCheckBox)c).getClientProperty("priority"))) {
                    ((JCheckBox)c).setSelected(true);
                }
            }

        wrapper.add(pane);
        return wrapper;
    }
    
    public JPopupMenu SortBox() {
        JPanel Filter;
        JPopupMenu wrapper = new JPopupMenu();
        JRadioButton name,priority,person,time_left,age;
        
        SortGroup = new ButtonGroup();

        Filter = new JPanel(new GridLayout(5,1));
        name = new JRadioButton("Name");
        priority = new JRadioButton("Priority");
        person = new JRadioButton("Person");
        time_left = new JRadioButton("Due Time ");
        age = new JRadioButton("Age");

        name.putClientProperty("source", "sort");
        priority.putClientProperty("source", "sort");
        person.putClientProperty("source", "sort");
        time_left.putClientProperty("source", "sort");
        age.putClientProperty("source", "sort");
        
        SortGroup.add(age);
        SortGroup.add(name);
        SortGroup.add(priority);
        SortGroup.add(person);
        SortGroup.add(time_left);
        
        Filter.add(age);
        Filter.add(name);
        Filter.add(priority);
        Filter.add(person);
        Filter.add(time_left);
        
        
        name.addActionListener(this);
        priority.addActionListener(this);
        person.addActionListener(this);
        time_left.addActionListener(this);
        age.addActionListener(this);
        
        name.setSelected(true);
        
        wrapper.add(Filter);
        
        for (Enumeration<AbstractButton> buttons = SortGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.getText().equals(sort)) {
                button.setSelected(true);
                break;
            }
        }
        
        return wrapper; 
    }
    

    private String getfromgroup() {
        for (Enumeration<AbstractButton> buttons = SortGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    public ArrayList<Priority> test() {
        return this.bv.board.getPriority();
    }

    @Override
    public ArrayList<Tag> getTag() {
        ArrayList<Tag> a = new ArrayList<>();
        return a;
    }
    
    public String getSort() {
        return sort;
    }

    @Override
    public ArrayList<Person> getPerson() {
        ArrayList<Person> a = new ArrayList<>();
        return a;
    }

    @Override
    public ArrayList<Priority> getPriority() {
        return prioritylist;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JCheckBox && ((JCheckBox)e.getSource()).getClientProperty("source").equals("Priority")) {
            JCheckBox CheckBox = (JCheckBox)e.getSource();
            if (CheckBox.isSelected()) {
                prioritylist.add((Priority)CheckBox.getClientProperty("priority"));
            } else {
                prioritylist.remove((Priority)CheckBox.getClientProperty("priority"));
            }
        } else if (e.getSource() instanceof JRadioButton && ((JRadioButton)e.getSource()).getClientProperty("source").equals("sort")) {
            JRadioButton CheckBox = (JRadioButton)e.getSource();
            sort = CheckBox.getText();
        }
        this.bv.render();
    }
    
}
