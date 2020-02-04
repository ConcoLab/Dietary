package views.location;

import models.Location;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Add extends JFrame {
    private JFrame frame;
    private JTextField textField1;
    private JTextField textField2;
    private JTable locationList;
    private JButton button1;
    private JLabel name;
    private JLabel address;
    private JPanel locationPanel;

    public ArrayList<Location> locations() {
        ArrayList<Location> locations = new ArrayList<Location>();
        Location l1 = new Location(1, "Parham", "Shiraz");
        Location l2 = new Location(1, "Parham", "Shiraz");
        Location l3 = new Location(1, "Parham", "Shiraz");
        Location l4 = new Location(1, "Parham", "Shiraz");
        locations.add(l1);
        locations.add(l2);
        locations.add(l3);
        locations.add(l4);
        return locations;
    }

    public Add() {
//        locationList.setModel();


        setContentPane(locationPanel);
        updateLocations(locations());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Location> data = locations();
                data.add(new Location(2, "Ali", "Tehran"));
                updateLocations(data);
            }
        });
    }



    private void updateLocations(ArrayList<Location> locations){
        DefaultTableModel model = new DefaultTableModel(new Object[][]{},
                new Object[] { "Name", "Address", "Delete", "Edit" });


        locationList.setModel(new DefaultTableModel());
        Object[] rowData = new Object[4];
        for (int i=0; i<locations.size(); i++)
        {
            rowData[0] = locations.get(i).getName();
            rowData[1] = locations.get(i).getAddress();
//            rowData[2] = new JButton("Delete");
//            rowData[3] = new JButton("Edit");
            model.addRow(rowData);
        }
        locationList.setModel(model);
    }
}
