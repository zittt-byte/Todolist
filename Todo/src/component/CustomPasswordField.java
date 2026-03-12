package component;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import resources.Etc;
/**
 *
 * @author User
 */
public class CustomPasswordField extends JPasswordField {

    
    public CustomPasswordField(String name,ImageIcon icon){
        putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, name);
        putClientProperty(FlatClientProperties.STYLE, CustomTextField.TEXTFIELDPROPERTY);
        putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, Etc.resizeImageIcon(icon,20,20));
        setMinimumSize(CustomTextField.FIXEDSIZE);
        setPreferredSize(CustomTextField.FIXEDSIZE);
        setMaximumSize(CustomTextField.FIXEDSIZE);
    }

    
}