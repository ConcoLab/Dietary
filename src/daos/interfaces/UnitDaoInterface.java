package daos.interfaces;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import models.Unit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observer;

public interface UnitDaoInterface {
    Unit insert(Unit unit) throws SQLException;
    ArrayList<Unit> all() throws SQLException;
    int deleteAll();
    int delete(long id);
    Unit findById(long id) throws SQLException;
    Unit findByName(String name);
}
