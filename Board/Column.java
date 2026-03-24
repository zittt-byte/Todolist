package Todolist.Board;


import Todolist.Priority_Manage.CusColor;
import com.formdev.flatlaf.FlatClientProperties;
import component.RoundedTopPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
/**
 *
 * @Kanin 68070224
 */
public class Column extends JPanel implements ActionListener {
    public final String title;
    public final CusColor color;
    public int count = 0;
    public Board board;
    private final String uuid = UUID.randomUUID().toString();

    public JLabel count_label,title_label;
    public JButton add_button;
    public JPanel body;

    
    
    public Column(String name,String color,Board board){
        this.board = board;
        this.title = name;this.color = CusColor.colorFromString(color);
        this.setLayout(new BorderLayout());
        
        
        // Header
        
        RoundedTopPanel header = new RoundedTopPanel(8);
        header.setLayout(new FlowLayout(FlowLayout.LEFT));
        title_label = new JLabel(this.title);
        title_label.setFont(new Font("Inter",4,12));
        count_label = new JLabel(String.valueOf(count));
        count_label.setFont(new Font("Inter",4,16));
        JPanel bg = new JPanel();
        bg.putClientProperty( FlatClientProperties.STYLE,"border: 0,0,0,0,"+this.color.borderColer+",1,999;background:"+this.color.labelColor);
        bg.add(title_label);
        title_label.putClientProperty(FlatClientProperties.STYLE,"foreground:"+this.color.textColor);
        header.putClientProperty( FlatClientProperties.STYLE,"background:"+this.color.labelColor);
        header.add(bg);header.add(count_label);
        header.setMaximumSize(new Dimension(10000,50));
        
        
        // Body
        body = new JPanel(); 
        body.setLayout(new BoxLayout(body,BoxLayout.Y_AXIS));
        body.setBackground(new Color(255,255,255));
        
        
        
        // Button
        
        add_button = new JButton("+ New Task");
        add_button.putClientProperty(FlatClientProperties.STYLE,"background: " + this.color.labelColor+";arc:20;foreground: "+this.color.inTextColor+";pressedBackground:#D9D7D3;borderColor:" + this.color.borderColer);
        add_button.setFocusable(false);
        add_button.setMaximumSize(new Dimension(500000, 50)); 
        add_button.setPreferredSize(new Dimension(0, 50));
        add_button.setMinimumSize(new Dimension(500000, 50)); 
        
        add_button.addActionListener(this);
        
        // Footer
       
        JPanel footer = new JPanel();
        footer.setPreferredSize(new Dimension(0, 50));
        footer.setMinimumSize(new Dimension(0, 50)); 
        footer.setMaximumSize(new Dimension(500000, 50)); 
        footer.setLayout(new BoxLayout(footer,BoxLayout.X_AXIS));
        footer.putClientProperty(FlatClientProperties.STYLE,"background:"+this.color.labelColor+";;border: 4,4,4,4,,,8");
        
        footer.add(add_button);
        body.add(footer);
        
        
        ////
        
        
        this.add(header,BorderLayout.NORTH);
        this.add(body,BorderLayout.CENTER);
        
        new DropTarget(this, new DropTargetAdapter() {
        @Override
        public void drop(DropTargetDropEvent dtde) {
            try {
                if (dtde.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                    Transferable t = dtde.getTransferable();
                    String data = (String) t.getTransferData(DataFlavor.stringFlavor);
                    
                    for (int i = 0;i<board.getContains().size();i++) {
                        if (board.getContains().get(i).uuid.equals(uuid)) {
                            board.updateTask(data,i);
                            break;
                        }
                    }
                    System.out.println(title);
                    dtde.dropComplete(true);
                } else {
                    dtde.rejectDrop();
                }
            } catch (UnsupportedFlavorException e) {
                dtde.rejectDrop();
            } catch (IOException ex) {
                System.out.println("ok");
            }
        }
    });
    }
    
    public void updateUi(){
        count_label.setText(String.valueOf(count));
        body.revalidate();
        body.repaint();
    }
    
    public int columnAddTask(Task task){
        count++;
        task.setBackground(CusColor.hexToColorObject(color.labelColor));
        body.add(task,count - 1);
        updateUi();
        return 0;
    }
    
    public int removeTask(Task task){
        count--;
        body.remove(task);
        updateUi();
        return 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("+ New Task")) {
            TaskInternalFrame a = new TaskInternalFrame(this.board,this);
            JDesktopPane DesktopPane = new JDesktopPane();
            JFrame ff = new JFrame();
            DesktopPane.add(a);
            a.setVisible(true);
            ff.getContentPane().add(DesktopPane, BorderLayout.CENTER);
            ff.setVisible(true);
                    }
    }


}

