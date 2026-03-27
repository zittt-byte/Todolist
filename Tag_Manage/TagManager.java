package Todolist.Tag_Manage;


/**
 *
 * @author bank ratchanon
 */

import Todolist.Board.Board;
import Todolist.Priority_Manage.CusColor;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.*;

public class TagManager implements ActionListener{
    private String name;
    private DefaultTableModel model;
    private JFrame j;
    private JTable table;
    private JButton btnAdd , btnRemove , btnModify;
    private ArrayList<Tag> tags = new ArrayList<>();
    private Board board;


    
    public TagManager(Board board){
        this.board = board;
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
        
        for (Tag tag : board.getTag_contain()) {
            tags.add(tag);
            model.addRow(new Object[]{tag.getName(), tag.getColor()});
        }

        table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(1).setCellRenderer(new TagColorRenderer());
        JScrollPane scrollPane = new JScrollPane(table);
        j.add(scrollPane , BorderLayout.CENTER);
        
        j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        j.setVisible(true);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void Add() {
        AddDialog dialog = new AddDialog(j);
        dialog.setVisible(true);
        Object[] newData = dialog.getData();
        if (newData != null) {
            Tag tag = new Tag((String) newData[0], CusColor.colorFromString((String)newData[1] ));
            tags.add(tag);
            board.addTag(tag);
            model.addRow(new Object[]{tag.getName(), tag.getColor()});
        }
    }
    
    public void Remove() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int confirm = JOptionPane.showConfirmDialog(j,
                    "Want to Remove this Tag?",
                    "Warning",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                board.removeTag(selectedRow);
                tags.remove(selectedRow);
                model.removeRow(selectedRow);
            }
        } else {
            JOptionPane.showMessageDialog(j, "Please select one!");
        }
    }
    
    public void Modify() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            Tag existing = tags.get(selectedRow);

            AddDialog dialog = new AddDialog(j);
            dialog.setInitialData(existing.getName(), existing.getColor().toString());
            dialog.setVisible(true);

            Object[] newData = dialog.getData();
            if (newData != null) {
                this.board.modifyTag(selectedRow, (String)newData[0], CusColor.colorFromString((String)newData[1]));
                existing.setName((String)newData[0]);
                existing.setColor(CusColor.colorFromString((String)newData[1] ));
                model.setValueAt(existing.getName(), selectedRow, 0);
                model.setValueAt(existing.getColor(), selectedRow, 1);
                
            }
        } else {
            JOptionPane.showMessageDialog(j, "Please select one!");
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
}