package Todolist.Board;

import Todolist.PersonManger_src.Person;
import Todolist.Priority_Manage.Priority;
import Todolist.Tag_Manage.*;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @Kanin
 */
public class BoardView extends JPanel implements ChangeListener ,ActionListener{
    public Board board;
    public User parent;
    public JPanel pane;
    public Bar panelbar;
    

    public BoardView(Board board,User Parent) {
        this.board = board;
        parent = Parent;
        pane = new JPanel();
        setLayout(new BorderLayout());
        
        JScrollPane scrollPane = new JScrollPane(pane);

        pane.setLayout(new GridLayout(1, 3, 10, 0));
        for (Column contain1 : board.getContains()) { 
            pane.add(contain1);
        }
        pane.setBackground(new Color(255, 255, 255));

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panelbar = new BoardBarPanel(this);
        this.add(pane,BorderLayout.CENTER);
        this.add((BoardBarPanel)panelbar,BorderLayout.NORTH);
    }
     
    private void attachCloseButton(Task task) {
       JButton closeButton = new JButton("✕");
       closeButton.setFont(new Font("SeNoto Color Emoji", Font.PLAIN, 12));
       closeButton.putClientProperty(FlatClientProperties.STYLE, "border:2,2,2,2,#d1d1d1,1,12;");
       closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       closeButton.setPreferredSize(new Dimension(24, 24));
       closeButton.setVisible(false);

       JPanel closeBtnWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT, 4, 4));
       closeBtnWrapper.setOpaque(false);
       closeBtnWrapper.add(closeButton);

       JLayeredPane lp = task.getLayeredPane();
       lp.add(closeBtnWrapper, JLayeredPane.PALETTE_LAYER);

       task.getWrapper().addMouseListener(new MouseAdapter() {
           @Override
           public void mouseEntered(MouseEvent e) {
               closeButton.setVisible(true);
           }

           @Override
           public void mouseExited(MouseEvent e) {
               Point p = SwingUtilities.convertPoint(
        e.getComponent(), e.getPoint(), task.getWrapper()
               );
               if (!task.getWrapper().contains(p)) {
                   closeButton.setVisible(false);
               }
           }
       });

       closeButton.addActionListener(e -> {
           int result = JOptionPane.showConfirmDialog(this,"Delete task \"" + task.getTitle() + "\"?","Confirm Delete",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
           if (result == JOptionPane.YES_OPTION) {
               board.removeTask(task);
               render();
           }
       });
   }    
    public void render() {
        System.out.println("render");
        for (Column Column : board.getContains()) {
            Column.count = 0;
            Column.clearTask();
        }
        
        System.out.println(isSameInstance(((BoardBarPanel)panelbar).test(),this.board.getPriority()));
        ArrayList<Priority> priority = panelbar.getPriority();
        ArrayList<Tag> tag = panelbar.getTag();
        ArrayList<Person> person = panelbar.getPerson();
        ArrayList<Task> ListClone = new ArrayList<>(this.board.getTask_contain());
        switch (panelbar.getSort()) {
            case "Name" -> ListClone.sort(Comparator.comparing(Task::getTitle));
            case "Age" -> ListClone.sort(Comparator.comparing(Task::getCreatedAt));
            case "Due Time" -> ListClone.sort(Comparator.comparing(Task::getTitle));
            case "Priority" -> ListClone.sort(Comparator.comparing(Task::getTitle));
            case "Person" -> ListClone.sort(Comparator.comparing(Task::getTitle));
        }
        
        for (Task task : ListClone) {
            if ((tag.isEmpty() || !Collections.disjoint(tag, task.getTag())) && (priority.isEmpty() || priority.contains(task.getPriority())) && (person.isEmpty() || person.contains(task.getAssignee()))) {
                board.getContains().get(task.getStatus()).columnAddTask(task);
                attachCloseButton(task);
            }
        }
    }
    
    public boolean isSameInstance(ArrayList<?> a, ArrayList<?> b) {
        if (a.size() != b.size()) return false;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) != b.get(i)) return false;  // == check for singleton identity
        }
        return true;
    }

    public int moveTask(Task task, int to) {
        board.getTask_contain().get(board.getTask_contain().indexOf(task)).setStatus(to);
        render();
        return 0;
    }

    public int addTask(Task task, int to) {
        board.getContains().get(to).columnAddTask(task);
        render();
        return 0;
    }

    public int removeTask(Task task, int to) {
        board.getTask_contain().remove(task);
        board.getContains().get(to).removeTask(task);
        render();
        return 0;
    }
    

   

    @Override
    public void stateChanged(ChangeEvent e) {
        render();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        BoardBarPanel BarPanel = (BoardBarPanel)panelbar;
        if (source.equals(BarPanel.reverseButton)){
            System.out.println("");
            parent.setVisible(true);
            ((JFrame)this.getParent().getParent().getParent().getParent()).dispose();
            
        } else if (source.equals(BarPanel.refreshButton)) {
            render();
            
        } else if (source.equals(BarPanel.eyeButton)) {
            
            
        } else if (source.equals(BarPanel.SwapButton)) {
            JPopupMenu popup = ((BoardBarPanel)panelbar).priorityBox();
            popup.show(BarPanel.SwapButton, 0, BarPanel.SwapButton.getHeight());
        } else if (source.equals(BarPanel.MagnifierButton)) {
            JPopupMenu popup = ((BoardBarPanel)panelbar).SortBox();
            popup.show(BarPanel.SwapButton, 0, BarPanel.SwapButton.getHeight());
            
        } else if (source.equals(BarPanel.prepleButton)) {
            
        } else if (source.equals(BarPanel.settinButton)) {
            new TagManager(this.board);
            
        } else if (source.equals(BarPanel.burgerButton)) {
            
        }
    }
    

}
