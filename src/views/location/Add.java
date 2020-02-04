package views.location;

import daos.concrete.MysqlLocationDao;
import models.Location;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventObject;

public class Add extends JFrame {
    private JFrame frame;
    private JTextField textField1;
    private JTextField textField2;
    private JTable locationList;
    private JButton button1;
    private JLabel name;
    private JLabel address;
    private JPanel locationPanel;

    public Add() {
//        locationList.setModel();


        setContentPane(locationPanel);
        MysqlLocationDao locations = new MysqlLocationDao();
        updateLocations(locations.all());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                locations.insert(new Location(1, textField1.getText(), textField2.getText()));
                updateLocations(locations.all());
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

//        locationList.getColumn("Delete").setCellRenderer(new TableCellRenderer() {
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//                JButton deleteButton = new JButton("Delete");
//                deleteButton.setForeground(Color.white);
//                deleteButton.setBackground(Color.RED);
//                deleteButton.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        JOptionPane.showMessageDialog(null, "test");
//                    }
//                });
//                return deleteButton;
//            }
//        });
//
//        locationList.getColumn("Edit").setCellRenderer(new TableCellRenderer() {
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//                JButton editButton = new JButton("Edit");
//                editButton.setForeground(Color.black);
//                editButton.setBackground(Color.YELLOW);
//                return editButton;
//            }
//        });
    }

}
