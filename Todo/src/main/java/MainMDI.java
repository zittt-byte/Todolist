
import javax.swing.*;
import java.awt.*;

public class MainMDI {
    public JDesktopPane desktopPane;
    public JFrame frame;
    public  MainMDI () {
        frame = new JFrame("Main MDi");
        desktopPane = new JDesktopPane();
        CardFrame LoginUI = new CardFrame();

        frame.setSize(700,700);
        LoginUI.setSize(500,500);
        frame.add(desktopPane);

        desktopPane.setBackground(Color.gray);
        desktopPane.setSize(900,900);
        desktopPane.add(LoginUI);
        desktopPane.setVisible(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        MainMDI esan = new MainMDI();
    }

}
