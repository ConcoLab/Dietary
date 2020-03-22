package views.panels;

import controllers.GroupController;
import daoFactories.Context;
import daoFactories.ContextFactory;
import javafx.collections.ListChangeListener;
import models.Group;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroupPanel extends TemplatePanel {
    private static DefaultTableModel model;

    public GroupPanel(ArrayList<Group> groups) throws SQLException {
        model = new DefaultTableModel(new Object[][]{}, new Object[]{"ID", "NAME"});


        //Components
        JTable table = new JTable(model);

        JLabel groupNameLable = new JLabel("Enter New Group:");

        JTextField groupName = new JTextField();

        JButton insertButton = new JButton("Insert");
        insertButton.addActionListener(event -> {
            //validate the input for "New Group" field.
            String name = groupName.getText();
            if(name.length()== 0){
                JOptionPane.showMessageDialog(this,"Please input the new food group name in the \" New Group\" field!");
                return;
            }

            Group newGroup = new Group(0, name, null);
//            ContextFactory._GroupDao().insert(newGroup);
            GroupController.create(newGroup);
            groupName.setText("");
        });

        deleteButton = new JButton("DELETE");
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            Long id = Long.parseLong(table.getModel().getValueAt(row, 0).toString());
            GroupController.delete(id);
        });


        // Adding Components to the Panel And Design
        setLayout(new BorderLayout(1,1));

        JPanel dataEntryPanel = new JPanel();
        dataEntryPanel.setLayout(new GridLayout(0, 1,1,1));
        dataEntryPanel.add(groupNameLable);
        dataEntryPanel.add(groupName);
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

        // Add data to the table
        updateGroupModel();
    }

    public static void updateGroupModel() throws SQLException {
        // Creating a model for the table in this panel

        // Refreshing the components' models according to any changes in the model
//        JOptionPane.showMessageDialog(this,"Change is applied successfully.");
        while(model.getRowCount() != 0){
            model.removeRow(0);
        }
        for (Group group : GroupController.getAll())
            model.addRow(new Object[]{group.getId(), group.getName()});
    }
}
