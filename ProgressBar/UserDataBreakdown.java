/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Todolist.ProgressBar;

import Todolist.Board.*;
import Todolist.PersonManger_src.Person;
import Todolist.Priority_Manage.Priority;
import Todolist.Tag_Manage.Tag;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.*;
import java.util.stream.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author User
 */

public class UserDataBreakdown extends JInternalFrame {
    private Map<Tag, Integer> tag_counter = new HashMap<>();
    private Map<Person, Integer> person_counter = new HashMap<>();
    private Map<Priority,Integer> priority_counter = new HashMap<>();
    private ArrayList<Integer> status = new ArrayList<>();
    public UserDataBreakdown(Board board) {
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        setLayout(new FlowLayout());
        setBackground(Color.WHITE);
        
        for (Tag tag: board.getTag_contain()) {
            tag_counter.put(tag,0);
        }
        for (Person person: board.getPerson_contain()) {
            person_counter.put(person,0);
        }
        
        for (Priority priority :board.getPriority()) {
            priority_counter.put(priority,0);
        }
        
        for (int i = 0;i < board.getContains().size();i++) {
            status.add(0);
            
        }
 
        
        for (Task task: board.getTask_contain()) {
            status.set(task.getStatus(), status.get(task.getStatus()) + 1);
            for (Tag tag : task.getTag()){
                tag_counter.put(tag,tag_counter.get(tag) + 1);
            }
            if (task.getAssignee() != null){
            person_counter.put(task.getAssignee(), person_counter.get(task.getAssignee()) + 1);
            }
            priority_counter.put(task.getPriority(), priority_counter.get(task.getPriority()) + 1);
            
        }
        System.out.println("total task - "+board.getTask_contain().size());
        System.out.println("priority_counter - "+priority_counter);
        System.out.println("status - "+status);
        System.out.println("tag_counter - "+tag_counter);
        System.out.println("person_counter - "+person_counter);
        
        List<Map.Entry<Tag, Integer>> topTags = tag_counter.entrySet().stream().sorted(Map.Entry.<Tag, Integer>comparingByValue().reversed()).limit(5).collect(Collectors.toList());
        List<Map.Entry<Person, Integer>> topPerson = person_counter.entrySet().stream().sorted(Map.Entry.<Person, Integer>comparingByValue().reversed()).limit(5).collect(Collectors.toList());
        
        ArrayList<Integer> priorityList = new ArrayList<>();
        for (Priority pri : board.getPriority()) {
            priorityList.add(priority_counter.get(pri));
        }
        add(new StatusBox(status));
        add(new TaskProgressBar(priorityList));
        add(new TagProgressBar(topTags));
        add(new PersonProgressBar(topPerson));
        setBackground(Color.WHITE);
        setSize(1200,800);
        setVisible(true);
 
        
           
    }
    
    
}


