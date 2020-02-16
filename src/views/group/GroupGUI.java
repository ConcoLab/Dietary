package views.group;

import daoFactories.Context;
import daos.concrete.MysqlGroupDao;
import models.Group;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GroupGUI extends JFrame{
    private JTable groupTable;
    private JPanel groupPanel;
    private JButton deleteBtn;
    private JButton addBtn;
    private JTextField newGroupTextField;
    private JPanel bottomPanel;


    public GroupGUI(){
        setContentPane(groupPanel);
        Context context = new Context();
        MysqlGroupDao groups = new MysqlGroupDao(context);
        DefaultTableModel tableModel = new DefaultTableModel();
        //tableModel.setDataVector(new Object[][]{}, new Object[]{"Food Group"});
        ArrayList<Group> gr = groups.all();
        JScrollPane scroll = new JScrollPane(groupTable);
        //groupTable.setPreferredScrollableViewportSize(groupTable.getPreferredSize());
//        add(scroll);
        for (Group g : gr)
            tableModel.addRow(new Object[]{g.getName()});
        System.out.print(gr.get(0).getName());
        groupTable.setModel(tableModel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(300,300);

        deleteBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int sr = groupTable.getSelectedRow();
                tableModel.removeRow(sr);
            }
        });
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newGroup = newGroupTextField.getText();
                if (newGroup.length() != 0)
                    tableModel.addRow(new String[]{newGroup});
                newGroupTextField.setText("");
            }
        });
    }

    public void setData(MysqlGroupDao data) {

    }

    public void getData(MysqlGroupDao data) {
    }

    public boolean isModified(MysqlGroupDao data) {
        return false;
    }

}
