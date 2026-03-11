import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import resources.Etc;

/**
 *
 * @author User
 */
public class CustomPasswordField extends JPasswordField {

    
    public CustomPasswordField(String name,ImageIcon icon){
        putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, name);
        putClientProperty(FlatClientProperties.STYLE, CustomTextField.TextFieldProperty);
        putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, Etc.resizeImageIcon(icon,20,20));
        setMinimumSize(CustomTextField.fixedSize);
        setPreferredSize(CustomTextField.fixedSize);
        setMaximumSize(CustomTextField.fixedSize);
    }

    
}