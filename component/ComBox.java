package Todolist.component;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import javax.swing.*;
import static resources.Etc.boxFiller;


public abstract class ComBox extends JPanel {
    public JPanel pane,content;
    private final JLabel title;
    

    public ComBox(String titleText) {
        setLayout(new BorderLayout());
        
        pane = new JPanel();
        pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));
        pane.add(boxFiller(10,0));
        title = new JLabel(titleText);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        pane.setPreferredSize(new Dimension(280, 80));
        title.setFont(new Font("Inter", Font.PLAIN, 16));
        pane.putClientProperty(FlatClientProperties.STYLE, "background:#ffffff;border: 6,6,6,6,#d1d1d1,1,16;");
        putClientProperty(FlatClientProperties.STYLE, ";background:#ffffff");
        pane.add(title);
        add(pane, BorderLayout.CENTER);
    }
    public static void main(String[] args) {
        FlatLightLaf.setup();
        JFrame fr = new JFrame();
        fr.setSize(700,700);
        fr.setLayout(new FlowLayout());
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
    }
    
    public void modifyContent(JPanel panel){
        content = panel;
        revalidate();
        repaint();
    }
    
    @Override
    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, getPreferredSize().height);
    }
     
    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        return new Dimension(d.width, d.height);
    }
    
}

