package views.panels;

import daoFactories.ContextFactory;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import models.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.stream.Collectors;

public class EatenMealPanel extends JPanel {
    public JButton addButton;
    public JButton deleteButton;
    public JTable table;

    public EatenMealPanel(ObservableList<Meal> meals, ObservableList<Food> foods, String[] mealTypes, ObservableList<Location> locations, ObservableList<Group> groups){
        setLayout(new BorderLayout());

        //Table Model
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{}, new Object[]{
//                "ID",
                "Food Name",
                "Meal Type",
                "Amount",
                "Calories",
                "Location",
                "Food Group",
                "Date"
        }
        );

        for (Meal meal: meals)
            model.addRow(new Object[]{
//                    meal.getId(),
                    foods.stream().filter(food -> food.getId() == meal.getFoodId()).findFirst().get().getName(),
                    mealTypes[(int) meal.getFoodId()],
                    meal.getAmount(),
                    meal.getCalories(),
                    locations.stream().filter(location -> location.getId() == meal.getLocationId()).findFirst().get().getName(),
                    String.join(",", ContextFactory._FoodGroupDao().getGroupsOfOneFood(meal.getFoodId()).stream().map(group -> group.getName()).collect(Collectors.toList())),
                    meal.getDateTime()});

        // Components
        JTable table = new JTable(model);
        JLabel eatenLabel = new JLabel("List of Consumed Food");

        //Design
        setLayout(new BorderLayout(1,1));
        eatenLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        add(eatenLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Listeners
        meals.addListener((ListChangeListener.Change<? extends Meal> m) -> {
            JOptionPane.showMessageDialog(this, "Meals Updated");
            while(model.getRowCount() != 0){
                model.removeRow(0);
            }
            for (Meal meal : meals)
                model.addRow(new Object[]{
//                        meal.getId(),
                        foods.stream().filter(food -> food.getId() == meal.getFoodId()).findFirst().get().getName(),
                        mealTypes[(int) meal.getFoodId()],
                        meal.getAmount(),
                        meal.getCalories(),
                        locations.stream().filter(location -> location.getId() == meal.getLocationId()).findFirst().get().getName(),
                        String.join(",", ContextFactory._FoodGroupDao().getGroupsOfOneFood(meal.getFoodId()).stream().map(group -> group.getName()).collect(Collectors.toList())),
                        meal.getDateTime()});
        });



    }
}
