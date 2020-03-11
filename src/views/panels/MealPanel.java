package views.panels;

import daoFactories.Context;
import daoFactories.ContextFactory;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import models.*;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Vector;

public class MealPanel extends JPanel{
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


    public MealPanel(ArrayList<Food> foods, ArrayList<Unit> units, ArrayList<Group> groups, ArrayList<Location> locations, String[] mealTypes){

        // Initialize needed data to fill the tables and other fields
        Vector mealTypeVector = new Vector();
        for (String mealType:mealTypes){
            mealTypeVector.addElement(new Item(mealType.indexOf(mealType), mealType));
        }

        Vector foodVector = new Vector();
        for (Food food:foods){
            foodVector.addElement(new Item(food.getId(), food.getName() + " (" + food.getQuantity() + " " + units.stream().filter(u->u.getId() == food.getUnitId()).findFirst().get().getName() + ")"));
        }

        Vector locationVector = new Vector();
        for (Location location:locations) {
            locationVector.addElement(new Item(location.getId(), location.getName()));
        }



        //Components


        JLabel mealTypeLabel = new JLabel("Meal Type:");
        JComboBox mealtypeCombobox = new JComboBox(mealTypeVector);

        JLabel foodNameLabel = new JLabel("Food Name:");
        JComboBox foodsCombox = new JComboBox(foodVector);


        JLabel locationLabel = new JLabel("Location:");
        JComboBox locationComboBox = new JComboBox(locationVector);

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

        JButton insertButton = new JButton("Add to Consumed Food List");
        insertButton.addActionListener(e -> {
            //validate the input for the "Number of Serving" field.
            String numOfServ=amountTextField.getText();
            float numOfServing;
            if(numOfServ.length()==0 || numOfServ.equals("0") || numOfServ.substring(0,1).equals("-")){
                JOptionPane.showMessageDialog(this,"Please input a positive decimal fraction in the \"Number of Servings\" field!");
                return;
            }
            try{
                numOfServing=Float.parseFloat(numOfServ);
            }
            catch (NumberFormatException event){
                System.out.println("Please input a positive decimal fraction in the \"Number of Servings\" field!");
                return;
            }

            //validate the input for the "Date" field.
            if(datetimeTextField.getFormattedTextField().getText().equals("")){
                JOptionPane.showMessageDialog(this,"Please input the date in the \"Date\" field!");
                return;
            }

            long foodCalorie = ContextFactory._FoodDao().findById(foodsCombox.getSelectedIndex()).getCalories();
//            long foodQuantity = ContextFactory._FoodDao().findById(foodsCombox.getSelectedIndex()).getQuantity();
//            long calPerQuantity = foodCalorie/foodQuantity;

            int y, m, d, M, h;
            y = datetimeTextField.getModel().getYear();
            m = datetimeTextField.getModel().getMonth() + 1; // JDatePicker returns 0-11 for months
            d = datetimeTextField.getModel().getDay();
            M = (int) minuteTextField.getValue();
            h = (int) hourTextField.getValue();;

            Meal meal = new Meal(null, foodsCombox.getSelectedIndex(),
                    mealtypeCombobox.getSelectedIndex(),
                    locationComboBox.getSelectedIndex(),
                    Long.parseLong(amountTextField.getText()),
                    foodCalorie*Long.parseLong(amountTextField.getText()),
                    LocalDateTime.of(y, m, d, h, M));
            ContextFactory._MealDao().insert(meal);

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
        Context.foods.addListener((ListChangeListener.Change<? extends Food> f) -> {
            JOptionPane.showMessageDialog(this, "Food Updated");
            foodVector.clear();
            for (Food food:foods){
                foodVector.addElement(new Item(food.getId(), food.getName() + " (" + food.getQuantity() + " " + units.stream().filter(u->u.getId() == food.getUnitId()).findFirst().get().getName() + ")"));
            }
            foodsCombox.updateUI();
        });

        Context.locations.addListener((ListChangeListener.Change<? extends Location> l) -> {
            locationVector.clear();
            for (Location location:locations) {
                locationVector.addElement(new Item(location.getId(), location.getName()));
            }
            locationComboBox.updateUI();
        });

    }

}
