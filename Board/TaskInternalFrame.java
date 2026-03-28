package Todolist.Board;

import Todolist.PersonManger_src.Person;
import Todolist.Priority_Manage.CusColor;
import Todolist.Priority_Manage.Priority;
import Todolist.Tag_Manage.Tag;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.function.Consumer;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.InternalFrameUI;
import javax.swing.plaf.basic.BasicInternalFrameUI;


// AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | 
// ══════════════════════════════════════════════════════════════
//  This File is AI Generated 100%
// ══════════════════════════════════════════════════════════════
// AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | AI Generated | 



// ══════════════════════════════════════════════════════════════
//  Task Input Internal Frame
// ══════════════════════════════════════════════════════════════
public class TaskInternalFrame extends JInternalFrame {
    

    @Override
    public InternalFrameUI getUI() {
        return super.getUI(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    

    

    // ── Model fields ──────────────────────────────────────────
    private int status;
    private ArrayList<Tag> tags = new ArrayList<>();
    private String title, desc, icon;
    private Priority priority;
    private LocalDateTime deadline;

    // ── UI Components ─────────────────────────────────────────
    private JTextField           titleField;
    private JTextArea            descArea;
    private JTextField           iconField;
    private JComboBox<Priority>  priorityCombo;
    private JComboBox<Column>    statusCombo;
    private JComboBox<Person>    personCombo;
    private JSpinner             deadlineSpinner;
    private JLabel               statusBar;
    private MultiSelectDropdown  tagsDropdown;

    // ── Palette ───────────────────────────────────────────────
    private static final Color BG      = new Color(28, 28, 38);
    private static final Color CARD    = new Color(36, 36, 50);
    private static final Color ACCENT  = new Color(99, 102, 241);
    private static final Color ACCENT2 = new Color(167, 139, 250);
    private static final Color TEXT    = new Color(226, 226, 240);
    private static final Color MUTED   = new Color(120, 120, 150);
    private static final Color SUCCESS = new Color(52, 211, 153);
    private static final Color DANGER  = new Color(248, 113, 113);
    private static final Color BORDER  = new Color(55, 55, 75);
    
    private Task editingTask = null;

    // Predefined tag options as Tag objects — colour already baked in
    private ArrayList<Tag> TAG_OPTIONS;
    public Board board;
    public Column column;

    public TaskInternalFrame(Board board,Column column) {
        super("New Task", false, true, false, false);
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        this.board = board;
        this.column = column;
        TAG_OPTIONS = board.getTag_contain();
        setSize(620, 740);
        buildUI();
    }

    // ──────────────────────────────────────────────────────────
    //  UI Construction
    // ──────────────────────────────────────────────────────────
    private void buildUI() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG);
        root.setBorder(BorderFactory.createEmptyBorder(16, 20, 12, 20));

        root.add(buildHeader(),  BorderLayout.NORTH);
        root.add(buildForm(),    BorderLayout.CENTER);
        root.add(buildFooter(), BorderLayout.SOUTH);

        setContentPane(root);
    }

    private JPanel buildHeader() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(BG);
        p.setBorder(BorderFactory.createEmptyBorder(0, 0, 16, 0));

        JLabel heading = new JLabel("Create Task");
        heading.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
        heading.setForeground(TEXT);

        JLabel sub = new JLabel("Fill in the details below");
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        sub.setForeground(MUTED);

        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setBackground(BG);
        left.add(heading);
        left.add(Box.createVerticalStrut(2));
        left.add(sub);

        JSeparator sep = new JSeparator();
        sep.setForeground(ACCENT);
        sep.setBackground(ACCENT);
        sep.setPreferredSize(new Dimension(0, 2));

        p.add(left, BorderLayout.CENTER);
        JPanel wrap = new JPanel(new BorderLayout());
        wrap.setBackground(BG);
        wrap.add(p, BorderLayout.CENTER);
        wrap.add(sep, BorderLayout.SOUTH);
        return wrap;
    }

    private JScrollPane buildForm() {
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(BG);
        form.setBorder(BorderFactory.createEmptyBorder(12, 0, 8, 0));

        form.add(buildRow("Title",       buildTitleField()));
        form.add(spacer(10));
        form.add(buildRow("Description", buildDescArea()));
        form.add(spacer(10));
        form.add(buildTwoColumn(
            buildRow("Priority", buildPriorityCombo()),
            buildRow("Status",   buildStatusCombo())
        ));
        form.add(spacer(10));
        form.add(buildTwoColumn(
            buildRow("Icon / Emoji", buildIconField()),
            buildRow("Deadline",     buildDeadlineSpinner())
        ));
        form.add(spacer(10));
        form.add(buildRow("Tags", buildTagsDropdown()));
        form.add(spacer(10));
        form.add(buildRow("Assignee", buildPersonCombo()));

        JScrollPane scroll = new JScrollPane(form);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setBackground(BG);
        scroll.getViewport().setBackground(BG);
        scroll.getVerticalScrollBar().setUnitIncrement(12);
        return scroll;
    }
    
    public void loadTask(Task task) {
        this.editingTask = task;

        // ── Header ────────────────────────────────────────────
        setTitle("Edit Task");

        // Swap the heading label text by walking the content-pane tree.
        // buildHeader() puts the heading JLabel inside a BoxLayout panel
        // that is added to NORTH of root, which is itself wrapped once more.
        // It is simpler and safer to just search by type + font size.
        swapHeadingLabel("Edit Task", "Modify the details below");

        // ── Fields ────────────────────────────────────────────
        titleField.setText(task.getTitle());
        descArea.setText(task.getDesc());
        iconField.setText(task.getIcon() != null ? task.getIcon() : "");

        // Priority — match by object identity first, fall back to name
        for (int i = 0; i < priorityCombo.getItemCount(); i++) {
            Priority p = priorityCombo.getItemAt(i);
            if (p == task.getPriority() ||
                    (p != null && p.getName().equals(task.getPriority().getName()))) {
                priorityCombo.setSelectedIndex(i);
                break;
            }
        }

        // Status / Column
        int colIndex = task.getStatus();
        if (colIndex >= 0 && colIndex < statusCombo.getItemCount()) {
            statusCombo.setSelectedIndex(colIndex);
        }

        // Deadline — convert LocalDateTime → java.util.Date for the spinner
        java.util.Date deadlineDate = java.util.Date.from(
            task.getDeadline()
                .atZone(java.time.ZoneId.systemDefault())
                .toInstant()
        );
        deadlineSpinner.setValue(deadlineDate);

        // Tags
        tags.clear();
        tags.addAll(task.getTag());
        tagsDropdown.setSelected(new ArrayList<>(task.getTag()));

        // Assignee — null → index 0 ("— Unassigned —")
        Person assignee = task.getAssignee();
        if (assignee == null) {
            personCombo.setSelectedIndex(0);
        } else {
            for (int i = 1; i < personCombo.getItemCount(); i++) {
                Person p = personCombo.getItemAt(i);
                if (p == assignee || (p != null && p.getName().equals(assignee.getName()))) {
                    personCombo.setSelectedIndex(i);
                    break;
                }
            }
        }
    }
    
    private void swapHeadingLabel(String heading, String sub) {
        findAndReplaceLabel((JComponent) getContentPane(), heading, sub);
    }

    private boolean foundHeading = false;

    private void findAndReplaceLabel(JComponent root, String heading, String sub) {
        for (Component c : root.getComponents()) {
            if (c instanceof JLabel lbl) {
                Font f = lbl.getFont();
                if (!foundHeading && f != null && f.getSize() >= 20) {
                    lbl.setText(heading);
                    foundHeading = true;
                } else if (foundHeading && f != null && f.getSize() <= 14
                        && lbl.getForeground().equals(MUTED)) {
                    lbl.setText(sub);
                    return; // both labels swapped
                }
            }
            if (c instanceof JComponent jc) {
                findAndReplaceLabel(jc, heading, sub);
            }
        }
    }


    // ── Field Builders ────────────────────────────────────────
    private JTextField buildTitleField() {
        titleField = styledTextField("e.g. Fix login bug");
        titleField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "e.g. Fix login bug");
        return titleField;
    }

    private JScrollPane buildDescArea() {
        descArea = new JTextArea(4, 20);
        descArea.setBackground(CARD);
        descArea.setForeground(TEXT);
        descArea.setCaretColor(ACCENT2);
        descArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        JScrollPane sp = new JScrollPane(descArea);
        sp.setBorder(BorderFactory.createLineBorder(BORDER, 1, true));
        sp.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));
        return sp;
    }
    
    

    private JComboBox<Priority> buildPriorityCombo() {
        priorityCombo = new JComboBox<>(board.getPriority().toArray(new Priority[0]));
        priorityCombo.setBackground(CARD);
        priorityCombo.setForeground(TEXT);
        priorityCombo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        priorityCombo.setRenderer(new PriorityRenderer());
        return priorityCombo;
    }

    private JComboBox<Column> buildStatusCombo() {
        statusCombo = new JComboBox<>(this.board.getContains().toArray(new Column[0]));
        statusCombo.setBackground(CARD);
        statusCombo.setForeground(TEXT);
        statusCombo.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        statusCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {

                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value instanceof Column col) {
                    setText(col.title_label != null ? col.title_label.getText() : col.title);
                }
                return this;
            }
        });
        statusCombo.setSelectedIndex(this.board.getContains().indexOf(column));
        return statusCombo;
    }

    private JTextField buildIconField() {
        iconField = styledTextField("icon");
        iconField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Icon");
        iconField.setFont(new FontUIResource("Segoe UI Emoji", Font.PLAIN, 12));
        return iconField;
    }

    private JSpinner buildDeadlineSpinner() {
        SpinnerDateModel model = new SpinnerDateModel();
        deadlineSpinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(deadlineSpinner, "yyyy-MM-dd  HH:mm");
        deadlineSpinner.setEditor(editor);
        deadlineSpinner.setBackground(CARD);
        deadlineSpinner.setForeground(TEXT);
        deadlineSpinner.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        editor.getTextField().setBackground(CARD);
        editor.getTextField().setForeground(TEXT);
        editor.getTextField().setCaretColor(ACCENT2);
        return deadlineSpinner;
    }

    /**
     * Builds the MultiSelectDropdown backed directly by Tag objects.
     * The callback receives an ArrayList<Tag> so no string-to-Tag
     * conversion is ever needed.
     */
    private MultiSelectDropdown buildTagsDropdown() {
        tagsDropdown = new MultiSelectDropdown(TAG_OPTIONS);

        tagsDropdown.setOnSelectionChanged(selectedTags -> {
            tags.clear();
            tags.addAll(selectedTags);   // selectedTags is already ArrayList<Tag>
        });

        tagsDropdown.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        tagsDropdown.setAlignmentX(LEFT_ALIGNMENT);
        return tagsDropdown;
    }

    /**
     * Builds the Assignee dropdown, populated from Board.getPerson_contain().
     * A sentinel "— Unassigned —" entry is always prepended so the field is optional.
     */
    private JComboBox<Person> buildPersonCombo() {
        // Sentinel: null signals "no assignee"
        personCombo = new JComboBox<>();
        personCombo.addItem(null);                                    // "— Unassigned —"
        for (Person p : board.getPerson_contain()) personCombo.addItem(p);

        personCombo.setBackground(CARD);
        personCombo.setForeground(TEXT);
        personCombo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        personCombo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        personCombo.setRenderer(new PersonRenderer());
        return personCombo;
    }

    // ── Footer ────────────────────────────────────────────────
    private JPanel buildFooter() {
        JPanel p = new JPanel(new BorderLayout(10, 0));
        p.setBackground(BG);
        p.setBorder(BorderFactory.createEmptyBorder(12, 0, 0, 0));

        statusBar = new JLabel(" ");
        statusBar.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        statusBar.setForeground(MUTED);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        buttons.setBackground(BG);

        JButton clearBtn = ghostButton("Clear");
        clearBtn.addActionListener(e -> clearForm());

        JButton saveBtn = accentButton("  Save Task  ", ACCENT);
        saveBtn.addActionListener(e -> saveForm());

        buttons.add(clearBtn);
        buttons.add(saveBtn);

        p.add(statusBar, BorderLayout.WEST);
        p.add(buttons, BorderLayout.EAST);
        return p;
    }

    // ──────────────────────────────────────────────────────────
    //  Logic
    // ──────────────────────────────────────────────────────────
    private void saveForm() {
        if (titleField.getText().trim().isEmpty()) {
            flash(statusBar, "⚠  Title is required.", DANGER);
            titleField.requestFocus();
            return;
        }

        title    = titleField.getText().trim();
        desc     = descArea.getText().trim();
        icon     = iconField.getText().trim();
        priority = (Priority) priorityCombo.getSelectedItem();
        status   = statusCombo.getSelectedIndex();

        java.util.Date d = (java.util.Date) deadlineSpinner.getValue();
        deadline = d.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();

        String summary = String.format(
            "Title: %s | Priority: %s | Status: %d | Deadline: %s | Tags: %d",
            title, priority, status,
            deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            tags.size()
        );

        flash(statusBar, "✔  Saved — " + summary, SUCCESS);
        System.out.println("=== Task Saved ===");
        System.out.println("Title    : " + title);
        System.out.println("Desc     : " + desc);
        System.out.println("Icon     : " + icon);
        System.out.println("Priority : " + priority);
        System.out.println("Status   : " + status);
        System.out.println("Deadline : " + deadline);
        System.out.print("Tags     : ");
        tags.forEach(t -> System.out.print("[" + t.getName() + "/" + t.getColor() + "] "));
        System.out.println("");
        Person assignee = (Person) personCombo.getSelectedItem();

        if (editingTask != null) {
            // ── Edit mode: mutate the existing task in-place ──────
            editingTask.setTitle(title);
            editingTask.setDesc(desc);
            editingTask.setIcon(icon);
            editingTask.setPriority(priority);
            editingTask.setStatus(status);
            editingTask.setAssignee(assignee);
            editingTask.setTag(new ArrayList<>(tags));
            editingTask.setDeadline(deadline);
            editingTask.refresh();
        } else {
            // ── Create mode: add a brand-new task ─────────────────
            Task a = new Task(title, desc, icon, priority, status, assignee, tags, deadline);
            this.board.addTask(a);
        }
        System.out.println();
    }

    private void clearForm() {
        titleField.setText("");
        descArea.setText("");
        iconField.setText("");
        priorityCombo.setSelectedIndex(0);
        statusCombo.setSelectedIndex(0);
        tagsDropdown.setSelected(new ArrayList<>());
        tags.clear();
        personCombo.setSelectedIndex(0);
        flash(statusBar, "Form cleared.", MUTED);
    }

    // ──────────────────────────────────────────────────────────
    //  Layout Helpers
    // ──────────────────────────────────────────────────────────
    private JPanel buildRow(String label, JComponent field) {
        JPanel row = new JPanel(new BorderLayout(0, 6));
        row.setBackground(BG);
        row.setAlignmentX(LEFT_ALIGNMENT);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
        lbl.setForeground(MUTED);
        lbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 4, 0));

        if (field instanceof JTextField)
            field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));

        row.add(lbl, BorderLayout.NORTH);
        row.add(field, BorderLayout.CENTER);
        return row;
    }

    private JPanel buildTwoColumn(JPanel left, JPanel right) {
        JPanel p = new JPanel(new GridLayout(1, 2, 12, 0));
        p.setBackground(BG);
        p.setAlignmentX(LEFT_ALIGNMENT);
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        p.add(left);
        p.add(right);
        return p;
    }

    private Component spacer(int h) { return Box.createVerticalStrut(h); }

    // ──────────────────────────────────────────────────────────
    //  Widget Factories
    // ──────────────────────────────────────────────────────────
    private JTextField styledTextField(String placeholder) {
        JTextField f = new JTextField();
        f.setBackground(CARD);
        f.setForeground(TEXT);
        f.setCaretColor(ACCENT2);
        f.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        f.setBorder(new CompoundBorder(
            BorderFactory.createLineBorder(BORDER, 1, true),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));
        return f;
    }

    private JButton accentButton(String text, Color color) {
        JButton b = new JButton(text);
        b.setBackground(color);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.putClientProperty(FlatClientProperties.BUTTON_TYPE, "roundRect");
        return b;
    }

    private JButton ghostButton(String text) {
        JButton b = new JButton(text);
        b.setBackground(CARD);
        b.setForeground(MUTED);
        b.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.putClientProperty(FlatClientProperties.BUTTON_TYPE, "roundRect");
        return b;
    }

    private void flash(JLabel label, String msg, Color color) {
        label.setText(msg);
        label.setForeground(color);
        Timer t = new Timer(4000, e -> {
            label.setText(" ");
            label.setForeground(MUTED);
        });
        t.setRepeats(false);
        t.start();
    }

    // ──────────────────────────────────────────────────────────
    //  Priority combo renderer
    // ──────────────────────────────────────────────────────────
    class PriorityRenderer implements ListCellRenderer<Priority> {
        @Override
        public Component getListCellRendererComponent(
                JList<? extends Priority> list, Priority value,
                int index, boolean isSelected, boolean cellHasFocus) {

            if (value == null) return new JPanel();

            JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
            JPanel badge   = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 2));
            badge.putClientProperty(FlatClientProperties.STYLE, "arc:999;");
            badge.setBackground(CusColor.hexToColorObject(value.getColor().labelColor));

            JLabel label = new JLabel(value.getName());
            label.setFont(new Font("Inter", Font.BOLD, 12));
            label.setForeground(CusColor.hexToColorObject(value.getColor().textColor));

            badge.add(label);
            wrapper.add(badge);
            wrapper.setBackground(isSelected
                ? list.getSelectionBackground()
                : list.getBackground());
            wrapper.setOpaque(true);
            return wrapper;
        }
    }

    // ──────────────────────────────────────────────────────────
    //  Person combo renderer
    // ──────────────────────────────────────────────────────────
    class PersonRenderer extends DefaultListCellRenderer {
        private static final int AVATAR_SIZE = 26;

        @Override
        public Component getListCellRendererComponent(
                JList<?> list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {

            JPanel row = new JPanel(new BorderLayout(8, 0));
            row.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
            row.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
            row.setOpaque(true);

            if (value == null) {
                JLabel lbl = new JLabel("— Unassigned —");
                lbl.setFont(new Font("Segoe UI", Font.ITALIC, 13));
                lbl.setForeground(MUTED);
                row.add(lbl, BorderLayout.CENTER);
                return row;
            }

            Person p = (Person) value;

            // Avatar circle (icon if present, initials fallback)
            JLabel avatar = new JLabel() {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(ACCENT);
                    g2.fillOval(0, 0, AVATAR_SIZE, AVATAR_SIZE);
                    if (p.getIcon() != null && p.getIcon().getIconWidth() > 0) {
                        Image img = p.getIcon().getImage().getScaledInstance(AVATAR_SIZE, AVATAR_SIZE, Image.SCALE_SMOOTH);
                        g2.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, AVATAR_SIZE, AVATAR_SIZE));
                        g2.drawImage(img, 0, 0, null);
                    } else {
                        String initials = p.getName().trim().isEmpty() ? "?"
                            : String.valueOf(p.getName().charAt(0)).toUpperCase();
                        g2.setColor(Color.WHITE);
                        g2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
                        FontMetrics fm = g2.getFontMetrics();
                        int tx = (AVATAR_SIZE - fm.stringWidth(initials)) / 2;
                        int ty = (AVATAR_SIZE - fm.getHeight()) / 2 + fm.getAscent();
                        g2.drawString(initials, tx, ty);
                    }
                    g2.dispose();
                }
            };
            avatar.setPreferredSize(new Dimension(AVATAR_SIZE, AVATAR_SIZE));
            avatar.setOpaque(false);

            // Name + role stacked
            JPanel info = new JPanel();
            info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
            info.setOpaque(false);

            JLabel nameLabel = new JLabel(p.getName());
            nameLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
            nameLabel.setForeground(TEXT);

            JLabel roleLabel = new JLabel(p.getRole() != null ? p.getRole() : "");
            roleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            roleLabel.setForeground(MUTED);

            info.add(nameLabel);
            info.add(roleLabel);

            row.add(avatar, BorderLayout.WEST);
            row.add(info,   BorderLayout.CENTER);
            return row;
        }
    }
}

// ══════════════════════════════════════════════════════════════
//  MultiSelectDropdown  — Tag-native
// ══════════════════════════════════════════════════════════════
class MultiSelectDropdown extends JPanel {

    private static final int   POPUP_MAX_HEIGHT = 220;
    private static final int   ROW_HEIGHT       = 34;
    private static final Color BG_POPUP         = new Color(0x27273A);
    private static final Color HOVER_COLOR      = new Color(0x35355A);
    private static final Color CHECK_COLOR      = new Color(0x6366F1);
    private static final Color BORDER_COLOR     = new Color(0x37374B);
    private static final Color TEXT_COLOR       = new Color(0xE2E2F0);
    private static final Color PLACEHOLDER_CLR  = new Color(0x7878A0);
    private static final Font  MAIN_FONT        = new Font("Segoe UI", Font.PLAIN, 13);

    private final ArrayList<Tag> allOptions = new ArrayList<>();
    private final ArrayList<Tag> selected   = new ArrayList<>();
    private final ArrayList<Tag> filtered   = new ArrayList<>();

    private final JPanel     fieldPanel;
    private final JPanel     tagsArea;
    private final JTextField searchField;
    private final JWindow    popup;
    private final JPanel     listPanel;

    /** Callback receives the live selection as ArrayList<Tag>. */
    private Consumer<ArrayList<Tag>> onSelectionChanged;
    private boolean popupVisible = false;

    // ── Constructor ────────────────────────────────────────────
    public MultiSelectDropdown(ArrayList<Tag> options) {
        allOptions.addAll(options);
        filtered.addAll(options);

        setLayout(new BorderLayout());
        setOpaque(false);

        // ── field panel ────────────────────────────────────────
        fieldPanel = new JPanel(new BorderLayout(4, 0));
        fieldPanel.setBackground(new Color(36, 36, 50));
        fieldPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1, true),
            new EmptyBorder(4, 8, 4, 8)
        ));
        fieldPanel.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

        tagsArea = new JPanel(new WrapLayout(FlowLayout.LEFT, 4, 2));
        tagsArea.setOpaque(false);

        searchField = new JTextField();
        searchField.setFont(MAIN_FONT);
        searchField.setForeground(TEXT_COLOR);
        searchField.setBackground(new Color(36, 36, 50));
        searchField.setCaretColor(new Color(0xA78BFA));
        searchField.setBorder(BorderFactory.createEmptyBorder());
        searchField.setOpaque(false);
        searchField.setPreferredSize(new Dimension(90, 24));
        searchField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search tags…");

        tagsArea.add(searchField);

        JLabel chevron = new JLabel("▾");
        chevron.setForeground(PLACEHOLDER_CLR);
        chevron.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        chevron.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        fieldPanel.add(tagsArea, BorderLayout.CENTER);
        fieldPanel.add(chevron, BorderLayout.EAST);
        add(fieldPanel, BorderLayout.CENTER);

        // ── popup ──────────────────────────────────────────────
        popup = new JWindow();
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(BG_POPUP);

        JScrollPane scroll = new JScrollPane(listPanel,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
        scroll.getViewport().setBackground(BG_POPUP);
        scroll.getVerticalScrollBar().setUnitIncrement(8);

        JPanel popupWrapper = new JPanel(new BorderLayout());
        popupWrapper.setBackground(BG_POPUP);
        popupWrapper.add(scroll, BorderLayout.CENTER);
        popup.setContentPane(popupWrapper);

        // ── events ─────────────────────────────────────────────
        fieldPanel.addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) { togglePopup(); }
        });
        chevron.addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) { togglePopup(); }
        });
        searchField.addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) { if (!popupVisible) showPopup(); }
        });
        searchField.addKeyListener(new KeyAdapter() {
            @Override public void keyReleased(KeyEvent e) { filterOptions(searchField.getText()); }
        });

        KeyboardFocusManager.getCurrentKeyboardFocusManager()
            .addPropertyChangeListener("focusOwner", evt -> {
                Component fo = (Component) evt.getNewValue();
                if (fo == null) return;
                if (!SwingUtilities.isDescendingFrom(fo, fieldPanel)
                        && !SwingUtilities.isDescendingFrom(fo, popup)) {
                    hidePopup();
                }
            });

        rebuildList();
    }

    // ── Public API ─────────────────────────────────────────────

    /** Returns a copy of the currently selected Tags. */
    public ArrayList<Tag> getSelected() { return new ArrayList<>(selected); }

    /** Programmatically replaces the selection (e.g. for Clear). */
    public void setSelected(ArrayList<Tag> items) {
        selected.clear();
        selected.addAll(items);
        rebuildChips();
        rebuildList();
        fireChanged();
    }

    /** Fired on every selection change with the updated ArrayList<Tag>. */
    public void setOnSelectionChanged(Consumer<ArrayList<Tag>> cb) {
        onSelectionChanged = cb;
    }

    /** Adds a new Tag option at runtime (skipped if name already present). */
    public void addOption(Tag tag) {
        boolean exists = allOptions.stream().anyMatch(t -> t.getName().equals(tag.getName()));
        if (!exists) {
            allOptions.add(tag);
            filterOptions(searchField.getText());
        }
    }

    // ── Internals ──────────────────────────────────────────────

    private void togglePopup() { if (popupVisible) hidePopup(); else showPopup(); }

    private void showPopup() {
        if (popupVisible) return;
        Point loc = fieldPanel.getLocationOnScreen();
        int h = Math.min(POPUP_MAX_HEIGHT, Math.max(1, filtered.size()) * ROW_HEIGHT + 6);
        popup.setLocation(loc.x, loc.y + fieldPanel.getHeight() + 2);
        popup.setSize(fieldPanel.getWidth(), h);
        popup.setVisible(true);
        popupVisible = true;
        searchField.requestFocusInWindow();
    }

    private void hidePopup() {
        popup.setVisible(false);
        popupVisible = false;
        searchField.setText("");
        filterOptions("");
    }

    private void filterOptions(String query) {
        filtered.clear();
        String q = query.trim().toLowerCase();
        for (Tag t : allOptions)
            if (q.isEmpty() || t.getName().toLowerCase().contains(q)) filtered.add(t);
        rebuildList();
        if (popupVisible) {
            int h = Math.min(POPUP_MAX_HEIGHT, Math.max(1, filtered.size()) * ROW_HEIGHT + 6);
            popup.setSize(popup.getWidth(), h);
        }
    }

    /** Identity check by name (Tag doesn't override equals). */
    private boolean isSelected(Tag tag) {
        return selected.stream().anyMatch(t -> t.getName().equals(tag.getName()));
    }

    private void rebuildList() {
        listPanel.removeAll();
        for (Tag tag : filtered) listPanel.add(createRow(tag));
        if (filtered.isEmpty()) {
            JLabel empty = new JLabel("  No results");
            empty.setForeground(PLACEHOLDER_CLR);
            empty.setFont(MAIN_FONT);
            empty.setPreferredSize(new Dimension(0, ROW_HEIGHT));
            listPanel.add(empty);
        }
        listPanel.revalidate();
        listPanel.repaint();
    }

    private JPanel createRow(Tag tag) {
        boolean sel = isSelected(tag);

        JPanel row = new JPanel(new BorderLayout(8, 0));
        row.setBackground(sel ? new Color(0x2E2E50) : BG_POPUP);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, ROW_HEIGHT));
        row.setPreferredSize(new Dimension(0, ROW_HEIGHT));
        row.setBorder(new EmptyBorder(0, 10, 0, 10));
        row.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel check = new JLabel(sel ? "✓" : "  ");
        check.setFont(new Font("Segoe UI", Font.BOLD, 13));
        check.setForeground(CHECK_COLOR);
        check.setPreferredSize(new Dimension(18, ROW_HEIGHT));

        // Show the Tag's own coloured pill as the row label
        JPanel tagPreview = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        tagPreview.setOpaque(false);
        tagPreview.add(new Tag(tag.getName(), tag.getColor()));

        row.add(check, BorderLayout.WEST);
        row.add(tagPreview, BorderLayout.CENTER);

        row.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                if (!isSelected(tag)) row.setBackground(HOVER_COLOR);
            }
            @Override public void mouseExited(MouseEvent e) {
                row.setBackground(isSelected(tag) ? new Color(0x2E2E50) : BG_POPUP);
            }
            @Override public void mousePressed(MouseEvent e) { toggleOption(tag); }
        });
        return row;
    }

    private void toggleOption(Tag tag) {
        if (isSelected(tag)) selected.removeIf(t -> t.getName().equals(tag.getName()));
        else                  selected.add(tag);
        rebuildChips();
        rebuildList();
        fireChanged();
    }

    private void rebuildChips() {
        tagsArea.removeAll();
        for (Tag tag : selected) {
            Tag chip = new Tag(tag.getName(), tag.getColor());

            JLabel remove = new JLabel(" ✕");
            remove.setFont(new Font("Segoe UI", Font.BOLD, 11));
            remove.setForeground(new Color(0xB0B0D0));
            remove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            remove.addMouseListener(new MouseAdapter() {
                @Override public void mousePressed(MouseEvent e) {
                    e.consume();
                    selected.removeIf(t -> t.getName().equals(tag.getName()));
                    rebuildChips();
                    rebuildList();
                    fireChanged();
                }
            });
            chip.add(remove);
            tagsArea.add(chip);
        }
        tagsArea.add(searchField);
        tagsArea.revalidate();
        tagsArea.repaint();
        revalidate();
        repaint();
    }

    private void fireChanged() {
        if (onSelectionChanged != null) onSelectionChanged.accept(getSelected());
    }

    // ── WrapLayout ────────────────────────────────────────────
    static class WrapLayout extends FlowLayout {
        WrapLayout(int align, int hgap, int vgap) { super(align, hgap, vgap); }

        @Override public Dimension preferredLayoutSize(Container t) { return layoutSize(t, true); }
        @Override public Dimension minimumLayoutSize(Container t)   { return layoutSize(t, false); }

        private Dimension layoutSize(Container target, boolean preferred) {
            synchronized (target.getTreeLock()) {
                int tw = target.getSize().width;
                if (tw == 0) tw = Integer.MAX_VALUE;
                Insets ins = target.getInsets();
                int maxW = tw - ins.left - ins.right - getHgap() * 2;
                int x = 0, y = ins.top + getVgap(), rowH = 0;
                for (int i = 0; i < target.getComponentCount(); i++) {
                    Component c = target.getComponent(i);
                    if (!c.isVisible()) continue;
                    Dimension d = preferred ? c.getPreferredSize() : c.getMinimumSize();
                    if (x == 0 || x + d.width <= maxW) {
                        x += d.width + getHgap();
                        rowH = Math.max(rowH, d.height);
                    } else {
                        y += rowH + getVgap();
                        x = d.width + getHgap();
                        rowH = d.height;
                    }
                }
                y += rowH + getVgap() + ins.bottom;
                return new Dimension(tw, y);
            }
        }
    }
}