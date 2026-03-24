/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import javax.swing.Box;
import javax.swing.JButton;

/**
 *
 * @Kanin
 */
public class ComButton extends JButton{
    final String TextFieldProperty = "arc:20;focusWidth:0;innerFocusWidth:0;";

    public ComButton(String text,Color bg,Color fg){
        setText(text);
        setBackground(bg);
        setForeground(fg);
        putClientProperty(FlatClientProperties.STYLE, TextFieldProperty);
        setMinimumSize(ComTextField.FIXEDSIZE);
        setPreferredSize(ComTextField.FIXEDSIZE);
        setMaximumSize(ComTextField.FIXEDSIZE);
        setAlignmentX(Box.CENTER_ALIGNMENT); 
    }
    
}
