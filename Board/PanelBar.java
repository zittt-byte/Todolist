/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Todolist.Board;

import javax.swing.*;
import java.awt.*;
import com.formdev.flatlaf.FlatLightLaf;

public class PanelBar {

    public static void main(String[] args) {
        FlatLightLaf.setup();

        JFrame frame = new JFrame("Panel");
        JPanel toolbar = new JPanel(new BorderLayout());
        JButton reverseButton = new JButton("↩");
        JButton refreshButton = new JButton("↻");
        JButton eyeButton = new JButton("👁");
        JButton SwapButton = new JButton("🔀");
        JButton MagnifierButton = new JButton("🔍");
        JButton prepleButton = new JButton("👤");
        JButton settinButton = new JButton("⚙");
        JButton burgerButton = new JButton("☰");

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT));
        left.add(reverseButton);
        left.add(refreshButton);
        left.add(eyeButton);

        JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER));
        center.add(SwapButton);
        center.add(MagnifierButton);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        right.add(prepleButton);
        right.add(settinButton);
        right.add(burgerButton);

        toolbar.add(left, BorderLayout.WEST);
        toolbar.add(center, BorderLayout.CENTER);
        toolbar.add(right, BorderLayout.EAST);

        frame.add(toolbar, BorderLayout.NORTH);

        frame.setSize(800, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
