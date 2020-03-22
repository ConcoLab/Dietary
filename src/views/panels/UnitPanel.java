package views.panels;

import controllers.UnitController;
import models.Unit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class UnitPanel extends TemplatePanel {
    private static DefaultTableModel model;
    private static JTable table;
    private static JLabel unitNameLabel;
    private static JTextField unitName;
    private static JButton insertButton;
    private static JButton deleteButton;



    public UnitPanel(ArrayList<Unit> units){
        setLayout(new BorderLayout(3,1));
        // Creating a model for the table in this panel
        model = new DefaultTableModel(new Object[][]{}, new Object[]{"ID", "NAME"});
        for (Unit unit : units)
            model.addRow(new Object[]{unit.getId(), unit.getName()});


        //Components

        table = new JTable(model);
        unitNameLabel = new JLabel("Unit Name: ");
        unitName = new JTextField();
        insertButton = new JButton("Insert");
        insertButton.addActionListener(e -> {
            //Validate the input for the "Unit Name" field.
            String name = unitName.getText();
            if(name.length()== 0){
                JOptionPane.showMessageDialog(this, "Please input the unit name in the \"Unit Name\" field!");
                return;
            }

            // Here creates new unit item using the Create Controller.
            try {
                UnitController.create(new Unit(0, name));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            unitName.setText("");
        });


        deleteButton = new JButton("DELETE");
        deleteButton.addActionListener(e -> {
            deleteUnit();
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

    public static void updateModel(){
//        JOptionPane.showMessageDialog(this,"Change is applied successfully.");
        try {
            while(model.getRowCount() != 0){
                model.removeRow(0);
            }

            for (Unit unit : UnitController.getAllUnits())
                model.addRow(new Object[]{unit.getId(), unit.getName()});
            table.updateUI();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteUnit(){
        int row = table.getSelectedRow();
        Long id = Long.parseLong(table.getModel().getValueAt(row, 0).toString());
        System.out.println("DEBUG: DELETING - id = " + id);
        UnitController.delete(id);
        table.updateUI();
    }


}
