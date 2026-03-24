/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author apimookweerakunwattana
 */
import java.awt.*;
import javax.swing.*;

public class TaskProgressBar {
    public JFrame fr;
    public JPanel toppn,ptask,pur,phi,pmid,plo;
    public JLabel task,ur,hi,mid,lo;
    public JLabel nur,nhi,nmid,nlo;
    public JProgressBar pbur,pbhi,pbmid,pblo;
    public Priority priority;
    public TaskProgressBar(){
        fr = new JFrame();
        toppn = new JPanel(new GridLayout(5,1));ptask = new JPanel(new BorderLayout());pur = new JPanel(new BorderLayout());phi = new JPanel(new BorderLayout());pmid = new JPanel(new BorderLayout());plo = new JPanel(new BorderLayout());
        task = new JLabel("Task Priorities");ur = new JLabel("Urgent/Critical");hi = new JLabel("High Priority");
        mid = new JLabel("Medium Priority");lo = new JLabel("Low Priority");
        
        pbur = createBar(0,priority.getColor());
        pbhi = createBar(0,priority.getColor());
        pbmid = createBar(0,priority.getColor());
        pblo = createBar(0,priority.getColor());
        
        nur = new JLabel("0");nhi = new JLabel("0");nmid = new JLabel("0");nlo = new JLabel("0");
        
        toppn.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        nur.setText(String.valueOf(pbur.getValue()));nhi.setText(String.valueOf(pbhi.getValue()));
        nmid.setText(String.valueOf(pbmid.getValue()));nlo.setText(String.valueOf(pblo.getValue()));
        
        ptask.add(task);
        pur.add(ur,BorderLayout.WEST);pur.add(nur,BorderLayout.EAST);pur.add(pbur,BorderLayout.SOUTH);
        phi.add(hi,BorderLayout.WEST);phi.add(nhi,BorderLayout.EAST);phi.add(pbhi,BorderLayout.SOUTH);
        pmid.add(mid,BorderLayout.WEST);pmid.add(nmid,BorderLayout.EAST);pmid.add(pbmid,BorderLayout.SOUTH);
        plo.add(lo,BorderLayout.WEST);plo.add(nlo,BorderLayout.EAST);plo.add(pblo,BorderLayout.SOUTH);
        
        toppn.add(ptask);
        toppn.add(pur);toppn.add(phi);toppn.add(pmid);toppn.add(plo);
        fr.add(toppn);
        
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.pack();
        fr.setVisible(true);
    }
    private JProgressBar createBar(int value, Color color) {
        JProgressBar bar = new JProgressBar(0, 100);
        bar.setValue(value);

        bar.setUI(new javax.swing.plaf.basic.BasicProgressBarUI());

        bar.setForeground(color);
        bar.setBorderPainted(false);
        bar.setPreferredSize(new Dimension(300, 8));

        return bar;
}
    public static void main(String[] args) {
        new TaskProgressBar();
    }
}
