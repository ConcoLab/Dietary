package views.panels;

import daoFactories.ContextFactory;
import models.Food;
import models.Unit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class FoodPanel extends JPanel{
    public JButton addButton;
    public JButton deleteButton;

    public JButton eatenButton;
    public JButton uneatenButton;

    public JTable table;


    public FoodPanel(){

        setLayout(new BorderLayout());
        ArrayList<Food> foods = ContextFactory.get_mysqlFoodDao().all();
        ArrayList<Unit> units = ContextFactory.get_mysqlUnitDao().all();

        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{}, new Object[]{"ID", "NAME", "QUANTITY", "UNITS", "CALORIES"});
        table = new JTable(model);

        for (Food food: foods)
            model.addRow(new Object[]{
                    food.getId(),
                    food.getName(),
                    food.getQuantity(),
                    units.stream().filter(unit -> unit.getId() == food.getUnit_id()).findFirst().get().getName(),
                    food.getCalories()});

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        add(scrollPane);


        // Bottom panel where buttons are added
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1,5));


        // Makes add button
        addButton = new JButton("ADD");

        // Main functionality for adding foods (individual foods)
        addButton.addActionListener(e -> {

            JFrame addGroup = new JFrame("Add Food ...");
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(9,1));
            addGroup.add(panel);

            panel.add(new JLabel("Enter New Food:"));
            JTextField foodName = new JTextField();
            panel.add(foodName);

            panel.add(new JLabel("Enter Quantity:"));
            JTextField foodQuantity = new JTextField();
            panel.add(foodQuantity);

            panel.add(new JLabel("Enter Units:"));
            JComboBox unitComboBox = new JComboBox();

            for (Unit unit : units)
                unitComboBox.addItem(unit.getName());

            panel.add(unitComboBox);

            panel.add(new JLabel("Enter Calories:"));
            JTextField foodCalories = new JTextField();
            panel.add(foodCalories);

            JButton submitButton = new JButton("SUBMIT");
            panel.add(submitButton);

            addGroup.pack();
            addGroup.setLocationRelativeTo(null);
            addGroup.setSize(200,300);
            addGroup.setVisible(true);

            submitButton.addActionListener(event -> {

                String name = foodName.getText();
                long quantity = Long.parseLong(foodQuantity.getText());
                String unit_name = (String)unitComboBox.getSelectedItem();
                Unit thisUnit = units.stream().filter(unit -> unit.getName().contains(unit_name)).findFirst().get();
                long calories = Long.parseLong(foodCalories.getText());
//                if(name.length()== 0)
//                    return;
                Food newFood = new Food(name, calories, thisUnit.getId(), quantity);
                ContextFactory.get_mysqlFoodDao().insert(newFood);

                model.addRow(new Object[]{newFood.getId(),
                        newFood.getName(),
                        newFood.getQuantity(),
                        units.stream().filter(unit -> unit.getId() == newFood.getUnit_id()).findFirst().get().getName(),
                        newFood.getCalories()});
                //groupName.setText("");
            });
        });


        // Makes delete button
        deleteButton = new JButton("DELETE");

        // Main functionality for deleting foods (individual foods)
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1)
                return;
            System.out.println("DEBUG: DELETING - " + foods.get(row).getId() + "  " + foods.get(row).getName());
            ContextFactory.get_mysqlFoodDao().delete(foods.get(row));
            model.removeRow(row);
        });


        // Makes buttons to trigger whether a food is eaten or not (will change Boolean eaten in a Food object() )
        // Functionality not yet added
        eatenButton = new JButton("EATEN");
        uneatenButton = new JButton("UNEATEN");


        // Adding buttons to GUI; for manipulating Foods in bottomPanel (bottom left)
        bottomPanel.add(addButton);
        bottomPanel.add(deleteButton);
        bottomPanel.add(eatenButton);
        bottomPanel.add(uneatenButton);

//        bottomPanel.add(new JButton("FIND"));
//        JButton exitButton = new JButton("CLOSE");
//        bottomPanel.add(exitButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

}
