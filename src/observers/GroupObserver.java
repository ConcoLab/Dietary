package observers;

import views.panels.GroupPanel;

import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;

public class GroupObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        try {
            GroupPanel.updateGroupModel();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
