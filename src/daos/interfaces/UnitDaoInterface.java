package daos.interfaces;

import javafx.collections.ObservableList;
import models.Unit;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UnitDaoInterface {
    Unit insert(Unit unit);
    ArrayList<Unit> all() throws SQLException;
    int deleteAll();
    int delete(Unit unit);
    Unit findById(long id);
    Unit findByName(String name);
}
