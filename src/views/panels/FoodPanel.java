package views.panels;

import controllers.FoodController;
import controllers.GroupController;
import controllers.UnitController;
import daoFactories.Context;
import daoFactories.ContextFactory;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import models.*;
import util.Item;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class FoodPanel extends JPanel{
    private static JButton addButton;
    private static JButton deleteButton;
    private static JTable table;
    private static JCheckBox checkBoxes[];
    private static JComboBox unitsComboBox;
    private static Vector unitVector;
    private static JLabel foodNameLabel;
    private static JLabel caloriesLabel;
    private static JLabel fatLabel;
    private static JLabel carbohydrateLabel;
    private static JLabel saltLabel;
    private static JLabel proteinLabel;
    private static JLabel groupLabel;
    private static JTextField foodNameTextField;
    private static JTextField caloriesTextField;
    private static JTextField fatTextField;
    private static JTextField carbohydrateTextField;
    private static JTextField saltTextField;
    private static JTextField proteinTextField;
    private static JButton insertButton;
    private static DefaultTableModel model;
    private static JPanel foodGroupPanel;

    public FoodPanel(ArrayList<Food> foods, ArrayList<Unit> units, ArrayList<Group> groups) throws SQLException {

        // Creating a model for the table in this panel
        model = new DefaultTableModel(
                new Object[][]{}, new Object[]{"ID", "NAME", "QUANTITY", "UNITS", "CALORIES", "FAT", "CARB", "SALT", "PROTEIN", "GROUPS"});


        unitVector = new Vector();
        for (Unit unit:units){
            unitVector.addElement(new Item(unit.getId(), unit.getName()));
        }

        // Components
        table = new JTable(model);
        updateFoodsTable();

        foodNameLabel = new JLabel("Food Name:");
        foodNameTextField = new JTextField();

        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityTextField = new JTextField();

        JLabel unitLabel = new JLabel("Unit:");

        unitsComboBox = new JComboBox(unitVector);

        caloriesLabel = new JLabel("Calories:");
        caloriesTextField = new JTextField();

        fatLabel = new JLabel("Fat (g):");
        fatTextField = new JTextField();

        carbohydrateLabel = new JLabel("Carbohydrate (g):");
        carbohydrateTextField = new JTextField();

        saltLabel = new JLabel("Salt (g):");
        saltTextField = new JTextField();

        proteinLabel = new JLabel("Protein (g):");
        proteinTextField = new JTextField();

        groupLabel = new JLabel("Groups:");

        // UPDATE: moved from where it was previously, dataEntryPanel.add() methods section
        foodGroupPanel = new JPanel();
        foodGroupPanel.setLayout(new FlowLayout());
        checkBoxes = new JCheckBox[groups.size()];
        int i = 0;

        insertButton = new JButton("Insert");

        // functionality for insert button
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

            MealPanel.Item unitItem = (MealPanel.Item) unitsComboBox.getSelectedItem();
            Food newFood = new Food(0,
                    foodName
                    , calories
                    , Long.parseLong(fatTextField.getText())
                    , Long.parseLong(carbohydrateTextField.getText())
                    , Long.parseLong(saltTextField.getText())
                    , Long.parseLong(proteinTextField.getText())
                    , unitItem.getId()
                    , quantity
                    , new ArrayList<Group>());
            System.out.println("DEBUG:---");



            for(JCheckBox checkBox:checkBoxes){
                if(checkBox.isSelected()){
//                    newFood.setGroups(newFood.getGroups().add(new Group(newFood.getId(), Long.parseLong(checkBox.getActionCommand())));
                    ArrayList<Group> listOfGroups = newFood.getGroups();
                    listOfGroups.add(new Group(Long.parseLong(checkBox.getActionCommand()), "", new ArrayList<Food>()));
                    newFood.setGroups(listOfGroups);
                }
            }
//            ContextFactory._FoodDao().insert(newFood);
            FoodController.create(newFood);

            //clear the contents in the text fields
            foodNameTextField.setText("");
            quantityTextField.setText("");
            caloriesTextField.setText("");
        });

        //  Do we need this delete button? See also: lines 213 - 223.

        //        deleteButton = new JButton("DELETE");
        //        deleteButton.addActionListener(e -> {
        //            int row = table.getSelectedRow();
        //            Long id = Long.parseLong(table.getModel().getValueAt(row, 0).toString());
        //            FoodController.delete(id);
        //        });


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
        dataEntryPanel.add(fatLabel);
        dataEntryPanel.add(fatTextField);
        dataEntryPanel.add(carbohydrateLabel);
        dataEntryPanel.add(carbohydrateTextField);
        dataEntryPanel.add(saltLabel);
        dataEntryPanel.add(saltTextField);
        dataEntryPanel.add(proteinLabel);
        dataEntryPanel.add(proteinTextField);
        dataEntryPanel.add(groupLabel);
        dataEntryPanel.add(foodGroupPanel);
        dataEntryPanel.add(insertButton);

        // UPDATE: BorderLayout changed from NORTH to CENTER
        add(dataEntryPanel, BorderLayout.CENTER);

        // changed size of whole panel; many things were not visible
        dataEntryPanel.setPreferredSize(new Dimension(400, 490));

        // Is there any need for a bottomPanel and a deleteButton? Foods would be deleted from consumed foods, no?
        // I think that this code below is redundant at this stage.

        //        JPanel bottomPanel = new JPanel();
        //
        //        bottomPanel.setLayout(new GridLayout(1,5));
        //
        //        deleteButton.setBackground(Color.RED);
        //        deleteButton.setForeground(Color.WHITE);
        //        bottomPanel.add(deleteButton);
        //        add(bottomPanel, BorderLayout.SOUTH);

        updateFoodGroupCheckboxes();
    }


    public static void updateUnitsCombobox() throws SQLException {
        unitVector.clear();
        for (Unit unit: UnitController.getAllUnits()){
            unitVector.addElement(new Item(unit.getId(), unit.getName()));
        }
        unitsComboBox.updateUI();
    }


    public static void updateFoodsTable() throws SQLException {
        while(model.getRowCount() != 0){
            model.removeRow(0);
        }
        //TODO: Implement the get food in controller
        for (Food food : ContextFactory._FoodDao().all())
            model.addRow(new Object[]{
                    food.getId(),
                    food.getName(),
                    food.getQuantity(),
                    UnitController.getById(food.getUnitId()).getName(),
                    food.getCalories(),
                    food.getFat(),
                    food.getCarbohydrate(),
                    food.getSalt(),
                    food.getProtein(),
                    GroupController.getGroupNames(food.getGroups())});
    }


    public static void updateFoodGroupCheckboxes() throws SQLException {
        foodGroupPanel.removeAll();
        ArrayList<Group> groups = GroupController.getAll();
        checkBoxes = new JCheckBox[groups.size()];
        int j = 0;
        for(Group group: groups){
            checkBoxes[j] = new JCheckBox(group.getName());
            Long groupId = group.getId();
            checkBoxes[j].setActionCommand(groupId.toString());
            foodGroupPanel.add(checkBoxes[j]);
            j++;
        }
    }

}
