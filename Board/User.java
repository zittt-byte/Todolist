package Todolist.Board;


import Todolist.Priority_Manage.CusColor;
import com.formdev.flatlaf.*;
import component.RoundedTopPanel;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.*;


/**
 *
 * @Kanin
 */
public class User extends JFrame {
    public ArrayList<Board> Contains;
    public JPanel panel;
    
    public User(){
        Contains = new ArrayList<Board>();
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER,10, 10));
        Board a = new Board("OOP 2026OOP 2026OOP 2026OOP 2026OOP 2026OOP 2026OOP 2026OOP 2026OOP 2026","Dr. Taravichet","😬","Green");
        Board b = new Board("DS&A","Dr. Taravichet","🤢","BLUE");
        Board c = new Board("Stat and Prob","Dr. Sooksan","🤢","Red");

        panel.setPreferredSize(new Dimension(700,700));
        setSize(700,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        this.add(panel);
        
        Add(a);Add(b);Add(c);
        setVisible(true);
        
    }
    
    public JPanel BoardCard(Board board) {
        JPanel pane = new JPanel(new BorderLayout()) {
            private boolean hovered = false;

            {
                addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent e) {
                        hovered = true;
                        repaint();
                    }

                    @Override
                    public void mouseExited(java.awt.event.MouseEvent e) {
                        java.awt.Component c = e.getComponent();
                        java.awt.Point p = e.getPoint();
                        if (!c.contains(p)) {
                            hovered = false;
                            repaint();
                        }
                    }
                });
            }
        };

        RoundedTopPanel banner = new RoundedTopPanel(16);
        JLabel title = new JLabel("<html>&nbsp;" + board.getName() + "</html>");
        pane.setPreferredSize(new Dimension(280, 160));

        banner.setPreferredSize(new Dimension(0, 50));
        banner.setBackground(CusColor.hexToColorObject(board.getBanner().labelColor));
        banner.setLayout(new BorderLayout());

        JLabel emojiLabel = new JLabel(board.getIcon());
        emojiLabel.setFont(new Font("SeNoto Color Emoji", Font.PLAIN, 28));
        emojiLabel.setBounds(20, 30, 40, 40);

        JButton closeButton = new JButton("X");
        closeButton.setFont(new Font("Inter", Font.BOLD, 24));
        closeButton.setForeground(Color.RED);
        closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeButton.setVisible(false);
        closeButton.setPreferredSize(new Dimension(28, 28));

        closeButton.addActionListener(e -> {
            Container parent = pane.getParent();
            if (parent != null) {
                Remove(board);
                Contains.remove(board);
                System.out.println(Contains);
            }
        });

        JPanel closeBtnWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT, 4, 4));
        closeBtnWrapper.setOpaque(false);
        closeBtnWrapper.add(closeButton);
        banner.add(closeBtnWrapper, BorderLayout.EAST);

        MouseAdapter hoverListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closeButton.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                java.awt.Point p = SwingUtilities.convertPoint(
                    e.getComponent(), e.getPoint(), pane
                );
                if (!pane.contains(p)) {
                    closeButton.setVisible(false);
                }
            
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isDescendingFrom(e.getComponent(), closeButton)) return;
                createBoardView(board);


            }

            @Override
            public void mousePressed(MouseEvent e) {
                pane.setBackground(CusColor.hexToColorObject("#f3f4f6")); 
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pane.setBackground(Color.WHITE);
            }
        };

        pane.addMouseListener(hoverListener);
        banner.addMouseListener(hoverListener);

        pane.add(banner, BorderLayout.NORTH);
        title.setFont(new Font("Inter", Font.PLAIN, 24));
        pane.putClientProperty(FlatClientProperties.STYLE, "arc:16;background:#ffffff;border: 0,0,0,0;");

        DateTimeFormatter customFormatter;
        if (board.getCreatedAt().getYear() - LocalDate.now().getYear() == 0) {
            customFormatter = DateTimeFormatter.ofPattern("dd MMM");
        } else {
            customFormatter = DateTimeFormatter.ofPattern("dd MMM yy");
        }
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(280, 120));

        JLabel timestamp = new JLabel(board.getCreatedAt().format(customFormatter));
        timestamp.setFont(new Font("Inter", Font.BOLD, 12));
        timestamp.setForeground(CusColor.hexToColorObject(CusColor.GRAY.textColor));
        timestamp.addMouseListener(hoverListener);
        
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);
        titlePanel.add(Box.createHorizontalStrut(8), BorderLayout.WEST);
        titlePanel.add(title, BorderLayout.CENTER);
        
        JPanel timestampPanel = new JPanel(new BorderLayout());
        timestampPanel.setOpaque(false);
        timestampPanel.add(Box.createHorizontalStrut(16), BorderLayout.WEST);
        timestampPanel.add(timestamp, BorderLayout.CENTER);
        
        titlePanel.addMouseListener(hoverListener);
        
        pane.add(titlePanel);
        pane.add(timestampPanel,BorderLayout.SOUTH);

        pane.setBounds(0, 0, 280, 120);
        layeredPane.add(pane, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(emojiLabel, JLayeredPane.PALETTE_LAYER);
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(layeredPane);
        return wrapper;
    }

    
    public JPanel AddBoardCard(){
        JPanel pane = new JPanel();
        pane.setPreferredSize(new Dimension(280, 120));
        pane.putClientProperty(FlatClientProperties.STYLE, "arc:16;background:#ffffff;border: 0,0,0,0,#fbbf23;");
        JLabel title = new JLabel("+");
        pane.add(title);
            
        
        MouseAdapter hoverListener = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Create Board clicked:");

            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
            } catch (Exception ex) {
                System.err.println("Failed to initialize LaF");
            }

            JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));

            //name 
            JTextField nameField = new JTextField();
            panel.add(new JLabel("Name:"));
            panel.add(nameField);

            //Desc
            JTextField descField = new JTextField();
            panel.add(new JLabel("Description:"));
            panel.add(descField);

            //icon
            JTextField iconField = new JTextField();
            panel.add(new JLabel("Icon:"));
            panel.add(iconField);

            //colers
            String[] colors = {"Red", "Blue", "Green", "Yellow"};
            JComboBox<String> colorCombo = new JComboBox<>(colors);
            panel.add(new JLabel("Select Color:"));
            panel.add(colorCombo);

            int dialog = JOptionPane.showConfirmDialog(null, panel, "input data", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            if (dialog == JOptionPane.OK_OPTION) {
                String boardName = nameField.getText();
                String boardDesc = descField.getText();
                String boardIcon = iconField.getText();
                String boardColor = (String) colorCombo.getSelectedItem();

                if (boardName.trim().isEmpty()) {
                    boardName = "Untitled Board";
                }
                if (boardIcon.trim().isEmpty()) {
                    boardIcon = "📝";
                }

                Board newBoard = new Board(boardName, boardDesc, boardIcon, boardColor);
                Add(newBoard);
            }
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            pane.setBackground(Color.WHITE);
        }
    };
        pane.addMouseListener(hoverListener);
        title.setFont(new Font("Inter",Font.PLAIN,48));
        return pane;
    }

    public void createBoardView(Board board){
        JFrame fr = new JFrame();
        fr.setSize(1200,1000);
        BoardView b = new BoardView(board,this);
        board.addChangeListener(b);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.add(b);
        fr.setVisible(true);
        
        System.out.println("Board clicked: " + board.getUuid());
        setVisible(false);
    }
    
    
    public void Add(Board board){
        Contains.add(board);
        DisplayBoard();
    }
    
    public void Remove(Board board){
        Contains.remove(board);
        DisplayBoard();
    }
    
    
    public void DisplayBoard(){
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
        for (Board board  : Contains) {
            panel.add(BoardCard(board)); 
        }
        panel.add(AddBoardCard());
    }
    
    public static void main(String[] args) {
        FlatLightLaf.setup();
        new User();
    }
    
}