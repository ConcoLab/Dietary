package views.panels;

import daoFactories.ContextFactory;
import models.Group;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class GroupPanel extends TemplatePanel {

    public GroupPanel(){
        ArrayList<Group> data = ContextFactory.get_mysqlGroupDao().all();

        model = new DefaultTableModel(new Object[][]{}, new Object[]{"ID", "NAME"});
        table = new JTable(model);
        for (Group group : data)
            model.addRow(new Object[]{group.getId(), group.getName()});

        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        add(scrollPane, BorderLayout.CENTER);
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1,5));
        addButton = new JButton("ADD");
        addButton.addActionListener(e -> {
            JFrame addGroup = new JFrame("Add Group ...");
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3,1));
            addGroup.add(panel);
            panel.add(new JLabel("Enter New Group:"));
            JTextField groupName = new JTextField();
            panel.add(groupName);
            JButton submitButton = new JButton("SUBMIT");
            panel.add(submitButton);
            addGroup.pack();
            addGroup.setLocationRelativeTo(null);
            addGroup.setSize(200,120);
            addGroup.setVisible(true);
            submitButton.addActionListener(event -> {
                String name = groupName.getText();
                if(name.length()== 0)
                    return;
                Group newGroup = new Group(name);
                ContextFactory.get_mysqlGroupDao().insert(newGroup);
                model.addRow(new Object[]{newGroup.getId(), newGroup.getName()});
                groupName.setText("");
            });
        });
        deleteButton = new JButton("DELETE");
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1)
                return;
            System.out.println("DEBUG: DELETING - " + data.get(row).getId() + "  " + data.get(row).getName());
            ContextFactory.get_mysqlGroupDao().delete(data.get(row));
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
