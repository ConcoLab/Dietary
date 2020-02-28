package views.panels;

import daoFactories.ContextFactory;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import models.Group;
import models.Meal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class ReportPanel extends JPanel {
    private JLabel caloriesLable;

    public ReportPanel(ObservableList<Meal> meals, ObservableList<Group> groups) {
        // Calculations for eaten and notEaten groups
        String calories = String.valueOf(meals.stream().mapToDouble(meal -> meal.getCalories()).sum());

        ArrayList<String> eatens = new ArrayList<String>();

        for (Meal meal: meals){
            ObservableList<Group> groupsInMeals = ContextFactory._FoodGroupDao().getGroupsOfOneFood(meal.getFoodId());
            for(Group group:groupsInMeals){
                if(!eatens.contains(group.getName()))
                    eatens.add(group.getName());
            }
        }

        DefaultTableModel eatenModel = new DefaultTableModel(
                new Object[][]{}, new Object[]{"Group Name"}
        );


        ArrayList<String> notEatens = new ArrayList<String>();

        for (Group group: groups){
            if(!eatens.contains(group.getName()))
                notEatens.add(group.getName());
        }

        for(String eaten:eatens){
            eatenModel.addRow(new Object[]{
                    eaten
            });
        }

        DefaultTableModel notEatenModel = new DefaultTableModel(
                new Object[][]{}, new Object[]{"Group Name"}
        );

        for(String notEaten:notEatens){
            notEatenModel.addRow(new Object[]{
                    notEaten
            });
        }

        // Components


        JLabel caloriesValueLabel = new JLabel(calories);
        JLabel caloriesLabel = new JLabel("Calories");
        JLabel reportsLabel = new JLabel("Report Panel");

        JTable eatenTable = new JTable(eatenModel);

        JTable notEatenTable = new JTable(notEatenModel);


        // Design
        setLayout(new BorderLayout(1,1));
        JPanel textReportPanel = new JPanel();
        textReportPanel.setLayout(new GridLayout(1,4));
        reportsLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        textReportPanel.add(reportsLabel);
        textReportPanel.add(caloriesLabel);
        textReportPanel.add(caloriesValueLabel);
        add(textReportPanel, BorderLayout.NORTH);




        JPanel eatenPanel = new JPanel();
        eatenPanel.setLayout(new BorderLayout());
        eatenTable.setFillsViewportHeight(true);
        JScrollPane eatenScrollPane = new JScrollPane(eatenTable);
        eatenPanel.add(new JLabel("Eaten"), BorderLayout.NORTH);
        eatenPanel.add(eatenScrollPane, BorderLayout.CENTER);

        JPanel notEatenPanel = new JPanel();
        notEatenPanel.setLayout(new BorderLayout());
        notEatenTable.setFillsViewportHeight(true);
        JScrollPane notEatenScrollPane = new JScrollPane(notEatenTable);
        notEatenPanel.add(new JLabel("Not Eaten Group"), BorderLayout.NORTH);
        notEatenPanel.add(notEatenScrollPane, BorderLayout.CENTER);

        JPanel eatNotEatPanel = new JPanel();
        eatNotEatPanel.setLayout(new GridLayout(1,2));
        eatNotEatPanel.add(eatenPanel);
        eatNotEatPanel.add(notEatenPanel);

        add(eatNotEatPanel);

        // Listeners

        meals.addListener((ListChangeListener.Change<? extends Meal> m) -> {
            caloriesValueLabel.setText(String.valueOf(meals.stream().mapToDouble(meal -> meal.getCalories()).sum()));


            eatens.clear();

            for (Meal meal: meals){
                ObservableList<Group> groupsInMeals = ContextFactory._FoodGroupDao().getGroupsOfOneFood(meal.getFoodId());
                for(Group group:groupsInMeals){
                    if(!eatens.contains(group.getName()))
                        eatens.add(group.getName());
                }
            }

            while(eatenModel.getRowCount() != 0){
                eatenModel.removeRow(0);
            }


            notEatens.clear();

            for (Group group: groups){
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

        });
    }
}
