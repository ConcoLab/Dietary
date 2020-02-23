package views.panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TemplatePanel extends JPanel {
    public JButton addButton;
    public JButton deleteButton;
    public JTable table;
    public DefaultTableModel model;
    public JScrollPane scrollPane;
    public JPanel bottomPanel;

    public TemplatePanel(){
        setLayout(new BorderLayout());
    }
}
