package views.panels;

import daoFactories.ContextFactory;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import models.Food;
import models.Group;
import models.Location;
import models.Meal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class EatenMealPanel extends JPanel {
    public JButton addButton;
    public JButton deleteButton;
    public JTable table;
    // Radio buttons for view change.
    public ButtonGroup viewButtons;
    public JRadioButton inDiningButton, outDiningButton, allButton;
    // Current view mode.
    public DiningType curDiningView;

    public EatenMealPanel(ObservableList<Meal> meals, ObservableList<Food> foods, String[] mealTypes, ObservableList<Location> locations, ObservableList<Group> groups){
        setLayout(new BorderLayout());
        curDiningView = DiningType.ALL;

        viewButtons = new ButtonGroup();
        // Create and add indining button
        inDiningButton = new JRadioButton();
        inDiningButton.setText("InDining");
        inDiningButton.setSelected(curDiningView == DiningType.INDINING);
        viewButtons.add(inDiningButton);
        // Create and add outdining button
        outDiningButton = new JRadioButton();
        outDiningButton.setText("OutDining");
        outDiningButton.setSelected(curDiningView == DiningType.OUTDINING);
        viewButtons.add(outDiningButton);
        // Create and add all button
        allButton = new JRadioButton();
        allButton.setText("All");
        allButton.setSelected(curDiningView == DiningType.ALL);
        viewButtons.add(allButton);

        //Table Model
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{}, new Object[]{
//                "ID",
                "Food Name",
                "Meal Type",
                "Number of Servings",
                "Calories",
                "Location",
                "Food Group",
                "Date"
        }
        );

        // TODO: Refactor to make redrawing the tables a separate, reusable function instead of copying the same code block five times.
        inDiningButton.addActionListener(e -> {
            curDiningView = DiningType.INDINING;
            while(model.getRowCount() != 0){
                model.removeRow(0);
            }
            for (Meal meal : meals) {
                if (shouldDrawFood(meal, curDiningView)) {
                    model.addRow(new Object[]{
//                        meal.getId(),
                            foods.stream().filter(food -> food.getId() == meal.getFoodId()).findFirst().get().getName(),
                            mealTypes[(int) meal.getMealTypeId()],
                            meal.getAmount(),
                            meal.getCalories(),
                            locations.stream().filter(location -> location.getId() == meal.getLocationId()).findFirst().get().getName(),
                            String.join(",", ContextFactory._FoodGroupDao().getGroupsOfOneFood(meal.getFoodId()).stream().map(group -> group.getName()).collect(Collectors.toList())),
                            meal.getDateTime().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))});
                }
            }
        });
        outDiningButton.addActionListener(e -> {
            curDiningView = DiningType.OUTDINING;
            while(model.getRowCount() != 0){
                model.removeRow(0);
            }
            for (Meal meal : meals) {
                if (shouldDrawFood(meal, curDiningView)) {
                    model.addRow(new Object[]{
//                        meal.getId(),
                            foods.stream().filter(food -> food.getId() == meal.getFoodId()).findFirst().get().getName(),
                            mealTypes[(int) meal.getMealTypeId()],
                            meal.getAmount(),
                            meal.getCalories(),
                            locations.stream().filter(location -> location.getId() == meal.getLocationId()).findFirst().get().getName(),
                            String.join(",", ContextFactory._FoodGroupDao().getGroupsOfOneFood(meal.getFoodId()).stream().map(group -> group.getName()).collect(Collectors.toList())),
                            meal.getDateTime().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))});
                }
            }
        });
        allButton.addActionListener(e -> {
            curDiningView = DiningType.ALL;
            while(model.getRowCount() != 0){
                model.removeRow(0);
            }
            for (Meal meal : meals) {
                if (shouldDrawFood(meal, curDiningView)) {
                    model.addRow(new Object[]{
//                        meal.getId(),
                            foods.stream().filter(food -> food.getId() == meal.getFoodId()).findFirst().get().getName(),
                            mealTypes[(int) meal.getMealTypeId()],
                            meal.getAmount(),
                            meal.getCalories(),
                            locations.stream().filter(location -> location.getId() == meal.getLocationId()).findFirst().get().getName(),
                            String.join(",", ContextFactory._FoodGroupDao().getGroupsOfOneFood(meal.getFoodId()).stream().map(group -> group.getName()).collect(Collectors.toList())),
                            meal.getDateTime().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))});
                }
            }
        });

        for (Meal meal: meals) {
            if (shouldDrawFood(meal, curDiningView)) {
                model.addRow(new Object[]{
//                    meal.getId(),
                        foods.stream().filter(food -> food.getId() == meal.getFoodId()).findFirst().get().getName(),
                        mealTypes[(int) meal.getMealTypeId()],
                        meal.getAmount(),
                        meal.getCalories(),
                        locations.stream().filter(location -> location.getId() == meal.getLocationId()).findFirst().get().getName(),
                        String.join(",", ContextFactory._FoodGroupDao().getGroupsOfOneFood(meal.getFoodId()).stream().map(group -> group.getName()).collect(Collectors.toList())),
                        meal.getDateTime().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))});
            }
        }

        // Components
        JTable table = new JTable(model);
        JLabel eatenLabel = new JLabel("List of Consumed Food");

        //Design
        setLayout(new BorderLayout(1,1));
        eatenLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        add(eatenLabel, BorderLayout.NORTH);
        // TEST: add radio buttons. Seems to hide whole eatenLabel?
        JPanel test = new JPanel();
        test.add(inDiningButton);
        test.add(outDiningButton);
        test.add(allButton);
        add(test, BorderLayout.NORTH);
        // END TEST
        add(scrollPane, BorderLayout.CENTER);
        deleteButton = new JButton("REMOVE FROM CONSUMED FOOD LIST");
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1)
                return;
            System.out.println("DEBUG: DELETING - " + meals.get(row).getId() + "  " + meals.get(row).getDateTime());
            ContextFactory._MealDao().delete(meals.get(row));
        });

        add(deleteButton, BorderLayout.SOUTH);



        // Listeners
        meals.addListener((ListChangeListener.Change<? extends Meal> m) -> {
            JOptionPane.showMessageDialog(this, "Meals Updated");
            while(model.getRowCount() != 0){
                model.removeRow(0);
            }
            for (Meal meal : meals) {
                if (shouldDrawFood(meal, curDiningView)) {
                    model.addRow(new Object[]{
//                        meal.getId(),
                            foods.stream().filter(food -> food.getId() == meal.getFoodId()).findFirst().get().getName(),
                            mealTypes[(int) meal.getMealTypeId()],
                            meal.getAmount(),
                            meal.getCalories(),
                            locations.stream().filter(location -> location.getId() == meal.getLocationId()).findFirst().get().getName(),
                            String.join(",", ContextFactory._FoodGroupDao().getGroupsOfOneFood(meal.getFoodId()).stream().map(group -> group.getName()).collect(Collectors.toList())),
                            meal.getDateTime().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))});
                }
            }
        });
    }

    // Type of dining we wish to view.
    enum DiningType {
        INDINING,
        OUTDINING,
        ALL
    };

    // Should we draw the food item?
    boolean shouldDrawFood(Meal inMeal, DiningType View) {
        final int INDINING_INDEX = 0; // Index of "Home" location
        if (inMeal.getLocationId() == INDINING_INDEX) {
            return View == DiningType.ALL || View == DiningType.INDINING;
        }
        else {
            return View == DiningType.ALL || View == DiningType.OUTDINING;
        }
    }
}
