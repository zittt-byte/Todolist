package Todolist.Board;

import Todolist.PersonManger_src.Person;
import Todolist.Priority_Manage.CusColor;
import Todolist.Priority_Manage.Priority;
import Todolist.Tag_Manage.Tag;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.time.*;
import javax.swing.event.*;


/**
 *
 * @Kanin
 */
public class Board implements ActionListener {
    private String name,desc,icon;
    private final String uuid;
    private CusColor Banner; 
    private final LocalDate CreatedAt;
    private ArrayList<Column> contains = new ArrayList<>(Arrays.asList(new Column("●Not Started","Gray",this),new Column("●In Progress","Blue",this),new Column("●Finished","Green",this)));
    private ArrayList<Priority> priority = new ArrayList<>(Arrays.asList(new Priority("Low",CusColor.GREEN,0),new Priority("Medium",CusColor.YELLOW,1),new Priority("High",CusColor.RED,2),new Priority("Urgent",CusColor.PURPLE,999)));
    private ArrayList<Task> Task_contain = new ArrayList<>();
    private ArrayList<Tag> Tag_contain = new ArrayList<>();
    private ArrayList<Person> Person_contain = new ArrayList<>();
    private final EventListenerList listenerList = new EventListenerList();



    public Board(String name, String desc, String icon, CusColor Banner) {
        this.name = name;
        this.desc = desc;
        this.icon = icon;
        this.Banner = Banner;
        this.CreatedAt = LocalDate.now();
        this.uuid = UUID.randomUUID().toString();
    }

    public void addChangeListener(ChangeListener l) {
        listenerList.add(ChangeListener.class, l);
    }

    
    public void addTask(Task task) {
        Task_contain.add(task);
        fireStateChanged();
        System.out.println(Task_contain);
    }

    public void removeTask(Task task) {
        Task_contain.remove(task);
        fireStateChanged();
    }
    
    
    
    public void updateTask(String uuid,int to) {
        for (Task task : Task_contain) {
            if (task.getUuid().equals(uuid)) {
                task.setStatus(to);
            }
        
        }
        fireStateChanged();
    }
    
    public Board(String name, String desc, String icon, String Banner) {
        this(name,desc,icon,CusColor.colorFromString(Banner));
    }
    
    public void addtaskmdi(Column column) {
        TaskInternalFrame a = new TaskInternalFrame(this,column);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public CusColor getBanner() {
        return Banner;
    }

    public void setBanner(CusColor Banner) {
        this.Banner = Banner;
    }
    
    public LocalDate getCreatedAt() {
        return CreatedAt;
    }

    public String getUuid() {
        return uuid;
    }

    public ArrayList<Column> getContains() {
        return contains;
    }

    public void setContains(ArrayList<Column> contains) {
        this.contains = contains;
    }

    public ArrayList<Task> getTask_contain() {
        return Task_contain;
    }

    public void setTask_contain(ArrayList<Task> Task_contain) {
        this.Task_contain = Task_contain;
    }

    public ArrayList<Priority> getPriority() {
        return priority;
    }

    public ArrayList<Tag> getTag_contain() {
        return Tag_contain;
    }

    public void setTag_contain(ArrayList<Tag> Tag_contain) {
        this.Tag_contain = Tag_contain;
    }
    
    public void addTag(Tag tag) {
        Tag_contain.add(tag);
    }
    
    public void removeTag(int index) {
        Tag_contain.remove(index);
    }
    
    public void removeTag(Tag tag) {
        Tag_contain.remove(tag);
    }
    
    public void modifyTag(int index,String name,CusColor color) {
        Tag_contain.get(index).setName(name);
        Tag_contain.get(index).setColor(color);
    }
    
    
    protected void fireStateChanged() {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ChangeListener.class) {
                ((ChangeListener) listeners[i + 1]).stateChanged(new ChangeEvent(this));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    
}
