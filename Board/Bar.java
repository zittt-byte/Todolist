/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Todolist.Board;
import Todolist.PersonManger_src.Person;
import Todolist.Priority_Manage.Priority;
import Todolist.Tag_Manage.Tag;
import java.util.ArrayList;
import javax.swing.JPanel;
/**
 *
 * @author User
 */
public interface Bar {
    public abstract ArrayList<Tag> getTag();
    public abstract ArrayList<Person> getPerson();
    public abstract ArrayList<Priority> getPriority();
    public abstract String getSort();
}
