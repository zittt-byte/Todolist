package Todolist.Tag_Manage;



import Todolist.Priority_Manage.CusColor;
import com.formdev.flatlaf.*;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @Kanin
 */
public class Tag extends JPanel implements java.io.Serializable{
    private String name;
    private CusColor color;

    public Tag(String name, CusColor color) {
        this.name = name;
        this.color = color;
        JLabel text = new JLabel(this.name);
        JPanel bg = new JPanel();
        text.setForeground(CusColor.hexToColorObject(this.color.textColor));
        text.setFont(new Font("Inter",Font.BOLD,12));
        bg.setBackground(CusColor.hexToColorObject(this.color.labelColor));
        bg.add(text);
        bg.putClientProperty(FlatClientProperties.STYLE, "border:0,2,0,2,,,999;");
        add(bg);
        setBackground(Color.WHITE);
    }

    public Tag(String name, String color) {
        this(name,CusColor.colorFromString(color));
    }
    
    public Tag copy(){
        return new Tag(this.name,this.color);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CusColor getColor() {
        return color;
    }

    public void setColor(CusColor color) {
        this.color = color;
    }
        
    
}
