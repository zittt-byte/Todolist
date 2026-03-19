/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bank ratchanon
 */
import Todolist.Priority_Manage.CusColor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class AddDialog extends JDialog implements ActionListener{
    private String inputName = null;
    private String inputpriority = "";
    private JTextField nameField;
    private JComboBox<String> colorDropdown;
    private JButton btnCancel,btnAdd;
    
    public AddDialog(JFrame parent) {
        super(parent, "Add Tag", true);
        setSize(300, 150);
        setLayout(new GridLayout(3, 2, 5, 5));
        setLocationRelativeTo(parent);

        add(new JLabel(" Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Color:"));
        colorDropdown = new JComboBox<>(CusColor.COLORNAME);
        add(colorDropdown);

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
            return new Object[]{inputName, inputpriority};
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
            dispose();
        }
    }
}
