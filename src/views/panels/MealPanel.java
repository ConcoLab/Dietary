package views.panels;

import controllers.FoodController;
import controllers.LocationController;
import controllers.MealController;
import controllers.UnitController;
import models.Food;
import models.Location;
import models.Meal;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Vector;

public class MealPanel extends JPanel{
    private static Vector mealTypeVector;
    private static Vector foodVector;
    private static Vector locationVector;
    private static JComboBox mealtypeCombobox;
    private static JComboBox foodsCombox;
    private static JComboBox locationComboBox;

    static class Item
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


    public MealPanel(ArrayList<Food> foods, ArrayList<Location> locations, String[] mealTypes) throws SQLException {

        // Initialize needed data to fill the tables and other fields
        mealTypeVector = new Vector();
        for (String mealType:mealTypes){
            mealTypeVector.addElement(new Item(mealType.indexOf(mealType), mealType));
        }

        foodVector = new Vector();
        for (Food food:foods){
//            foodVector.addElement(new Item(food.getId(), food.getName() + " (" + food.getQuantity() + " " + units.stream().filter(u->u.getId() == food.getUnitId()).findFirst().get().getName() + ")"));
        }

        locationVector = new Vector();
        for (Location location:locations) {
            locationVector.addElement(new Item(location.getId(), location.getName()));
        }



        //Components
        JLabel mealTypeLabel = new JLabel("Meal Type:");
        mealtypeCombobox = new JComboBox(mealTypeVector);

        JLabel foodNameLabel = new JLabel("Food Name:");
        foodsCombox = new JComboBox(foodVector);


        JLabel locationLabel = new JLabel("Location:");
        locationComboBox = new JComboBox(locationVector);

        JLabel amountLabel = new JLabel("Number of Servings:");
        JTextField amountTextField = new JTextField();

        JLabel dateLabel = new JLabel("Date:");
        JDatePicker datetimeTextField = new JDatePicker();

        JLabel timeLabel = new JLabel("Time");
        JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        SpinnerModel hourModel = new SpinnerNumberModel(0, 0, 23, 1);
        JSpinner hourTextField = new JSpinner(hourModel);
        SpinnerModel minuteModel = new SpinnerNumberModel(0, 0, 59, 1);
        JSpinner minuteTextField = new JSpinner(minuteModel);
        timePanel.add(hourTextField);
        timePanel.add(new JLabel(" : "));
        timePanel.add(minuteTextField);

        JButton insertButton = new JButton("Add to Diet Plan");
        insertButton.addActionListener(e -> {
            //validate the input for the "Number of Serving" field.
            String numOfServ=amountTextField.getText();
            float numOfServing;
            if(numOfServ.length()==0 || numOfServ.equals("0") || numOfServ.substring(0,1).equals("-")){
                JOptionPane.showMessageDialog(this,"Please input a positive integer in the \"Number of Servings\" field!");
                return;
            }
            try{
                numOfServing=Long.parseLong(numOfServ);
            }
            catch (NumberFormatException event){
                JOptionPane.showMessageDialog(this,"Please input a positive integer in the \"Number of Servings\" field!");
                return;
            }

            //validate the input for the "Date" field.
            if(datetimeTextField.getFormattedTextField().getText().equals("")){
                JOptionPane.showMessageDialog(this,"Please input the date in the \"Date\" field!");
                return;
            }

            long foodCalorie = 0;
            long foodFat = 0;
            long foodCarbohydrate = 0;
            long foodSalt = 0;
            long foodProtein = 0;

            Item foodItem = (Item) foodsCombox.getSelectedItem();
            try {
                System.out.println("--DEBUG: Food Index: " + foodItem.getId());
                foodCalorie = FoodController.getFoodById(foodItem.getId()).getCalories();
                foodFat = FoodController.getFoodById(foodItem.getId()).getFat();
                foodCarbohydrate = FoodController.getFoodById(foodItem.getId()).getCarbohydrate();
                foodSalt = FoodController.getFoodById(foodItem.getId()).getSalt();
                foodProtein = FoodController.getFoodById(foodItem.getId()).getProtein();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            int y, m, d, M, h;
            y = datetimeTextField.getModel().getYear();
            m = datetimeTextField.getModel().getMonth() + 1; // JDatePicker returns 0-11 for months
            d = datetimeTextField.getModel().getDay();
            M = (int) minuteTextField.getValue();
            h = (int) hourTextField.getValue();;

            Item locationItem = (Item) locationComboBox.getSelectedItem();
            Meal meal = new Meal(0, foodItem.getId(),
                    mealtypeCombobox.getSelectedIndex(),
                    locationItem.getId(),
                    Long.parseLong(amountTextField.getText()),
                    foodCalorie*Long.parseLong(amountTextField.getText()),
                    foodFat*Long.parseLong(amountTextField.getText()),
                    foodCarbohydrate*Long.parseLong(amountTextField.getText()),
                    foodSalt*Long.parseLong(amountTextField.getText()),
                    foodProtein*Long.parseLong(amountTextField.getText()),
                    0,
                    LocalDateTime.of(y, m, d, h, M));

            MealController.create(meal);

            //Clear contents in all fields
            amountTextField.setText("");

        });


        // Design
        setLayout(new BorderLayout(3,1));
        JPanel dataEntryPanel = new JPanel();
        dataEntryPanel.add(mealTypeLabel);
        dataEntryPanel.add(mealtypeCombobox);
        dataEntryPanel.setLayout(new GridLayout(0, 1));
        dataEntryPanel.add(foodNameLabel);
        dataEntryPanel.add(foodsCombox);
        dataEntryPanel.add(locationLabel);
        dataEntryPanel.add(locationComboBox);
        dataEntryPanel.add(amountLabel);
        dataEntryPanel.add(amountTextField);
        dataEntryPanel.add(dateLabel);
        dataEntryPanel.add(datetimeTextField);
        dataEntryPanel.add(timeLabel);
        dataEntryPanel.add(timePanel);
        dataEntryPanel.add(insertButton);
        add(dataEntryPanel, BorderLayout.NORTH);

        // Listeners
        updateFoodCombobox();
        updateLocationCombobox();
    }

    public static void updateFoodCombobox() throws SQLException {
//        JOptionPane.showMessageDialog(this, "Food Updated");
            foodVector.clear();
            for (Food food: FoodController.getAll()){
                System.out.println("-- DEBUG: foodId = " + food.getId());
                foodVector.addElement(new Item(food.getId(), food.getName() + " (" + food.getQuantity() + " " + UnitController.getById(food.getUnitId()).getName() + ")"));
            }
            foodsCombox.updateUI();
    }

    public static void updateLocationCombobox() throws SQLException {
        locationVector.clear();
        for (Location location: LocationController.getAllLocations()) {
            locationVector.addElement(new Item(location.getId(), location.getName()));
        }
        locationComboBox.updateUI();
    }



}
