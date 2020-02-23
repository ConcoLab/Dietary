package views.panels;

import models.Meal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class EatenMealsPanel extends JPanel {
    public JButton addButton;
    public JButton deleteButton;
    public JTable table;

    public EatenMealsPanel(){
        setLayout(new BorderLayout());
//        ArrayList<Meal> meals = new ArrayList();

        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{}, new Object[]{
                "ID",
                "Food Name",
                "Food Group",
                "Date"
        }
        );

        table = new JTable(model);
//        for (Meal meal: meals){
//            model.addRow(new Object[]{
//                    1
//            });
//        }

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        add(scrollPane);

//        JPanel bottomPanel = new JPanel();
//        bottomPanel.setLayout(new GridLayout(1,5));
//        addButton = new JButton("ADD");
    }
}
