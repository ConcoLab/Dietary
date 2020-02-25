package views.panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReportPanel extends JPanel {
    private JLabel caloriesLabel;

    public ReportPanel() {

        setLayout(new GridLayout(0,2));

        // add(new JLabel("Calories"));
        // add(new JLabel("Calories"));


        // Creation of new JPanel for 'Eaten'
        JPanel eatenPanel = new JPanel();
        eatenPanel.setLayout(new BorderLayout());

        // BOTTOM RIGHT (LEFT), 'Eaten'
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


        // Creation of new JPanel for 'Not Eaten'
        JPanel notEatenPanel = new JPanel();
        notEatenPanel.setLayout(new BorderLayout());

        // BOTTOM RIGHT (RIGHT), Not Eaten
        notEatenPanel.add(new JLabel("Not Eaten"), BorderLayout.NORTH);

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
