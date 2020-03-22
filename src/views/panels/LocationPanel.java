package views.panels;

import controllers.LocationController;
import daoFactories.Context;
import daoFactories.ContextFactory;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import models.Location;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class LocationPanel extends TemplatePanel {
    private static DefaultTableModel model;

    public LocationPanel(ArrayList<Location> locations){
        // Creating a model for the table in this panel
        model = new DefaultTableModel(new Object[][]{}, new Object[]{"ID", "NAME", "ADDRESS"});
        for (Location location : locations)
            model.addRow(new Object[]{location.getId(), location.getName(), location.getAddress()});

        // Creating different Components
        JTable table = new JTable(model);

        JLabel locationNameLabel = new JLabel("Location Name:");
        JTextField locationName = new JTextField();

        JLabel locationAddressLabel = new JLabel("Location Address:");
        JTextField locationAddress = new JTextField();

        JButton insertButton = new JButton("Insert");
        insertButton.addActionListener(e->{
            //Validate the input for the "Location Name" and "Location Address" fields.
            String name = locationName.getText();
            String address = locationAddress.getText();
            if(name.length()==0){
                JOptionPane.showMessageDialog(this,"Please input the location name in the \"Location Name\" field!");
                return;
            }
            if(address.length() == 0){
                JOptionPane.showMessageDialog(this, "Please input the location address in the \"Location Address\" field!");
                return;
            }
            Location newLocation = new Location(0, name, address);
            LocationController.create(newLocation);
            locationName.setText("");
            locationAddress.setText("");
        });

        JButton deleteButton = new JButton("DELETE");
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            Long id = Long.parseLong(table.getModel().getValueAt(row, 0).toString());
            LocationController.delete(id);
        });

        // Adding Components to the Panel And Design
        setLayout(new BorderLayout(1,1));

        JPanel dataEntryPanel = new JPanel();
        dataEntryPanel.setLayout(new GridLayout(0, 1,1,1));
        dataEntryPanel.add(locationNameLabel);
        dataEntryPanel.add(locationName);
        dataEntryPanel.add(locationAddressLabel);
        dataEntryPanel.add(locationAddress);
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

    public static void locationUpdater() throws SQLException {
//        JOptionPane.showMessageDialog(this,"Change is applied successfully.");
        while(model.getRowCount() != 0){
            model.removeRow(0);
        }
        for (Location location : LocationController.getAllLocations())
            model.addRow(new Object[]{location.getId(), location.getName(), location.getAddress()});
    }

}
