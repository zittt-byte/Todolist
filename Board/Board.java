/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Board;

import Todolist.Priority_Manage.CusColor;
import java.util.*;
import javax.swing.ImageIcon;
import java.time.LocalDate;


/**
 *
 * @Kanin
 */
public class Board {
    private String name,desc,icon;
    private CusColor Banner; 
    private final LocalDate CreatedAt = LocalDate.now();
    public ArrayList<Column> contains = new ArrayList<>(Arrays.asList(new Column("●Not Started","Grey"),new Column("●In Progress","Blue"),new Column("●Finished","Green")));

    public Board(String name, String desc, String icon, CusColor Banner) {
        this.name = name;
        this.desc = desc;
        this.icon = icon;
        this.Banner = Banner;
    }
    
    public Board(String name, String desc, String icon, String Banner) {
        this.name = name;
        this.desc = desc;
        this.icon = icon;
        this.Banner = CusColor.colorFromString(Banner);
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
    
}
