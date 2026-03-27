package Todolist.Board;


import Todolist.PersonManger_src.Person;
import Todolist.Priority_Manage.CusColor;
import Todolist.Priority_Manage.Priority;
import Todolist.Tag_Manage.Tag;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author User
 */
public class Task extends JPanel {
    private int status;
    private ArrayList<Tag> Tag;
    private String title,desc,icon;
    private final String uuid = UUID.randomUUID().toString();
    private Priority priority;
    private Person assignee;
    private final LocalDateTime CreatedAt = LocalDateTime.now();
    private LocalDateTime Deadline;
    
    public JLabel title_label,icon_label;
    public JPanel wrapper;
    private JLayeredPane layeredPane;
    
    
    

    
    
    public Task(String title, String desc, String icon, Priority priority, int status, Person assignee, ArrayList<Tag> Tag, LocalDateTime Deadline) {
        this.title    = title;
        this.desc     = desc;
        this.icon     = icon;
        this.priority = priority;
        this.assignee = assignee;
        this.status   = status;
        this.Tag      = Tag;
        this.Deadline = Deadline;
 
        DragSource ds = DragSource.getDefaultDragSource();
 
        wrapper = new JPanel() {
            private boolean hovered = false;
 
            {
                addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent e) {
                        hovered = true;
                        repaint();
                    }
 
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent e) {
                        java.awt.Component c = e.getComponent();
                        java.awt.Point p = e.getPoint();
                        if (!c.contains(p)) {
                            hovered = false;
                            repaint();
                        }
                    }
                });
            }
        };
 
        wrapper.setLayout(new GridBagLayout());
        wrapper.setOpaque(false);
 
        GridBagConstraints gbc = new GridBagConstraints();
 
        JPanel tagcollection = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 2));
        for (Tag tag : Tag) {
            tagcollection.add(tag);
            tag.setBorder(null);
        }
 
        JLabel emojiLabel = new JLabel(icon);
        emojiLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
 
        JLabel titleLabel = new JLabel("<html>" + title + "</html>");
        titleLabel.setFont(new Font("Georgia", Font.BOLD, 24));
        titleLabel.setForeground(new Color(30, 30, 30));
 
        gbc.gridx     = 0;
        gbc.gridy     = 0;
        gbc.gridwidth = 1;
        gbc.anchor    = GridBagConstraints.WEST;
        gbc.fill      = GridBagConstraints.NONE;
        gbc.weightx   = 0;
        gbc.insets    = new Insets(18, 20, 4, 6);
        wrapper.add(emojiLabel, gbc);
 
        gbc.gridx     = 1;
        gbc.gridy     = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor    = GridBagConstraints.WEST;
        gbc.fill      = GridBagConstraints.HORIZONTAL;
        gbc.weightx   = 1.0;
        gbc.insets    = new Insets(18, 0, 4, 20);
        wrapper.add(titleLabel, gbc);
 
        Priority difficultyBadge = priority.copy();
 
        JPanel timerBadge = this.timepanel();
 
        JPanel badgeRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        badgeRow.setOpaque(false);
        badgeRow.add(difficultyBadge);
        badgeRow.add(tagcollection);
 
        gbc.gridx     = 0;
        gbc.gridy     = 1;
        gbc.gridwidth = 1;
        gbc.anchor    = GridBagConstraints.WEST;
        gbc.fill      = GridBagConstraints.NONE;
        gbc.weightx   = 0;
        gbc.insets    = new Insets(4, 20, 18, 6);
        wrapper.add(badgeRow, gbc);
 
        gbc.gridx   = 1;
        gbc.gridy   = 1;
        gbc.weightx = 1.0;
        gbc.fill    = GridBagConstraints.HORIZONTAL;
        gbc.insets  = new Insets(0, 0, 0, 0);
        wrapper.add(Box.createHorizontalGlue(), gbc);
 
        gbc.gridx   = 2;
        gbc.gridy   = 1;
        gbc.weightx = 0;
        gbc.fill    = GridBagConstraints.NONE;
        gbc.anchor  = GridBagConstraints.EAST;
        gbc.insets  = new Insets(4, 6, 18, 20);
        


        Duration timeLeft = Duration.between(LocalDateTime.now(), this.Deadline);
        System.out.println(timeLeft.toMinutes());
        if (!timeLeft.isNegative() && timeLeft.toMinutes() <= 30){
        wrapper.add(timerBadge, gbc);
        }
        wrapper.putClientProperty(FlatClientProperties.STYLE, "border: 4,4,4,4,#d1d1d1,1,12");
 
        putClientProperty(FlatClientProperties.STYLE, "border: 4,4,4,4,#000000,1,12");
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        wrapper.setBackground(Color.white);
 
        layeredPane = new JLayeredPane() {
            @Override
            public void doLayout() {
                Dimension size = getSize();
                wrapper.setBounds(0, 0, size.width, size.height);
                for (Component c : getComponentsInLayer(JLayeredPane.PALETTE_LAYER)) {
                    c.setBounds(0, 0, size.width, size.height);
                }
            }
 
            @Override
            public Dimension getPreferredSize() {
                return wrapper.getPreferredSize();
            }
        };
 
        layeredPane.add(wrapper, JLayeredPane.DEFAULT_LAYER);
 
        add(layeredPane);
 
        ds.createDefaultDragGestureRecognizer(wrapper, DnDConstants.ACTION_MOVE, dge -> {
            Transferable t = new StringSelection(uuid);
            dge.startDrag(DragSource.DefaultMoveDrop, t);
        });
    }
    
    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }
 
    public JPanel getWrapper() {
        return wrapper;
    }
    
    
    public JPanel timepanel() {
        JLabel text = new JLabel("●Time left 00:00:00");
        JPanel bg = new JPanel();JPanel wrapper = new JPanel();
        text.setForeground(CusColor.hexToColorObject(CusColor.RED.inTextColor));
        text.setFont(new Font("Inter",Font.BOLD,12));
        bg.setBackground(CusColor.hexToColorObject(CusColor.RED.labelColor));
        bg.add(text);
        putClientProperty(FlatClientProperties.STYLE, "arc:8;");
        bg.putClientProperty(FlatClientProperties.STYLE, "arc:8;");
        add(bg);
        wrapper.add(bg);
        wrapper.setBackground(Color.white);
        return wrapper;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<Tag> getTag() {
        return Tag;
    }

    public void setTag(ArrayList<Tag> Tag) {
        this.Tag = Tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDateTime getDeadline() {
        return Deadline;
    }

    public void setDeadline(LocalDateTime Deadline) {
        this.Deadline = Deadline;
    }
    
    public String getUuid() {
        return uuid;
    }

    public LocalDateTime getCreatedAt() {
        return CreatedAt;
    }
    
    
    @Override
    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, getPreferredSize().height);
    }
     
    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        return new Dimension(d.width, d.height);
    }

    public Person getAssignee() {
        return assignee;
    }

    public void setAssignee(Person assignee) {
        this.assignee = assignee;
    }
    
    
    
}

