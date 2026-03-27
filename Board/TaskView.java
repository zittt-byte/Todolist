package Todolist.Board;


import Todolist.component.BoxGroup;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import resources.Etc;

/**
 *
 * @Kanin
 */
public class TaskView extends JPanel implements ActionListener,java.io.Serializable {
    private JLabel header,desc;
    private JPanel content;
    
    
    
    public TaskView(Task task){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        header = new JLabel("<html>"+task.getTitle()+"</html>");desc = new JLabel("<html>"+task.getDesc()+"</html>");
        
        header.setFont(new Font("Inter",Font.BOLD,24));
        desc.setFont(new Font("Inter",Font.PLAIN,14));
        
        
        header.setAlignmentX(Component.LEFT_ALIGNMENT);
        desc.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        header.setMaximumSize(new Dimension(Integer.MAX_VALUE, header.getPreferredSize().height));
        desc.setMaximumSize(new Dimension(Integer.MAX_VALUE, desc.getPreferredSize().height));
        
        header.setBorder(BorderFactory.createEmptyBorder(0, 32, 0, 0));
        desc.setBorder(BorderFactory.createEmptyBorder(0, 32, 0, 0));

        
        add(header);
        add(Etc.boxFiller(0, 10));
        add(desc);
        add(Etc.boxFiller(0, 10));
        
        BoxGroup body = new BoxGroup(task);
        
        body.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        add(body);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

    
}