/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.formdev.flatlaf.FlatClientProperties;
import java.awt.*;
import javax.swing.*;
import resources.Etc;

/**
 *
 * @author User
 */
public class CustomTextField extends JTextField {
    public static final Dimension fixedSize = new Dimension(450, 50);
    public static final String TextFieldProperty = "arc:20;focusWidth:0;innerFocusWidth:0;";
    
    public CustomTextField(String name,ImageIcon icon){
        putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, name);
        putClientProperty(FlatClientProperties.STYLE, TextFieldProperty);
        putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, Etc.resizeImageIcon(icon,20,20));
        setMinimumSize(fixedSize);
        setPreferredSize(fixedSize);
        setMaximumSize(fixedSize);
    }

    
}
