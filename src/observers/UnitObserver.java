package observers;

import views.panels.EatenMealPanel;
import views.panels.FoodPanel;
import views.panels.UnitPanel;

import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;

public class UnitObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        try {
            FoodPanel.updateUnitsCombobox();
            UnitPanel.updateModel();
            EatenMealPanel.updateMealsTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
