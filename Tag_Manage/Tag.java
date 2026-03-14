/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bank ratchanon
 */

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.*;

public class Tag implements ActionListener{
    private String name;
    private Priority priority;
    private DefaultTableModel model;
    private JFrame j;
    private JTable table;
    private JButton btnAdd , btnRemove , btnModify;
    
    public Tag(){
        j = new JFrame("Tag Manage");
        j.setLayout(new BorderLayout());
        j.setSize(500,400);
        
        JPanel rg = new JPanel(new GridLayout(3,1,0,20));
        btnAdd = new JButton("Add");
        btnRemove = new JButton("Remove");
        btnModify = new JButton("Modify"); 
        btnAdd.addActionListener(this);
        btnRemove.addActionListener(this);
        btnModify.addActionListener(this);
        rg.add(btnAdd);
        rg.add(btnRemove);
        rg.add(btnModify);
        
        JPanel East_button = new JPanel(new FlowLayout());
        East_button.add(rg);
        j.add(East_button , BorderLayout.EAST);
        
        String[] columnNames = {"Name", "color"};
        model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        model.setColumnIdentifiers(columnNames);
        
        ArrayList<Object[]> myList = new ArrayList<>();
        for (Object[] rowData : myList) {
            model.addRow(rowData);
        }
        table = new JTable(model);
        table.getColumnModel().getColumn(1).setCellRenderer(new PriorityRenderer());
        JScrollPane scrollPane = new JScrollPane(table);
        j.add(scrollPane , BorderLayout.CENTER);
        
        j.setDefaultCloseOperation(3);
        j.setVisible(true);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    
    public void Add(){
        AddDialog dialog = new AddDialog(j);
        dialog.setVisible(true);
        Object[] newData = dialog.getData();
        if (newData != null) {
            model.addRow(newData);
        }
    }
    
    public void Remove(){
        int selectedRow = table.getSelectedRow();
        if(selectedRow != -1) { 
            int confirm = JOptionPane.showConfirmDialog(j, 
                    "Want to Remove this Tag?", 
                    "Warning", 
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                model.removeRow(selectedRow);
            }
        } else {
            JOptionPane.showMessageDialog(j, "please Select one!");
        }
    }
    
    public void Modify(){
        int selectedRow = table.getSelectedRow();
        if(selectedRow != -1) {
            String oldName = (String) model.getValueAt(selectedRow, 0);
            String oldpriority = (String) model.getValueAt(selectedRow, 1);
            
            AddDialog dialog = new AddDialog(j);
            dialog.setInitialData(oldName, oldpriority);
            dialog.setVisible(true);
            
            Object[] newData = dialog.getData();
            if (newData != null) {
                model.setValueAt(newData[0], selectedRow, 0);
                model.setValueAt(newData[1], selectedRow, 1);
            }
        }
        else{
            JOptionPane.showMessageDialog(j, "please Select one!");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnAdd)){
            Add();
        }
        else if (e.getSource().equals(btnRemove)){
            Remove();
        }
        else if (e.getSource().equals(btnModify)){
            Modify();
        }
    }
    
    public static void main(String[] args) {
        FlatLightLaf.setup();
        new Tag();
    }
}
