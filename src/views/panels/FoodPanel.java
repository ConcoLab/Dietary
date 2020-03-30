package views.panels;

import controllers.FoodController;
import controllers.GroupController;
import controllers.UnitController;
import models.Food;
import models.Group;
import models.Unit;
import util.Item;

import javax.swing.*;
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

        //Components
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


        insertButton = new JButton("Insert");
        insertButton.addActionListener(e -> {
            // validate the input for "Food Name", "Quantity", and "Calories" fields.
            String foodName=foodNameTextField.getText();
            Long quantity,calories,fat,carbohydrate,salt, protein;

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
            if(fatTextField.getText().length()==0 || fatTextField.getText().substring(0,1).equals("-")){
                JOptionPane.showMessageDialog(this, "Please input zero or a positive integer in the \"Fat (g)\" field!");
                return;
            }
            try{
                fat=Long.parseLong(fatTextField.getText());
            }
            catch (NumberFormatException event){
                JOptionPane.showMessageDialog(this,"Please input zero or a positive integer in the \"Fat (g)\" field!");
                return;
            }
            if(carbohydrateTextField.getText().length()==0 || carbohydrateTextField.getText().substring(0,1).equals("-")){
                JOptionPane.showMessageDialog(this, "Please input zero or a positive integer in the \"Carbohydrate (g)\" field!");
                return;
            }
            try{
                carbohydrate=Long.parseLong(carbohydrateTextField.getText());
            }
            catch (NumberFormatException event){
                JOptionPane.showMessageDialog(this,"Please input zero or a positive integer in the \"Carbohydrate (g)\" field!");
                return;
            }
            if(saltTextField.getText().length()==0 || saltTextField.getText().substring(0,1).equals("-")){
                JOptionPane.showMessageDialog(this,"Please input zero or a positive integer in the \"Salt (g)\" field!");
                return;
            }
            try{
                salt=Long.parseLong(saltTextField.getText());
            }
            catch (NumberFormatException event){
                JOptionPane.showMessageDialog(this,"Please input zero or a positive integer in the \"Salt (g)\" field!");
                return;
            }
            if(proteinTextField.getText().length()==0 || proteinTextField.getText().substring(0,1).equals("-")){
                JOptionPane.showMessageDialog(this,"Please input zero or a positive integer in the \"Protein (g)\" field!");
                return;
            }
            try{
                protein=Long.parseLong(proteinTextField.getText());
            }
            catch (NumberFormatException event){
                JOptionPane.showMessageDialog(this,"Please input zero or a positive integer in the \"Protein (g)\" field!");
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

            Item unitItem = (Item) unitsComboBox.getSelectedItem();
            Food newFood = new Food(0,
                    foodName
                    , calories
                    , fat
                    , carbohydrate
                    , salt
                    , protein
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
            //clear the contents in the text fields.
            foodNameTextField.setText("");
            quantityTextField.setText("");
            caloriesTextField.setText("");
            fatTextField.setText("");
            carbohydrateTextField.setText("");
            saltTextField.setText("");
            proteinTextField.setText("");
        });

        deleteButton = new JButton("DELETE");
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            Long id = Long.parseLong(table.getModel().getValueAt(row, 0).toString());
            FoodController.delete(id);
        });




        // Adding Components to the Panel And Design
        setLayout(new GridLayout(2,1));
        JPanel dataEntryPanel = new JPanel();
        dataEntryPanel.setLayout(new GridLayout(0,1));
        JPanel tablePanel = new JPanel(new BorderLayout());
        JPanel dataEntryPanelWrapper = new JPanel(new BorderLayout());
        JPanel wrapper = new JPanel(new BorderLayout());
        JScrollPane scrollPaneData = new JScrollPane(dataEntryPanel);
        wrapper.add(scrollPaneData, BorderLayout.CENTER);
        dataEntryPanelWrapper.add(wrapper, BorderLayout.CENTER);
        dataEntryPanelWrapper.add(insertButton, BorderLayout.SOUTH);
        {
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
        }

        foodGroupPanel = new JPanel();
        foodGroupPanel.setLayout(new GridLayout(0,4));
        checkBoxes = new JCheckBox[groups.size()];
        JPanel groupPanelWrapper = new JPanel(new BorderLayout());

        groupPanelWrapper.add(groupLabel, BorderLayout.WEST);
        groupPanelWrapper.add(foodGroupPanel, BorderLayout.CENTER);
        wrapper.add(groupPanelWrapper, BorderLayout.SOUTH);
//        dataEntryPanel.add(insertButton);
        add(dataEntryPanelWrapper);

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        add(tablePanel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1,5));

        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);
        bottomPanel.add(deleteButton);
        tablePanel.add(bottomPanel, BorderLayout.SOUTH);

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
        for (Food food : FoodController.getAll())
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
            checkBoxes[j].setMargin(new Insets(-2,0,-2,0));
            foodGroupPanel.add(checkBoxes[j]);
            j++;
        }
    }

}
