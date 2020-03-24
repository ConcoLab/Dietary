package views.panels;

import controllers.FoodController;
import controllers.GroupController;
import controllers.LocationController;
import controllers.MealController;
import daoFactories.Context;
import daoFactories.ContextFactory;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import models.Food;
import models.Group;
import models.Location;
import models.Meal;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.stream.Collectors;

public class EatenMealPanel extends JPanel {
//    public JButton addButton;
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

    public EatenMealPanel(ArrayList<Food> foods, String[] mealTypes, ArrayList<Location> locations, ArrayList<Group> groups) throws SQLException {
        mealTypesInPanel = mealTypes;
        setLayout(new BorderLayout());

        //Table Model
        model = new DefaultTableModel(
                new Object[][]{}, new Object[]{
//                "ID",
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
                "Date"
        }
        );


        // Components
        diningRadioGroup = new ButtonGroup();
        diningRadioGroup.add(indiningRadioButton);
        diningRadioGroup.add(outdiningRadioButton);
        diningRadioGroup.add(allRadioButton);

        JTable table = new JTable(model);
        JLabel eatenLabel = new JLabel("List of Consumed Food");
        filterButton = new JButton("Apply Filter");

        UtilDateModel model = new UtilDateModel();
        model.setDate(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue()-1, LocalDateTime.now().getDayOfMonth());
        model.setSelected(true);
        filterDatePicker = new JDatePicker(model);

        //Design


        TitledBorder panelBorder = BorderFactory.createTitledBorder("LIST OF CONSUMED FOOD");
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
        filterPanel.add(filterButton);
        filterButton.addActionListener(e -> {
            try {
                updateMealsTable();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });



        topPanel.add(filterPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        deleteButton = new JButton("REMOVE FROM CONSUMED FOOD LIST");
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            Long id = Long.parseLong(table.getModel().getValueAt(row, 0).toString());
        });

        add(deleteButton, BorderLayout.SOUTH);



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
            startDate = LocalDateTime.of(y, m, d, 0, 00);
            endDate = LocalDateTime.of(y, m, d, 23, 59);
        }

        ArrayList<Meal> meals = new ArrayList<>();
        if(indiningRadioButton.isSelected()){
            meals = MealController.getInDiningAllMeals(startDate, endDate);
        }else if(outdiningRadioButton.isSelected()){
            meals = MealController.getOutDinigAllMeals(startDate, endDate);
        }else if(allRadioButton.isSelected()){
            meals = MealController.getAllMeals(startDate, endDate);
        }



        while(model.getRowCount() != 0){
            model.removeRow(0);
        }

        if(meals.size() != 0){
            for (Meal meal : meals) {
                try {
                    model.addRow(new Object[]{
    //                        meal.getId(),
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
                            meal.getDateTime().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))});
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }


        // updates the report panel on change or new query
        ReportPanel.updateTables(meals);



    }
}
