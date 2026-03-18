package Todolist.Priority_Manage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bank ratchanon
 */
import java.awt.*;
import javax.swing.table.*;
import javax.swing.*;
public class PriorityRenderer extends DefaultTableCellRenderer{
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        Object COLORNAME = table.getModel().getValueAt(row, 1);
        
        CusColor p = (CusColor)value;
        Color level = CusColor.hexToColorObject(p.textColor); 
        
        ((JLabel)c).setText(CusColor.getColor(p));
        c.setForeground(level);

        if (isSelected) {
            c.setForeground(Color.WHITE);
        }
        
        return c;
    }
}
