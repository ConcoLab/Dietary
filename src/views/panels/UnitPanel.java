package views.panels;

import daoFactories.ContextFactory;
import models.Unit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class UnitPanel extends JPanel{
    public JButton addButton;
    public JButton deleteButton;
    public JTable table;

    public UnitPanel(){
        setLayout(new BorderLayout());
        ArrayList<Unit> data = ContextFactory.get_mysqlUnitDao().all();

        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new Object[]{"ID", "NAME"});
        table = new JTable(model);
        for (Unit unit : data)
            model.addRow(new Object[]{unit.getId(), unit.getName()});

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        add(scrollPane);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1,5));
        addButton = new JButton("ADD");
        addButton.addActionListener(e -> {
            JFrame addGroup = new JFrame("Add Unit ...");
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3,1));
            addGroup.add(panel);
            panel.add(new JLabel("Enter New Unit:"));
            JTextField unitName = new JTextField();
            panel.add(unitName);
            JButton submitButton = new JButton("SUBMIT");
            panel.add(submitButton);
            addGroup.pack();
            addGroup.setLocationRelativeTo(null);
            addGroup.setSize(200,120);
            addGroup.setVisible(true);
            submitButton.addActionListener(event -> {
                String name = unitName.getText();
                if(name.length()== 0)
                    return;
                Unit newUnit = new Unit(name);
                ContextFactory.get_mysqlUnitDao().insert(newUnit);
                model.addRow(new Object[]{newUnit.getId(), newUnit.getName()});
                unitName.setText("");
            });
        });
        deleteButton = new JButton("DELETE");
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1)
                return;
            System.out.println("DEBUG: DELETING - " + data.get(row).getId() + "  " + data.get(row).getName());
            ContextFactory.get_mysqlUnitDao().delete(data.get(row));
            model.removeRow(row);
        });
        bottomPanel.add(addButton);
        bottomPanel.add(deleteButton);
//        bottomPanel.add(new JButton("FIND"));
//        JButton exitButton = new JButton("CLOSE");
//        bottomPanel.add(exitButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

}
