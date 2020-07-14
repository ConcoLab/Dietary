package observers;

import views.panels.EatenMealPanel;

import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;

public class MealObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        try {
            EatenMealPanel.updateMealsTable();
//            System.out.println("-- DEBUG: Meals are updated: " + arg);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
