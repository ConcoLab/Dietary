package views.panels;

import com.sun.rowset.internal.Row;
import controllers.GroupController;
import controllers.MealController;
import daoFactories.Context;
import daoFactories.ContextFactory;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import models.Group;
import models.Meal;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class ReportPanel extends JPanel {
    private JTable  foodContentTable;
    private ArrayList<Meal> meals = new ArrayList<>();
    private static JTable eatenTable;
    private static JTable notEatenTable;
    private static DefaultTableModel eatenModel = new DefaultTableModel(
            new Object[][]{}, new Object[]{"Group Name"}
    );
    private static DefaultTableModel notEatenModel = new DefaultTableModel(
            new Object[][]{}, new Object[]{"Group Name"}
    );
    private static DefaultTableModel stuffModel = new DefaultTableModel(
            new Object[][]{}, new Object[]{"Food Properties", "Total Intake Amount"}
    );

    public ReportPanel() throws SQLException {




        // Components
        foodContentTable = new JTable(stuffModel);

        eatenTable = new JTable(eatenModel);

        notEatenTable = new JTable(notEatenModel);


        // Design
        TitledBorder panelBorder = BorderFactory.createTitledBorder("REPORT PANEL");
        panelBorder.setTitleFont(new Font("Arial", Font.PLAIN, 20));
        setBorder(panelBorder);


        setLayout(new BorderLayout(1,1));
        JPanel contentPanel = new JPanel(new BorderLayout());
        TitledBorder contentPanelBorder = BorderFactory.createTitledBorder("Calories and Ingredients");
        contentPanel.setBorder(contentPanelBorder);
        foodContentTable.setFillsViewportHeight(true);
        JScrollPane textReportPane = new JScrollPane(foodContentTable);
        contentPanel.add(textReportPane);
        add(contentPanel, BorderLayout.WEST);


        JPanel eatenPanel = new JPanel();
        TitledBorder eatenPanelBorder = BorderFactory.createTitledBorder("Eaten food");
        eatenPanel.setBorder(eatenPanelBorder);

        eatenPanel.setLayout(new BorderLayout());
        eatenTable.setFillsViewportHeight(true);
        JScrollPane eatenScrollPane = new JScrollPane(eatenTable);
        eatenPanel.add(eatenScrollPane, BorderLayout.EAST);

        JPanel notEatenPanel = new JPanel();
        TitledBorder notEatenPanelBorder = BorderFactory.createTitledBorder("Not Eaten food");
        notEatenPanel.setBorder(notEatenPanelBorder);
        notEatenPanel.setLayout(new BorderLayout());
        notEatenTable.setFillsViewportHeight(true);
        JScrollPane notEatenScrollPane = new JScrollPane(notEatenTable);
        notEatenPanel.add(notEatenScrollPane, BorderLayout.CENTER);

        JPanel eatNotEatPanel = new JPanel();
        eatNotEatPanel.setLayout(new GridLayout(1,2));
        eatNotEatPanel.add(eatenPanel);
        eatNotEatPanel.add(notEatenPanel);
        add(eatNotEatPanel);
    }

    public static void updateTables(ArrayList<Meal> meals) throws SQLException {
        ArrayList<String> eatens = new ArrayList<String>();
        ArrayList<String> notEatens = new ArrayList<String>();

        double calories = meals.stream().mapToDouble(meal -> meal.getCalories()).sum();
        double fat = meals.stream().mapToDouble(meal -> meal.getFat()).sum();
        double salt = meals.stream().mapToDouble(meal -> meal.getSalt()).sum();
        double protein = meals.stream().mapToDouble(meal -> meal.getProtein()).sum();
        double carbohydrate = meals.stream().mapToDouble(meal -> meal.getCarbohydrate()).sum();
        while(stuffModel.getRowCount() != 0){
            stuffModel.removeRow(0);
        }
        stuffModel.addRow(new Object[]{"Calories", calories});
        stuffModel.addRow(new Object[]{"Fat", fat});
        stuffModel.addRow(new Object[]{"Salt", salt});
        stuffModel.addRow(new Object[]{"Protein", protein});
        stuffModel.addRow(new Object[]{"Carbohydrate", carbohydrate});


        for (Meal meal: meals){
            ArrayList<Group> groupsInMeals = null;
            try {
                groupsInMeals = ContextFactory._FoodGroupDao().getGroupsOfOneFood(meal.getFoodId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            for(Group group:groupsInMeals){
                if(!eatens.contains(group.getName()))
                    eatens.add(group.getName());
            }
        }

        while(eatenModel.getRowCount() != 0){
            eatenModel.removeRow(0);
        }


        notEatens.clear();

        for (Group group: GroupController.getAll()){
            if(!eatens.contains(group.getName()))
                notEatens.add(group.getName());
        }


        for(String eaten:eatens){
            eatenModel.addRow(new Object[]{
                    eaten
            });
        }

        while(notEatenModel.getRowCount() != 0){
            notEatenModel.removeRow(0);
        }

        for(String notEaten:notEatens){
            notEatenModel.addRow(new Object[]{
                    notEaten
            });
        }
    }
}
