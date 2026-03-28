package Todolist.Board;



import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Clock extends JPanel implements Runnable {
    private JLabel timeLabel = new JLabel("");
    private JLabel dateLabel = new JLabel("");
    
    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM - dd - yyyy");

    public Clock() {
        setLayout(new GridLayout(2, 1));
        timeLabel.setFont(new Font("Inria Serif", Font.PLAIN, 36));
        dateLabel.setFont(new Font("Inria Serif", Font.PLAIN, 16));
        dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(timeLabel);
        add(dateLabel);
       setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        new Thread(this).start();
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 100);
    }

    @Override
    public void run() {
        try {
            while (true) {
                LocalDateTime now = LocalDateTime.now();
                String time = now.format(timeFormat);
                String date = now.format(dateFormat);
                timeLabel.setText(time);
                dateLabel.setText(date);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Vertical Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Clock clockPanel = new Clock();
        clockPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        frame.add(clockPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}