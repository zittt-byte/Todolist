package component;



import com.formdev.flatlaf.FlatClientProperties;
import java.awt.*;
import javax.swing.*;
import resources.Etc;

public class ComTextField extends JTextField {
    public static final Dimension FIXEDSIZE = new Dimension(450, 50);
    public static final String TEXTFIELDPROPERTY = "arc:20;focusWidth:0;innerFocusWidth:0;";
    
    public ComTextField(String name,ImageIcon icon){
        putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, name);
        putClientProperty(FlatClientProperties.STYLE, TEXTFIELDPROPERTY);
        putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, Etc.resizeImageIcon(icon,20,20));
        setMinimumSize(FIXEDSIZE);
        setPreferredSize(FIXEDSIZE);
        setMaximumSize(FIXEDSIZE);
    }

    
}
