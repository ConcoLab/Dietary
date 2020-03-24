package observers;

import views.panels.FoodPanel;

import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;

public class FoodGroupObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        try {
            FoodPanel.updateFoodGroupCheckboxes();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
