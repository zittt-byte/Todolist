package Todolist.Priority_Manage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bank ratchanon & Kanin
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class AddDialog extends JDialog implements ActionListener{
    private String inputName = null;
    private String inputpriority = "";
    private int inputorder;
    private JTextField nameField,inputField;
    private JComboBox<String> colorDropdown;
    private JButton btnCancel,btnAdd;
    
    public AddDialog(JFrame parent) {
        super(parent, "Add Tag", true);
        setSize(300, 150);
        setLayout(new GridLayout(4, 2, 5, 5));
        setLocationRelativeTo(parent);

        add(new JLabel(" Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Color:"));
        colorDropdown = new JComboBox<>(CusColor.COLORNAME);
        add(colorDropdown);
        
        add(new JLabel("Order:"));
        inputField = new JTextField();
        add(inputField);

        btnCancel = new JButton("Cancel");
        btnAdd = new JButton("ADD");

        btnCancel.addActionListener(this);
        btnAdd.addActionListener(this);

        add(btnCancel);
        add(btnAdd);
    }
    
    public void setInitialData(String oldName, String oldpriority) {
        nameField.setText(oldName);
        colorDropdown.setSelectedItem(oldpriority);
    }
    
    public Object[] getData() {
        if (inputName != null && !inputName.trim().isEmpty()) {
            return new Object[]{inputName, inputpriority,inputorder};
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnCancel)){
            dispose();
        }
        else if (e.getSource().equals(btnAdd)){
            inputName = nameField.getText();
            inputpriority = (String)colorDropdown.getSelectedItem();
            inputorder = Integer.parseInt(inputField.getText());
            dispose();
        }
    }
}
