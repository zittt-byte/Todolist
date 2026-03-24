package resources;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.Box;
import javax.swing.ImageIcon;

/**
 *
 * @Kanin
 */
public class Etc {
    // For create filler
    public static Box.Filler boxFiller(int width,int height) {
        Dimension minSize = new Dimension(width, height);
        Dimension prefSize = new Dimension(width, height);
        Dimension maxSize = new Dimension(Short.MAX_VALUE, height);
        return new Box.Filler(minSize, prefSize, maxSize);
    }
    
    
    // For resize icon
    public static ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

}
