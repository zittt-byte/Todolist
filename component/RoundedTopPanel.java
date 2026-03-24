/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;

/**
 *
 * @author User
 */
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class RoundedTopPanel extends JPanel {

    private final int arc;

    public RoundedTopPanel(int arc) {
        this.arc = arc;
        setOpaque(false);
    }

    private Shape buildShape() {
        int w = getWidth() - 1;
        int h = getHeight();
        int r = arc - 6;

        Path2D path = new Path2D.Float();

        // Start at bottom-left (sharp corner)
        path.moveTo(0, h);

        // Left edge up to top-left arc start
        path.lineTo(0, r);

        // Top-left: true circular arc (Arc2D gives a real quarter-circle)
        // Arc2D(x, y, width, height, start, extent, type)
        // The bounding box of this arc is the square [0,0, 2r,2r]
        path.append(new Arc2D.Float(0, 0, r * 2, r * 2, 180, -90, Arc2D.OPEN), true);

        // Top edge
        path.lineTo(w - r, 0);

        // Top-right: arc bounding box starts at [w-2r, 0]
        path.append(new Arc2D.Float(w - r * 2, 0, r * 2, r * 2, 90, -90, Arc2D.OPEN), true);

        // Right edge down to bottom-right (sharp corner)
        path.lineTo(w, h);

        // Bottom edge back to start (sharp corners)
        path.closePath();

        return path;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                            RenderingHints.VALUE_RENDER_QUALITY);

        Shape shape = buildShape();

        // Fill
        g2.setColor(getBackground());
        g2.fill(shape);

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Suppressed — border is drawn inside paintComponent to stay inside the clip
    }
}
