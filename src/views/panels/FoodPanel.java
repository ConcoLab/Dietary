package views.panels;

import controllers.FoodController;
import daoFactories.ContextFactory;
import models.Food;
import models.FoodGroup;
import models.Group;
import models.Unit;

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


    public FoodPanel(){
        setLayout(new GridLayout(0,1));
        ArrayList<Food> foods = ContextFactory.get_mysqlFoodDao().all();
        ArrayList<Unit> units = ContextFactory.get_mysqlUnitDao().all();
        ArrayList<Group> groups = ContextFactory.get_mysqlGroupDao().all();

        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{}, new Object[]{"ID", "NAME", "QUANTITY", "UNITS", "CALORIES", "GROUPS"});
        table = new JTable(model);
        for (Food food: foods)
            model.addRow(new Object[]{
                    food.getId(),
                    food.getName(),
                    food.getQuantity(),
                    units.stream().filter(unit -> unit.getId() == food.getUnit_id()).findFirst().get().getName(),
                    food.getCalories()});

        JPanel dataEntryPanel = new JPanel();
        dataEntryPanel.setLayout(new GridLayout(0, 1));
        dataEntryPanel.add(new JLabel("Food Name:"));
        JTextField foodNameTextField = new JTextField();
        dataEntryPanel.add(foodNameTextField);
        dataEntryPanel.add(new JLabel("Quantity:"));
        JTextField quantityTextField = new JTextField();
        dataEntryPanel.add(quantityTextField);
        dataEntryPanel.add(new JLabel("Unit:"));
        Vector unitVector = new Vector();
        for (Unit unit:units){
            unitVector.addElement(new Item(unit.getId(), unit.getName()));
        }
        JComboBox unitsComboBox = new JComboBox(unitVector);
        dataEntryPanel.add(unitsComboBox);
        dataEntryPanel.add(new JLabel("Calories:"));
        JTextField caloriesTextField = new JTextField();
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
        JButton insertButton = new JButton("Insert");
        insertButton.addActionListener(e -> {
            int a = unitsComboBox.getSelectedIndex();
            Food newFood = new Food(foodNameTextField.getText(), Long.parseLong(caloriesTextField.getText()), unitsComboBox.getSelectedIndex(), 25);
            for(JCheckBox checkBox:checkBoxes){
                if(checkBox.isSelected()){
                    ContextFactory.get_mysqlFoodGroupDao().insert(new FoodGroup(newFood.getId(), Long.parseLong(checkBox.getActionCommand())));
                }
            }

            model.addRow(new Object[]{
                    newFood.getId(),
                    newFood.getName(),
                    newFood.getQuantity(),
                    units.stream().filter(unit -> unit.getId() == newFood.getUnit_id()).findFirst().get().getName(),
                    newFood.getCalories()});
        });
        dataEntryPanel.add(insertButton);
        add(dataEntryPanel);



        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        add(scrollPane);


        JPanel bottomPanel = new JPanel();

        bottomPanel.setLayout(new BorderLayout());
        deleteButton = new JButton("DELETE");
        deleteButton.setBackground(Color.red);
        deleteButton.setForeground(Color.white);
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1)
                return;
            System.out.println("DEBUG: DELETING - " + foods.get(row).getId() + "  " + foods.get(row).getName());
            ContextFactory.get_mysqlFoodDao().delete(foods.get(row));
            model.removeRow(row);
        });
        bottomPanel.add(deleteButton, BorderLayout.NORTH);
//        bottomPanel.add(new JButton("FIND"));
//        JButton exitButton = new JButton("CLOSE");
//        bottomPanel.add(exitButton);

        add(bottomPanel);
    }

}
