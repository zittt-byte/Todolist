/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;

import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class ComBoxGroup extends JPanel {
    public ComBoxGroup(){
        setLayout(new FlowLayout());
    }
    public void AddCard(ComBox card){
        add(card);
    }
    
    
}
