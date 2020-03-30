package views.panels;

import controllers.FoodController;
import controllers.GroupController;
import controllers.LocationController;
import controllers.MealController;
import models.Food;
import models.Group;
import models.Location;
import models.Meal;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EatenMealPanel extends JPanel {
//    public JButton addButton;
    private static boolean showConsumedFoods = true;
    private static boolean showNotConsumedFoods = true;
    public JButton deleteButton;
    public JTable table;
    private static DefaultTableModel model;
    private static String[] mealTypesInPanel;
    private static JDatePicker filterDatePicker;
    private static JRadioButton indiningRadioButton = new JRadioButton("In-Dining");
    private static JRadioButton outdiningRadioButton = new JRadioButton("Out-Dining");
    private static JRadioButton allRadioButton = new JRadioButton("All");
    private JButton filterButton;
    private JPanel topPanel = new JPanel();
    private JPanel filterPanel = new JPanel();
    private static ButtonGroup diningRadioGroup;
    private static Panel buttonPanel = new Panel();
    private static JButton isConsumedButton = new JButton();
    private static JButton ConsumedFoodsButton = new JButton();
    private static JButton NotConsumedFoodsButton = new JButton();
    private static JSpinner fromMinuteTextField;
    private static JSpinner fromHourTextField;
    private static JSpinner toMinuteTextField;
    private static JSpinner toHourTextField;

    public EatenMealPanel(ArrayList<Food> foods, String[] mealTypes, ArrayList<Location> locations, ArrayList<Group> groups) throws SQLException {
        mealTypesInPanel = mealTypes;
        setLayout(new BorderLayout());

        //Table Model
        model = new DefaultTableModel(
                new Object[][]{}, new Object[]{
                "ID",
                "Food Name",
                "Meal Type",
                "Number of Servings",
                "Calories",
                "Fat",
                "Carbohydrate",
                "Salt",
                "Protein",
                "Location",
                "Food Group",
                "Date",
                "Consumed?"
        }
        );


        // Components
        JLabel fromTimeLabel = new JLabel("From:");
        JPanel fromTimePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        SpinnerModel fromHourModel = new SpinnerNumberModel(0, 0, 23, 1);
        fromHourTextField = new JSpinner(fromHourModel);
        SpinnerModel fromMinuteModek = new SpinnerNumberModel(0, 0, 59, 1);
        fromMinuteTextField = new JSpinner(fromMinuteModek);
        fromTimePanel.add(fromTimeLabel);
        fromTimePanel.add(fromHourTextField);
        fromTimePanel.add(new JLabel(":"));
        fromTimePanel.add(fromMinuteTextField);

        JLabel toTimeLabel = new JLabel("To:");
        JPanel toTimePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        SpinnerModel toHourModel = new SpinnerNumberModel(0, 0, 23, 1);
        toHourTextField = new JSpinner(toHourModel);
        toHourTextField.setValue(23);
        SpinnerModel toMinuteModel = new SpinnerNumberModel(0, 0, 59, 1);
        toMinuteTextField = new JSpinner(toMinuteModel);
        toMinuteTextField.setValue(59);
        toTimePanel.add(toTimeLabel);
        toTimePanel.add(toHourTextField);
        toTimePanel.add(new JLabel(":"));
        toTimePanel.add(toMinuteTextField);

        diningRadioGroup = new ButtonGroup();
        diningRadioGroup.add(indiningRadioButton);
        diningRadioGroup.add(outdiningRadioButton);
        diningRadioGroup.add(allRadioButton);


        JTable table = new JTable(model);
        JLabel eatenLabel = new JLabel("Diet Plan");
        filterButton = new JButton("Apply Filter");

        UtilDateModel model = new UtilDateModel();
        model.setDate(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue()-1, LocalDateTime.now().getDayOfMonth());
        model.setSelected(true);
        filterDatePicker = new JDatePicker(model);

        buttonPanel.setLayout(new FlowLayout());



        //Design


        TitledBorder panelBorder = BorderFactory.createTitledBorder("DIET PLAN");
        panelBorder.setTitleFont(new Font("Arial", Font.PLAIN, 20));
        setBorder(panelBorder);

        setLayout(new BorderLayout(1,1));
        eatenLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        Border filterBorder = BorderFactory.createTitledBorder("Filters");
        topPanel.setLayout(new BorderLayout(1,1));
        filterPanel.setBorder(filterBorder);
        filterPanel.setLayout(new FlowLayout());
        filterPanel.add(indiningRadioButton);
        filterPanel.add(outdiningRadioButton);
        allRadioButton.setSelected(true);
        filterPanel.add(allRadioButton);
        filterPanel.add(new JLabel("Date:"));
        filterPanel.add(filterDatePicker);
        filterPanel.add(fromTimePanel);
        filterPanel.add(toTimePanel);
        filterPanel.add(filterButton);

        filterButton.addActionListener(e -> {
            try {
                showConsumedFoods = true;
                showNotConsumedFoods = true;
                ConsumedFoodsButton.setText("HIDE CONSUMED");
                NotConsumedFoodsButton.setText("HIDE NOT CONSUMED");
                updateMealsTable();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        ConsumedFoodsButton.setText("HIDE CONSUMED");
        ConsumedFoodsButton.setBackground(Color.blue);
        ConsumedFoodsButton.setForeground(Color.WHITE);
        NotConsumedFoodsButton.setText("HIDE NOT CONSUMED");
        NotConsumedFoodsButton.setBackground(Color.orange);
//        filterPanel.add(NotConsumedFoodsButton);
        ConsumedFoodsButton.addActionListener(e -> {
            try {
                showConsumedFoods = !showConsumedFoods;
                ConsumedFoodsButton.setText(showConsumedFoods ? "HIDE CONSUMED" : "SHOW CONSUMED");
                updateMealsTable();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        NotConsumedFoodsButton.addActionListener(e -> {
            try {
                showNotConsumedFoods = !showNotConsumedFoods;
                NotConsumedFoodsButton.setText(showNotConsumedFoods ? "HIDE NOT CONSUMED" : "SHOW NOT CONSUMED");
                updateMealsTable();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });



        topPanel.add(filterPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        deleteButton = new JButton("REMOVE FOOD FROM THE DIET LIST");
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            Long id = Long.parseLong(table.getModel().getValueAt(row, 0).toString());
            MealController.delete(id);
        });

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                int row = table.getSelectedRow();
                if (row > -1) {
                    Long id = Long.parseLong(table.getModel().getValueAt(row, 0).toString());
                    try {
                        if (MealController.findById(id).getIsConsumed() == 1) {
                            isConsumedButton.setText("NOT CONSUMED");
                            isConsumedButton.setBackground(Color.magenta);
                        }
                        else {
                            isConsumedButton.setText("CONSUMED");
                            isConsumedButton.setBackground(Color.GREEN);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
                }
            }
        });

        isConsumedButton = new JButton("CONSUMED");
        isConsumedButton.setBackground(Color.GREEN);
        isConsumedButton.addActionListener(e -> {
            boolean isConsumed = false;
            int row = table.getSelectedRow();
            if (row > -1) {
                Long id = Long.parseLong(table.getModel().getValueAt(row, 0).toString());
                try {
                    if (MealController.findById(id).getIsConsumed() == 1)
                        isConsumed = false;
                    else
                        isConsumed = true;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                MealController.makeFoodIsConsumed(id, isConsumed);
            }
        });

        buttonPanel.add(deleteButton);
        buttonPanel.add(isConsumedButton);
        buttonPanel.add(ConsumedFoodsButton);
        buttonPanel.add(NotConsumedFoodsButton);
        add(buttonPanel, BorderLayout.SOUTH);



        // Data input
        updateMealsTable();

    }

    public static void updateMealsTable() throws SQLException {
        LocalDateTime startDate = LocalDateTime.of(LocalDateTime.now().getYear(),
                                                    LocalDateTime.now().getMonth(),
                                                    LocalDateTime.now().getDayOfMonth(), 0, 00);
        LocalDateTime endDate = LocalDateTime.of(LocalDateTime.now().getYear(),
                                                    LocalDateTime.now().getMonth(),
                                                    LocalDateTime.now().getDayOfMonth(), 23, 59);

        if(filterDatePicker.getModel().getValue() != null){
            int y, m, d, M, h;
            y = filterDatePicker.getModel().getYear();
            m = filterDatePicker.getModel().getMonth() + 1; // JDatePicker returns 0-11 for months
            d = filterDatePicker.getModel().getDay();
            startDate = LocalDateTime.of(y, m, d, (int)fromHourTextField.getValue(), (int) fromMinuteTextField.getValue());
            endDate = LocalDateTime.of(y, m, d, (int)toHourTextField.getValue(), (int) toMinuteTextField.getValue());
        }

        if (showNotConsumedFoods){

        }else{

        }

        ArrayList<Meal> meals = new ArrayList<>();
        if(indiningRadioButton.isSelected()){
            meals = MealController.getInDiningAllMeals(startDate, endDate, showConsumedFoods, showNotConsumedFoods);
        }else if(outdiningRadioButton.isSelected()){
            meals = MealController.getOutDinigAllMeals(startDate, endDate, showConsumedFoods, showNotConsumedFoods);
        }else if(allRadioButton.isSelected()){
            meals = MealController.getAllMeals(startDate, endDate, showConsumedFoods, showNotConsumedFoods);
        }



        while(model.getRowCount() != 0){
            model.removeRow(0);
        }

        if(meals.size() != 0){
            for (Meal meal : meals) {
                try {
                    model.addRow(new Object[]{
                            meal.getId(),
                            FoodController.getFoodById(meal.getFoodId()).getName(),
                            mealTypesInPanel[(int) meal.getMealTypeId()],
                            meal.getAmount(),
                            meal.getCalories(),
                            meal.getFat(),
                            meal.getCarbohydrate(),
                            meal.getSalt(),
                            meal.getProtein(),
                            LocationController.getLocationById(meal.getLocationId()).getName(),
                            GroupController.getGroupNames(FoodController.getFoodById(meal.getFoodId()).getGroups()),
                            meal.getDateTime().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")),
                            meal.getIsConsumed() == 1 ? "Yes" : "No"});
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }


        // updates the report panel on change or new query
        ReportPanel.updateTables(meals);



    }
}
