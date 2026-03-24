package Todolist.Board;



import Todolist.Priority_Manage.Priority;
import Todolist.Tag_Manage.Tag;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @Kanin
 */
public class BoardView extends JPanel implements ChangeListener {
    public Board board;


    public BoardView(Board board) {
        this.board = board;
        JScrollPane scrollPane = new JScrollPane(this);

        this.setLayout(new GridLayout(1, 3, 10, 0));
        for (Column contain1 : board.getContains()) { 
            this.add(contain1);
        }
        this.setBackground(new Color(255, 255, 255));

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }
     

    public void render(ArrayList<Priority> priority, ArrayList<Tag> Tag) {
        for (Column Column : board.getContains()) {
            Column.count = 0;
        }
        for (Task task : board.getTask_contain()) {
            if ((Tag.isEmpty() || !Collections.disjoint(Tag, task.getTag())) && (priority.isEmpty() || priority.contains(task.getPriority()))) {
                board.getContains().get(task.getStatus()).columnAddTask(task);
            }
        }
    }

    public int moveTask(Task task, int to) {
        board.getTask_contain().get(board.getTask_contain().indexOf(task)).setStatus(to);
        render(null,null);
        return 0;
    }

    public int addTask(Task task, int to) {
        board.getContains().get(to).columnAddTask(task);
        render(null,null);
        return 0;
    }

    public int removeTask(Task task, int to) {
        board.getTask_contain().remove(task);
        board.getContains().get(to).removeTask(task);
        render(null,null);
        return 0;
    }
    

    
    public static void main(String[] args) {
        FlatLightLaf.setup();
        
        Board a = new Board("OOP","Testing","O","Green");
        BoardView b = new BoardView(a);
        a.addChangeListener(b);
        JFrame fr = new JFrame();
        fr.setSize(1200,1000);
        fr.add(b);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
       
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        ArrayList<Tag> aa = new ArrayList<Tag>();
        ArrayList<Priority> bb = new ArrayList<Priority>();
        render(bb,aa);;
    }
    

}
