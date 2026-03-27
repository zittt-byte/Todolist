/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Todolist.PersonManger_src;

import javax.swing.ImageIcon;

/**
 *
 * @author User
 */
public class Person implements java.io.Serializable {
    private String name,role,mail;
    private ImageIcon icon;

    public Person(String name, String role, String mail, ImageIcon icon) {
        this.name = name;
        this.role = role;
        this.mail = mail;
        this.icon = icon;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    
}