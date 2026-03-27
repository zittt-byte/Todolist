import Todolist.PersonManger_src.Person;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
 
import com.formdev.flatlaf.FlatLightLaf;
 
public class PersonManager {
    private JFrame fr;
    private JTable table;
    private DefaultTableModel tableModel;
 
    private JPanel mainpn;
 
    private JButton add;
    private JButton remove;
    private JButton modify;
 
    private List<Person> people = new ArrayList<>();
 
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
 
        btnPanel.add(add);    add.addActionListener(e -> openAddDialog());
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
 
    private ImageIcon createPlaceholderIcon() {
        int size = 60;
        BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        return new ImageIcon(img);
    }
 
    private void personTable() {
        String[] columnNames = {"Icon", "Name", "Role"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // make table read-only
            }
        };
 
        table = new JTable(tableModel);
        table.setRowHeight(40);
        table.getTableHeader().setReorderingAllowed(false);
 
        // Icon column renderer
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
                    label.setIcon(createPlaceholderIcon());
                }
                return label;
            }
        });
 
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(160);
        table.getColumnModel().getColumn(2).setPreferredWidth(160);
    }
 
    private void openAddDialog() {
        AddPerson dialog = new AddPerson(fr, "Add Person");
        dialog.setVisible(true);
 
        if (dialog.isConfirmed()) {
            Person p = new Person(
                dialog.getPersonName(),
                dialog.getPersonRole(),
                dialog.getPersonMail(),
                dialog.getSelectedIcon()
            );
            people.add(p);
            tableModel.addRow(new Object[]{p.getIcon(), p.getName(), p.getRole()});
        }
    }
 
    private void removeSelectedRow() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(fr, "Please select a person to remove.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        int answer = JOptionPane.showConfirmDialog(fr, "Remove selected person?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            tableModel.removeRow(row);
            people.remove(row);
        }
    }
 
    private void modifySelectedRow() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(fr, "Please select a row to modify.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        Person p = people.get(row);
 
        AddPerson dialog = new AddPerson(fr, "Modify [" + p.getName() + "]");
        dialog.setConfirmButtonText("Save");
        dialog.setName(p.getName());
        dialog.setRole(p.getRole());
        dialog.setMail(p.getMail());
        if (p.getIcon() != null) {
            dialog.setProfileIcon(p.getIcon());
        }
 
        dialog.setVisible(true);
 
        if (dialog.isConfirmed()) {
            p.setName(dialog.getPersonName());
            p.setRole(dialog.getPersonRole());
            p.setMail(dialog.getPersonMail());
            p.setIcon(dialog.getSelectedIcon());
 
            tableModel.setValueAt(p.getIcon(), row, 0);
            tableModel.setValueAt(p.getName(), row, 1);
            tableModel.setValueAt(p.getRole(), row, 2);
        }
    }
 
    public static void main(String[] args) {
        FlatLightLaf.setup();
        new PersonManager();
    }
}