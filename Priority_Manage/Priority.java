package Todolist.Priority_Manage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.formdev.flatlaf.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
/**
 *
 * @Kanin
 */
public class Priority extends JPanel{
    private String name;
    private CusColor color;
    private int order;
    public final static ArrayList<Priority> priority = new ArrayList<>(Arrays.asList(new Priority("Low",CusColor.GREEN,0),new Priority("Medium",CusColor.YELLOW,1),new Priority("High",CusColor.RED,2),new Priority("Urgent",CusColor.PURPLE,999)));

    public Priority(String name, CusColor color,int order) {
        this.name = name;
        this.color = color;
        this.order = order;
        JLabel text = new JLabel(this.name);
        JPanel bg = new JPanel();
        text.setForeground(CusColor.hexToColorObject(this.color.textColor));
        text.setFont(new Font("Inter",Font.BOLD,12));
        bg.setBackground(CusColor.hexToColorObject(this.color.labelColor));
        bg.add(text);
        putClientProperty(FlatClientProperties.STYLE, "arc:8;");
        bg.putClientProperty(FlatClientProperties.STYLE, "arc:8;");
        add(bg);
        setBackground(Color.white);
    }
    
    public static ArrayList<Priority> fromExisting(){
        return new ArrayList<>(Arrays.asList(new Priority("Low",CusColor.GREEN,0),new Priority("Medium",CusColor.YELLOW,1),new Priority("High",CusColor.RED,2),new Priority("Urgent",CusColor.PURPLE,999)));
    
}
    
    public Priority copy() {
        return new Priority(this.name, this.color, this.order);
    }

    public Priority(String name, String color,int order) {
        this(name,CusColor.colorFromString(color),0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CusColor getColor() {
        return color;
    }

    public void setColor(CusColor color) {
        this.color = color;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

}
