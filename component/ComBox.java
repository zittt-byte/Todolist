package component;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import javax.swing.*;


public class ComBox extends JPanel {
    private JPanel pane,content;
    private JLabel title;
    

    public ComBox(String titleText,JPanel panel) {
        pane = new JPanel();
        pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));
        title = new JLabel(titleText);
        pane.setPreferredSize(new Dimension(280, 80));
        title.setFont(new Font("Inter", Font.PLAIN, 16));
        pane.putClientProperty(FlatClientProperties.STYLE, "background:#ffffff;border: 6,6,6,6,#d1d1d1,1,16;");
        putClientProperty(FlatClientProperties.STYLE, ";background:#ffffff");
        pane.add(title);
        pane.add(panel);
        add(pane);
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

