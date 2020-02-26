package views.panels;

import daoFactories.ContextFactory;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import models.Location;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LocationPanel extends TemplatePanel {

    public LocationPanel(ObservableList<Location> locations){
        // Creating a model for the table in this panel
        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new Object[]{"ID", "NAME", "ADDRESS"});
        for (Location location : locations)
            model.addRow(new Object[]{location.getId(), location.getName(), location.getAddress()});

        // Refreshing the components' models according to any changes in the model

        locations.addListener((ListChangeListener.Change<? extends Location> l) -> {
            JOptionPane.showMessageDialog(this,"Change is applied successfully.");
            while(model.getRowCount() != 0){
                model.removeRow(0);
            }
            for (Location location : locations)
                model.addRow(new Object[]{location.getId(), location.getName(), location.getAddress()});
        });

        // Creating different Components
        JTable table = new JTable(model);

        JLabel locationNameLabel = new JLabel("Location Name:");
        JTextField locationName = new JTextField();

        JLabel locationAddressLabel = new JLabel("Location Address:");
        JTextField locationAddress = new JTextField();

        JButton insertButton = new JButton("Insert");
        insertButton.addActionListener(e->{
            String name = locationName.getText();
            String address = locationAddress.getText();
            if(name.length()== 0 || address.length() == 0)
                return;
            Location newLocation = new Location(name, address);
            ContextFactory._LocationDao().insert(newLocation);
            locationName.setText("");
            locationAddress.setText("");
        });

        JButton deleteButton = new JButton("DELETE");
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1)
                return;
            System.out.println("DEBUG: DELETING - " + locations.get(row).getId() + "  " + locations.get(row).getName());
            ContextFactory._LocationDao().delete(locations.get(row));
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

}
