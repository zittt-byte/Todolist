package component;

import java.awt.*;
import javax.swing.*;
import resources.Etc;

public class ComHeader extends JPanel {
    public ComHeader(String header,String footer){
        JLabel title = new JLabel(header);
        title.setFont(new Font("Inter",Font.BOLD,28));
        title.setForeground(Color.black);
        title.setHorizontalTextPosition(JTextField.LEFT);
                
        JLabel desc = new JLabel(footer);
        desc.setFont(new Font("Inter",Font.PLAIN,16));
        desc.setForeground(Color.gray);
        
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setMaximumSize(new Dimension(460,500));
        setPreferredSize(new Dimension(460,75));
        setMinimumSize(new Dimension(460,75));
        setAlignmentX(Box.CENTER_ALIGNMENT);
        setBackground(Color.white);
        add(desc);
        add(Etc.boxFiller(10,5));
        add(title);
    }
    
    
}
