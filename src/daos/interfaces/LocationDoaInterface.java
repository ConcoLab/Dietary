package daos.interfaces;

import javafx.collections.ObservableList;
import models.Location;
import models.Unit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface LocationDoaInterface {
    Location insert(Location location);
    ArrayList<Location> all() throws SQLException;
    int deleteAll();
    int delete(Location location);
    Location findById(long id);
    Location findByName(String name);
    Location findByAddress(String address);
}
