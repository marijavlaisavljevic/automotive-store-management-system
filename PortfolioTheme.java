package ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

/**
 * Visual theme matching Marija's portfolio:
 * burgundy #741B3F, cream #FBF7F5 and soft rose accents.
 */
public final class PortfolioTheme {
    public static final Color BURGUNDY = new Color(116, 27, 63);
    public static final Color BURGUNDY_LIGHT = new Color(141, 41, 76);
    public static final Color CREAM = new Color(251, 247, 245);
    public static final Color FIELD = Color.WHITE;
    public static final Color BORDER = new Color(234, 221, 226);
    public static final Color TEXT = new Color(24, 20, 23);

    private PortfolioTheme() {}

    public static void apply(Window window) {
        if (window == null) return;
        style(window);
        window.setBackground(CREAM);
    }

    private static void style(Component c) {
        if (c instanceof JPanel || c instanceof JTabbedPane || c instanceof JScrollPane) {
            c.setBackground(CREAM);
        }
        if (c instanceof JLabel) {
            c.setForeground(TEXT);
            c.setFont(c.getFont().deriveFont(Font.PLAIN, 13f));
        }
        if (c instanceof JButton) {
            JButton b = (JButton)c;
            b.setBackground(BURGUNDY);
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setOpaque(true);
            b.setBorder(new EmptyBorder(9, 16, 9, 16));
            b.setFont(b.getFont().deriveFont(Font.BOLD, 13f));
        }
        if (c instanceof JTextField || c instanceof JPasswordField || c instanceof JComboBox) {
            c.setBackground(FIELD);
            c.setForeground(TEXT);
            if (c instanceof JComponent) {
                ((JComponent)c).setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(BORDER),
                    new EmptyBorder(7, 9, 7, 9)
                ));
            }
        }
        if (c instanceof JTable) {
            JTable t = (JTable)c;
            t.setBackground(Color.WHITE);
            t.setForeground(TEXT);
            t.setSelectionBackground(BURGUNDY_LIGHT);
            t.setSelectionForeground(Color.WHITE);
            t.setGridColor(BORDER);
            t.setRowHeight(25);
            JTableHeader h = t.getTableHeader();
            if (h != null) {
                h.setBackground(BURGUNDY);
                h.setForeground(Color.WHITE);
                h.setFont(h.getFont().deriveFont(Font.BOLD));
            }
        }
        if (c instanceof Container) {
            for (Component child : ((Container)c).getComponents()) style(child);
        }
    }
}
