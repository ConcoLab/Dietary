package observers;

import views.panels.EatenMealPanel;
import views.panels.LocationPanel;
import views.panels.MealPanel;

import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;

public class LocationObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        try {
            LocationPanel.locationUpdater();
            MealPanel.updateLocationCombobox();
            EatenMealPanel.updateMealsTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
