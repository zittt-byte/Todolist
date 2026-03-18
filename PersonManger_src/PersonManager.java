import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.formdev.flatlaf.FlatLightLaf;

public class PersonManager  {
    private JFrame fr;
    private JTable table;
    private DefaultTableModel tableModel;

    private JPanel wrapperpn, mainpn;

    private JButton add;
    private JButton remove;
    private JButton modify;

    private List rowIcons = new ArrayList();

    public PersonManager() {
        fr = new JFrame("Person Manager");
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(640, 400);
        fr.setLocationRelativeTo(null);

        personTable();

        JScrollPane scrollPane = new JScrollPane(table);
        JPanel btnPanel = new JPanel(new GridLayout(3, 1, 0, 6));

        add = new JButton("Add");
        remove = new JButton("Remove");
        modify = new JButton("Modify");

        btnPanel.add(add); add.addActionListener(e -> openAddDialog());
        btnPanel.add(remove); remove.addActionListener(e -> removeSelectedRow());
        btnPanel.add(modify); modify.addActionListener(e -> modifySelectedRow());

        JPanel btnWrapper = new JPanel(new BorderLayout());
        btnWrapper.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 8));
        btnWrapper.add(btnPanel, BorderLayout.NORTH);

        mainpn = new JPanel(new BorderLayout(8, 0));
        mainpn.add(scrollPane, BorderLayout.CENTER);
        mainpn.add(btnWrapper, BorderLayout.EAST);

        fr.add(mainpn);
        fr.setVisible(true);
    }
    
    // making icon.
    private ImageIcon createIcon() {
        int size = 60;
        BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        return new ImageIcon(img);
    }

    private void personTable() {
        String[] columnNames = {"Icon", "Name", "Role"};
        tableModel = new DefaultTableModel(columnNames, 0);

        table = new JTable(tableModel);
        table.setRowHeight(40);
        table.getTableHeader().setReorderingAllowed(false);
        
        table.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, null, isSelected, hasFocus, row, col);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                if (value instanceof ImageIcon) {
                    Image img = ((ImageIcon) value).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
                    label.setIcon(new ImageIcon(img));
                } else {
                    label.setIcon(createIcon());
                }
                return label;
            }
        });
        
        // set sizes for each colum.
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(160);
        table.getColumnModel().getColumn(2).setPreferredWidth(160);
    }

    private void openAddDialog() {
        AddPerson dialog = new AddPerson(fr, "Add");
        dialog.setVisible(true);
        if (dialog.isConfirmed()) {
            ImageIcon icon = dialog.getSelectedIcon();
            tableModel.addRow(new Object[]{icon, dialog.getPersonName(), dialog.getPersonRole()});
            rowIcons.add(icon);
        }
    }

    private void removeSelectedRow() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(fr, "Please select a person to be remove.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int anser = JOptionPane.showConfirmDialog(fr, "Remove selected person?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (anser == JOptionPane.YES_OPTION) {
            tableModel.removeRow(row);
            if (row < rowIcons.size()) {
                rowIcons.remove(row);
            }
        }
    }

    private void modifySelectedRow() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(fr, "Please select a row to modify.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        AddPerson dialog = new AddPerson(fr, "Modify " + "[" + tableModel.getValueAt(row, 1) + "]");
        dialog.setConfirmButtonText("Save");
        dialog.setName((String) tableModel.getValueAt(row, 1));
        dialog.setRole((String) tableModel.getValueAt(row, 2));
        Object icon = tableModel.getValueAt(row, 0);
        if (icon instanceof ImageIcon) { // Check if has icon.
            dialog.setProfileIcon((ImageIcon) icon);
        }

        dialog.setVisible(true);

        if (dialog.isConfirmed()) {
            ImageIcon icon2 = dialog.getSelectedIcon();
            tableModel.setValueAt(icon2, row, 0);
            tableModel.setValueAt(dialog.getPersonName(), row, 1);
            tableModel.setValueAt(dialog.getPersonRole(), row, 2);
            if (row < rowIcons.size()) rowIcons.set(row, icon2);
        }
    }
    
    
    public static void main(String[] args) {
        FlatLightLaf.setup();
        new PersonManager();
    }
}