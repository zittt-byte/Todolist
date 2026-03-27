package Todolist.PersonManger_src;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class AddPerson {
    private JDialog dialog;
    private JTextField nameTextField, roleTextField, mailTextField;
    private JLabel profileImage;
    private JButton uploadImage, confirm, cancel;

    private ImageIcon selectedProfileIcon;
    private boolean confirmed = false;

    public AddPerson(Frame owner, String title) {
        dialog = new JDialog(owner, title ,true);

        profileImage = new JLabel();
        profileImage.setPreferredSize(new Dimension(36, 36));
        profileImage.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        profileImage.setIcon(createIcon());

        uploadImage = new JButton("Browse"); uploadImage.addActionListener(e -> uploadProfileImage());
        confirm = new JButton("Add"); confirm.addActionListener(e -> onConfirm());
        cancel = new JButton("Cancel"); cancel.addActionListener(e -> dialog.dispose());
        
        nameTextField = new JTextField(16);
        roleTextField = new JTextField(16);
        mailTextField = new JTextField(16);
        JPanel imgRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        imgRow.add(profileImage);
        imgRow.add(uploadImage);
        
        JPanel panel = new JPanel(new GridLayout(4,2));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        panel.add(new JLabel(" Name :")); panel.add(nameTextField);
        panel.add(new JLabel(" Role :")); panel.add(roleTextField);
        panel.add(new JLabel(" Mail :")); panel.add(mailTextField);
        panel.add(new JLabel(" Profile :")); panel.add(imgRow);

        buttonPanel.add(cancel);
        buttonPanel.add(confirm);

        dialog.setLayout(new BorderLayout());
        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.pack();
        dialog.setLocationRelativeTo(owner);
        dialog.setResizable(false);
    }
    
    // making profile.
    private void uploadProfileImage() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(
            new javax.swing.filechooser.FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif", "bmp")
        );
        if (chooser.showOpenDialog(dialog) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                Image img = new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                selectedProfileIcon = new ImageIcon(img);
                if (selectedProfileIcon.getIconWidth() <= 0) {
                    throw new Exception("Invalid image");
                }
                profileImage.setIcon(selectedProfileIcon);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Failed to load image.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private ImageIcon createIcon() {
        int size = 60;
        BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        return new ImageIcon(img);
    }
    //
    
    private void onConfirm() {
        if ((nameTextField.getText().trim().isEmpty()) || (roleTextField.getText().trim().isEmpty())) {
            JOptionPane.showMessageDialog(dialog, "Name/Roles cannot be empty.", "Warning", JOptionPane.WARNING_MESSAGE);
        } 
        else {
            confirmed = true;
            dialog.dispose();
        }
    }

    // setters & getterss

    public void setName(String name) { nameTextField.setText(name); }
    public void setRole(String role) { roleTextField.setText(role); }
    public void setMail(String mail) { mailTextField.setText(mail); }
    public void setConfirmButtonText(String text) { confirm.setText(text); }
    
    public boolean isConfirmed() { return confirmed; }
    public String getPersonName() { return nameTextField.getText().trim(); }
    public String getPersonRole() { return roleTextField.getText().trim(); }
    public String getPersonMail() { return mailTextField.getText().trim(); }
    public ImageIcon getSelectedIcon() { return selectedProfileIcon; }
    
    public void setProfileIcon(ImageIcon icon) { // setter for icon.
        selectedProfileIcon = icon;
        profileImage.setIcon(icon);
    }

    void setVisible(boolean b) {
        dialog.setVisible(b);
    }
}