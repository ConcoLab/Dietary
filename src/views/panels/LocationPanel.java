package views.panels;

import daoFactories.ContextFactory;
import models.Location;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class LocationPanel extends JPanel{
    public JButton addButton;
    public JButton deleteButton;
    public JTable table;

    public LocationPanel(){

        setLayout(new BorderLayout());
        ArrayList<Location> data = ContextFactory.get_mysqlLocationDao().all();

        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new Object[]{"ID", "NAME", "ADDRESS"});

        table = new JTable(model);

        for (Location location : data)
            model.addRow(new Object[]{location.getId(), location.getName(), location.getAddress()});

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        add(scrollPane);


        // Bottom panel where buttons are added
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1,5));


        // Makes add button
        addButton = new JButton("ADD");

        // Main functionality for adding locations (outdining locations)
        addButton.addActionListener(e -> {
            JFrame addGroup = new JFrame("Add Location ...");
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(5,1));
            addGroup.add(panel);
            panel.add(new JLabel("Location Name:"));
            JTextField locationName = new JTextField();
            panel.add(locationName);
            panel.add(new JLabel("Location Address:"));
            JTextField locationAddress = new JTextField();
            panel.add(locationAddress);
            JButton submitButton = new JButton("SUBMIT");
            panel.add(submitButton);
            addGroup.pack();
            addGroup.setLocationRelativeTo(null);
            addGroup.setSize(200,200);
            addGroup.setVisible(true);

            submitButton.addActionListener(event -> {
                String name = locationName.getText();
                String address = locationAddress.getText();
                if(name.length()== 0 || address.length() == 0)
                    return;
                Location newLocation = new Location(name, address);
                ContextFactory.get_mysqlLocationDao().insert(newLocation);
                model.addRow(new Object[]{newLocation.getId(), newLocation.getName(), newLocation.getAddress()});
                locationName.setText("");
                locationAddress.setText("");
            });
        });


        // Makes delete button
        deleteButton = new JButton("DELETE");

        // Main functionality for deleting locations (outdining locations)
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1)
                return;
            System.out.println("DEBUG: DELETING - " + data.get(row).getId() + "  " + data.get(row).getName());
            ContextFactory.get_mysqlLocationDao().delete(data.get(row));
            model.removeRow(row);
        });


        // Adding buttons to GUI; for manipulating Locations in bottomPanel (bottom left)
        bottomPanel.add(addButton);
        bottomPanel.add(deleteButton);

//        bottomPanel.add(new JButton("FIND"));
//        JButton exitButton = new JButton("CLOSE");
//        bottomPanel.add(exitButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

}
