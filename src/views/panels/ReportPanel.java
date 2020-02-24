package views.panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReportPanel extends JPanel {
    private JLabel caloriesLable;

    public ReportPanel() {
        setLayout(new GridLayout(0,2));
        add(new JLabel("Calories"));
        add(new JLabel("Calories"));

        JPanel eatenPanel = new JPanel();
        eatenPanel.setLayout(new BorderLayout());
        eatenPanel.add(new JLabel("Eaten"), BorderLayout.NORTH);
        DefaultTableModel eatenModel = new DefaultTableModel(
                new Object[][]{}, new Object[]{"Group Name", "Foods"}
        );
        JTable eatenTable = new JTable(eatenModel);
        eatenTable.setFillsViewportHeight(true);


        eatenModel.addRow(new Object[]{
                "A group"
        });
        JScrollPane eatenScrollPane = new JScrollPane(eatenTable);
        eatenTable.setFillsViewportHeight(true);
        add(eatenScrollPane);

        eatenPanel.add(eatenScrollPane);

        add(eatenPanel);



        JPanel notEatenPanel = new JPanel();
        notEatenPanel.setLayout(new BorderLayout());
        notEatenPanel.add(new JLabel("Not Eaten Group"), BorderLayout.NORTH);
        DefaultTableModel notEatenModel = new DefaultTableModel(
                new Object[][]{}, new Object[]{"Group Name", "Foods"}
        );
        JTable notEatenTable = new JTable(notEatenModel);
        notEatenTable.setFillsViewportHeight(true);


        notEatenModel.addRow(new Object[]{
                "A group"
        });
        JScrollPane notEatenScrollPane = new JScrollPane(notEatenTable);
        notEatenTable.setFillsViewportHeight(true);
        add(notEatenScrollPane);

        notEatenPanel.add(notEatenScrollPane);
        add(notEatenPanel);



    }
}
