package observers;

import views.panels.EatenMealPanel;
import views.panels.FoodPanel;
import views.panels.GroupPanel;
import views.panels.ReportPanel;

import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;

public class GroupObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        try {
            GroupPanel.updateGroupModel();
            FoodPanel.updateFoodGroupCheckboxes();
            EatenMealPanel.updateMealsTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
