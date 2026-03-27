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
        
        add(Etc.boxFiller(20, 20));
        add(header);
        add(Etc.boxFiller(0, 10));
        add(desc);
        add(Etc.boxFiller(0, 10));
        
        BoxGroup body = new BoxGroup(task);
        
        add(body);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

    
}