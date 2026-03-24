package component;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import resources.Etc;
/**
 *
 * @Kanin
 */
public class ComPasswordField extends JPasswordField {

    
    public ComPasswordField(String name,ImageIcon icon){
        putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, name);
        putClientProperty(FlatClientProperties.STYLE, ComTextField.TEXTFIELDPROPERTY);
        putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, Etc.resizeImageIcon(icon,20,20));
        setMinimumSize(ComTextField.FIXEDSIZE);
        setPreferredSize(ComTextField.FIXEDSIZE);
        setMaximumSize(ComTextField.FIXEDSIZE);
    }

    
}