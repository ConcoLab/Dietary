package views.panels;

import daoFactories.ContextFactory;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import models.Group;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GroupPanel extends TemplatePanel {

    public GroupPanel(ObservableList<Group> groups){
        // Creating a model for the table in this panel
        model = new DefaultTableModel(new Object[][]{}, new Object[]{"ID", "NAME"});
        for (Group group : groups)
            model.addRow(new Object[]{group.getId(), group.getName()});

        // Refreshing the components' models according to any changes in the model
        groups.addListener((ListChangeListener.Change<? extends Group> g) -> {
            JOptionPane.showMessageDialog(this,"Change is applied successfully.");
            while(model.getRowCount() != 0){
                model.removeRow(0);
            }
            for (Group group : groups)
                model.addRow(new Object[]{group.getId(), group.getName()});
        });

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

            Group newGroup = new Group(name);
            ContextFactory._GroupDao().insert(newGroup);
            groupName.setText("");
        });

        deleteButton = new JButton("DELETE");
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1)
                return;
            System.out.println("DEBUG: DELETING - " + groups.get(row).getId() + "  " + groups.get(row).getName());
            ContextFactory._GroupDao().delete(groups.get(row));
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
    }
}
