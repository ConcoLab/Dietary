package views.panels;

import daoFactories.Context;
import daoFactories.ContextFactory;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class FoodPanel extends JPanel{
    private JButton addButton;
    private JButton deleteButton;
    private JTable table;
    private JCheckBox checkBoxes[];

    class Item
    {
        private long id;
        private String description;

        public Item(long id, String description)
        {
            this.id = id;
            this.description = description;
        }

        public long getId()
        {
            return id;
        }

        public String getDescription()
        {
            return description;
        }

        public String toString()
        {
            return description;
        }
    }


    public FoodPanel(ArrayList<Food> foods, ArrayList<Unit> units, ArrayList<Group> groups){

        // Creating a model for the table in this panel
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{}, new Object[]{"ID", "NAME", "QUANTITY", "UNITS", "CALORIES", "GROUPS"});
        for (Food food: foods)
            model.addRow(new Object[]{
                    food.getId(),
                    food.getName(),
                    food.getQuantity(),
                    units.stream().filter(unit -> unit.getId() == food.getUnitId()).findFirst().get().getName(),
                    food.getCalories()});

        Vector unitVector = new Vector();
        for (Unit unit:units){
            unitVector.addElement(new Item(unit.getId(), unit.getName()));
        }

        //Components
        JTable table = new JTable(model);

        JLabel foodNameLabel = new JLabel("Food Name:");
        JTextField foodNameTextField = new JTextField();

        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityTextField = new JTextField();

        JLabel unitLabel = new JLabel("Unit:");

        JComboBox unitsComboBox = new JComboBox(unitVector);


        JLabel caloriesLabel = new JLabel("Calories:");
        JTextField caloriesTextField = new JTextField();

        JButton insertButton = new JButton("Insert");
        insertButton.addActionListener(e -> {
            // validate the input for "Food Name", "Quantity", and "Calories" fields.
            String foodName=foodNameTextField.getText();
            Long quantity;
            Long calories;
            if(foodName.length()==0){
                JOptionPane.showMessageDialog(this,"Please input the food name in the \"Food Name\" field!");
                return;
            }
            if(quantityTextField.getText().length()==0 || quantityTextField.getText().equals("0") || quantityTextField.getText().substring(0,1).equals("-")){
                JOptionPane.showMessageDialog(this,"Please input a positive integer in the \"Quantity\" field!");
                return;
            }
            try{
                quantity=Long.parseLong(quantityTextField.getText());
            }
            catch(NumberFormatException event){
                JOptionPane.showMessageDialog(this,"Please input a positive integer in the \"Quantity\" field!");
                return;
            }
            if(caloriesTextField.getText().length()==0 || caloriesTextField.getText().substring(0,1).equals("-")){
                JOptionPane.showMessageDialog(this,"Please input zero or a positive integer in the \"Calories\" field!");
                return;
            }
            try{
                calories=Long.parseLong(caloriesTextField.getText());
            }
            catch(NumberFormatException event){
                JOptionPane.showMessageDialog(this,"Please input zero or a positive integer in the \"Calories\" field!");
                return;
            }
            int a = unitsComboBox.getSelectedIndex();
            // validate the input for "Food Group" checkboxes.
            int count=0;
            for(JCheckBox checkBox:checkBoxes){
                if(checkBox.isSelected())
                    count++;
            }
            if(count==0){
                JOptionPane.showMessageDialog(this,"Please select at least one food group from the \"Food Groups\" field!");
                return;
            }

            Food newFood = new Food(null,
                    foodName
                    , calories
                    , unitsComboBox.getSelectedIndex()
                    , quantity);
            ContextFactory._FoodDao().insert(newFood);


            for(JCheckBox checkBox:checkBoxes){

                if(checkBox.isSelected()){
                    ContextFactory._FoodGroupDao().insert(new FoodGroup(newFood.getId(), Long.parseLong(checkBox.getActionCommand())));
                }
            }

            //clear the contents in the text fileds.
            foodNameTextField.setText("");
            quantityTextField.setText("");
            caloriesTextField.setText("");
            //Error: foodGroupPanel can't be resolved, need to correct
//            foodGroupPanel.removeAll();
//            checkBoxes = new JCheckBox[groups.size()];
//            int j = 0;
//            for(Group group:groups){
//                checkBoxes[j] = new JCheckBox(group.getName());
//                Long groupId = group.getId();
//                checkBoxes[j].setActionCommand(groupId.toString());
//                foodGroupPanel.add(checkBoxes[j]);
//                j++;
//            }

        });

        deleteButton = new JButton("DELETE");
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1)
                return;
            System.out.println("DEBUG: DELETING - " + foods.get(row).getId() + "  " + foods.get(row).getName());
            ContextFactory._FoodDao().delete(foods.get(row));
        });




        // Adding Components to the Panel And Design
        setLayout(new BorderLayout(3,1));
        JPanel dataEntryPanel = new JPanel();
        dataEntryPanel.setLayout(new GridLayout(0, 1));
        dataEntryPanel.add(foodNameLabel);
        dataEntryPanel.add(foodNameTextField);
        dataEntryPanel.add(quantityLabel);
        dataEntryPanel.add(quantityTextField);
        dataEntryPanel.add(unitLabel);
        dataEntryPanel.add(unitsComboBox);
        dataEntryPanel.add(caloriesLabel);
        dataEntryPanel.add(caloriesTextField);

        JPanel foodGroupPanel = new JPanel();
        foodGroupPanel.setLayout(new FlowLayout());
        checkBoxes = new JCheckBox[groups.size()];
        int i = 0;
        for(Group group:groups){
            checkBoxes[i] = new JCheckBox(group.getName());
            Long groupId = group.getId();
            checkBoxes[i].setActionCommand(groupId.toString());
            foodGroupPanel.add(checkBoxes[i]);
            i++;
        }

        dataEntryPanel.add(foodGroupPanel);
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


        // Refreshing the components' models according to any changes in the model
        Context.foods.addListener((ListChangeListener.Change<? extends Food> f) -> {
            while(model.getRowCount() != 0){
                model.removeRow(0);
            }
            for (Food food : foods)
                model.addRow(new Object[]{
                        food.getId(),
                        food.getName(),
                        food.getQuantity(),
                        units.stream().filter(unit -> unit.getId() == food.getUnitId()).findFirst().get().getName(),
                        food.getCalories(),
                        food.getName()});
        });

//        units.addListener((ListChangeListener.Change<? extends Unit> u) -> {
//            unitVector.clear();
//            for (Unit unit:units){
//                unitVector.addElement(new Item(unit.getId(), unit.getName()));
//            }
//            unitsComboBox.updateUI();
//        });

        Context.groups.addListener((ListChangeListener.Change<? extends Group> g) -> {
            foodGroupPanel.removeAll();
            checkBoxes = new JCheckBox[groups.size()];
            int j = 0;
            for(Group group:groups){
                checkBoxes[j] = new JCheckBox(group.getName());
                Long groupId = group.getId();
                checkBoxes[j].setActionCommand(groupId.toString());
                foodGroupPanel.add(checkBoxes[j]);
                j++;
            }

        });
    }

}
