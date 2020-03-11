package views.panels;

import controllers.UnitController;
import daoFactories.Context;
import daoFactories.ContextFactory;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import models.Unit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class UnitPanel extends TemplatePanel {

    public UnitPanel(ArrayList<Unit> units){
        setLayout(new BorderLayout(3,1));
        // Creating a model for the table in this panel
        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new Object[]{"ID", "NAME"});
        for (Unit unit : units)
            model.addRow(new Object[]{unit.getId(), unit.getName()});

        // Refreshing the components' models according to any changes in the model
        Context.units.addListener((ListChangeListener.Change<? extends Unit> u) -> {
            JOptionPane.showMessageDialog(this,"Change is applied successfully.");
            while(model.getRowCount() != 0){
                model.removeRow(0);
            }
            try {
                for (Unit unit : UnitController.getAllUnits())
                    model.addRow(new Object[]{unit.getId(), unit.getName()});
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        //Components
        JTable table = new JTable(model);
        JLabel unitNameLabel = new JLabel("Unit Name: ");
        JTextField unitName = new JTextField();

        JButton insertButton = new JButton("Insert");
        insertButton.addActionListener(e -> {
            //Validate the input for the "Unit Name" field.
            String name = unitName.getText();
            if(name.length()== 0){
                JOptionPane.showMessageDialog(this, "Please input the unit name in the \"Unit Name\" field!");
                return;
            }

            // Here creates new unit item using the Create Controller.
            UnitController.create(new Unit(0, name));

            unitName.setText("");
        });

        JButton deleteButton = new JButton("DELETE");
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1)
                return;
            System.out.println("DEBUG: DELETING - " + units.get(row).getId() + "  " + units.get(row).getName());
            ContextFactory._UnitDao().delete(units.get(row));
        });

        // Adding Components to the Panel And Design
        setLayout(new BorderLayout(1,1));

        JPanel dataEntryPanel = new JPanel();
        dataEntryPanel.setLayout(new GridLayout(0, 1,1,1));
        dataEntryPanel.add(unitNameLabel);
        dataEntryPanel.add(unitName);
        dataEntryPanel.add(insertButton);
        add(dataEntryPanel, BorderLayout.NORTH);


        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1,5));

        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);
        bottomPanel.add(deleteButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

}
