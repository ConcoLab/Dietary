package observers;

import views.panels.FoodPanel;
import views.panels.MealPanel;

import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;

public class FoodObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        try {
            FoodPanel.updateFoodsTable();
            MealPanel.updateFoodCombobox();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
